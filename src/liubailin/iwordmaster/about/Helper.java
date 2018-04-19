package liubailin.iwordmaster.about;
import liubailin.iwordmaster.until.HighLight;

public class Helper {
	
	/**
	 * 
	 * @return ������Ϣ
	 */
	public static String getHelp(){
		String help = HighLight.red("_--------------Help---------------\n")
					+ "ʹ��: s [OPTION]... [word] \n"
					+ " --en ��Ӣ��\n"
					+ " --am ����Ӣ\n"
					+ " -l --local ��ʾ���شʿ�\n"
					+ " -j --jushi ��ʾ����\n"
					+ " -h --help ��ʾ����\n"
					+ " -a --about ��ʾ����\n"
					+HighLight.red("---------------------------------\n");
		return help;
	}

	/**
	 * 
	 * @return ������Ϣ
	 */
	public static String getAbout() {
		String about = HighLight.red("-----------about---------------\n")
					+ "author:" + HighLight.green("liubailin") + "\n"
					+ "email: " + HighLight.green("liubailinprivate@163.com") + "\n"
					+ "--------------------------------";
		return about;
	}
}
