package codemate.ui;

/**
 * Config
 * 
 * This class loads configuration from file and provides configuration data to
 * other classes
 * 
 * @author Li Dong <dongli@lasg.iap.ac.cn>
 */

import codemate.compilermate.*;
import codemate.librarymate.*;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.Map.Entry;

public class Config {
	private static String defaultRoot = System.getenv("HOME")+"/.codemate";
	private static String defaultConfig = defaultRoot+"/config";
	
	// ConfigData and ConfigDataDeserializer are used to read JSON configuration
	// from file.
	private static class BlockData {
		public Map<String, String> data =
				new HashMap<String, String>();
	}
	
	private static class SectionData {
		public Map<String, BlockData> data =
				new HashMap<String, BlockData>();
	}
	
	private static class ConfigData {
		public Map<String, SectionData> data =
				new HashMap<String, SectionData>();
	}
	
	private static class ConfigDataSerializer
		implements JsonSerializer<ConfigData> {

		@Override
		public JsonElement serialize(ConfigData configData, Type arg1,
				JsonSerializationContext arg2) {
			JsonObject json = new JsonObject();
			for (Entry<String, SectionData> section :
				configData.data.entrySet()) {
				JsonObject sectionJson = new JsonObject();
				for (Entry<String, BlockData> block :
					section.getValue().data.entrySet()) {
					JsonObject blockJson = new JsonObject();
					for (Entry<String, String> data :
						block.getValue().data.entrySet()) {
						blockJson.addProperty(data.getKey(), data.getValue());
					}
					sectionJson.add(block.getKey(), blockJson);
				}
				json.add(section.getKey(), sectionJson);
			}
			return json;
		}
		
	}
	
	private static class ConfigDataDeserializer
		implements JsonDeserializer<ConfigData> {

		@Override
		public ConfigData deserialize(JsonElement json, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			ConfigData configData = new ConfigData();
			for (Entry<String, JsonElement> section :
				json.getAsJsonObject().entrySet()) {
				String sectionName = section.getKey();
				SectionData sectionData = new SectionData();
				for (Entry<String, JsonElement> block :
					section.getValue().getAsJsonObject().entrySet()) {
					String blockName = block.getKey();
					BlockData blockData = new BlockData();
					for (Entry<String, JsonElement> data :
						block.getValue().getAsJsonObject().entrySet()) {
						blockData.data.put(data.getKey(),
								data.getValue().getAsString());
					}
					sectionData.data.put(blockName, blockData);
				}
				configData.data.put(sectionName, sectionData);
			}
			return configData;
		}
		
	}
	
	public static ConfigData configData = new ConfigData();
	
	/**
	 * load
	 * 
	 * This method loads the default configuration.
	 * 
	 * @author Li Dong <dongli@lasg.iap.ac.cn>
	 */
	public static void load() {
		load(defaultConfig);
	}
	
	/**
	 * load
	 * 
	 * This method loads the given configuration.
	 * 
	 * @param fileName
	 * 
	 * @author Li Dong <dongli@lasg.iap.ac.cn>
	 */
	public static void load(String fileName) {
		if (fileName.equals(defaultConfig)) {
			File root = new File(defaultRoot);
			if (!root.exists()) {
				UI.notice("Config",
						"There is no runtime directory as"+defaultRoot+".");
				boolean succ = (new File(defaultRoot)).mkdir();
				if (succ)
					UI.notice("Config", "Create one.");
				else
					UI.error("Config", "Couldn't create one!");
			}
		}
		File file = new File(fileName);
		if (!file.exists()) {
			UI.notice("Config", "Create a configuration as "+fileName+".");
			createTemplateConfig(fileName);
		} else {
			UI.notice("Config", "Load configuration from "+fileName+".");
			try {
				GsonBuilder gsonBuilder = new GsonBuilder();
				gsonBuilder.registerTypeAdapter(ConfigData.class,
						new ConfigDataDeserializer());
				Gson gson = gsonBuilder.create();
				String json = new Scanner(file).useDelimiter("\\Z").next();
				configData = gson.fromJson(json, ConfigData.class);
			} catch (Exception e) {
				e.printStackTrace();
				UI.error("Config",
						"Encounter error while loading configuation from "+
						fileName+"!");
			}
		}
		// spread configuration to other classes
		for (Entry<String, BlockData> libraryData :
			configData.data.get("library").data.entrySet()) {
			LibraryMate libraryMate =
					LibraryMates.searchLibrary(libraryData.getKey());
			if (libraryMate != null)
				libraryMate.setRoot(libraryData.getValue().data.get("root"));
		}
	}
	
	/**
	 * createTemplateConfig
	 * 
	 * This method creates a template of configuration and asks user for
	 * necessary information.
	 * 
	 * @param fileName
	 * 
	 * @author Li Dong <dongli@lasg.iap.ac.cn>
	 */
	private static void createTemplateConfig(String fileName) {
		PrintWriter writer = null;
		try {
			 writer = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			UI.error("Config",
					"Encounter error while creating configuration as "+
					fileName+"!");
		}
		UI.notice("Config", "Create a configuration as "+fileName+".");
		// compiler section
		SectionData compilerSection = new SectionData();
		BlockData fortranBlock = new BlockData();
		UI.notice("Config", "Choose a Fortran compiler:");
		String vendor = UI.getAnswer(CompilerMates.getVendorNames())[0];
		fortranBlock.data.put("vendor", vendor);
		compilerSection.data.put("Fortran", fortranBlock);
		configData.data.put("compiler", compilerSection);
		// library section
		SectionData librarySection = new SectionData();
		for (String libraryName : LibraryMates.getLibraryNames()) {
			UI.notice("Config", "Set library root for "+libraryName+":");
			String root = UI.getAnswer(null)[0];
			BlockData libraryBlock = new BlockData();
			libraryBlock.data.put("root", root);
			librarySection.data.put(libraryName, libraryBlock);
		}
		configData.data.put("library", librarySection);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(ConfigData.class,
				new ConfigDataSerializer());
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		writer.println(gson.toJson(configData));
		writer.flush();
	}
	
	public static String getDefaultCompilerVendor(String language) {
		String vendor = null;
		vendor = configData.
				data.get("compiler").
				data.get("Fortran").
				data.get("vendor");
		return vendor;
	}
}
