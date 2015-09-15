package com.example.wfindgarage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TiGongCheFangZiLiaoActivity extends Activity {

	private ImageView mIvtt1;
	private LinearLayout mWhatbad;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tigongchefangziliao);
		mIvtt1 =(ImageView)this.findViewById(R.id.mIvtt1);
		mIvtt1.setOnClickListener(listener);
		mWhatbad=(LinearLayout)this.findViewById(R.id.mWhatbad);
		mWhatbad.setOnClickListener(listener);

	
	}

	
	OnClickListener listener =new  OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mIvtt1:
				finish();
				break;
			case R.id.mWhatbad:
				startActivity(new Intent(getApplicationContext(), PingFenActivity.class));
				break;

			default:
				break;
			}
		}
	};
private Handler handler =new Handler();
	
}
