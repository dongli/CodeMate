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
	 * getIncludeOptions
	 * 
	 * This method returns the "-I..." option for compiling codes.
	 * 
	 * @param 	language		The language interface
	 * @return	String
	 */
	public String getIncludeOptions();
	
	/**
	 * getLibraryOptions
	 * 
	 * This method returns the "-L... -l..." options for compiling codes.
	 * 
	 * @param 	language		The language interface
	 * @return	String
	 */
	public String getLibraryOptions(String language);
	
	/**
	 * setRoot
	 * 
	 * This method sets the root of the library.
	 * 
	 * @param	root			The library root
	 */
	public void setRoot(String root);
	
	/**
	 * getRoot
	 * 
	 * This method gets the root of the library.
	 * 
	 * @return	String
	 */
	public String getRoot();
}
