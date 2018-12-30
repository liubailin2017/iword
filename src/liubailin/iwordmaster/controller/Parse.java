package liubailin.iwordmaster.controller;

import java.util.ArrayList;
import java.util.List;

import liubailin.iwordmaster.about.Helper;
import liubailin.iwordmaster.resource.Resource;
import liubailin.iwordmaster.until.HighLight;
import liubailin.iwordmaster.until.JuShi;
import liubailin.iwordmaster.until.LocalSimple;
import liubailin.iwordmaster.until.SimpleTmp;
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
	
	List<String> cmdsLong = new ArrayList<>();
	
	List<String> cmdsShort = new ArrayList<>();
	List<String> word = new ArrayList<>();
	
	/**
	 * 	解析命令	
	 * 
	 * 	以- 短命令, -- 开头的认为是 长命令
	 * 	其它开头的认为是 word
	 * @param args 参数 字符串数组
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
		 * ---------isCmd 
		 * 0表时值
		 * 1表示短命令
		 * 2表示长命令
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
				action.addMsg("zerror", action.getMsg().get("zerror")+ ( "---------错误的命令-----------\n"));
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
	 * 
	 */
	private String getWord() {
		String w ="";
		
		for(int i = 0; i < word.size(); i++ ) {
			if(i == 0) {
				w += word.get(i);
			}else {
				w += (" "+word.get(i));
			}
		}
		return w;
	}
	/**
	 * 添加基本释意action 
	 */
	public void addSymbols(){
		//System.out.println("cmd:"+cmd+ "word"+word);
//		if(word.size() > 1) 
//			System.out.println(HighLight.red("一次只能查一个单词"));
		String w =getWord();
		 
		
		Symbols symbols = resource.getSymbols(w);
		
		/**
		 * 基本释意
		 */
		String res ="";
		/**
		 * symbols 不知道为什么是个list 这个要问金山为什么要这样。
		 */
	
	if(symbols == null){
		if(!w.equals("") && w != null)
			action.addMsg("msg", HighLight.red("没有网络结果"));
	}
	else
		for(Symbols.SybolsItem item : symbols.getSymbols()){
		res += (HighLight.blue("美:/")+item.getPh_am()+"/ \n"+ HighLight.blue("英:/" )+item.getPh_en()+"/\n");
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
		String w =getWord(); 
		List<JuShi> jushis = resource.getJushi(w);
		
	
		String res = "例句：\n";
		if(jushis != null)
		for(JuShi item : jushis){
			res += (item.getEnglish()+"\n");
			res += (item.getChinese()+"\n");
		}
		action.addMsg("hjushi", res);
	}
	
	public void addLocalSimple() {
		//System.out.println("cmd:"+cmd+ "word"+word);
	 
		String w =getWord(); 
		LocalSimple localSimple = resource.getLocalSimple(w);
		String res = "本地词典：\n";	
		if(localSimple != null) {
			res += (localSimple.getPronL()+":"+localSimple.getPronV()+"\n");
		
			for(LocalSimple.Sens item : localSimple.getSensList()){
					res += (item.getPos()+"\n"+item.getDefSenD()+"\n");
			}
		}else {
			action.addMsg("warn", "本地词库中没有这单词");
		}
		action.addMsg("alocal", res);
	}
	
	/**
	 *  运行
	 */
	public void start() {
		String w ="";
		if(word.size() > 0) w = word.get(0);
		Symbols symbols = null;
		
		/**
		 * 把短命令添加到对应的长命令
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
			case "l":
				cmdsLong.add("local");
				break;
			default :
				action.addMsg("zerror", action.getMsg().get("zerror")+ ("未知参数："+ cmd +"\n"));
			}
		}
		Boolean isHidden = false;
		for(String cmd : cmdsLong){
			switch(cmd) {
			case "local":
				addLocalSimple();
				action.show();
				action.clearMsg();
				break;
			case "en" :
				symbols = resource.getSymbols(w);
				if(symbols != null && symbols.getSymbols().get(0).getPh_en_mp3() != null &&!symbols.getSymbols().get(0).getPh_en_mp3().equals(""))
						action.playMp3(symbols.getSymbols().get(0).getPh_en_mp3());
				else {
					action.addMsg("warn", "---------没有英音-------");
				}
				break;
			case "am" :
				symbols = resource.getSymbols(w);
				if(symbols != null && symbols.getSymbols().get(0).getPh_am_mp3() != null &&!symbols.getSymbols().get(0).getPh_am_mp3().equals(""))
					action.playMp3(symbols.getSymbols().get(0).getPh_am_mp3());
				else {
					action.addMsg("warn", "---------没有美音-------");
				}
				break;
			case "jushi": //例句
				addJushi();
				break;
			case "help" :
				action.addMsg("bhelp", Helper.getHelp());
				break;
			case "about":
				action.addMsg("aabout", Helper.getAbout());
				break;
			case "force": /*设为强制高亮，但这个参数要放在第一个 如: s --force hello -j --am*/
				HighLight.FORCE =true;
				break;
			case "hidden":
				action.removeAll();
				action.addMsg("hidden", "******************");
				isHidden = true;
				break;
			default :
				action.addMsg("zerror", action.getMsg().get("zerror")+ ("未知参数："+ cmd +"\n"));
			}
		}
		if(!isHidden)	addSymbols();
		
		action.show();
		
	}

}
