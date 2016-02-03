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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import co.favorie.wearable.et.MainActivity;
import co.favorie.wearable.et.R;
import co.favorie.wearable.et.action.ConnectAction;
import co.favorie.wearable.et.bluetooth.BluetoothConfig;
import co.favorie.wearable.et.service.AccessoryService;

/**
 * Created by Yohan on 2016-02-01.
 */
public class toeflActivity extends AppCompatActivity {
    private ImageButton settingButton;
    private ImageButton startButton;
    private Button toefl_reading;
    private int reading_selector,speaking_selector,writing_selector,total_selector,independent_selector =0;
    private Button toefl_writing;
    private Button toefl_listening;
    private Button toefl_speaking;
    private Button independent,total;
    private Switch bluetooth_toefl;
    public AccessoryService mAccessoryService = new AccessoryService();
    private boolean isBound;

    protected void onCreate(Bundle savedInstanceState) {
        Log.d("tag", "yoyo5");
        super.onCreate(savedInstanceState);
        Log.d("tag", "yoyo6");
        setContentView(R.layout.layout_toefl);
        initBluetoothConnection();
        bindAccessoryService();
        Log.d("tag", "yoyo7");
        settingButton= (ImageButton)findViewById(R.id.toefl_setting);
        toefl_reading = (Button)findViewById(R.id.toefl_reading);
        toefl_writing= (Button)findViewById(R.id.toefl_writing);
        toefl_speaking = (Button)findViewById(R.id.toefl_speaking);
        bluetooth_toefl = (Switch)findViewById(R.id.bluetooth_toefl);
        startButton = (ImageButton)findViewById(R.id.toefl_start);
        independent = (Button)findViewById(R.id.toefl_indipendent);
        total = (Button)findViewById(R.id.toefl_total);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data =  toefl_reading.getText().toString();
                sendDataToService(data);
            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("tag","yoyo4");

                Intent intent = new Intent(toeflActivity.this,
                        settingActivity.class);
                Log.d("tag", "yoyo first finished");


                startActivity(intent);
            }
        });

        bluetooth_toefl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "yoyo3");
                startConnection();

            }
        });


        toefl_reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "reading");

                reading_selector++;
                if (reading_selector % 2 == 1) {
                    selectOperation(toefl_reading);
                } else {
                    unselectedOperation(toefl_reading);
                }
            }
        });
        toefl_speaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag","part6");
                speaking_selector++;
                if(speaking_selector%2==1) {
                    selectOperation(toefl_speaking);
                }else{
                    unselectedOperation(toefl_speaking);
                }
            }
        });
        toefl_writing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag","part7");
                writing_selector++;
                if(writing_selector%2==1) {
                    selectOperation(toefl_writing);
                }else{
                    unselectedOperation(toefl_writing);
                }

            }
        });
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "part7");
                total_selector++;
                if (total_selector % 2 == 1) {
                    selectOperation(total);
                } else {
                    unselectedOperation(total);
                }

            }
        });
        independent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "part7");
                independent_selector++;
                if (independent_selector % 2 == 1) {
                    selectOperation(independent);
                } else {
                    unselectedOperation(independent);
                }

            }
        });


    }

    public void selectOperation(Button selectedButton) {
        selectedButton.setBackgroundColor(Color.parseColor("#B2E2F0"));

    }
    public void unselectedOperation(Button selectedButton){
        selectedButton.setBackgroundColor(Color.parseColor("#ddf2fa"));
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
                        Toast.makeText(getApplicationContext(),"FAIL TO SERVICE CONNECTION",Toast.LENGTH_SHORT).show();
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

