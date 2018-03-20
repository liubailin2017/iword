package liubailin.iwordmaster.presenter;

import java.util.ArrayList;
import java.util.List;

import liubailin.iwordmaster.model.Resource;
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
	 *  ����
	 */
	public void start() {
		//System.out.println("cmd:"+cmd+ "word"+word);
		if(word.size() > 1) System.out.println("һ��ֻ�ܲ�һ������");
		String w = word.get(0);
		Symbols symbols = resource.getSymbols(w);
		
		/**
		 * ��������
		 */
		String res = "";
		/**
		 * symbols ��֪��Ϊʲô�Ǹ�list ���Ҫ�ʽ�ɽΪʲôҪ������
		 */
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
		action.addMsg("symbols", res);
		
		/**
		 * ��չ��
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
				action.addMsg("other", action.getMsg().get("other")+ ("δ֪������"+ cmd +"\n"));
			}
		}
		
		action.show();
		
	}
	
	public static void main(String args[]) {
		Parse p = new Parse(args);
		p.start();
	}
	
}
