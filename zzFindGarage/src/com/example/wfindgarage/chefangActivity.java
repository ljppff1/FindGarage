package com.example.wfindgarage;


import java.util.ArrayList;
import java.util.List;

import com.example.fragment.FragmentCheFang;
import com.example.fragment.FragmentCheFang1;
import com.example.fragment.FragmentCheFang2;
import com.example.fragment.FragmentLogin;
import com.example.fragment.FragmentRegister;
import com.example.wfindgarage.huiyuandengluActivity.ZxzcAdapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class chefangActivity extends FragmentActivity {

	private ImageView mIvtt1;

	private RadioButton rb1;

	private RadioButton rb2;

	private RadioButton rb3;

	private RadioGroup rg1;

	private LinearLayout mLLCf1;

	private LinearLayout mLLCf2;
	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.aatest);
		mLLCf1 =(LinearLayout)this.findViewById(R.id.mLLCf1);
		mLLCf2 =(LinearLayout)this.findViewById(R.id.mLLCf2);
		
		mIvtt1 =(ImageView)this.findViewById(R.id.mIvtt1);
		mIvtt1.setOnClickListener(listener);
	
		rg1 = (RadioGroup) this.findViewById(R.id.rg1cf);
		rb1 = (RadioButton) this.findViewById(R.id.rb1cf);
		rb2 = (RadioButton) this.findViewById(R.id.rb2cf);
		rb3 = (RadioButton) this.findViewById(R.id.rb3cf);		
	
		rg1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int cheakedId) {
				if (cheakedId == rb1.getId()) {
					mLLCf1.setVisibility(View.VISIBLE);
					mLLCf2.setVisibility(View.GONE);
				} else if (cheakedId == rb2.getId()) {
					mLLCf1.setVisibility(View.GONE);
					mLLCf2.setVisibility(View.VISIBLE);
				}else if (cheakedId == rb3.getId()) {
					mLLCf1.setVisibility(View.GONE);
					mLLCf2.setVisibility(View.VISIBLE);
				}
							
			}
		});
		rb1.setChecked(true);
		
		
		
	}


	OnClickListener listener =new  OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mIvtt1:
				finish();
				break;

			default:
				break;
			}
		}
	};

private Handler handler =new Handler();
	
}
