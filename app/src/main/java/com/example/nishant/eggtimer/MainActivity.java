package com.example.nishant.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {
MediaPlayer mp;
SeekBar sb;
CountDownTimer timer;

TextView disp;
void reset()
{
    disp.setText(sectostr(30));
    sb.setProgress(30);
}
String sectostr(long seconds)
{
    long min=(seconds/60)%60;
    long sec=(seconds)%60;
    String s=String.format("%02d",min) + ":" + String.format("%02d",sec);
    return s;

}
public void onclick(View v)
{
    final Button b=(Button)findViewById(R.id.button);
    String cur=b.getText().toString();
    if(cur=="START")
    {
        long t=(sb.getProgress())*1000;
      //  Toast.makeText(MainActivity.this,Long.toString(t), Toast.LENGTH_SHORT).show();
        sb.setVisibility(View.GONE);
        b.setText("STOP");
        timer=new CountDownTimer(t,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                disp.setText(sectostr(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                mp.start();
                reset();
                b.setText("START");
                sb.setVisibility(View.VISIBLE);
            }
        };
        timer.start();

    }
    else
    {
   //     if(timer!=null)
        timer.cancel();
        reset();
        b.setText("START");
        sb.setVisibility(View.VISIBLE);
    }
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sb=findViewById(R.id.sb);
        disp=findViewById(R.id.disp);
        sb.setMax(600);
        reset();
        Button b=findViewById(R.id.button);
        b.setText("START");
        mp=MediaPlayer.create(this,R.raw.ah);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress==0)
                {
                    progress=1;
                    sb.setProgress(1);
                }
                disp.setText(sectostr(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
