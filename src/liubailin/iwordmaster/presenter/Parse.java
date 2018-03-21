package liubailin.iwordmaster.presenter;

import java.util.ArrayList;
import java.util.List;

import liubailin.iwordmaster.about.Helper;
import liubailin.iwordmaster.model.Resource;
import liubailin.iwordmaster.until.JuShi;
import liubailin.iwordmaster.until.Symbols;
import liubailin.iwordmaster.view.Action;

/**
 * 分析命令
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
	 * 	以-开头的认为是 命令
	 * 	其它开头的认为是 word
	 * @param args 参数 字符串数组
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
	 * 添加基本释意action 
	 */
	public void addSymbols(){
		//System.out.println("cmd:"+cmd+ "word"+word);
		if(word.size() > 1) System.out.println("一次只能查一个单词");
		String w ="";
		if(word.size() > 0) w = word.get(0);
		Symbols symbols = resource.getSymbols(w);
		
		/**
		 * 基本释意
		 */
		String res = "";
		/**
		 * symbols 不知道为什么是个list 这个要问金山为什么要这样。
		 */
	if(symbols != null)
		for(Symbols.SybolsItem item : symbols.getSymbols()){
		res += ("美:/"+item.getPh_am()+"/ \t 英:/" +item.getPh_en()+"/\n");
		/** 
		 * 这个主要是形容词，名词啊之类的list
		 */
			for(Symbols.SybolsItem.PartItem part : item.getParts()) {
		
			res+= (part.getPart()+" \n");
			/**
			 * 各词性的，不同解释
			 */
				for(String mean: part.getMeans()){
					res += (mean +"\n");
				}
			}
		}
		action.addMsg("gsymbols", res);
	}
	
	/**
	 * 添加例句进action
	 */
	public void addJushi() {
		//System.out.println("cmd:"+cmd+ "word"+word);
		if(word.size() > 1) System.out.println("一次只能查一个单词");
		String w ="";
		if(word.size() > 0) w = word.get(0);
		List<JuShi> jushis = resource.getJushi(w);
		
	
		String res = "例句：\n";
	 if(jushis != null)
		for(JuShi item : jushis){
			res += (item.getEnglish()+"\n");
			res += (item.getChinese()+"\n");
		}
		action.addMsg("hjushi", res);
	}
	
	/**
	 *  运行
	 */
	public void start() {
		String w ="";
		if(word.size() > 0) w = word.get(0);
		Symbols symbols = resource.getSymbols(w);
		
		addSymbols();
		/**
		 * 扩展项
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
			case "-js": //例句
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
				action.addMsg("zother", action.getMsg().get("other")+ ("未知参数："+ cmd +"\n"));
			}
		}
		
		action.show();
		
	}
	
	public static void main(String args[]) {
		Parse p = new Parse(args);
		p.start();
	}
	
}
