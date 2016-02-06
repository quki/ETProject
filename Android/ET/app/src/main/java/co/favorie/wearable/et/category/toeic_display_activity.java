package co.favorie.wearable.et.category;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import co.favorie.wearable.et.R;

/**
 * Created by Yohan on 2016-02-02.
 */
public class toeic_display_activity extends AppCompatActivity {

    private Button toeic_part5;
    private Button toeic_part6;
    private Button toeic_part7;
    private TextView minuite_text;


    protected void onCreate(Bundle savedInstanceState) {



        Log.d("tag", "yoyo5");
        super.onCreate(savedInstanceState);
        Log.d("tag", "yoyo6");
        setContentView(R.layout.layout_toeic_start);
        Log.d("tag", "yoyo7");


        toeic_part5  = (Button)findViewById(R.id.toeic_display_part5);
        toeic_part6= (Button)findViewById(R.id.toeic_display_part6);
        toeic_part7  = (Button)findViewById(R.id.toeic_display_part7);
        minuite_text = (TextView)findViewById(R.id.minuite_display_toeic);


        minuite_text.setText((String.valueOf(Global_Variable.get_gloval_toeic_part5())));
        //toeic_part6.setText((String.valueOf(Global_Variable.get_gloval_toeic_part6())));
        //toeic_part7.setText((String.valueOf(Global_Variable.get_gloval_toeic_part7())));

        if(Global_Variable.get_global_toeic_part5()== true) {
            toeic_part5.setBackgroundColor(Color.parseColor("#B2E2F0"));
        }
        if(Global_Variable.get_global_toeic_part6()== true) {
            toeic_part6.setBackgroundColor(Color.parseColor("#B2E2F0"));
        }
        if(Global_Variable.get_global_toeic_part7()== true) {
            toeic_part7.setBackgroundColor(Color.parseColor("#B2E2F0"));
        }




    }










}
