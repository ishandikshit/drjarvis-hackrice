package edu.asu.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONObject;

public class LoginDatabase 
{
	public String getUser(String pass)
	{

		String url = "http://dbapi.io/db/coll/hackrice_passphrase_"+pass;
		URL obj = null;
		try {
			obj = new URL(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) obj.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// optional default is GET
		try {
			con.setRequestMethod("GET");
		} catch (ProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = 0;
		try {
			responseCode = con.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = null;
		try {
			in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String inputLine;
		StringBuffer response = new StringBuffer();

		try {
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject obj1 = new JSONObject((response.toString()));
		
		System.out.println(obj1.toString());
		String name = obj1.getString("name");
		return name;
	}
	public static void main(String args[])
	{
		LoginDatabase ad= new LoginDatabase();
		System.out.println("THIS is "+ad.getUser("phoenix"));
	}

}
