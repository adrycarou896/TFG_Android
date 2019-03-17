package com.uo246262.tfg;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    private Button boton;
    private ImageView imagen;
    int ID=100;

    private MediaRecorder mediaRecorder = null;
    private MediaPlayer mediaPlayer = null;

    private String fileName = null;

    private boolean recording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileName = Environment.getExternalStorageDirectory() + "/test.mp4";
        SurfaceView surface = (SurfaceView)findViewById(R.id.surfaceView);
        SurfaceHolder holder = surface.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        final Button btnRec = (Button)findViewById(R.id.btStart);

        btnRec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                btnRec.setEnabled(false);
                prepareRecorder();
                mediaRecorder.setOutputFile(fileName);
                try {
                    mediaRecorder.prepare();
                } catch (IllegalStateException e) {
                } catch (IOException e) {
                }

                //mediaRecorder.start();
                recording = true;
            }
        });
        //boton = (Button) findViewById(R.id.btTomarFoto);
        //imagen = (ImageView) findViewById(R.id.imageView);

        /*boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(intent,ID);//Busca activity con ese id y ese intent
            }
        });*/
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mediaRecorder == null) {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setPreviewDisplay(holder.getSurface());
        }

        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDisplay(holder);
        }
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        mediaRecorder.release();
        mediaPlayer.release();
    }

    public void prepareRecorder(){
        //mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        //mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        //mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        //mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
    }
}
