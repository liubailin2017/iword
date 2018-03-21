package liubailin.iwordmaster.presenter;

import java.util.ArrayList;
import java.util.List;

import liubailin.iwordmaster.about.Helper;
import liubailin.iwordmaster.model.Resource;
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
	
	List<String> cmds = new ArrayList<>();
	List<String> word = new ArrayList<>();
	
	/**
	 * 	
	 * 	��-��ͷ����Ϊ�� ����
	 * 	������ͷ����Ϊ�� word
	 * @param args ���� �ַ�������
	 */
	public Parse(String [] args){
		for(String s :args) {
			if(s.charAt(0) == '-'){
				cmds.add(s);
			} else {
				word.add(s);
			}
		}
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
	if(symbols != null)
		for(Symbols.SybolsItem item : symbols.getSymbols()){
		res += ("��:/"+item.getPh_am()+"/ \t Ӣ:/" +item.getPh_en()+"/\n");
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
		
		addSymbols();
		/**
		 * ��չ��
		 */

		for(String cmd : cmds){
			switch(cmd) {
			case "-en" :
				if(symbols != null)
					action.playMp3(symbols.getSymbols().get(0).getPh_en_mp3());
				break;
			case "-am" :
				if(symbols != null)
					action.playMp3(symbols.getSymbols().get(0).getPh_am_mp3());
				break;
			case "-js": //����
				addJushi();
				break;
			case "-help" :
			case "--help" :
				action.addMsg("bhelp", Helper.getHelp());
				break;
			case "-about":
			case "--about":
				action.addMsg("aabout", Helper.getAbout());
				break;
			default :
				action.addMsg("zother", action.getMsg().get("other")+ ("δ֪������"+ cmd +"\n"));
			}
		}
		
		action.show();
		
	}
	
	public static void main(String args[]) {
		Parse p = new Parse(args);
		p.start();
	}
	
}
