package edu.asu.intent;

import edu.asu.nlp.SingletonClass;

public class symptoms {
	
	SingletonClass sc=SingletonClass.getInstance();
	public symptoms() {
		String value=sc.getAIResponse().getResult().getStringParameter("any");
		sc.addList(value);
		System.out.println(value);
	}
}
