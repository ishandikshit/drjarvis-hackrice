package edu.asu.nlp;

import java.util.HashMap;

public class otpWork {
	
	HashMap<Integer,String> hm = new HashMap<Integer,String>();
	SingletonClass sc=SingletonClass.getInstance();
	String a;
	public otpWork() {
		hm.put(1, "one");
		hm.put(2, "two");
		hm.put(3, "three");
		hm.put(4, "four");
		hm.put(5, "five");
		hm.put(6, "six");
		hm.put(7, "seven");
		hm.put(8, "eight");
		hm.put(9, "nine");
		hm.put(0, "zero");
		
		int num=Integer.parseInt(sc.getOTP());
		a = "";
		for(int i=0;i<4;i++)
		{
			int value=num%10;
			a=hm.get(value)+a;
			num=num-value;
			num=num/10;
			a=" "+a;
			
		}
		a=a.substring(1);
		System.out.println(a);
	}
	public static void main(String args[])
	{
		new otpWork();
	}
	public String getValue()
	{
		return this.a;
	}
}
