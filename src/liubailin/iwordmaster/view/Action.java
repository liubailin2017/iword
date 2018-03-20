package liubailin.iwordmaster.view;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import liubailin.iwordmaster.until.player.PlayerUrl;

/**
 * 动作模块， 如 显示意思，播放语音等
 * @author liubailin
 *
 */
public class Action {
	/**
	 * 要显示的信息
	 */
	Map<String,String> msg = new HashMap<>();

	public Map<String, String> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, String> msg) {
		this.msg = msg;
	}
	/**
	 * 添加一物条消息
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
	 * @return 删除项的值
	 */
	public String removeMsg(String key){
			return msg.remove(key);
	}
	
	public void clearMsg(){
		msg.clear();
	}
	
	public void playMp3(String urlStr){
		
		if(urlStr == null) {
			this.msg.put("mp3msg", "----没有语音----");
		}
		try {
			URL url = new URL(urlStr);
			PlayerUrl playerUrl = new PlayerUrl(url);
			/**
			 * 后台播放
			 */
			new Thread(playerUrl).start();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	/** 
	 * 显示
	 */
	public void show() {
		for(String m : msg.keySet()) {
			System.out.println(msg.get(m));
		}
	}
	
	
	
	/**
	 * 简单作个测试
	 * @param args
	 */
	public static void main(String [] args) {
		Action active = new Action();
		active.addMsg("1", "第一条消息");
		active.addMsg("2", "第二条消息");
		active.show();
		active.playMp3("http://res.iciba.com/resource/amp3/1/0/5d/41/5d41402abc4b2a76b9719d911017c592.mp3");
	}
}
