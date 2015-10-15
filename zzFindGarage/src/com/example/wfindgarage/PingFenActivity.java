package com.example.wfindgarage;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

public class PingFenActivity extends BaseActivity {


	private ImageView mIvtt1;
	private Button mBtn1;
	private ImageView mIvpf4;
	private ImageView mIvpf3;
	private ImageView mIvpf2;
	private ImageView mIvpf1;
    private int ff=1;
	private RatingBar mSbpf1;
	private RatingBar mSbpf2;
	private RatingBar mSbpf3;
	private RatingBar mSbpf4;

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pingfen);
		
		mIvtt1 =(ImageView)this.findViewById(R.id.mIvtt1);
		mIvtt1.setOnClickListener(listener);
		mBtn1 =(Button)this.findViewById(R.id.mBtn1);
		mBtn1.setOnClickListener(listener);
		mIvpf3 =(ImageView)this.findViewById(R.id.mIvpf3);
		mIvpf2 =(ImageView)this.findViewById(R.id.mIvpf2);
		mIvpf1 =(ImageView)this.findViewById(R.id.mIvpf1);
		mSbpf1 =(RatingBar)this.findViewById(R.id.mSbpf1);
		mSbpf2 =(RatingBar)this.findViewById(R.id.mSbpf2);
		mSbpf3 =(RatingBar)this.findViewById(R.id.mSbpf3);
		mSbpf4 =(RatingBar)this.findViewById(R.id.mSbpf4);
		mIvpf1.setOnClickListener(listener);
		mIvpf2.setOnClickListener(listener);
		mIvpf3.setOnClickListener(listener);
		
	}
	
	
	@SuppressLint("ResourceAsColor")
	OnClickListener listener =new  OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mIvpf3:
				ff=-1;
				     mIvpf3.setImageResource(R.drawable.bba3);
				     mIvpf2.setImageResource(R.drawable.ok2e);
				     mIvpf1.setImageResource(R.drawable.bba1e);
				break;
			case R.id.mIvpf2:
				ff=0;
				     mIvpf3.setImageResource(R.drawable.bba3e);
				     mIvpf2.setImageResource(R.drawable.ok2);
				     mIvpf1.setImageResource(R.drawable.bba1e);
				break;
			case R.id.mIvpf1:
				ff=1;
				     mIvpf3.setImageResource(R.drawable.bba3e);
				     mIvpf2.setImageResource(R.drawable.ok2e);
				     mIvpf1.setImageResource(R.drawable.bba1);
				break;
			case R.id.mIvtt1:
				finish();
				break;
			case R.id.mBtn1:
				SharedPreferences mySharedPreferences= getSharedPreferences("USER", Activity.MODE_PRIVATE); 
				SharedPreferences.Editor editor = mySharedPreferences.edit(); 
				editor.putString("fen", ff+""); 
				editor.putString("Coms1", mSbpf1.getRating()+"");
				editor.putString("Coms2", mSbpf2.getRating()+"");
				editor.putString("Coms3", mSbpf3.getRating()+"");
				editor.putString("Coms4", mSbpf4.getRating()+"");
				editor.commit(); 

				finish();
				break;

			default:
				break;
			}
		}
	};
	
	
}