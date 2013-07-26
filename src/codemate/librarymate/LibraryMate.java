package codemate.librarymate;

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
	 * provideCompilerWrapper
	 * 
	 * This method manifests whether the library provides a compiler wrapper or
	 * not. If yes, then no include option and library option are needed.
	 * 
	 * @return boolean
	 */
	public boolean provideCompilerWrapper();
	
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
	
	/**
	 * setWrapper
	 * 
	 * This method set the compiler wrapper of given language.
	 * 
	 * @param  language
	 * @param  wrapper
	 */
	public void setWrapper(String language, String wrapper);
	
	/**
	 * getWrapper
	 * 
	 * This method gets the compiler wrapper of given language.
	 * 
	 * @param  language
	 * @return String
	 */
	public String getWrapper(String language);
}
