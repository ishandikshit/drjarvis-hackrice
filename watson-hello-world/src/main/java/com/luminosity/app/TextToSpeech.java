//package com.luminosity.app;
//import java.util.List;
//
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.DataLine;
//import javax.sound.sampled.TargetDataLine;
//
//import com.ibm.watson.developer_cloud.http.HttpMediaType;
//import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
//import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
//import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
//import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback;
//import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
//import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
//
//import org.jnativehook.GlobalScreen;
//import org.jnativehook.NativeHookException;
//import org.jnativehook.keyboard.NativeKeyEvent;
//import org.jnativehook.keyboard.NativeKeyListener;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//public class TextToSpeech {
//
//
//  public static void main(String[] args) {
//    TextToSpeech service = new TextToSpeech();
//    service.setUsernameAndPassword("<username>", "<password>");
//
//    List<Voice> voices = service.getVoices().execute();
//    System.out.println(voices);
//  }
//
//}