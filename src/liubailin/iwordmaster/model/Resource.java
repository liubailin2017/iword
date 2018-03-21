package liubailin.iwordmaster.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import liubailin.iwordmaster.until.JuShi;
import liubailin.iwordmaster.until.Symbols;

/** 
 * 这个可以优化，以后有时间做
 * 主要用来获取网络资源
 * 
 */
public class Resource {
	
	private String urlStr = "http://www.iciba.com/index.php?a=getWordMean&c=search&list=1%2C12%2C13%2C3005&word=";
	
	/**
	 * 
	 * @param word 请求单词
	 * @return 
	 * 成功： 返回对应单词的json
	 * 失败： 返回null
	 */
	public String getJson(String word) {
		
		String json = null;
		URL url;
		
		try {
			url = new URL(urlStr+ word);
	
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.connect();
				if(conn.getResponseCode() == 200){
					InputStream in = conn.getInputStream();
					InputStreamReader read = new InputStreamReader(in);
					BufferedReader buf = new BufferedReader(read);
					
					String stra = buf.readLine();
					StringBuilder sb = new StringBuilder();;
					while(stra != null){
						sb.append(stra);
						stra = buf.readLine();
					}
					json = sb.toString();
				
				}else {
					json = null;
				}
				
			} catch (MalformedURLException e) {
				json = null;
			} catch (IOException e) {
				json = null;
			}
		
			return json;

	}
	
	/**
	 * 获得基本释意
	 * 
	 * @param word 请求的单词
	 * @return 
	 * 成功返回 Symbols 对像
	 * 失败返回 null
	 */
	
	public Symbols getSymbols(String word){
		Symbols symbols = null;
		List<Map<Object,Object>> list = null;
		String json = this.getJson(word);
		/* 如果是null就直接返回了*/
		if(word == null || word.equals("")) return null;
		
		JSONObject obj = JSON.parseObject(json);
		obj = obj.getJSONObject("baesInfo");
		JSONArray objArray = obj.getJSONArray("symbols");
		//System.out.println(objArray.toJSONString());
		symbols = (Symbols) JSON.parseObject("{\"symbols\":" + objArray.toJSONString()+"}",Symbols.class);
		//System.out.println(symbols);
		return symbols;
		
	}
	
	/**
	 * 
	 * @param word
	 * @return
	 */
	public List<JuShi> getJushi(String word) {
		JuShi symbols = null;
		List<JuShi> list = null;
		String json = this.getJson(word);
		/* 如果是null就直接返回了*/
		if(word == null) return null;
		
		JSONObject obj = JSON.parseObject(json);
		JSONArray arr = obj.getJSONArray("jushi");
		if(arr == null) 
			return null;
		else
			return arr.toJavaList(JuShi.class);
		//System.out.println(arr.toJavaList(JuShi.class));
	
	}
	
	
	/**
	 * 作个简单测试
	 * @param args
	 */
	public static void main(String args[]) {
		Resource res = new Resource();
		res.getSymbols("hello");
		res.getJushi("hello");
	}
}
