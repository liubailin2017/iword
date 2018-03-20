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

import liubailin.iwordmaster.until.Symbols;

/** 
 * ��Ҫ������ȡ������Դ
 * 
 */

public class Resource {
	
	private String urlStr = "http://www.iciba.com/index.php?a=getWordMean&c=search&list=1%2C12%2C13%2C3005&word=";
	
	/**
	 * 
	 * @param word ���󵥴�
	 * @return 
	 * �ɹ��� ���ض�Ӧ���ʵ�json
	 * ʧ�ܣ� ����null
	 */
	public String getJson(String word){
		
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
					/**
					 * �����ֱ����� ��
					 * */
					System.out.println("����ʧ��");
				}
				
			} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		
			return json;

	}
	
	/**
	 * ��û�������
	 * 
	 * @param word ����ĵ���
	 * @return 
	 * �ɹ����� Symbols ����
	 * ʧ�ܷ��� null
	 */
	
	Symbols getSymbols(String word){
		Symbols symbols = null;
		List<Map<Object,Object>> list = null;
		String json = this.getJson(word);
		/* �����null��ֱ�ӷ�����*/
		if(word == null) return null;
		
		JSONObject obj = JSON.parseObject(json);
		obj = obj.getJSONObject("baesInfo");
		JSONArray objArray = obj.getJSONArray("symbols");
		System.out.println(objArray.toJSONString());
		symbols = (Symbols) JSON.parseObject("{\"symbols\":" + objArray.toJSONString()+"}",Symbols.class);
		System.out.println(symbols);
		return symbols;
		
	}
	
//	public static void main(String args[]) {
//		Resource res = new Resource();
//		res.getSymbols("hello");
//	}
}
