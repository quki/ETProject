package co.favorie.wearable.et.category;

import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.favorie.wearable.et.MainActivity;
import co.favorie.wearable.et.R;
import co.favorie.wearable.et.action.ConnectAction;
import co.favorie.wearable.et.bluetooth.BluetoothConfig;
import co.favorie.wearable.et.service.AccessoryService;

/**
 * Created by Yohan on 2016-02-01.
 */
public class opicActivity extends AppCompatActivity {
    private ImageButton settingButton;
    private ImageButton startButton;
    private TextView opic_time_display;
    private TextView opic_prepare_display;
    private EditText opic_num_display;
    public AccessoryService mAccessoryService = new AccessoryService();
    private boolean isBound;
    private Button BluetoothButton;
    private Switch bluetooth_switch;
    final JSONArray jsonArray = new JSONArray();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_opic);
        Log.d("testing2", "wow1");
        initBluetoothConnection();
        bindAccessoryService();
        settingButton= (ImageButton)findViewById(R.id.opic_setting);
       // BluetoothButton= (ImageButton)findViewById(R.id.bluetooth_opic);
       // backButton=(Button)findViewById(R.id.back_opic);
        //opic_text = (EditText)findViewById(R.id.op);
        opic_time_display = (TextView)findViewById(R.id.opic_time_display);
        opic_prepare_display = (TextView)findViewById(R.id.opic_prepare_display);
        opic_num_display = (EditText)findViewById(R.id.practice_num_opic);
        startButton = (ImageButton) findViewById(R.id.opic_start_please);
        bluetooth_switch = (Switch)findViewById(R.id.bluetooth_opic);
        opic_num_display.setText((String.valueOf(Global_Variable.get_gloval_opic_num())));
        settingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(opicActivity.this,
                        opic_settingActivity.class);
                startActivityForResult(intent, 1);

                JSONObject obj[];

                JSONObject obj1 = new JSONObject();
                JSONObject obj2 = new JSONObject();
                JSONObject obj3 = new JSONObject();
                JSONObject obj4 = new JSONObject();
                JSONObject obj5 = new JSONObject();
                JSONObject obj6 = new JSONObject();
                JSONObject obj7 = new JSONObject();
                JSONObject obj8 = new JSONObject();
                JSONObject obj9 = new JSONObject();
                JSONObject obj10 = new JSONObject();
                JSONObject obj11 = new JSONObject();
                JSONObject obj12 = new JSONObject();
                JSONObject obj13 = new JSONObject();
                JSONObject obj14 = new JSONObject();
                JSONObject obj15 = new JSONObject();


                try {
                    obj1.put("title","Problem 1");
                    obj1.put("time", String.valueOf((Global_Variable.get_gloval_opic_speaking_prepare()+Global_Variable.get_gloval_opic_speaking_min()*60 +Global_Variable.get_gloval_opic_speaking_sec())*1000));
                    jsonArray.put(obj1);
                    obj2.put("title","Problem 2");
                    obj2.put("time",String.valueOf((Global_Variable.get_gloval_opic_speaking_prepare()+Global_Variable.get_gloval_opic_speaking_min()*60 +Global_Variable.get_gloval_opic_speaking_sec())*1000));
                    jsonArray.put(obj2);
                    obj3.put("title","Problem 3");
                    obj3.put("time",String.valueOf((Global_Variable.get_gloval_opic_speaking_prepare()+Global_Variable.get_gloval_opic_speaking_min()*60+Global_Variable.get_gloval_opic_speaking_sec())*1000));
                    jsonArray.put(obj3);
                    obj4.put("title","Problem 4");
                    obj4.put("time", String.valueOf((Global_Variable.get_gloval_opic_speaking_prepare()+Global_Variable.get_gloval_opic_speaking_min()*60 +Global_Variable.get_gloval_opic_speaking_sec())*1000));
                    jsonArray.put(obj4);
                    obj5.put("title","Problem 5");
                    obj5.put("time",String.valueOf((Global_Variable.get_gloval_opic_speaking_prepare()+Global_Variable.get_gloval_opic_speaking_min()*60 +Global_Variable.get_gloval_opic_speaking_sec())*1000));
                    jsonArray.put(obj5);
                    obj6.put("title","Problem 6");
                    obj6.put("time",String.valueOf((Global_Variable.get_gloval_opic_speaking_prepare()+Global_Variable.get_gloval_opic_speaking_min()*60+Global_Variable.get_gloval_opic_speaking_sec())*1000));
                    jsonArray.put(obj6);
                    obj7.put("title","Problem 7");
                    obj7.put("time", String.valueOf((Global_Variable.get_gloval_opic_speaking_prepare()+Global_Variable.get_gloval_opic_speaking_min()*60 +Global_Variable.get_gloval_opic_speaking_sec())*1000));
                    jsonArray.put(obj7);
                    obj8.put("title","Problem 8");
                    obj8.put("time",String.valueOf((Global_Variable.get_gloval_opic_speaking_prepare()+Global_Variable.get_gloval_opic_speaking_min()*60 +Global_Variable.get_gloval_opic_speaking_sec())*1000));
                    jsonArray.put(obj8);
                    obj9.put("title","Problem 9");
                    obj9.put("time",String.valueOf((Global_Variable.get_gloval_opic_speaking_prepare()+Global_Variable.get_gloval_opic_speaking_min()*60+Global_Variable.get_gloval_opic_speaking_sec())*1000));
                    jsonArray.put(obj9);
                    obj10.put("title","Problem 10");
                    obj10.put("time", String.valueOf((Global_Variable.get_gloval_opic_speaking_prepare()+Global_Variable.get_gloval_opic_speaking_min()*60 +Global_Variable.get_gloval_opic_speaking_sec())*1000));
                    jsonArray.put(obj10);
                    obj11.put("title","Problem 11");
                    obj11.put("time",String.valueOf((Global_Variable.get_gloval_opic_speaking_prepare()+Global_Variable.get_gloval_opic_speaking_min()*60+Global_Variable.get_gloval_opic_speaking_sec())*1000));
                    jsonArray.put(obj11);
                    obj12.put("title","Problem 12");
                    obj12.put("time",String.valueOf((Global_Variable.get_gloval_opic_speaking_prepare()+Global_Variable.get_gloval_opic_speaking_min()*60+Global_Variable.get_gloval_opic_speaking_sec())*1000));
                    jsonArray.put(obj12);
                    obj13.put("title","Problem 13");
                    obj13.put("time", String.valueOf((Global_Variable.get_gloval_opic_speaking_prepare()+Global_Variable.get_gloval_opic_speaking_min()*60 +Global_Variable.get_gloval_opic_speaking_sec())*1000));
                    jsonArray.put(obj13);
                    obj14.put("title","Problem 14");
                    obj14.put("time",String.valueOf((Global_Variable.get_gloval_opic_speaking_prepare()+Global_Variable.get_gloval_opic_speaking_min()*60 +Global_Variable.get_gloval_opic_speaking_sec())*1000));
                    jsonArray.put(obj14);
                    obj15.put("title","Problem 15");
                    obj15.put("time",String.valueOf((Global_Variable.get_gloval_opic_speaking_prepare()+Global_Variable.get_gloval_opic_speaking_min()*60 +Global_Variable.get_gloval_opic_speaking_sec())*1000));
                    jsonArray.put(obj15);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("testing2", "wow");
                opic_prepare_display.setText((String.valueOf(Global_Variable.get_gloval_opic_speaking_prepare() + "sec")));
                opic_time_display.setText((String.valueOf(Global_Variable.get_gloval_opic_speaking_min() + "min" + Global_Variable.get_gloval_opic_speaking_sec() + "sec")));

            }
        });


        bluetooth_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    initBluetoothConnection();
                    bindAccessoryService();
                    //isconnect = true;
                    startConnection();
                } else {
                   // isconnect = false;
                    //unbindAccessoryService();
                }

            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                sendDataToService(jsonArray.toString());

                Intent intent = new Intent(opicActivity.this,
                        opic_display_activity.class);
                startActivity(intent);

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindAccessoryService();
    }


    public void initBluetoothConnection() {
        BluetoothAdapter mBTAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBTAdapter.isEnabled()) {
            Intent btIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(btIntent, BluetoothConfig.REQUEST_ENABLE_BT);
        }
    }

    /**
     *
     */
    private void bindAccessoryService() {
        Intent serviceIntent = new Intent(this, AccessoryService.class);
        bindService(serviceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    /**
     *
     */
    private void unbindAccessoryService() {
        unbindService(mServiceConnection);
    }

    /**
     *
     */
    private void startConnection(){
        if(isBound && mAccessoryService != null){
            mAccessoryService.findPeers();
        }
    }

    /**
     * AccessoryService으로 알람시간간격 전달
     * @param mData
     */
    private void sendDataToService(String mData) {

        if (isBound && mAccessoryService != null) {
            mAccessoryService.sendData(mData);
        } else {
            Toast.makeText(getApplicationContext(), "기어와 연결을 확인하세요", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BluetoothConfig.REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "BLUETOOTH ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "PLEASE BLUETOOTH ON", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAccessoryService = ((AccessoryService.MyBinder) service).getService();
            mAccessoryService.registerAction(getAction());
            isBound = true;
            Toast.makeText(getApplicationContext(),"onSERVICECONNECTED",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAccessoryService = null;
            isBound = false;
        }
    };

    AccessoryService getmAccessoryService(){

        return this.mAccessoryService;

    }



    private ConnectAction getAction(){
        return new ConnectAction() {
            @Override
            public void onSuccessConnection() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        settingButton.setEnabled(true);
                    }
                });
            }

            @Override
            public void onFailConnection() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bluetooth_switch.setChecked(false);
                    }
                });
            }

            @Override
            public void onSuccessTransfer(final String data) {
                //runOnUiThread(new Runnable() {
                //  @Override
                //    public void run() {
                //       statusTxtView.setText(data);
                //   }
                //  });
            }
        };
    }

}

