package co.favorie.wearable.et.category;

import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.favorie.wearable.et.R;
import co.favorie.wearable.et.action.ConnectAction;
import co.favorie.wearable.et.bluetooth.BluetoothConfig;
import co.favorie.wearable.et.bluetooth.BluetoothConnection;
import co.favorie.wearable.et.service.AccessoryService;

/**
 * Created by Yohan on 2016-02-01.
 */


public class toeicActivity extends AppCompatActivity  implements BluetoothConnection {
    private ImageButton settingButton;
   // private Button backButton;
    private ImageButton startButton;
    private TextView part5_text;
    private TextView part6_text;
    private TextView part7_text;
    private Switch bluetooth_switch;
    public  AccessoryService mAccessoryService = new AccessoryService();
    private boolean isBound;
    private int part5_selector=0;
    private int part6_selector=0;
    private int part7_selector=0;
    private LinearLayout layout_part5;
    private LinearLayout layout_part6;
    private LinearLayout layout_part7;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_toeic);
        initBluetoothConnection();
        bindAccessoryService();
        settingButton= (ImageButton)findViewById(R.id.toeic_setting);
        part5_text = (TextView)findViewById(R.id.toeic_part5);
        part6_text = (TextView)findViewById(R.id.toeic_part6);
        part7_text = (TextView)findViewById(R.id.toeic_part7);
        bluetooth_switch = (Switch)findViewById(R.id.bluetooth_toeic);
        startButton = (ImageButton)findViewById(R.id.toeic_start_please);
        layout_part5 = (LinearLayout)findViewById(R.id.layout_part5);
        layout_part6 = (LinearLayout)findViewById(R.id.layout_part6);
        layout_part7 = (LinearLayout)findViewById(R.id.layout_part7);

        part5_text.setText((String.valueOf(Global_Variable.get_gloval_toeic_part5()+"min")));
        part6_text.setText((String.valueOf(Global_Variable.get_gloval_toeic_part6()+"min")));
        part7_text.setText((String.valueOf(Global_Variable.get_gloval_toeic_part7() + "min")));

        JSONObject obj1 = new JSONObject();
        JSONObject obj2 = new JSONObject();
        JSONObject obj3 = new JSONObject();
        final JSONArray jsonArray = new JSONArray();
        try {
            obj1.put("title","TOEIC PART5");
            obj1.put("time", String.valueOf(Global_Variable.get_gloval_toeic_part5()*60*100));
            jsonArray.put(obj1);
            obj2.put("title","TOEIC PART6");
            obj2.put("time",String.valueOf(Global_Variable.get_gloval_toeic_part6()*60*100));
            jsonArray.put(obj2);
            obj3.put("title","TOEIC PART7");
            obj3.put("time",String.valueOf(Global_Variable.get_gloval_toeic_part7()*60*100));
            jsonArray.put(obj3);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        layout_part5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                part5_selector++;
                if (part5_selector % 2 == 1) {
                    selectOperation(layout_part5);
                    Global_Variable.set_global_toeic_part5();

                } else {
                    unselectedOperation(layout_part5);
                    Global_Variable.set_global_toeic_part5();
                }
            }
        });
        layout_part6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                part6_selector++;
                if(part6_selector%2==1) {
                    selectOperation(layout_part6);
                    Global_Variable.set_global_toeic_part6();
                }else{
                    unselectedOperation(layout_part6);
                    Global_Variable.set_global_toeic_part6();
                }
            }
        });
        layout_part7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                part7_selector++;
                if(part7_selector%2==1) {
                    selectOperation(layout_part7);
                    Global_Variable.set_global_toeic_part7();
                }else{
                    unselectedOperation(layout_part7);
                    Global_Variable.set_global_toeic_part7();
                }

            }
        });

        bluetooth_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                startConnection();

            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(toeicActivity.this,
                        settingActivity.class);
                startActivity(intent);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                sendDataToService(jsonArray.toString());

                Intent intent = new Intent(toeicActivity.this,
                        toeic_display_activity.class);
                startActivity(intent);

            }
        });

    }

    public void selectOperation(LinearLayout selectedButton) {
        selectedButton.setBackgroundColor(Color.parseColor("#B2E2F0"));
    }
    public void unselectedOperation(LinearLayout selectedButton){
        selectedButton.setBackgroundColor(Color.parseColor("#ddf2fa"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindAccessoryService();
    }

    @Override
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
            Log.d("blue", "startConnection now");
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
