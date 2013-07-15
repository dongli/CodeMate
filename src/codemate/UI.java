package codemate;

/**
 * UI
 * 
 * This class provides several methods for printing out message.
 * 
 * @author Li Dong <dongli@lasg.iap.ac.cn>
 */

public class UI {
	public static void notice(String caller, String message) {
		System.out.println("[Notice]: "+caller+": "+message);
	}
	
	public static void warning(String caller, String message) {
		System.out.println("[Warning]: "+caller+": "+message);
	}
	
	public static void error(String caller, String message) {
		System.out.println("[Error]: "+caller+": "+message);
		System.exit(1);
	}
}
