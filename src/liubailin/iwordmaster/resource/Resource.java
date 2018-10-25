package liubailin.iwordmaster.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import liubailin.iwordmaster.model.db.DBCon;
import liubailin.iwordmaster.until.JuShi;
import liubailin.iwordmaster.until.LocalSimple;
import liubailin.iwordmaster.until.SimpleTmp;
import liubailin.iwordmaster.until.Symbols;
import liubailin.iwordmaster.until.Symbols.SybolsItem;

/** 
 * 这个可以优化，以后有时间做
 * 主要用来获取网络资源
 * 
 */


public class Resource {
	
	/**
	 * 非官方金山词典源 
	 *  <a href="https://github.com/jokermonn/-Api/blob/master/KingsoftDic.md">api说明</a>
	 */
	private String icibaUrlStr = "http://www.iciba.com/index.php?a=getWordMean&c=search&list=1%2C3005%2C12%2C13&word=";
	
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
		//System.out.println(json);
		/* 如果是null就直接返回了*/
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
			/** 说明
			 * 
			 * 这里我选择捕获空指针异常，这样做肯定是不好的， 因为后期很难查错。
			 * 但这个程序我不想再维护了，抛空指针应该是没有获取到值，就返回null表示没有查到单词。 
			 */
			return null;
		}
	}

	/**
	 * 本地词典
	 * @param word
	 * @return
	 */
	public LocalSimple getLocalSimple(String word) {
		ResultSet set = new DBCon().doQuery("select * from Dict where word=?", new Object[]{word});
		LocalSimple localSimple = new LocalSimple();
		try {
			while(set.next()) {
//				System.out.println(set.getString(1));
//				System.out.println(set.getString(2));
//				System.out.println(set.getString(3));
				String xml = set.getString(3);
				StringReader read = new StringReader(xml);
				InputSource source = new InputSource(read);
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				try {
					  DocumentBuilder db  = dbf.newDocumentBuilder();
					  Document document = db.parse(source);
					  NodeList nodelist =document.getElementsByTagName("V");
					  localSimple.setHwV(nodelist.item(0).getTextContent());
					  localSimple.setPronV(nodelist.item(1).getTextContent());
					  List<LocalSimple.Sens> senslist  = new ArrayList<>();
					  nodelist = document.getElementsByTagName("SENS");
					  for(int i = 0; i < nodelist.getLength();i++) {
						  LocalSimple.Sens sens = new LocalSimple.Sens();
						  sens.setPos(nodelist.item(i).getChildNodes().item(0).getTextContent());
						  sens.setDefSenD(nodelist.item(i).getChildNodes().item(2).getChildNodes().item(0).getChildNodes().item(0).getTextContent());
						  senslist.add(sens);
					  }
					  localSimple.setSensList(senslist);
				} catch (ParserConfigurationException | SAXException | IOException e) {
					e.printStackTrace();
				  }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return localSimple;
	}
	/**
	 * 获得例句
	 * @param word
	 * @return 成功返回JuShi列表 失败 null
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
