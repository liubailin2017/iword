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
 * ��������Ż����Ժ���ʱ����
 * ��Ҫ������ȡ������Դ
 * 
 */


public class Resource {
	
	/**
	 * �ǹٷ���ɽ�ʵ�Դ 
	 *  <a href="https://github.com/jokermonn/-Api/blob/master/KingsoftDic.md">api˵��</a>
	 */
	private String icibaUrlStr = "http://www.iciba.com/index.php?a=getWordMean&c=search&list=1%2C3005%2C12%2C13&word=";
	
	/**
	 * 
	 * @param word ���󵥴�
	 * @return 
	 * �ɹ��� ���ض�Ӧ���ʵ�json
	 * ʧ�ܣ� ����null
	 */
	public String getJson(String word) {
		
		String json = null;
		URL url;
		
		try {
			url = new URL(icibaUrlStr+ word);
	
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
	 * ��û�������
	 * 
	 * @param word ����ĵ���
	 * @return 
	 * �ɹ����� Symbols ����
	 * ʧ�ܷ��� null
	 */
	
	public Symbols getSymbols(String word){
		Symbols symbols = null;
		List<Map<Object,Object>> list = null;
		String json = this.getJson(word);
		//System.out.println(json);
		/* �����null��ֱ�ӷ�����*/
		if(word == null || word.equals("") || json == null) return null;
		try{
			JSONObject obj = JSON.parseObject(json);
			obj = obj.getJSONObject("baesInfo");
			JSONArray objArray = obj.getJSONArray("symbols");
			//System.out.println(objArray.toJSONString());
			symbols = (Symbols) JSON.parseObject("{\"symbols\":" + objArray.toJSONString()+"}",Symbols.class);
			//System.out.println(symbols);
			return symbols;
		}catch(NullPointerException e){
			/** ˵��
			 * 
			 * ������ѡ�񲶻��ָ���쳣���������϶��ǲ��õģ� ��Ϊ���ں��Ѳ��
			 * ����������Ҳ�����ά���ˣ��׿�ָ��Ӧ����û�л�ȡ��ֵ���ͷ���null��ʾû�в鵽���ʡ� 
			 */
			return null;
		}
	}
	
	/**
	 * �������
	 * @param word
	 * @return �ɹ�����JuShi�б� ʧ�� null
	 */
	public List<JuShi> getJushi(String word) {
		JuShi symbols = null;
		List<JuShi> list = null;
		String json = this.getJson(word);
		/* �����null��ֱ�ӷ�����*/
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
	 * �����򵥲���
	 * @param args
	 */
	public static void main(String args[]) {
		Resource res = new Resource();
		res.getSymbols("hello");
		res.getJushi("hello");
	}
}
