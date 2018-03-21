package edu.asu.intent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.asu.nlp.SingletonClass;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class end_diagnosis {
	
	SingletonClass sc=SingletonClass.getInstance();
	List<String> l;
	String disease="";
	int accuracy=50;
	public end_diagnosis() {
		
		l=sc.getList();
		
		List<String> stockList = new ArrayList<String>();
		System.out.println(l);
		

		String[] stockArr = new String[l.size()];
		stockArr = l.toArray(stockArr);

		String answer = null;
		try {
			answer = getDisease(stockArr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//
		}
		System.out.println(answer);
		sc.setSpeechOutput("There is a "+this.accuracy+" possibility of you having "+this.disease);
		
		
	}
	
	public static void main(String[] sas) throws IOException
	{
		String[] stockArr={"Fever"};
		System.out.println(new end_diagnosis().getDisease(stockArr));
		
	}
	
	public String getDisease(String[] symps) throws IOException {
		System.out.println("Syms"+symps.length);
        OkHttpClient client = new OkHttpClient();

        // %5B 234 %2C 11 %5D
        String[] sampleString = symps;
        String url = "%5B";
        List<Integer> s = getSymptomKeys(sampleString);
        System.out.println("s "+s);
        for (int i = 0; i < s.size(); i++) {
            if (i < s.size() - 1)
                url = url + s.get(i) + "%2C";
            else
                url = url + s.get(i);
        }
        url = url + "%5D";
        System.out.println(url);

        Request request = new Request.Builder()
                .url("https://priaid-symptom-checker-v1.p.mashape.com/diagnosis?gender=male&language=en-gb&symptoms="
                        + url + "&year_of_birth=1984")
                .get().addHeader("x-mashape-key", "xFhAL3oRVpmsh2t18bqadvZrgAQZp1rfiIHjsn1hBkzoM6Unw2")
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        
        String s1 = ""+response.body().string();
        System.out.println(s1);
        JSONArray ja = new JSONArray(s1);
        System.out.println(ja.get(0));
        JSONObject j = (JSONObject) ja.get(0);
        JSONObject j1 = (JSONObject) j.get("Issue");
//        System.out.println(j1.get("Name"));
        this.disease=(String)j1.get("Name");
        this.accuracy=(int) j1.get("Accuracy");
        
        return (String)j1.get("Name");
    }

    public static List<Integer> getSymptomKeys(String sym[]) {
    	System.out.println("getSymptoms "+sym.length);
        List<Integer> output = new ArrayList<Integer>();
        String listofSym = "[{\"ID\": 175, \"Name\": \"chills\" }, { \"ID\": 207, \"Name\": \"dizziness\" }, { \"ID\": 11, \"Name\": \"fever\" }, { \"ID\": 9, \"Name\": \"headache\" }, { \"ID\": 974, \"Name\": \"aggressiveness\" }, { \"ID\": 981, \"Name\": \"agitation\" }, { \"ID\": 27, \"Name\": \"joint pain\" }, { \"ID\": 44, \"Name\": \"nausea\" }, { \"ID\": 975, \"Name\": \"sadness\" }, { \"ID\": 95, \"Name\": \"sneezing\" }, { \"ID\": 138, \"Name\": \"sweating\" }, { \"ID\": 104, \"Name\": \"back pain\" }, { \"ID\": 101, \"Name\": \"vomiting\" }, { \"ID\": 24, \"Name\": \"blackhead\" }, { \"ID\": 284, \"Name\": \"bleeding from vagina\" }, { \"ID\": 152, \"Name\": \"hair loss\" }, { \"ID\": 48, \"Name\": \"bloated feeling in the stomach\" }, { \"ID\": 151, \"Name\": \"dry skin\" }]";
        HashMap<String, String> map = new HashMap<String, String>();
        JSONArray jArray = new JSONArray(listofSym.trim());
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject j = jArray.optJSONObject(i);
            // System.out.println(j.get("ID")+", "+j.get("Name"));
            map.put(j.get("Name").toString(), j.get("ID").toString());
        }

        for (int i = 0; i < sym.length; i++) {
            if (map.containsKey(sym[i].toLowerCase())) {
                output.add(Integer.parseInt(map.get(sym[i].toLowerCase())));
            }
        }

        // System.out.println("json : "+jObject);
        System.out.println("map : " + map);
        System.out.println("output" + output);

        return output;
    }
	

}
