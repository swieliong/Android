package ryanman.example.audio;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;

import android.os.Bundle;
import android.os.Environment;

import android.view.View;
import android.widget.Button;

import android.widget.Toast;
import java.io.IOException;

import ryanman.example.R;

/*
Android has a built in microphone through which you can capture audio and store it, or play it in your phone.
There are many ways to do that but the most common way is through MediaRecorder class.
MediaRecorder class can be used to record audio and video files.
 */
public class AudioCaptureActivity extends Activity {
    Button playBtn, stopBtn, recordBtn;
    private MediaRecorder recorder;
    private String outputFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_capture_layout);

        playBtn = (Button)findViewById(R.id.playBtn);
        stopBtn = (Button)findViewById(R.id.stopBtn);
        recordBtn =(Button)findViewById(R.id.recordbtn);

        stopBtn.setEnabled(false);
        playBtn.setEnabled(false);
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/sample_recording.3gp";;

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        recorder.setOutputFile(outputFile);

        recordBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    recorder.prepare();
                    recorder.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                recordBtn.setEnabled(false);
                stopBtn.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws IllegalArgumentException, SecurityException, IllegalStateException {
                MediaPlayer m = new MediaPlayer();
                try {
                    m.setDataSource(outputFile);
                    m.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                m.start();
                Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recorder.stop();
                recorder.release();
                recorder = null;

                stopBtn.setEnabled(false);
                playBtn.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Audio recorded successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
}
