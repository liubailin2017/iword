package liubailin.iwordmaster.until;

public class  HighLight {
	public static boolean FORCE = false;
	/**
	 * 判断命令行是否支持高亮 
	 * @return true 支持，false 不支持
	 */
	public static boolean isSupport() {
//		return false ;
		if(System.getProperties().getProperty("os.name").toLowerCase().startsWith("lin") || FORCE)
			return true;
		else 
			return false;
	}
	
	public static String red(String str) {
		if(isSupport()) {
			str = "\033[31m" + str +" \033[0m";
		}
		return str;
	}
	
	public static String blue(String str) {
		if(isSupport()) {
			str = "\033[34m" + str +" \033[0m";
		}
		return str;
	}
	
	public static String yellow(String str) {
		if(isSupport()) {
			str = "\033[33m" + str +" \033[0m";
		}
		return str;
	}
	
	public static String green(String str) {
		if(isSupport()) {
			str = "\033[32m" + str +" \033[0m";
		}
		return str;
	}
	
}
