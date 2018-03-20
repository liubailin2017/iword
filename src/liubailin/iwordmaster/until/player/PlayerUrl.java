package liubailin.iwordmaster.until.player;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
/**
 * 用来播放mp3
 * @author liubailin
 *
 */
public class PlayerUrl implements Runnable {

	private URL url = null;
	/**
	 * 传一个URL
	 * @param url
	 */
	public PlayerUrl(URL url) {
		this.url = url;
	}

	@Override
	public void run() {
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.connect();
			if(con.getResponseCode() != 200) {
				System.out.println("PlayerUrl say: 话音播放失败");
			}else {
				Player player = new Player(con.getInputStream());
				player.play();
			}
		} catch (IOException | JavaLayerException e) {
			System.out.println("Exception of PlayerUrl say: 话音播放失败(可能是没有联接网络，请检查你的网络连接)");
		}
		
	}
	
	/**
	 * 简单测试一下
	 * @param args
	 */
	public static void main(String[] args){
	
		 
			try {
				URL url =new URL( "http://res.iciba.com/resource/amp3/1/0/5d/41/5d41402abc4b2a76b9719d911017c592.mp3");
				new Thread(new PlayerUrl(url)).start();;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
	}
}
