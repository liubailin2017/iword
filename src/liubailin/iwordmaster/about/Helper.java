package liubailin.iwordmaster.about;

public class Helper {
	
	/**
	 * 
	 * @return 帮助信息
	 */
	public static String getHelp(){
		String help = "--------------Help---------------\n"
					+ "使用: s [OPTION]... [word] \n"
					+ "-en 发英音\n"
					+ "-am 发美英\n"
					+ "-js 显示例句\n"
					+ "--help 显示帮助\n"
					+ "--about 显示关于"
					+ "blabla ...\n"
					+ "---------------------------------";
		return help;
	}

	/**
	 * 
	 * @return 关于信息
	 */
	public static String getAbout() {
		String about = "-----------about---------------\n"
					+ "author:liubailin \n"
					+ "email: liubailinprivate@163.com \n"
					+ "blabla...\n"
					+ "--------------------------------";
		return about;
	}
}
