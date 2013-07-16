package codemate;

/**
 * LibraryMate
 * 
 * This class create an interface for describing library specifications.
 * 
 * @author Li Dong <dongli@lasg.iap.ac.cn>
 */

public interface LibraryMate {
	/**
	 * getLibraryName
	 * 
	 * This method returns the library name.
	 * 
	 * @return 	String
	 */
	public String getLibraryName();
	
	/**
	 * isDepended
	 * 
	 * This method checks if this library has been depended.
	 * 
	 * @param	language		The language interface
	 * @param 	headerOrModule	The header or module that is used in code
	 * @return	String
	 */
	public boolean isDepended(String language, String headerOrModule);
	
	/**
	 * getCompileOptions
	 * 
	 * This method returns the options for compiling codes.
	 * 
	 * @param 	language		The language interface
	 * @return	String
	 */
	public String getCompileOptions(String language);
}
