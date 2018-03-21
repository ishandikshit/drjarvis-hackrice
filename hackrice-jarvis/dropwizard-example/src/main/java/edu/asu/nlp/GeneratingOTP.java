package edu.asu.nlp;

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

public class GeneratingOTP {
	
	
	private static final String CLIENT_TOKEN = "AKIAI6INDN6NMYTENC4A";
    private static final String CLIENT_SECRET = "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";
    private static final BasicAWSCredentials CREDENTIALS = new BasicAWSCredentials(CLIENT_TOKEN, CLIENT_SECRET);

    private static AmazonSNSClient snsClient = null;

    

    public  void pushOTP(int otp) throws Exception {
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

    public  void sendSMSMessage(AmazonSNSClient snsClient, String message, String phoneNumber,
            Map<String, MessageAttributeValue> smsAttributes) {
        PublishResult result = snsClient.publish(new PublishRequest().withMessage(message).withPhoneNumber(phoneNumber)
                .withMessageAttributes(smsAttributes));
        System.out.println(result); // Prints the message ID.

    }

    public  AmazonSNSClient getSnsClient() {
        if (null == snsClient) {
            snsClient = new AmazonSNSClient(CREDENTIALS).withRegion(Regions.US_WEST_2);
        }
        return snsClient;
    }

}
