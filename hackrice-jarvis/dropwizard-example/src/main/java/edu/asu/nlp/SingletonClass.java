package edu.asu.nlp;

import java.util.ArrayList;
import java.util.List;

import ai.api.model.AIResponse;


public class SingletonClass {
	private static SingletonClass instance = null;
	String speech_output;
	String speech_input;
	String session;
	String user;
	AIResponse response;
	String OTP;
	
	
	
	List<String> lt = new ArrayList<String>();
	
	public void setOTP(String value)
	{
		this.OTP=value;
	}
	public String getOTP()
	{
		return this.OTP;
	}
	
	public void addList(String value)
	{
		lt.add(value);
	}
	public List<String> getList()
	{
		return this.lt;
	}
	public AIResponse getAIResponse()
	{
		return this.response;
	}
	public void setAIResponse(AIResponse air)
	{
		this.response=air;
	}
	
	public String getUser()
	{
		return this.user;
	}
	public void setUser(String name)
	{
		this.user=name;
	}
	
	public String getSession()
	{
		return this.session;
	}
	public String getSpeechInput()
	{
		return this.speech_input;
	}
	public String getSpeechOutput()
	{
		return this.speech_output;
	}
	public void setSession(String session)
	{
		this.session=session;
	}
	public void setSpeechInput(String speech)
	{
		this.speech_input=speech;
	}
	public void setSpeechOutput(String speech)
	{
		this.speech_output=speech;
	}
	protected SingletonClass() {
		// Exists only to defeat instantiation.
	}
	public static SingletonClass getInstance() {
		if(instance == null) {
			instance = new SingletonClass();
		}
		return instance;
	}
}


