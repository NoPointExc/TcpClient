package com.example.tcpclient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	// For debug
	private final String TAG = "MainActivity";

	// About the ui controls
	private Button btn_connect = null;
	private TextView edit_receive = null;
	private EditText edit_send = null;
	private EditText edit_T = null;
	private Button btn_send = null;
	// private boolean isConnected = false;

	// About the socket
	Handler handler;
	ClientThread clientThread = null;
	private String ip = "192.168.2.1";
	private String port="8000";

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		edit_receive = (TextView) this.findViewById(R.id.edit_receive);
		edit_send = (EditText) this.findViewById(R.id.edit_send);
		edit_T = (EditText) this.findViewById(R.id.edit_T);
		btn_connect = (Button) this.findViewById(R.id.btn_connect);
		btn_send = (Button) this.findViewById(R.id.btn_send);

		init();

		// Click here to connect
		btn_connect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				

				Log.d(TAG, ip + port);

				clientThread = new ClientThread(handler, ip, port);
				new Thread(clientThread).start();
				Log.d(TAG, "clientThread is start!!");
				if (clientThread.isConnected()) {
					btn_connect.setText("connected"+ip+":"+port);
				} else {
					btn_connect.setText("try again");
				}
			}
		});

		// Click here to Send Msg to Server
		btn_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				try {
					Message msg = new Message();
					msg.what = 0x852;
					msg.obj = edit_send.getText().toString();
					clientThread.sendHandler.sendMessage(msg);
					edit_send.setText("");
				} catch (Exception e) {
					Log.d(TAG, e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}

	private void init() {
		
		

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x123) {
					edit_receive.setText(msg.obj.toString());
				}
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onDestory() {
		return true;

	}

	// ****************************
	// button listener
	public void L1Onclick(View v) {
		if (clientThread != null && clientThread.isConnected()) {
			try {
				Message msg = new Message();
				msg.what = 0x852;
				msg.obj = "L1";
				clientThread.sendHandler.sendMessage(msg);
			} catch (Exception e) {
				Log.d(TAG, e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public void L2Onclick(View v) {
		if (clientThread != null && clientThread.isConnected()) {
			try {
				Message msg = new Message();
				msg.what = 0x852;
				msg.obj = "L2";
				clientThread.sendHandler.sendMessage(msg);
			} catch (Exception e) {
				Log.d(TAG, e.getMessage());
				e.printStackTrace();
			}
		}

	}

	public void L3Onclick(View v) {
		if (clientThread != null && clientThread.isConnected()) {
			try {
				Message msg = new Message();
				msg.what = 0x852;
				msg.obj = "L3";
				clientThread.sendHandler.sendMessage(msg);
			} catch (Exception e) {
				Log.d(TAG, e.getMessage());
				e.printStackTrace();
			}
		}

	}

	public void L4Onclick(View v) {
		if (clientThread != null && clientThread.isConnected()) {
			try {
				Message msg = new Message();
				msg.what = 0x852;
				msg.obj = "L4";
				clientThread.sendHandler.sendMessage(msg);
			} catch (Exception e) {
				Log.d(TAG, e.getMessage());
				e.printStackTrace();
			}
		}

	}

	public void TOnclick(View v) {
		if (clientThread != null && clientThread.isConnected()) {
			try {
				Message msg = new Message();
				msg.what = 0x852;				
				msg.obj = "T"+edit_T.getText().toString();
				clientThread.sendHandler.sendMessage(msg);
			} catch (Exception e) {
				Log.d(TAG, e.getMessage());
				e.printStackTrace();
			}
		}

	}

	public void checkOnclick(View v) {
		if(clientThread!=null&&clientThread.isConnected()){
			Toast.makeText(this, "connected"+ip+":"+port, 1000).show();
		}else{
		Toast.makeText(this, "not connected", 1000).show();}
	}

}
