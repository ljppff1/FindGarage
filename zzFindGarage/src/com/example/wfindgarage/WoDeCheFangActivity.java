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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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

public class WoDeCheFangActivity extends FragmentActivity {

	private ImageView mIvtt1;

	private String UserID;

	private RelativeLayout mRlNn1;
	private RelativeLayout mRlNn2;
	private RelativeLayout mRlNn3;

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wodechefang);
		SharedPreferences sharedPreferences=getSharedPreferences("USER", 
				Activity.MODE_PRIVATE); 
		  UserID = sharedPreferences.getString("UserID", ""); 

		mIvtt1 =(ImageView)this.findViewById(R.id.mIvtt1);
		mIvtt1.setOnClickListener(listener);
		
		mRlNn1 =(RelativeLayout)this.findViewById(R.id.mRlNn1);
		mRlNn2 =(RelativeLayout)this.findViewById(R.id.mRlNn2);
		mRlNn3 =(RelativeLayout)this.findViewById(R.id.mRlNn2a);
		mRlNn1.setOnClickListener(listener);
		mRlNn2.setOnClickListener(listener);
		mRlNn3.setOnClickListener(listener);
		
	}
	
	
	
	OnClickListener listener =new  OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mIvtt1:
				finish();
				break;
			case R.id.mRlNn2:
				startActivity(new Intent(getApplicationContext(), WoDeYouHuiActivity.class));
				
				break;
			case R.id.mRlNn2a:
				startActivity(new Intent(getApplicationContext(), WoDeShouCangActivity.class));
				
				break;

			default:
				break;
			}
		}
	};

private Handler handler =new Handler();
	
}
