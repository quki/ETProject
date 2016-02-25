package co.favorie.wearable.et.category;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import co.favorie.wearable.et.R;

/**
 * Created by Yohan on 2016-02-23.
 */
public class opic_display_activity extends AppCompatActivity {


    private TextView time_text;
    private int total_time;
    private int each_time = Integer.parseInt((String.valueOf(Global_Variable.get_gloval_opic_speaking_min())))*60 + Integer.parseInt(String.valueOf(Global_Variable.get_gloval_opic_speaking_sec())+Integer.parseInt(String.valueOf(Global_Variable.get_gloval_opic_speaking_prepare())));
    private int num_of_problem = Integer.parseInt((String.valueOf(Global_Variable.get_gloval_opic_num())));


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_opic_start);
        time_text = (TextView)findViewById(R.id.time_display_opic);

        total_time +=each_time*num_of_problem;

        final CounterClass timer = new CounterClass(total_time*1000, 1000);
        timer.start();

    }


    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

            // TODO Auto-generated constructor stub
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub

            long millis = millisUntilFinished;
            String hms = String.format("%02d: %02d: %02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

            String minuites = String.format("%d",
                    TimeUnit.MILLISECONDS.toMinutes(millis));

            String seconds = String.format("%d",
                    TimeUnit.MILLISECONDS.toSeconds(millis) % 60);

            // textViewTime.setText(hms);

            time_text.setText(hms);
            //second_text.setText(seconds);
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(3000);

        }

    }
}


