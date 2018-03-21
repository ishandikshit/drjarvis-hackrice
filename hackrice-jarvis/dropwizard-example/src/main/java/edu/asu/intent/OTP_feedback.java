package edu.asu.intent;

import java.io.IOException;

import ai.api.model.AIResponse;
import edu.asu.nlp.App;
import edu.asu.nlp.SingletonClass;
import edu.asu.nlp.otpWork;

public class OTP_feedback {
	
	SingletonClass sc=SingletonClass.getInstance();
	AIResponse air;
	public OTP_feedback() {
		boolean flag = false;
		air=sc.getAIResponse();
		String input=air.getResult().getStringParameter("number");
		System.out.println(input);
		String temp= new otpWork().getValue();
		input=input.toLowerCase();
		temp=temp.toLowerCase();
		if(input.equals(sc.getOTP()) || input.contains(temp) || temp.contains(input) )
		{
			flag=true;
		}
		else
		{
			//sc.setSpeechOutput("Invalid One time password");
		}
		if(true)
		{
			InitPayment();
		}
		// TODO Auto-generated constructor stub
	}
	private void InitPayment() {
		// TODO Auto-generated method stub
		App app= new App();
		String token = null;
		try {
			token = app.getToken();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			app.authorizePayer(token);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			app.makePayment(token);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
