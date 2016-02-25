package co.favorie.wearable.et.category;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import co.favorie.wearable.et.R;

/**
 * Created by Yohan on 2016-02-23.
 */
public class opic_settingActivity extends AppCompatActivity {

    private EditText min_opic;
    private EditText sec_opic;
    private EditText prepare_opic;
    private ImageButton opic_setting_button;
    private ImageButton opic_reset_button;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_opic_setting);
        opic_setting_button = (ImageButton)findViewById(R.id.opic_setting_save);
        opic_reset_button = (ImageButton)findViewById(R.id.opic_setting_reset);
        min_opic = (EditText)findViewById(R.id.min_opic);
        sec_opic = (EditText)findViewById(R.id.sec_opic);
        prepare_opic = (EditText)findViewById(R.id.opic_prepare_time_setting);
        min_opic.setText((String.valueOf(Global_Variable.get_gloval_opic_speaking_min())));
        sec_opic.setText((String.valueOf(Global_Variable.get_gloval_opic_speaking_sec())));
        prepare_opic.setText((String.valueOf(Global_Variable.get_gloval_opic_speaking_prepare())));

        opic_setting_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Global_Variable.set_gloval_opic(Integer.parseInt(min_opic.getText().toString()), Integer.parseInt(sec_opic.getText().toString()), Integer.parseInt(prepare_opic.getText().toString()));
                setResult(RESULT_OK, null);
                finish();

            }
        });

        opic_reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "yoyo3");
                Global_Variable.reset_gloval_opic();

                min_opic.setText((String.valueOf(Global_Variable.get_gloval_opic_speaking_min())));
                sec_opic.setText((String.valueOf(Global_Variable.get_gloval_opic_speaking_sec())));
                prepare_opic.setText((String.valueOf(Global_Variable.get_gloval_opic_speaking_prepare())));

            }
        });


    }
}
