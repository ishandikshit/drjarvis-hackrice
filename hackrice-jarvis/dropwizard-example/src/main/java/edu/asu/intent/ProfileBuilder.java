package edu.asu.intent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.util.TypeKey;
import com.google.gson.Gson;

import ai.api.model.AIResponse;
import edu.asu.nlp.SingletonClass;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProfileBuilder 
{

	HashMap<String,String> hm;
	String query="";
	String name;
	public ProfileBuilder() {
		hm = new HashMap<String, String>();
		Initiate();

	}

	public void run() throws Exception
	{
		sendGet();
		System.out.println(this.query);
	}

	public String getQuery()
	{
		return this.query;
	}
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name=name;
	}


	private void Initiate() 
	{
		hm.put("name","");  
		hm.put("gender","");  
		hm.put("age","");  
		hm.put("email","");
		hm.put("phone_number","");  
		hm.put("allergies","");  
		hm.put("vegan","");  
		hm.put("zip_code","");
	}




	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) 
	{
		ProfileBuilder pf;
		pf= new ProfileBuilder();
		try {
			pf.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(pf.getQuery());
	}





	// HTTP GET request
	private void sendGet() throws Exception 
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
		Iterator<?> keys = obj1.keys();

		while( keys.hasNext() ) {
			String key = (String)keys.next();
			if ( hm.containsKey(key) ) 
			{
				hm.put(key, obj1.getString(key));
			}
		}
		for (String name: hm.keySet())
		{
			String key =name.toString();
			String value = hm.get(name).toString();  
			System.out.println(key + " : " + value);
			query+= " " + value;
		} 
		this.name=name.toLowerCase();
		hm.put("name", hm.get("name").toLowerCase());
		if(!(this.name.equals(hm.get("name"))))
		{
			SingletonClass sg= SingletonClass.getInstance();
			sg.setSpeechOutput("You are not a registered user. Kindly register with us.");
			sg.setSession("");
		}
		query=query.replaceAll("\\s+", " ");
		System.out.println(query);
	}


	public void sendPut() throws Exception 
	{
		SingletonClass sc= SingletonClass.getInstance();
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"name\":\""+ hm.get("name")+"\", \"gender\":\""+ hm.get("gender")+"\", \"age\":\""+ hm.get("age")+"\", \"email\":\""+ hm.get("email")+"\", \"phone_number\":\""+ hm.get("phone_number")+"\", \"allergies\": \"\", \"vegan\":\""+ hm.get("vegan")+"\", \"zip_code\":\""+ hm.get("zip_code")+"\" }'");
		Request request = new Request.Builder()
				.url("http://dbapi.io/db/coll/hackrice_fb_user_"+sc.getUser())
				.put(body)
				.addHeader("content-type", "application/json")
				.addHeader("cache-control", "no-cache")
				.build();

		Response response = client.newCall(request).execute();

	}


	// HTTP POST request
	private void sendPost() throws Exception {

		String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

	public void initdata() {

		SingletonClass sg= SingletonClass.getInstance();
		AIResponse air=sg.getAIResponse();
		//System.out.println(air.getResult().getIntParameter("age"));

		hm.put("name",air.getResult().getStringParameter("given-name"));  
		hm.put("gender",air.getResult().getStringParameter("Sex"));  
		hm.put("age","23");  
		hm.put("email",air.getResult().getStringParameter("email"));
		hm.put("phone_number",""+air.getResult().getStringParameter("phone-number"));  
		hm.put("allergies","");  
		hm.put("vegan",air.getResult().getStringParameter("Diet"));  
		hm.put("zip_code",air.getResult().getIntParameter("zip-code")+"");


	}

}



//http://dbapi.io/db/coll/hackrice_fb_data

//}
