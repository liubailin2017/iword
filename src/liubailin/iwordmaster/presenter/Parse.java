package liubailin.iwordmaster.presenter;

import java.util.ArrayList;
import java.util.List;

import liubailin.iwordmaster.about.Helper;
import liubailin.iwordmaster.model.Resource;
import liubailin.iwordmaster.until.HighLight;
import liubailin.iwordmaster.until.JuShi;
import liubailin.iwordmaster.until.Symbols;
import liubailin.iwordmaster.view.Action;

/**
 * ��������
 * @author liubailin
 *
 */
public class Parse {
	
	private Action action = new Action();
	private Resource resource = new Resource();
	
	List<String> cmdsLong = new ArrayList<>();
	
	List<String> cmdsShort = new ArrayList<>();
	List<String> word = new ArrayList<>();
	
	/**
	 * 	��������	
	 * 
	 * 	��- ������, -- ��ͷ����Ϊ�� ������
	 * 	������ͷ����Ϊ�� word
	 * @param args ���� �ַ�������
	 */
	public Parse(String [] args){
		
		List<String> tmps = new ArrayList<>();
		for(String s: args) {
			if(null == s ) continue;
			for(int i = 0; i < s.length(); i++){
				if(s.charAt(i) == '-') 
					tmps.add("-");
				else{
					tmps.add(s.substring(i));
					break;
				}
			}
		}
		/**
		 * ---------inCmd 
		 * 0��ʱֵ
		 * 1��ʾ������
		 * 2��ʾ������
		 */
		int  isCmd = 0;
		
		for(String s : tmps) {
			
			if(isCmd == 0 && s.equals("-")) {
				isCmd = 1;
			}else if(isCmd == 0 && !s.equals("-")){
				 word.add(s);
				 isCmd = 0;
			}else if(isCmd == 1 && s.equals("-")){
				isCmd = 2;
			}else if(isCmd == 1 && !s.equals("-")) {
				cmdsShort.add(s);
				isCmd = 0;
			}else if(isCmd == 2 && !s.equals("-")){
				cmdsLong.add(s);
				isCmd = 0;
			}else{
				word.clear();
				cmdsLong.clear();
				cmdsShort.clear();
				action.addMsg("zerror", action.getMsg().get("zerror")+ ( "---------���������-----------\n"));
			}
			
			
		}
//	
//		System.out.println("tmp:"+tmps);
// 		System.out.println("word:"+word);
// 		System.out.println("cmdsLong:"+cmdsLong);
// 		System.out.println("short:"+cmdsShort);

		
//		for(String s :args) {
//			if(s.charAt(0) == '-'){
//				cmds.add(s);
//			} else {
//				word.add(s);
//			}
//		}
	}
	
	/**
	 * ��ӻ�������action 
	 */
	public void addSymbols(){
		//System.out.println("cmd:"+cmd+ "word"+word);
		if(word.size() > 1) System.out.println("һ��ֻ�ܲ�һ������");
		String w ="";
		if(word.size() > 0) w = word.get(0);
		Symbols symbols = resource.getSymbols(w);
		
		/**
		 * ��������
		 */
		String res = "";
		/**
		 * symbols ��֪��Ϊʲô�Ǹ�list ���Ҫ�ʽ�ɽΪʲôҪ������
		 */
	if(symbols == null){
		if(!w.equals("") && w != null)
			action.addMsg("msg", "û�н��");
	}
	else
		for(Symbols.SybolsItem item : symbols.getSymbols()){
		res += (HighLight.blue("��:/")+item.getPh_am()+"/ \n"+ HighLight.blue("Ӣ:/" )+item.getPh_en()+"/\n");
		/** 
		 * �����Ҫ�����ݴʣ����ʰ�֮���list
		 */
			for(Symbols.SybolsItem.PartItem part : item.getParts()) {
		
			res+= (part.getPart()+" \n");
			/**
			 * �����Եģ���ͬ����
			 */
				for(String mean: part.getMeans()){
					res += (mean +"\n");
				}
			}
		}
		action.addMsg("gsymbols", res);
	}
	
	/**
	 * ��������action
	 */
	public void addJushi() {
		//System.out.println("cmd:"+cmd+ "word"+word);
		if(word.size() > 1) System.out.println("һ��ֻ�ܲ�һ������");
		String w ="";
		if(word.size() > 0) w = word.get(0);
		List<JuShi> jushis = resource.getJushi(w);
		
	
		String res = "���䣺\n";
	 if(jushis != null)
		for(JuShi item : jushis){
			res += (item.getEnglish()+"\n");
			res += (item.getChinese()+"\n");
		}
		action.addMsg("hjushi", res);
	}
	
	/**
	 *  ����
	 */
	public void start() {
		String w ="";
		if(word.size() > 0) w = word.get(0);
		Symbols symbols = resource.getSymbols(w);
		
		/**
		 * �Ѷ�������ӵ���Ӧ�ĳ�����
		 */
		for(String cmd : cmdsShort){
			switch(cmd) {
			case "j":
				cmdsLong.add("jushi");
				break;
			case "h":
				cmdsLong.add("help");
				break;
			case "a":
				cmdsLong.add("about");
				break;
			default :
				action.addMsg("zerror", action.getMsg().get("zerror")+ ("δ֪������"+ cmd +"\n"));
			}
		}
		
		for(String cmd : cmdsLong){
			switch(cmd) {
			case "en" :
				if(symbols != null && symbols.getSymbols().get(0).getPh_en_mp3() != null &&!symbols.getSymbols().get(0).getPh_en_mp3().equals(""))
						action.playMp3(symbols.getSymbols().get(0).getPh_en_mp3());
				else {
					action.addMsg("warn", "---------û��Ӣ��-------");
				}
				break;
			case "am" :
				if(symbols != null && symbols.getSymbols().get(0).getPh_am_mp3() != null &&!symbols.getSymbols().get(0).getPh_am_mp3().equals(""))
					action.playMp3(symbols.getSymbols().get(0).getPh_am_mp3());
				else {
					action.addMsg("warn", "---------û������-------");
				}
				break;
			case "jushi": //����
				addJushi();
				break;
			case "help" :
				action.addMsg("bhelp", Helper.getHelp());
				break;
			case "about":
				action.addMsg("aabout", Helper.getAbout());
				break;
			case "force": /*��Ϊǿ�Ƹ��������������Ҫ���ڵ�һ�� ��: s --force hello -j --am*/
				HighLight.FORCE =true;
				break;
			default :
				action.addMsg("zerror", action.getMsg().get("zerror")+ ("δ֪������"+ cmd +"\n"));
			}
		}
		
		addSymbols();
		
		action.show();
		
	}
	/**
	 * ������Ҫ���
	 * @param args
	 */
	public static void main(String args[]) {
		Parse p = new Parse(args);
		p.start();
	
	}
	
}
