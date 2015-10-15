package com.example.wfindgarage;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ZiliaochefangActivity extends FragmentActivity {

	private ViewPager vp;
	private RadioGroup rg1;
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private ArrayList<Fragment> list;
	private ImageView mIvtt1;
	   private ArrayList<Data> mDataList_origin=new ArrayList<ZiliaochefangActivity.Data>();
	private TextView mTvfjc1;
	private RelativeLayout mRlzz1;

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ziliaochefang);
	   AppManager.getAppManager().addActivity(ZiliaochefangActivity.this);
		mTvfjc1 =(TextView)this.findViewById(R.id.mTvfjc1);
		mTvfjc1.setOnClickListener(listener);
		mRlzz1 =(RelativeLayout)this.findViewById(R.id.mRlzz1);
    
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
			case R.id.mTvfjc1:
				ShowWindow();
				break;
			case R.id.mIvtt1:
				finish();
				break;

			default:
				break;
			}
		}
	};

	private View layout;
	private PopupWindow mPop;
	private View mV1;
	private View mV2;
	private ImageView mIv1;
	private RelativeLayout mRl1;
	private RelativeLayout mRl2;
	private RelativeLayout mRl3;
	private RelativeLayout mRl4;
	private RelativeLayout mRl5;
	private RelativeLayout mRl6;
	private RelativeLayout mRl7;
	private RelativeLayout mRl8;
	private RelativeLayout mRl9;
	private RelativeLayout mRl10;
	private RelativeLayout mRl11;
	private RelativeLayout mRl12;
	private RelativeLayout mRl13;
	
	public void ShowWindow(){
		LayoutInflater mLayoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
		 layout = mLayoutInflater.inflate(R.layout.popmain, null);
		 mV1 =layout.findViewById(R.id.mV1);
		 mV2 =layout.findViewById(R.id.mV2);
		 mV1.setOnClickListener(listener1);
		 mRl1 =(RelativeLayout)layout.findViewById(R.id.mRl1);
		 mRl2 =(RelativeLayout)layout.findViewById(R.id.mRl2);
		 mRl3 =(RelativeLayout)layout.findViewById(R.id.mRl3);
		 mRl4 =(RelativeLayout)layout.findViewById(R.id.mRl4);
		 mRl5 =(RelativeLayout)layout.findViewById(R.id.mRl5);
		 mRl6 =(RelativeLayout)layout.findViewById(R.id.mRl6);
		 mRl7 =(RelativeLayout)layout.findViewById(R.id.mRl7);
		 mRl8 =(RelativeLayout)layout.findViewById(R.id.mRl8);
		 mRl9 =(RelativeLayout)layout.findViewById(R.id.mRl9);
		 mRl10 =(RelativeLayout)layout.findViewById(R.id.mRl10);
		 mRl11=(RelativeLayout)layout.findViewById(R.id.mRl11);
		 mRl12 =(RelativeLayout)layout.findViewById(R.id.mRl12);
		 mRl13 =(RelativeLayout)layout.findViewById(R.id.mRl13);
		 mRl1.setOnClickListener(listener1);
		 mRl2.setOnClickListener(listener1);
		 mRl3.setOnClickListener(listener1);
		 mRl4.setOnClickListener(listener1);
		 mRl5.setOnClickListener(listener1);
		 mRl6.setOnClickListener(listener1);
		 mRl8.setOnClickListener(listener1);
		 mRl7.setOnClickListener(listener1);
		 mRl9.setOnClickListener(listener1);
		 mRl10.setOnClickListener(listener1);
		 mRl11.setOnClickListener(listener1);
		 mRl12.setOnClickListener(listener1);
		 mRl13.setOnClickListener(listener1);
		 mV2.setOnClickListener(listener1);
		// mLvNavi =(ListView)layout.findViewById(R.id.mLvNavi);
			layout.setFocusable(true); // 这个很重要  
			layout.setFocusableInTouchMode(true);  
		 mPop = new PopupWindow(layout,LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		 mPop.setFocusable(true);
		 mPop.setOutsideTouchable(true);
		 mPop.update();
		//位置在R.id.button的正下方
		 //mPop.showAsDropDown(findViewById(R.id.mTvfjc1), 15,8);
		 mPop.showAtLocation(findViewById(R.id.mTvfjc1), Gravity.CENTER_HORIZONTAL|Gravity.TOP, 0, 0);
		

			layout.setOnKeyListener(new OnKeyListener() {

				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					switch (keyCode) {
					case KeyEvent.KEYCODE_BACK:
						if (mPop != null && mPop.isShowing()) {
							mRlzz1.setVisibility(View.GONE);
				mPop.dismiss();
							mPop = null;

							return true;
						}
						break;
					default:
						break;
					}
					return false;
				}
			});
			layout.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					 if (mPop != null && mPop.isShowing()) {
						 mRlzz1.setVisibility(View.GONE);
				        mPop.dismiss();
					  mPop = null; }
					 return true;
				}
			});
			new Handler().postDelayed(new Runnable(){   
	            public void run() {  
	                   //显示dialog
	            	 mRlzz1.setVisibility(View.VISIBLE);
	            }  
	        }, 100);   //5秒			 mRlzz1.setVisibility(View.VISIBLE);
	}
	OnClickListener listener1 =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mRl1:
				
				mRlzz1.setVisibility(View.GONE); mPop.dismiss(); mPop= null; startActivity(new Intent(getApplicationContext(), SearchYaocheActivity.class));
				break;
			case R.id.mRl2:
				mRlzz1.setVisibility(View.GONE); mPop.dismiss(); mPop= null; startActivity(new Intent(getApplicationContext(), fujinchefangActivity.class));
				break;
			case R.id.mRl3:
				mRlzz1.setVisibility(View.GONE); mPop.dismiss(); mPop= null; startActivity(new Intent(getApplicationContext(), tuiguangyouhuiActivity.class));
				break;
			case R.id.mRl4:
    			mRlzz1.setVisibility(View.GONE); mPop.dismiss(); mPop= null; startActivity(new Intent(getApplicationContext(), Main1Activity.class));
				break;
			case R.id.mRl5:
    			mRlzz1.setVisibility(View.GONE); mPop.dismiss(); mPop= null; startActivity(new Intent(getApplicationContext(), MeiYuePaiHangActivity.class));
				break;
			case R.id.mRl6:
    			mRlzz1.setVisibility(View.GONE); mPop.dismiss(); mPop= null; startActivity(new Intent(getApplicationContext(), ZiliaochefangActivity1.class));
				break;
			case R.id.mRl7:
    			mRlzz1.setVisibility(View.GONE); mPop.dismiss(); mPop= null; startActivity(new Intent(getApplicationContext(), NewDiscussActivity.class));
				break;
			case R.id.mRl8:
    			mRlzz1.setVisibility(View.GONE); mPop.dismiss(); mPop= null; startActivity(new Intent(getApplicationContext(), LianxiwomenActivity.class));
				break;
			case R.id.mRl9:
    			mRlzz1.setVisibility(View.GONE); mPop.dismiss(); mPop= null; startActivity(new Intent(getApplicationContext(), TiGongCheFangZiLiaoActivity.class));
				break;
			case R.id.mRl10:
    		//	mRlzz1.setVisibility(View.GONE); mPop.dismiss(); mPop= null; startActivity(new Intent(getApplicationContext(), Main1Activity.class));
				break;
			case R.id.mRl11:
        		SharedPreferences sharedPreferences=getSharedPreferences("USER", 
        				Activity.MODE_PRIVATE); 
        		 String UserID = sharedPreferences.getString("UserID", ""); 
        	     if(!TextUtils.isEmpty(UserID)){
            	 Intent intent =new Intent(getApplicationContext(), WoDeCheFangActivity.class);

             mRlzz1.setVisibility(View.GONE); mPop.dismiss(); mPop= null; startActivity(intent);
        	     }else{
	            	 Intent intent =new Intent(getApplicationContext(), huiyuandengluActivity.class);

	             mRlzz1.setVisibility(View.GONE); mPop.dismiss(); mPop= null; startActivity(intent);
        	     }

				break;
			case R.id.mRl12:
    			mRlzz1.setVisibility(View.GONE); mPop.dismiss(); mPop= null; startActivity(new Intent(getApplicationContext(), WoDeYouHuiActivity.class));
				break;
			case R.id.mRl13:
    			mRlzz1.setVisibility(View.GONE); mPop.dismiss(); mPop= null; startActivity(new Intent(getApplicationContext(), MyZiliaoActivity.class));
				break;
			case R.id.mV1:
				mRlzz1.setVisibility(View.GONE);
				mPop.dismiss();
				mPop = null;

				break;
			case R.id.mV2:
				mRlzz1.setVisibility(View.GONE);
				mPop.dismiss();
				mPop = null;
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
