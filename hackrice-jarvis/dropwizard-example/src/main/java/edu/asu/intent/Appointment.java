package edu.asu.intent;

import ai.api.model.AIResponse;
import edu.asu.database.AppointmentDatabase;
import edu.asu.nlp.SingletonClass;

public class Appointment 
{
	SingletonClass sc=SingletonClass.getInstance();
	AIResponse air;
	public Appointment() {
		air=sc.getAIResponse();
		String doc=air.getResult().getStringParameter("given-name");
		String time=air.getResult().getStringParameter("time");
		String date=air.getResult().getStringParameter("date");
		AppointmentDatabase ad= new AppointmentDatabase();
		try {
			ad.sendPut(doc, time, date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}