/*
 * Copyright 2017 IBM Corp. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.luminosity.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
 
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

/**
 * Recognize microphone input speech continuously using WebSockets.
 */
public class MicrophoneWithWebSocketsExample implements NativeKeyListener {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(final String[] args) throws Exception {

		SpeechToText service = new SpeechToText();
		service.setUsernameAndPassword("dfe43c1c-fb65-475f-a801-67c90121df05", "E2y023nHxGfc");

		// Signed PCM AudioFormat with 16kHz, 16 bit sample size, mono
		int sampleRate = 16000;
		AudioFormat format = new AudioFormat(sampleRate, 16, 1, true, false);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		System.out.println(info);
		if (!AudioSystem.isLineSupported(info)) {
			System.out.println("Line not supported");
			System.exit(0);
		}

		TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
		line.open(format);
		line.start();
		String s = "";
		AudioInputStream audio = new AudioInputStream(line);
		RecognizeOptions options = new RecognizeOptions.Builder().continuous(false).interimResults(false)
				.timestamps(true).wordConfidence(true)
				// .inactivityTimeout(5) // use this to stop listening when the
				// speaker pauses, i.e. for 5s
				.contentType(HttpMediaType.AUDIO_RAW + "; rate=" + sampleRate).build();

		service.recognizeUsingWebSocket(audio, options, new BaseRecognizeCallback() {
			@Override
			public void onTranscription(SpeechResults speechResults) {
				// System.out.println(speechResults.getResults().get(0));

				JSONObject jsonObj = new JSONObject(speechResults.getResults().get(0));
				JSONArray ar = (JSONArray) jsonObj.get("alternatives");
				JSONObject jsObj2 = ar.getJSONObject(0);
				System.out.println(jsObj2.get("transcript"));
				try {
					textToSpeech((String)jsObj2.get("transcript"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		System.out.println("Listening to your voice for the next 3s...");
		Thread.sleep(3 * 1000);

		// closing the WebSockets underlying InputStream will close the
		// WebSocket itself.
		line.stop();
		line.close();
		
		System.out.println("Fin.");
	}

	public static void textToSpeech(String text) throws IOException {
		TextToSpeech service = new TextToSpeech();
		service.setUsernameAndPassword("8ecadafb-de31-4cf5-9dde-6d18f557da3b", "wkcF30e7rV2v");
		InputStream in = service.synthesize(text, Voice.EN_LISA).execute();
		writeToFile(WaveUtils.reWriteWaveHeader(in), new File("output.wav"));
		
		String filename = "output.wav";
		 
        InputStream ins = new FileInputStream(new File(filename));
        AudioStream audioStream = new AudioStream(ins);
        AudioPlayer.player.start(audioStream);

		// List<Voice> voices = service.getVoices().execute();
		// System.out.println(voices);
		// System.out.println(voices.get(0));
	}

	public void nativeKeyPressed(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	private static void writeToFile(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
