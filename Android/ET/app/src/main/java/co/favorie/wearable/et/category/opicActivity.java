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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
        opic_num_display.setText((String.valueOf(Global_Variable.get_gloval_opic_num())));
        settingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(opicActivity.this,
                        opic_settingActivity.class);
                startActivityForResult(intent, 1);

                Log.d("testing2","wow");
                opic_prepare_display.setText((String.valueOf(Global_Variable.get_gloval_opic_speaking_prepare()+"sec")));
                opic_time_display.setText((String.valueOf(Global_Variable.get_gloval_opic_speaking_min() + "min" + Global_Variable.get_gloval_opic_speaking_sec() + "sec")));

            }
        });


        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // sendDataToService(jsonArray.toString());

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

