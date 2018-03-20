package liubailin.iwordmaster.presenter;

import java.util.ArrayList;
import java.util.List;

import liubailin.iwordmaster.model.Resource;
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
	 * @param args
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
	 *  运行
	 */
	public void start() {
		//System.out.println("cmd:"+cmd+ "word"+word);
		if(word.size() > 1) System.out.println("一次只能查一个单词");
		String w = word.get(0);
		Symbols symbols = resource.getSymbols(w);
		
		/**
		 * 基本释意
		 */
		String res = "";
		/**
		 * symbols 不知道为什么是个list 这个要问金山为什么要这样。
		 */
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
		action.addMsg("symbols", res);
		
		/**
		 * 扩展项
		 */
		
		for(String cmd : cmds){
			switch(cmd) {
			case "-en" :
				action.playMp3(symbols.getSymbols().get(0).getPh_en_mp3());
				break;
			case "-am" :
				action.playMp3(symbols.getSymbols().get(0).getPh_am_mp3());
				break;
			default :
				action.addMsg("other", action.getMsg().get("other")+ ("未知参数："+ cmd +"\n"));
			}
		}
		
		action.show();
		
	}
	
	public static void main(String args[]) {
		Parse p = new Parse(args);
		p.start();
	}
	
}
