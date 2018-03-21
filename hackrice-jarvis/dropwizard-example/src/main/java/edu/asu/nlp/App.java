package edu.asu.nlp;

import java.io.IOException;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class App {

    public String getToken() throws Exception {
        // TODO Auto-generated method stub
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&scope=paymentapi");
        Request request = new Request.Builder().url("https://hack.softheon.io/oauth2/connect/token").post(body)
                .addHeader("accept", "application/json").addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("authorization",
                        "Basic ZTdjMWYyMTktODgwMy00OThjLTk3ZDEtNDU0NWExNDkwYmUzOjRhZGQxMWRiLTkzODUtNDAwNi05NTM1LTI3ZTI0NDdmMmVhNg==")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "325dbe6d-d099-66f9-be5e-d56f152eb6d1").build();

        Response response = client.newCall(request).execute();
        String token_string = response.body().string();
        JSONObject token_json = new JSONObject(token_string);
        String access_token = token_json.get("access_token").toString();
        return access_token;
    }

    public void authorizePayer(String access_token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String authoriazation = "Bearer " + access_token;

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
                "{\n    \"cardNumber\": \"4111113956134018\",\n    \"securityCode\": \"123\",\n    \"expirationMonth\": 11,\n    \"expirationYear\": 2017,\n    \"cardHolderName\": \"John Doe\",\n    \"billingAddress\": {\n        \"address1\": \"1500 Stony Brook Road\",\n        \"city\": \"Stony Brook\",\n        \"state\": \"NY\",\n        \"zipCode\": \"11790\"\n    },\n    \"email\": \"jdoe@example.com\",\n    \"referenceId\": \"credit_card_example\"\n}");
        Request request = new Request.Builder().url("https://hack.softheon.io/api/payments/v1/creditcards").post(body)
                .addHeader("content-type", "application/json").addHeader("authorization", authoriazation)
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "2f77a261-f377-e9b8-bf67-0efeb2708bfa").build();

        Response response = client.newCall(request).execute();
        System.out.println("Authorization Status: "+response.body().string());

    }

    public void makePayment(String access_token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String authoriazation = "Bearer " + access_token;

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
                "{\n    \"paymentAmount\": 100.32,\n    \"description\": \"Payment of balance due\",\n    \"referenceId\": \"example_payment\",\n    \"paymentMethod\": {\n        \"paymentToken\": \"0363077379044018\",\n        \"type\": \"Credit Card\"\n    }\n}");
        Request request = new Request.Builder().url("http://hack.softheon.io/api/payments/v1/payments").post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", authoriazation)
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "d0ae39db-e22e-fc41-2f53-8c8743737a8a").build();

        Response response = client.newCall(request).execute();
        
        System.out.println("Payment Status: "+response.body().string());

    }

}