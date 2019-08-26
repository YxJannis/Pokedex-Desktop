package infovis.debug;

/**
 * @author patrick.riehmann(at)medien.uni-weimar.de
 * 
 * <p>Helper class to write debug messages.</p>
 * 
 */
public class Debug {
	public static void print(String string){
		System.out.print(string);
	}
	public static void p(String string) {
		println(string);
	}
	public static void println(String string) {
		System.out.println(string);
	}
}
