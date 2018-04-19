package liubailin.iwordmaster.about;
import liubailin.iwordmaster.until.HighLight;

public class Helper {
	
	/**
	 * 
	 * @return 帮助信息
	 */
	public static String getHelp(){
		String help = HighLight.red("_--------------Help---------------\n")
					+ "使用: s [OPTION]... [word] \n"
					+ " --en 发英音\n"
					+ " --am 发美英\n"
					+ " -l --local 显示本地词库\n"
					+ " -j --jushi 显示例句\n"
					+ " -h --help 显示帮助\n"
					+ " -a --about 显示关于\n"
					+HighLight.red("---------------------------------\n");
		return help;
	}

	/**
	 * 
	 * @return 关于信息
	 */
	public static String getAbout() {
		String about = HighLight.red("-----------about---------------\n")
					+ "author:" + HighLight.green("liubailin") + "\n"
					+ "email: " + HighLight.green("liubailinprivate@163.com") + "\n"
					+ "--------------------------------";
		return about;
	}
}
