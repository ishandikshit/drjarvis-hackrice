
////
//
////import com.datastax.driver.core.Cluster;
////import com.datastax.driver.core.ResultSet;
////import com.datastax.driver.core.Session;
////

////	public static void main(String[] args) {
////		Cluster cluster = Cluster.builder().addContactPoint("35.202.53.89").withCredentials("cassandra", "e3tKNaYVPE3M")
////				.build();
////		Session session = cluster.connect();
////		System.out.println("session object---" + session);
////		session.execute("use system");
////		ResultSet resultSet = session.execute("select * from hints");
////		System.out.println(resultSet.all());
////		session.close();
////	}
////}
//
package com.luminosity.app;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class App {
	//
	// public class App {
	//
	// public String getToken() throws Exception {
	// // TODO Auto-generated method stub
	// OkHttpClient client = new OkHttpClient();
	//
	// MediaType mediaType =
	// MediaType.parse("application/x-www-form-urlencoded");
	// RequestBody body = RequestBody.create(mediaType,
	// "grant_type=client_credentials&scope=paymentapi");
	// Request request = new
	// Request.Builder().url("https://hack.softheon.io/oauth2/connect/token").post(body)
	// .addHeader("accept", "application/json").addHeader("content-type",
	// "application/x-www-form-urlencoded")
	// .addHeader("authorization",
	// "Basic
	// ZTdjMWYyMTktODgwMy00OThjLTk3ZDEtNDU0NWExNDkwYmUzOjRhZGQxMWRiLTkzODUtNDAwNi05NTM1LTI3ZTI0NDdmMmVhNg==")
	// .addHeader("cache-control", "no-cache")
	// .addHeader("postman-token",
	// "325dbe6d-d099-66f9-be5e-d56f152eb6d1").build();
	//
	// Response response = client.newCall(request).execute();
	// String token_string = response.body().string();
	// JSONObject token_json = new JSONObject(token_string);
	// String access_token = token_json.get("access_token").toString();
	// return access_token;
	// }
	//
	// public void authorizePayer(String access_token) throws IOException {
	// OkHttpClient client = new OkHttpClient();
	// String authoriazation = "Bearer " + access_token;
	//
	// MediaType mediaType = MediaType.parse("application/json");
	// RequestBody body = RequestBody.create(mediaType,
	// "{\n \"cardNumber\": \"4111113956134018\",\n \"securityCode\": \"123\",\n
	// \"expirationMonth\": 11,\n \"expirationYear\": 2017,\n
	// \"cardHolderName\": \"John Doe\",\n \"billingAddress\": {\n \"address1\":
	// \"1500 Stony Brook Road\",\n \"city\": \"Stony Brook\",\n \"state\":
	// \"NY\",\n \"zipCode\": \"11790\"\n },\n \"email\":
	// \"jdoe@example.com\",\n \"referenceId\": \"credit_card_example\"\n}");
	// Request request = new
	// Request.Builder().url("https://hack.softheon.io/api/payments/v1/creditcards").post(body)
	// .addHeader("content-type", "application/json").addHeader("authorization",
	// authoriazation)
	// .addHeader("cache-control", "no-cache")
	// .addHeader("postman-token",
	// "2f77a261-f377-e9b8-bf67-0efeb2708bfa").build();
	//
	// Response response = client.newCall(request).execute();
	// System.out.println("Authorization Status: "+response.body().string());
	//
	// }
	//
	// private void makePayment(String access_token) throws IOException {
	// OkHttpClient client = new OkHttpClient();
	// String authoriazation = "Bearer " + access_token;
	//
	// MediaType mediaType = MediaType.parse("application/json");
	// RequestBody body = RequestBody.create(mediaType,
	// "{\n \"paymentAmount\": 100.32,\n \"description\": \"Payment of balance
	// due\",\n \"referenceId\": \"example_payment\",\n \"paymentMethod\": {\n
	// \"paymentToken\": \"0363077379044018\",\n \"type\": \"Credit Card\"\n
	// }\n}");
	// Request request = new
	// Request.Builder().url("http://hack.softheon.io/api/payments/v1/payments").post(body)
	// .addHeader("content-type", "application/json")
	// .addHeader("authorization", authoriazation)
	// .addHeader("cache-control", "no-cache")
	// .addHeader("postman-token",
	// "d0ae39db-e22e-fc41-2f53-8c8743737a8a").build();
	//
	// Response response = client.newCall(request).execute();
	//
	// System.out.println("Payment Status: "+response.body().string());
	//
	// }
	//
	// }

	// OkHttpClient client = new OkHttpClient();
	//
	// // %5B 234 %2C 11 %5D
	// String[] sampleString = symps;
	// String url = "%5B";
	// List<Integer> s = getSymptomKeys(sampleString);
	// for (int i = 0; i < s.size(); i++) {
	// if (i < s.size() - 1)
	// url = url + s.get(i) + "%2C";
	// else
	// url = url + s.get(i);
	// }
	// url = url + "%5D";
	// System.out.println(url);
	//
	// Request request = new Request.Builder()
	// .url("https://priaid-symptom-checker-v1.p.mashape.com/diagnosis?gender=male&language=en-gb&symptoms="
	// + url + "&year_of_birth=1984")
	// .get().addHeader("x-mashape-key",
	// "xFhAL3oRVpmsh2t18bqadvZrgAQZp1rfiIHjsn1hBkzoM6Unw2")
	// .addHeader("cache-control", "no-cache")
	// .addHeader("postman-token",
	// "fc5dd53a-c34e-05cc-4375-a522b374e02d").build();
	//
	// Response response = client.newCall(request).execute();
	// System.out.println(response.body().string());
	// return response.body().string();
	// }
	//
	// public static List<Integer> getSymptomKeys(String sym[]) {
	// List<Integer> output = new ArrayList<Integer>();
	// String listofSym = "[{\"ID\": 188, \"Name\": \"Abdominal guarding\"
	// }, {
	// \"ID\": 10, \"Name\": \"Abdominal pain\" }, { \"ID\": 223, \"Name\":
	// \"Abdominal pain associated with menstruation\" }, { \"ID\": 984,
	// \"Name\": \"Absence of a pulse\" }, { \"ID\": 974, \"Name\":
	// \"Aggressiveness\" }, { \"ID\": 981, \"Name\": \"Agitation\" }, {
	// \"ID\":
	// 996, \"Name\": \"Ankle deformity\" }, { \"ID\": 147, \"Name\":
	// \"Ankle
	// swelling\" }, { \"ID\": 238, \"Name\": \"Anxiety\" }, { \"ID\": 971,
	// \"Name\": \"Arm swelling\" }, { \"ID\": 998, \"Name\": \"Back
	// deformity\"
	// }, { \"ID\": 104, \"Name\": \"Back pain\" }, { \"ID\": 180, \"Name\":
	// \"Black stools\" }, { \"ID\": 24, \"Name\": \"Blackhead\" }, {
	// \"ID\":
	// 284, \"Name\": \"Bleeding from vagina\" }, { \"ID\": 176, \"Name\":
	// \"Bleeding in the conjunctiva of the eye\" }, { \"ID\": 48, \"Name\":
	// \"Bloated feeling in the stomach\" }, { \"ID\": 190, \"Name\":
	// \"Blood in
	// stool\" }]";
	// HashMap<String, String> map = new HashMap<String, String>();
	// JSONArray jArray = new JSONArray(listofSym.trim());
	// for (int i = 0; i < jArray.length(); i++) {
	// JSONObject j = jArray.optJSONObject(i);
	// // System.out.println(j.get("ID")+", "+j.get("Name"));
	// map.put(j.get("Name").toString(), j.get("ID").toString());
	// }
	//
	// for (int i = 0; i < sym.length; i++) {
	// if (map.containsKey(sym[i])) {
	// output.add(Integer.parseInt(map.get(sym[i])));
	// }
	// }
	//
	// // System.out.println("json : "+jObject);
	// System.out.println("map : " + map);
	// System.out.println("output" + output);
	//
	// return output;

	// OkHttpClient client = new OkHttpClient();
	// String user = "david";
	// MediaType mediaType = MediaType.parse("application/json");
	// RequestBody body = RequestBody.create(mediaType,
	// "{\"name\":\"David\",
	// \"gender\":\"Male\", \"age\":\"21\",
	// \"email\":\"david_mtiqgkx_walker@tfbnw.net\", \"phone_number\":\"\",
	// \"allergies\": \"\", \"vegan\":\"asdfj\", \"zip_code\":\"85285\"
	// }'");
	// Request request = new Request.Builder()
	// .url("http://dbapi.io/db/coll/hackrice_fb_user_"+user)
	// .put(body)
	// .addHeader("content-type", "application/json")
	// .addHeader("cache-control", "no-cache")
	// .build();
	//
	// Response response = client.newCall(request).execute();
	// System.out.println(response);
	private static final String CLIENT_TOKEN = "AKIAI6INDN6NMYTENC4A";
	private static final String CLIENT_SECRET = "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";
	private static final BasicAWSCredentials CREDENTIALS = new BasicAWSCredentials(CLIENT_TOKEN, CLIENT_SECRET);

	private static AmazonSNSClient snsClient = null;

	public static void main(String[] args) throws Exception {
		int otp = (int) (Math.random() * 9000) + 1000;
		String message = "Your secret PIN is " + otp;
		pushOTP(otp);
		String phoneNumber = "+14804017816";
		Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();
		// <set SMS attributes>
		snsClient = getSnsClient();
		sendSMSMessage(snsClient, message, phoneNumber, smsAttributes);
	}

	private static void pushOTP(int otp) throws Exception {
		// TODO Auto-generated method stub
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"otp\":\"" + otp + "\"}'");
		Request request = new Request.Builder().url("http://dbapi.io/db/coll/hackrice_otp").put(body)
				.addHeader("content-type", "application/json").addHeader("cache-control", "no-cache")
				.addHeader("postman-token", "19db1c1f-a572-70ce-afbc-54059022aae3").build();

		Response response = client.newCall(request).execute();
		System.out.println(response);
	}

	public static void sendSMSMessage(AmazonSNSClient snsClient, String message, String phoneNumber,
			Map<String, MessageAttributeValue> smsAttributes) {
		PublishResult result = snsClient.publish(new PublishRequest().withMessage(message).withPhoneNumber(phoneNumber)
				.withMessageAttributes(smsAttributes));
		System.out.println(result); // Prints the message ID.

	}

	public static AmazonSNSClient getSnsClient() {
		if (null == snsClient) {
			snsClient = new AmazonSNSClient(CREDENTIALS).withRegion(Regions.US_WEST_2);
		}
		return snsClient;
	}

}
