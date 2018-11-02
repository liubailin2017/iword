package remember.words.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import remember.words.Words;
 

public class WordsImpl implements Words {

	String root = "C:\\Users\\liubailin\\Desktop\\成都信息工程大学\\";
	String name = "36.txt";
	

	@Override
	public List<String[]> words() {
		 List<String[]> ws = new ArrayList();
		 
		  File file = new File(root+name);  
	        BufferedReader reader = null;  
	        try {   
	            reader = new BufferedReader(new FileReader(file));  
	            String tempString = null;  
	            int line = 1;  
	            // 一次读入一行，直到读入null为文件结束  
	            while ((tempString = reader.readLine()) != null) {   
	                System.out.println("line " + line + ": " + tempString);  
	                line++;  
	                ws.add(new String[]{
	                	tempString,"--am","hidden"
	                });
	            }  
	            reader.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (reader != null) {  
	                try {  
	                    reader.close();  
	                } catch (IOException e1) {  
	                }  
	            }  
	        }  
		 
		return ws;
	}

}
