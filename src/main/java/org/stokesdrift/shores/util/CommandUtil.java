package org.stokesdrift.shores.util;

/**
 * Handle output messages to console
 * 
 * @author driedtoast
 *
 */
public class CommandUtil {
	
	
	 public static void outWarn(String message) {
		
	 }
	
	 public static void outCritical(String message) {
			
	 }
	 
	 public static void outInfo(String message) {
		System.out.println("\033[0m " + message);	
	 }
//	 System.out.println("\033[0m BLACK");
//     System.out.println("\033[31m RED");
//     System.out.println("\033[32m GREEN");
//     System.out.println("\033[33m YELLOW");
//     System.out.println("\033[34m BLUE");
//     System.out.println("\033[35m MAGENTA");
//     System.out.println("\033[36m CYAN");
//     System.out.println("\033[37m WHITE");

}
