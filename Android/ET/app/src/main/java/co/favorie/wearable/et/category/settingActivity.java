package co.favorie.wearable.et.category;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import co.favorie.wearable.et.R;

/**
 * Created by Yohan on 2016-02-01.
 */
public class settingActivity extends AppCompatActivity {

    private EditText part5_min_toeic;
    private EditText part5_sec_toeic;
    private EditText part6_min_toeic;
    private EditText part6_sec_toeic;
    private EditText part7_min_toeic;
    private EditText part7_sec_toeic;
    private ImageButton toeic_setting_button;
    private ImageButton toeic_reset_button;



    protected void onCreate(Bundle savedInstanceState) {
        Log.d("tag", "yoyo5");
        super.onCreate(savedInstanceState);
        Log.d("tag", "yoyo6");
        setContentView(R.layout.layout_toeic_setting);
        Log.d("tag", "yoyo7");


        toeic_setting_button = (ImageButton)findViewById(R.id.toeic_setting_save);
        toeic_reset_button = (ImageButton)findViewById(R.id.toeic_setting_reset);
        part5_min_toeic = (EditText)findViewById(R.id.part5_min_toeic);
        part5_sec_toeic = (EditText)findViewById(R.id.part5_sec_toeic);
        part6_min_toeic = (EditText)findViewById(R.id.part6_min_toeic);
        part6_sec_toeic = (EditText)findViewById(R.id.part6_sec_toeic);
        part7_min_toeic = (EditText)findViewById(R.id.part7_min_toeic);
        part7_sec_toeic = (EditText)findViewById(R.id.part7_sec_toeic);

        part5_min_toeic.setText((String.valueOf(Global_Variable.get_gloval_toeic_part5())));
        part6_min_toeic.setText((String.valueOf(Global_Variable.get_gloval_toeic_part6())));
        part7_min_toeic.setText((String.valueOf(Global_Variable.get_gloval_toeic_part7())));
        part5_sec_toeic.setText((String.valueOf(Global_Variable.get_gloval_toeic_part5_sec())));
        part6_sec_toeic.setText((String.valueOf(Global_Variable.get_gloval_toeic_part6_sec())));
        part7_sec_toeic.setText((String.valueOf(Global_Variable.get_gloval_toeic_part7_sec())));

        Log.d("testing", part5_min_toeic.getText().toString());
        toeic_setting_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Global_Variable.set_gloval_toeic_part5(Integer.parseInt(part5_min_toeic.getText().toString()), Integer.parseInt(part5_sec_toeic.getText().toString()));
                Global_Variable.set_gloval_toeic_part6(Integer.parseInt(part6_min_toeic.getText().toString()), Integer.parseInt(part6_sec_toeic.getText().toString()));
                Global_Variable.set_gloval_toeic_part7(Integer.parseInt(part7_min_toeic.getText().toString()), Integer.parseInt(part7_sec_toeic.getText().toString()));
                Log.d("testing3","int"+Global_Variable.get_global_toeic_part5()+"value");
                setResult(RESULT_OK, null);
                finish();

            }
        });

        toeic_reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "yoyo3");
                Global_Variable.reset_gloval_toeic_part5();
                Global_Variable.reset_gloval_toeic_part6();
                Global_Variable.reset_gloval_toeic_part7();
                part5_min_toeic.setText((String.valueOf(Global_Variable.get_gloval_toeic_part5())));
                part6_min_toeic.setText((String.valueOf(Global_Variable.get_gloval_toeic_part6())));
                part7_min_toeic.setText((String.valueOf(Global_Variable.get_gloval_toeic_part7())));
                part5_sec_toeic.setText((String.valueOf(Global_Variable.get_gloval_toeic_part5_sec())));
                part6_sec_toeic.setText((String.valueOf(Global_Variable.get_gloval_toeic_part6_sec())));
                part7_sec_toeic.setText((String.valueOf(Global_Variable.get_gloval_toeic_part7_sec())));

            }
        });


    }
}
