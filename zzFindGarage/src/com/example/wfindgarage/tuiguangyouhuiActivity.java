package com.example.wfindgarage;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.support.v4.view.ViewPager.LayoutParams;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fragment.FragmentFJ1atui;
import com.example.wfindgarage.SearchResultActivity.Data;
import com.example.wfindgarage.SearchResultActivity.Holder;
import com.example.wfindgarage.SearchResultActivity.Myadapter;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class tuiguangyouhuiActivity extends FragmentActivity {

	private ViewPager vp;
	private RadioGroup rg1;
	private RadioButton rb1;
	private RadioButton rb2;
	private ArrayList<Fragment> list;
	private ImageView mIvtt1;
	private FragmentFJ1atui fa;
	private FragmentFJ1atui fb;
	private TextView mTvfjc1;
	private RelativeLayout mRlzz1;
	private ListView mLv1;
	private ProgressBar progressBar_sale;
	private Myadapter myadapter;
	private DisplayImageOptions options;
	private List<Data> mDataList =new ArrayList<tuiguangyouhuiActivity.Data>();
	private List<Data> mDataList_origin =new ArrayList<tuiguangyouhuiActivity.Data>();
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tui1);
		AppManager.getAppManager().addActivity(tuiguangyouhuiActivity.this);
		mIvtt1 =(ImageView)this.findViewById(R.id.mIvtt1);
		mIvtt1.setOnClickListener(listener);

		mTvfjc1 =(TextView)this.findViewById(R.id.mTvfjc1);
		mTvfjc1.setOnClickListener(listener);
		mRlzz1 =(RelativeLayout)this.findViewById(R.id.mRlzz1);

		mLv1 =(ListView)this.findViewById(R.id.mLv1);
		initData();
		progressBar_sale =(ProgressBar)this.findViewById(R.id.progressBar_sale);
		progressBar_sale.setVisibility(View.VISIBLE);



	}
	

	private void initData() {
		downloadsearch("0");
	}
	public void downloadsearch(String area11){
		 RequestParams params = new RequestParams();
	   List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
	   nameValuePairs.add(new BasicNameValuePair("CategoryID", 1+""));
	   
	   params.addBodyParameter(nameValuePairs);
	   HttpUtils http = new HttpUtils();
	   http.send(HttpRequest.HttpMethod.POST,
	  		 "http://josie.i3.com.hk/FG/json/sale_list.php",
	           params,
	           new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						JSONObject jsonObject;
						try {
							jsonObject = new JSONObject(arg0.result);
							String string_code = jsonObject.getString("code");
						//	{"data":[{"BadNum":"0","GarageTitle":"tttt","GarageID":"2","LocationOne":null,"PraiseNum":"2","LocationTwo"
							//:null,"GaragePhoto":"http:\/\/josie.i3.com.hk\/FG\/UPFile\/GaragePhoto\/CoverPhoto\/201582015471135.jpg"}],"msg":"","code":"1"}
							 int  num_code=Integer.valueOf(string_code);
							 
							 if (num_code==1) {
								 //保存到本地
								 mDataList_origin.clear();
								 JSONArray array = jsonObject.getJSONArray("data");
								  for (int i = 0; i < array.length(); i++) {
									  
									  Data  data=new Data();
									  
									 JSONObject jsonObject2 = array.getJSONObject(i);
									 data.ID= jsonObject2.getString("SaleID");
									 data.Name= jsonObject2.getString("SaleTitle");
									 data.CoverPic=jsonObject2.getString("SalePhoto");
									 data.hao =jsonObject2.getString("SaleRemark");
									 data.bad =jsonObject2.getString("LikeNum");
									 data.loca1 =jsonObject2.getString("SaleDate");
									 
									 mDataList_origin.add(data);
									 
			                          data.toString();						 
								}
								  mDataList.clear();
								  mDataList.addAll(mDataList_origin);
									progressBar_sale.setVisibility(View.GONE);
								  initListView();
							}
							 else {
									progressBar_sale.setVisibility(View.GONE);
									 Toast.makeText(getApplicationContext(), "暫無相關內容", 0).show();

								//new AlertInfoDialog(SaleActivity.this).show();
							}
						} catch (JSONException e) {
							 if(mDataList.isEmpty())
							//new Dialog_noInternet(SaleActivity.this).show();
								 Toast.makeText(getApplicationContext(), "暫無相關內容", 0).show();
							e.printStackTrace();
						}
							
					
						
					}

				
	     
	   });
	}
	private void initListView() {
	       myadapter = new Myadapter();
	       
	       mLv1.setAdapter(myadapter);
	       mLv1.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent,
						View view, int position, long id) {
					Intent intent =new Intent(getApplicationContext(), TuiDetailActivity.class);
					intent.putExtra("ID", mDataList.get(position).ID+"");
					startActivity(intent);
					
				}
			});
		}
	private void initImageLoaderOptions() {
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.a)
				.cacheInMemory()
				.cacheOnDisc().displayer(new FadeInBitmapDisplayer(2000))
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}
	class Holder{
		TextView mTvri10,mTvri11,mTvri12,mljt2,mljt1,mTvri13;
		ImageView imageView;
	}
	class  Myadapter extends   BaseAdapter{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
	        
			
	Holder holder = null;
	if(convertView==null){
		convertView = LayoutInflater.from(getApplicationContext())
				.inflate(R.layout.item_listview_5, null);
		holder = new Holder();
		holder.mTvri10 =(TextView)convertView.findViewById(R.id.mTvri10);
		holder.mTvri11 =(TextView)convertView.findViewById(R.id.mTvri11);
		holder.mTvri13 =(TextView)convertView.findViewById(R.id.mTvri13);
		holder.mTvri12 =(TextView)convertView.findViewById(R.id.mTvri12);
		holder.imageView =(ImageView)convertView.findViewById(R.id.iv_listview_rent_pic);
		convertView.setTag(holder);
		holder.mTvri12.setText(mDataList.get(position).Name);
		holder.mTvri11.setText(mDataList.get(position).hao);
		holder.mTvri13.setText(mDataList.get(position).loca1);
		
		
	}else{
		holder =(Holder)convertView.getTag();
	}
	holder.mTvri12.setText(mDataList.get(position).Name);
	initImageLoaderOptions();
	imageLoader.displayImage(mDataList.get(position).CoverPic,
			holder.imageView, options);
	
	return convertView;

}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mDataList.size();
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
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
		String hao;
		String bad;
		String loca1;
		String loca2;
		@Override
		public String toString() {
			return "Data [ID=" + ID + ", Name=" + Name + ", StreetName="
					+ StreetName + ", AreaGross=" + AreaGross + ", AreaNet="
					+ AreaNet + ", SellingPrice=" + SellingPrice
					+ ", RentPrice=" + RentPrice + ", CoverPic=" + CoverPic
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
