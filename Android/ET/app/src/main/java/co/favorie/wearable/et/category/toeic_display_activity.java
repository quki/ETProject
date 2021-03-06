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

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import co.favorie.wearable.et.R;

/**
 *
 *
 * Created by Yohan on 2016-02-02.
 */



@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")

public class toeic_display_activity extends AppCompatActivity {

    private Button toeic_part5;
    private Button toeic_part6;
    private Button toeic_part7;
    private TextView time_text;
    private int timeset_part5 = Integer.parseInt((String.valueOf(Global_Variable.get_gloval_toeic_part5())))*60 + Integer.parseInt(String.valueOf(Global_Variable.get_gloval_toeic_part5_sec()));
    private int timeset_part6 = Integer.parseInt((String.valueOf(Global_Variable.get_gloval_toeic_part6())))*60 + Integer.parseInt(String.valueOf(Global_Variable.get_gloval_toeic_part6_sec()));
    private int timeset_part7 = Integer.parseInt((String.valueOf(Global_Variable.get_gloval_toeic_part7())))*60 + Integer.parseInt(String.valueOf(Global_Variable.get_gloval_toeic_part7_sec()));
    private int total_time =0;
    protected void onCreate(Bundle savedInstanceState) {



        Log.d("tag", "yoyo5");
        super.onCreate(savedInstanceState);
        Log.d("tag", "yoyo6");
        setContentView(R.layout.layout_toeic_start);
        Log.d("tag", "yoyo7");


        toeic_part5  = (Button)findViewById(R.id.toeic_display_part5);
        toeic_part6= (Button)findViewById(R.id.toeic_display_part6);
        toeic_part7  = (Button)findViewById(R.id.toeic_display_part7);
        time_text = (TextView)findViewById(R.id.time_display_toeic);



        if(Global_Variable.get_global_toeic_part5()== true) {
            toeic_part5.setBackgroundColor(Color.parseColor("#B2E2F0"));
            total_time +=timeset_part5;

        }
        if(Global_Variable.get_global_toeic_part6()== true) {
            toeic_part6.setBackgroundColor(Color.parseColor("#B2E2F0"));
            total_time+= timeset_part6;

        }
        if(Global_Variable.get_global_toeic_part7()== true) {
            toeic_part7.setBackgroundColor(Color.parseColor("#B2E2F0"));
            total_time+=timeset_part7;
        }


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
