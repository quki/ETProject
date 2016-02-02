package co.favorie.wearable.et.category;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.bluetooth.BluetoothAdapter;
import android.widget.Toast;

import java.io.Serializable;

import co.favorie.wearable.et.MainActivity;
import co.favorie.wearable.et.R;
import co.favorie.wearable.et.action.ConnectAction;
import co.favorie.wearable.et.bluetooth.BluetoothConfig;
import co.favorie.wearable.et.bluetooth.BluetoothConnection;
import co.favorie.wearable.et.service.AccessoryService;

/**
 * Created by Yohan on 2016-02-01.
 */
public class toeicActivity extends AppCompatActivity  implements BluetoothConnection {
    private Button settingButton;
    private Button backButton;
    private EditText lc_text;
    private EditText part5_text;
    private EditText part6_text;
    private EditText part7_text;
    public  AccessoryService mAccessoryService = new AccessoryService();
    private boolean isBound;
    private Button BluetoothButton;

    protected void onCreate(Bundle savedInstanceState) {
        Log.d("tag", "yoyo5");
        super.onCreate(savedInstanceState);
        Log.d("tag", "yoyo6");
        setContentView(R.layout.layout_toeic);
        initBluetoothConnection();
        bindAccessoryService();
        Log.d("tag", "yoyo7");
        settingButton= (Button)findViewById(R.id.submit_toeic);
        BluetoothButton= (Button)findViewById(R.id.bluetooth);
        backButton=(Button)findViewById(R.id.back_toeic);
        lc_text = (EditText)findViewById(R.id.toeic_lc);
        part5_text = (EditText)findViewById(R.id.toeic_part5);
        part6_text = (EditText)findViewById(R.id.toeic_part6);
        part7_text = (EditText)findViewById(R.id.toeic_part7);

       // if(getIntent().hasExtra("wow")) {

      //  }



        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data =  lc_text.getText().toString();
                sendDataToService(data);
            }
        });


        BluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag","yoyo3");
                startConnection();

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("tag", "yoyo4");

                Intent intent = new Intent(toeicActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });


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
