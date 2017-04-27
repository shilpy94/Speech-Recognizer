package com.example.shilpysamaddar.speechrecognition;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView txtSpeechInput;
    Button submit;
    private ImageView btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    String s1=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageView) findViewById(R.id.btnSpeak);
        submit=(Button) findViewById(R.id.submit);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();

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

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "say something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),"Your device does not support this language",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    s1=s1+" "+result.get(0);
                    if(s1!=null) {
                        txtSpeechInput.setText(s1);
                    }
                }
                break;
            }

        }
    }
}
