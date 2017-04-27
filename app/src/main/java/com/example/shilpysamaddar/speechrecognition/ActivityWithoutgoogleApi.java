package com.example.shilpysamaddar.speechrecognition;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by shilpysamaddar on 02/03/17.
 */

public class ActivityWithoutgoogleApi extends AppCompatActivity {

    private TextView txtSpeechInput;
    Button submit;
    private ImageView btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    String s1=" ";
    private SpeechRecognizer sr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageView) findViewById(R.id.btnSpeak);
        submit=(Button) findViewById(R.id.submit);

        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new listener());
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                promptSpeechInput();
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
                intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"voice.recognition.test");
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,0);
                sr.startListening(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(s1!=null) {
                    txtSpeechInput.setText(s1);
                    s1=" ";
                    txtSpeechInput.setText(s1);
                    txtSpeechInput.setHint("Speak Your Mind!!");
                }
            }
        });
    }

    class listener implements RecognitionListener
    {
        public void onReadyForSpeech(Bundle params)
        {
            Log.d(TAG, "onReadyForSpeech");
        }
        public void onBeginningOfSpeech()
        {
            Log.d(TAG, "onBeginningOfSpeech");
        }
        public void onRmsChanged(float rmsdB)
        {
            Log.d(TAG, "onRmsChanged");
        }
        public void onBufferReceived(byte[] buffer)
        {
            Log.d(TAG, "onBufferReceived");
        }
        public void onEndOfSpeech()
        {
            Log.d(TAG, "onEndofSpeech");
        }
        public void onError(int error)
        {
            Log.d(TAG,  "error " +  error);
//        mText.setText("error " + error);
        }
        public void onResults(Bundle results)
        {
//            String str = new String();
//            Log.d(TAG, "onResults " + results);
//            ArrayList data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//            for (int i = 0; i < data.size(); i++)
//            {
//                Log.d(TAG, "result " + data.get(i));
//                str += data.get(i);
//            }
//            txtSpeechInput.setText("results: "+str);


            Log.d(TAG, "onResults");
            ArrayList<String> matches = results
                    .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            String text = "";
            for (String result : matches)
                text = result + "\n";
            txtSpeechInput.setText(text);
        }
        public void onPartialResults(Bundle partialResults)
        {
            Log.d(TAG, "onPartialResults");
        }
        public void onEvent(int eventType, Bundle params)
        {
            Log.d(TAG, "onEvent " + eventType);
        }
    }

}

