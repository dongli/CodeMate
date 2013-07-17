package codemate;

/**
 * CompilerMate
 * 
 * This class creates an interface for describing compiler which will hide the
 * differences among compilers.
 * 
 * @author Li Dong <dongli@lasg.iap.ac.cn>
 */

public interface CompilerMate {
	/**
	 * getVendorName
	 * 
	 * This method gets the name of the compiler vendor.
	 * 
	 * @return
	 */
	public String getVendorName();
	
	/**
	 * getCommandName
	 * 
	 * This method gets the command name of the compiler for the given language.
	 * 
	 * @param language
	 * @return
	 */
	public String getCommandName(String language);
	
	/**
	 * getDebugOptions
	 * 
	 * This method gets the compile options for debugging.
	 * 
	 * @return	String
	 */
	
	public String getDebugOptions();
	
	/**
	 * getReleaseOptions
	 * 
	 * This method get the compile options with some optimization.
	 * 
	 * @return	String
	 */
	
	public String getReleaseOptions();
	
	/**
	 * isAvailable
	 * 
	 * This method checks if the compiler is available in the computer.
	 * 
	 * @return
	 */
	public boolean isAvailable();
}
