package edu.asu.intent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;

import ai.api.model.AIResponse;
import edu.asu.nlp.GeneratingOTP;
import edu.asu.nlp.SingletonClass;

public class Payment_FeedBack {

	SingletonClass sg=SingletonClass.getInstance();
	AIResponse air;
	private static AmazonSNSClient snsClient = null;
	private final String USER_AGENT = "Mozilla/5.0";
	public Payment_FeedBack() {
		air=sg.getAIResponse();
		String answer= air.getResult().getStringParameter("Feedback");
		if(answer.equals("yes"))
		{
			GeneratingOTP gp = new GeneratingOTP();
			int otp = (int) (Math.random() * 9000) + 1000;
			sg.setOTP(""+otp);
			String message = "Your secret PIN is " + otp;
			try {
				gp.pushOTP(otp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String phoneNumber = "+14804344681";
			try {
				phoneNumber = "+1"+sendGet();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();
			// <set SMS attributes>
			snsClient = gp.getSnsClient();
			gp.sendSMSMessage(snsClient, message, phoneNumber, smsAttributes);

		}
		else
		{
			sg.setSpeechOutput("Thanks, Your apointment is on hold for now");
			sg.setSession("");
		}
	}

	private String sendGet() throws Exception 
	{
		SingletonClass sc = SingletonClass.getInstance();
		String url = "http://dbapi.io/db/coll/hackrice_fb_user_"+sc.getUser();

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		JSONObject obj1 = new JSONObject((response.toString()));
		return (String) obj1.get("phone_number");
	}


	public static void main(String[] args)
	{
		

		
	}
}


