package edu.asu.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.asu.nlp.SingletonClass;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AppointmentDatabase 
{
	SingletonClass sc = SingletonClass.getInstance();



	public void sendPut(String doc,String time, String date) throws Exception 
	{

		SingletonClass sc= SingletonClass.getInstance();
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType,"{\"name\":\""+sc.getUser() +"\", \"doctor\":\""+
				doc+"\", \"date\":\""+ date+"\", \"time\":\""+time+"\" }'");
		Request request = new Request.Builder()
				.url("http://dbapi.io/db/coll/hackrice_appointment_"+sc.getUser())
				.put(body)
				.addHeader("content-type", "application/json")
				.addHeader("cache-control", "no-cache")
				.build();

		Response response = client.newCall(request).execute();

	}

	public static void main(String[] args)
	{
		String doc="A";String time="1"; String date="2";
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType,"{\"name\":\""+"David"+"\", \"doctor\":\""+
				doc+"\", \"date\":\""+ date+"\", \"time\":\""+time+"\" }'");
		Request request = new Request.Builder()
				.url("http://dbapi.io/db/coll/hackrice_appointment_"+"david")
				.put(body)
				.addHeader("content-type", "application/json")
				.addHeader("cache-control", "no-cache")
				.build();

		try {
			Response response = client.newCall(request).execute();
			System.out.println(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}



