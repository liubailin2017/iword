package liubailin.iwordmaster.view;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import liubailin.iwordmaster.until.player.PlayerUrl;

/**
 * ����ģ�飬 �� ��ʾ��˼������������
 * @author liubailin
 *
 */
public class Action {
	/**
	 * Ҫ��ʾ����Ϣ
	 */
	Map<String,String> msg = new HashMap<>();

	public Map<String, String> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, String> msg) {
		this.msg = msg;
	}
	/**
	 * ���һ������Ϣ
	 * @param key
	 * @param value
	 * @return value
	 */
	public String addMsg(String key ,String value){
		return msg.put(key, value);
	}
	/**
	 * 
	 * @param key
	 * @return ɾ�����ֵ
	 */
	public String removeMsg(String key){
			return msg.remove(key);
	}
	
	public void clearMsg(){
		msg.clear();
	}
	
	public void playMp3(String urlStr){
		
		if(urlStr == null) {
			this.msg.put("mp3msg", "----û������----");
		}
		try {
			URL url = new URL(urlStr);
			PlayerUrl playerUrl = new PlayerUrl(url);
			/**
			 * ��̨����
			 */
			new Thread(playerUrl).start();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	/** 
	 * ��ʾ
	 */
	public void show() {
		for(String m : msg.keySet()) {
			System.out.println(msg.get(m));
		}
	}
	
	
	
	/**
	 * ����������
	 * @param args
	 */
	public static void main(String [] args) {
		Action active = new Action();
		active.addMsg("1", "��һ����Ϣ");
		active.addMsg("2", "�ڶ�����Ϣ");
		active.show();
		active.playMp3("http://res.iciba.com/resource/amp3/1/0/5d/41/5d41402abc4b2a76b9719d911017c592.mp3");
	}
}
