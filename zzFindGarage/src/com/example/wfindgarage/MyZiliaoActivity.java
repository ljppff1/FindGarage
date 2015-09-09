package com.example.wfindgarage;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.fragment.Fragment1a;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MyZiliaoActivity extends Activity {

	private ImageView mIvtt1;

	private RelativeLayout mRlmz1;

	private RelativeLayout mRlmz1a;

	private Button mBtmz1;

	private TextView mTvmz1;

	private String UserID;

	private String LoginName;
	
	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myziliao);
		mIvtt1 =(ImageView)this.findViewById(R.id.mIvtt1);
		mIvtt1.setOnClickListener(listener);
		mRlmz1 =(RelativeLayout)this.findViewById(R.id.mRlmz1);
		
		mRlmz1a =(RelativeLayout)this.findViewById(R.id.mRlmz1a);
		mBtmz1 =(Button)this.findViewById(R.id.mBtmz1);
		mTvmz1 =(TextView)this.findViewById(R.id.mTvmz1);
		
		SharedPreferences sharedPreferences= getSharedPreferences("USER", 
				Activity.MODE_PRIVATE); 
		 UserID =sharedPreferences.getString("UserID", ""); 
		 LoginName =sharedPreferences.getString("LoginName", ""); 
	     if(!TextUtils.isEmpty(UserID)){
	    	 mRlmz1a.setVisibility(View.VISIBLE);
	    	 mRlmz1.setVisibility(View.GONE);
	    	 mTvmz1.setText(LoginName);
	     }else{
	    	 mRlmz1.setVisibility(View.VISIBLE);
	    	 mRlmz1a.setVisibility(View.GONE);
	     }
		 
		mRlmz1.setOnClickListener(listener);
		mBtmz1.setOnClickListener(listener);
	}
	

	
	@Override
	protected void onResume() {
		SharedPreferences sharedPreferences= getSharedPreferences("USER", 
				Activity.MODE_PRIVATE); 
		 UserID =sharedPreferences.getString("UserID", ""); 
		 LoginName =sharedPreferences.getString("LoginName", ""); 
	     if(!TextUtils.isEmpty(UserID)){
	    	 mRlmz1a.setVisibility(View.VISIBLE);
	    	 mRlmz1.setVisibility(View.GONE);
	    	 mTvmz1.setText(LoginName);
	     }else{
	    	 mRlmz1.setVisibility(View.VISIBLE);
	    	 mRlmz1a.setVisibility(View.GONE);
	     }
		super.onResume();
	}



	OnClickListener listener =new  OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mIvtt1:
				finish();
				break;
			case R.id.mRlmz1:
				startActivity(new Intent(getApplicationContext(), huiyuandengluActivity.class));
				break;
			case R.id.mBtmz1:
				SharedPreferences mySharedPreferences= getApplicationContext().getSharedPreferences("USER", Activity.MODE_PRIVATE); 
				SharedPreferences.Editor editor = mySharedPreferences.edit(); 
				editor.putString("UserID", ""); 
				editor.putString("LoginName",""); 
				editor.commit(); 
			    	 mRlmz1.setVisibility(View.VISIBLE);
			    	 mRlmz1a.setVisibility(View.GONE);
				break;

			default:
				break;
			}
		}
	};

private Handler handler =new Handler();
	
}
