package liubailin.iwordmaster.about;

public class Helper {
	
	/**
	 * 
	 * @return ������Ϣ
	 */
	public static String getHelp(){
		String help = "--------------Help---------------\n"
					+ "ʹ��: s [OPTION]... [word] \n"
					+ "-en ��Ӣ��\n"
					+ "-am ����Ӣ\n"
					+ "-js ��ʾ����\n"
					+ "--help ��ʾ����\n"
					+ "--about ��ʾ����"
					+ "blabla ...\n"
					+ "---------------------------------";
		return help;
	}

	/**
	 * 
	 * @return ������Ϣ
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
