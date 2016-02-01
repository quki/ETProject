package co.favorie.wearable.et;

import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import co.favorie.wearable.et.action.ConnectAction;
import co.favorie.wearable.et.bluetooth.BluetoothConfig;
import co.favorie.wearable.et.bluetooth.BluetoothConnection;
import co.favorie.wearable.et.category.opicActivity;
import co.favorie.wearable.et.category.toeflActivity;
import co.favorie.wearable.et.category.toeicActivity;
import co.favorie.wearable.et.service.AccessoryService;

public class MainActivity extends AppCompatActivity implements BluetoothConnection{

    public  AccessoryService mAccessoryService = new AccessoryService();
    private boolean isBound;
    private Button requestBtn, sendBtn;
    private Button toeicBtn,toeflBtn,opicBtn;
    private TextView statusTxtView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBluetoothConnection();
        bindAccessoryService();
        Log.d("tag", "yoyo1");
        toeicBtn = (Button)findViewById(R.id.toeic);
        toeflBtn = (Button)findViewById(R.id.toefl);
        opicBtn = (Button)findViewById(R.id.opic);
        requestBtn = (Button) findViewById(R.id.requestBtn);
        sendBtn = (Button) findViewById(R.id.sendBtn);
        statusTxtView = (TextView) findViewById(R.id.statusTxtView);
        sendBtn.setEnabled(false);
        Log.d("tag", "yoyo2");
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "yoyo3");
                startConnection();

            }
        });
        sendBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String data =  "Hello GearS2";
                sendDataToService(data);
            }
        });

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
                Log.d("tag","yoyo4");

                Intent intent = new Intent(MainActivity.this,
                        toeflActivity.class);
                Log.d("tag", "yoyo first finished");


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
                        sendBtn.setEnabled(true);
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        statusTxtView.setText(data);
                    }
                });
            }
        };
    }
}
