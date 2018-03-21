package edu.asu.intent;


import edu.asu.database.LoginDatabase;
import edu.asu.nlp.SingletonClass;

public class Validation
{
	SingletonClass sg=SingletonClass.getInstance();
	String pass_phrase;
	public Validation() {
		// TODO Auto-generated constructor stub
		pass_phrase= sg.getAIResponse().getResult().getStringParameter("any");
		pass_phrase=pass_phrase.toLowerCase();
		LoginDatabase ld = new LoginDatabase();
		String user=ld.getUser(pass_phrase);
		if(user==null || user.equals(""))
		{
			sg.setSession("");
			sg.setSpeechOutput("Not able to authenticate you");
		}
		else
		{
			sg.setUser(user.toLowerCase());
			sg.setSpeechOutput("Welcome "+ user+" , How can I help you today ?");
		}
	}
}
