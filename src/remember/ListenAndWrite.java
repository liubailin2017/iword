package remember;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import liubailin.iwordmaster.controller.Parse;
import remember.words.Words;
import remember.words.impl.WordsImpl;


public class ListenAndWrite {
	
 
	private static Words words = new WordsImpl(); //单词来源
	
	public static void listen(int wait) { 
		List<String[]> ws = words.words();
		
		for(String[] strs:ws) {
			Parse p = new Parse(strs);
			try {
				p.start();
			Thread.sleep(wait/4*1000);
			p.start();
				Thread.sleep(wait*1000*3/4);
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
		}
	}
	
	public static void remember() {
		List<String[]> ws = words.words();
		int p = -1;
		int len = ws.size();
		 Scanner input = new Scanner(System.in);
	        while(true){
	            System.out.println("如果输入exit退出，0是第一个单词h上一个单词，j当前单词，k或其它下一个单词");
	            String temp = input.nextLine();
	            if(temp.trim().equals("exit")){
	                break;
	            }else if(temp.trim().equals("h") ) {
	            	p -- ;
	            	if(p < 0) {
	            		p = len-1;
	            	}
	            }
	            else if(temp.trim().equals("0")){
	            	p=0;
	            }else if(temp.trim().equals("j")){
	            	//不动
	            }
	            else {
	            	p++;
	            	p = p % len;
	            }
	            Parse parse = new Parse(ws.get(p));
				parse.start();
	        }
	        input.close();
	}
	
	public static void main(String args[]) {
		
		//remember();
		
		listen(10);
	}
 
}
