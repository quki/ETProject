package co.favorie.wearable.et;

import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import co.favorie.wearable.et.action.ConnectAction;
import co.favorie.wearable.et.bluetooth.BluetoothConfig;
import co.favorie.wearable.et.bluetooth.BluetoothConnection;
import co.favorie.wearable.et.category.opicActivity;
import co.favorie.wearable.et.category.settingActivity;
import co.favorie.wearable.et.category.toeflActivity;
import co.favorie.wearable.et.category.toeicActivity;
import co.favorie.wearable.et.service.AccessoryService;

public class MainActivity extends AppCompatActivity {


    private Button toeicBtn,toeflBtn,opicBtn,customBtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toeicBtn = (Button)findViewById(R.id.toeic);
        toeflBtn = (Button)findViewById(R.id.toefl);
        opicBtn = (Button)findViewById(R.id.opic);
        customBtn = (Button)findViewById(R.id.customs);

        toeicBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("tag","yoyo4");

                Intent intent = new Intent(MainActivity.this,
                        toeicActivity.class);
                Log.d("tag", "yoyo first finished");


                startActivity(intent);
            }
        });


        opicBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("tag","yoyo4");

                Intent intent = new Intent(MainActivity.this,
                        opicActivity.class);
                Log.d("tag", "yoyo first finished");


                startActivity(intent);
            }
        });

        toeflBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText( MainActivity.this , "Comming Soon",Toast.LENGTH_SHORT ).show();

            }
        });



        customBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText( MainActivity.this , "Comming Soon",Toast.LENGTH_SHORT ).show();


            }
        });
    }



}
