package com.xiaoyi.tencent;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class WelcomActivity extends Activity {
	private Handler mHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcom);
		mHandler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				if(msg.what==123){
					Intent mIntent = new Intent(WelcomActivity.this,MainActivity.class);
					startActivity(mIntent);
					WelcomActivity.this.finish();
				}
			};
		};
			
		new Thread(){
			public void run() {
				mHandler.sendEmptyMessageDelayed(123, 2000);
			};
		}.start();
	}

	
}
