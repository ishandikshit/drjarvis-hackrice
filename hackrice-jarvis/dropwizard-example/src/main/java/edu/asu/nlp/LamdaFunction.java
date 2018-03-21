package edu.asu.nlp;

import ai.api.model.AIResponse;
import edu.asu.intent.Appointment;
import edu.asu.intent.Default_Fallback_Intent;
import edu.asu.intent.Default_Welcome_Intent;
import edu.asu.intent.Diagnosis;
import edu.asu.intent.OTP_feedback;
import edu.asu.intent.Payment_FeedBack;
import edu.asu.intent.ProfileBuilder;
import edu.asu.intent.StartBuildingProfile;
import edu.asu.intent.Validation;
import edu.asu.intent.end_diagnosis;
import edu.asu.intent.symptoms;


public class LamdaFunction {

	AIResponse air;
	public LamdaFunction() {
		SingletonClass sg= SingletonClass.getInstance();
		air=sg.getAIResponse();
		onIntent(air.getResult().getMetadata().getIntentName(),air.getResult().isActionIncomplete());
	}

	private void onIntent(String intentName,boolean flag)
	{
		if(intentName.equals("") || intentName.isEmpty())
		{
			new Default_Fallback_Intent();
			return;
		}
		System.out.println("\nIntent Triggered is " + intentName);
		System.out.println("\nIs Intent Complete ?" + flag);

		SingletonClass sg= SingletonClass.getInstance();
		if(flag)
		{

			sg.setSpeechOutput(air.getResult().getFulfillment().getSpeech());
		}
		else
		{
			switch(intentName)
			{
			case "Appointment":

				new Appointment();
				break;

			case "StartBuildingProfile":
				new StartBuildingProfile();
				break;

			case "ProfileBuilder":
				try {
					ProfileBuilder pf =new ProfileBuilder();
					pf.initdata();
					pf.sendPut();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				sg.setSession("");
				break;


			case "Payment_FeedBack":
				new Payment_FeedBack();
				break;

			case "OTP_feedback":
				new OTP_feedback();
				break;
			
			case "Default Welcome Intent":
				new Default_Welcome_Intent();
				break;
			
			case "Validation":
				new Validation();
				break;

				
				
			case "symptoms":
				new symptoms();
				break;
			case "Diagnosis":
				new Diagnosis();
				break;
			case "end_diagnosis":
				new end_diagnosis();
				break;
			default:
				new Default_Fallback_Intent();
				break;


			}


		}
	}
}
