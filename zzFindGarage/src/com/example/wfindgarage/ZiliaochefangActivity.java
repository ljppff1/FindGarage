package com.example.wfindgarage;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ZiliaochefangActivity extends FragmentActivity {

	private ViewPager vp;
	private RadioGroup rg1;
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private ArrayList<Fragment> list;
	private ImageView mIvtt1;
	   private ArrayList<Data> mDataList_origin=new ArrayList<ZiliaochefangActivity.Data>();

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ziliaochefang);
		rg1 = (RadioGroup) this.findViewById(R.id.rg1);
		rb1 = (RadioButton) this.findViewById(R.id.rb1);
		rb2 = (RadioButton) this.findViewById(R.id.rb2);
		rb3 = (RadioButton) this.findViewById(R.id.rb3);
		rg1.setVisibility(View.GONE);
		downLoad();
		mIvtt1 =(ImageView)this.findViewById(R.id.mIvtt1);
		mIvtt1.setOnClickListener(listener);
		vp=(ViewPager)this.findViewById(R.id.vp1);
	    vp.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				if (arg0==0) {
		             rb1.setChecked(true);
					}
				if(arg0==1){
					rb2.setChecked(true);
				}
				if(arg0==2){
					rb3.setChecked(true);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		list = new ArrayList<Fragment>();
		com.example.fragment.Fragment1a fa=new com.example.fragment.Fragment1a();
		com.example.fragment.Fragment1b fb=new com.example.fragment.Fragment1b();
		com.example.fragment.Fragment1c fc=new com.example.fragment.Fragment1c();
		list.add(fa);
		list.add(fb);
		list.add(fc);
        
		ZxzcAdapter zxzc = new ZxzcAdapter(getSupportFragmentManager(), list);
		vp.setAdapter(zxzc);
		zxzc.notifyDataSetChanged();
		rg1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int cheakedId) {
				if (cheakedId == rb1.getId()) {
					vp.setCurrentItem(0);
				} else if (cheakedId == rb2.getId()) {
					vp.setCurrentItem(1);
				} else if (cheakedId == rb3.getId()) {
					vp.setCurrentItem(2);
				}
							
			}
		});
		rb1.setChecked(true);

	}
	
	private void downLoad() {
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute("http://josie.i3.com.hk/FG/json/article_categorylist.php");
	}
	class DownLoadAsyTask extends AsyncTask<String, Void, String>{  
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String string_code = jsonObject.getString("code");
				 int  num_code=Integer.valueOf(string_code);
				 if (num_code==1) {
					 JSONArray array = jsonObject.getJSONArray("data");
					  for (int i = 0; i < array.length(); i++) {
						  
						  Data  data=new Data();
						  
						 JSONObject jsonObject2 = array.getJSONObject(i);
						 data.ID= jsonObject2.getString("CategoryID");
						 data.Name= jsonObject2.getString("CategoryName");
						 mDataList_origin.add(data);
                          data.toString();						 
					}
					for(int i=0;i<mDataList_origin.size();i++){
						if(i==0){
							rb1.setText(mDataList_origin.get(i).Name);
						}
						if(i==1){
							rb2.setText(mDataList_origin.get(i).Name);
						}
						if(i==2){
							rb3.setText(mDataList_origin.get(i).Name);
						}
					}
					rg1.setVisibility(View.VISIBLE);

				}
				 else {
					 
				 }
			} catch (JSONException e) {
				
			}
				
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return com.example.utils.getJson.getData(str);
			}
			}
	
	class Data{
		String   ID;
		String   Name;
		String   StreetName;
		String   AreaGross;
		String   AreaNet;
		String   SellingPrice;
		String   RentPrice;
		String   CoverPic;
		String RentSale;
		@Override
		public String toString() {
			return "Data [ID=" + ID + ", Name=" + Name + ", StreetName="
					+ StreetName + ", AreaGross=" + AreaGross + ", AreaNet="
					+ AreaNet + ", SellingPrice=" + SellingPrice
					+ ", RentPrice=" + RentPrice + ", CoverPic=" + CoverPic+ ", RentSale=" + RentSale
					+ "]";
		}
		
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
class ZxzcAdapter extends FragmentStatePagerAdapter {
		   
		
		List<Fragment> list;			
		public ZxzcAdapter(FragmentManager fm,List<Fragment> list) {
			super(fm);
			this.list=list;			
		}
		@SuppressLint("ResourceAsColor")
		@Override
		public Fragment getItem(int arg0) {		
			return list.get(arg0);
		}
		@Override
		public int getCount() {
		
			return list.size();
		}

	}

private Handler handler =new Handler();
	
}
