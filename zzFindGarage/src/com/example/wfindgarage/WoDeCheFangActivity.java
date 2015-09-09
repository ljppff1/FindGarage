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
	private com.example.view.MyListView mLv1;
	private ProgressBar progressBar_sale;
	private Myadapter myadapter;
	private DisplayImageOptions options;
	private ArrayList<Data> mDataList_origin=new ArrayList<WoDeCheFangActivity.Data>();
	private ArrayList<Data> mDataList=new ArrayList<WoDeCheFangActivity.Data>();
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private ImageView mIvNn1;
	private RelativeLayout mRlNn1;
	private String UserID;

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
		mLv1 =(com.example.view.MyListView)this.findViewById(R.id.mLv1);
		mLv1.setFocusable(false);
		initData();
		progressBar_sale =(ProgressBar)this.findViewById(R.id.progressBar_sale);
		progressBar_sale.setVisibility(View.VISIBLE);
		
		mIvNn1 =(ImageView)this.findViewById(R.id.mIvNn1);
		mIvNn1.setVisibility(View.GONE);
		mRlNn1 =(RelativeLayout)this.findViewById(R.id.mRlNn1);
		mRlNn1.setVisibility(View.GONE);
	}
	
	

private void initData1() {
	downloadsearch1("0");
}

public void downloadsearch1(String area11){
	progressBar_sale.setVisibility(View.VISIBLE);

	 RequestParams params = new RequestParams();
   List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
   nameValuePairs.add(new BasicNameValuePair("UserID",UserID));
   
   params.addBodyParameter(nameValuePairs);
   HttpUtils http = new HttpUtils();
   http.send(HttpRequest.HttpMethod.POST,
  		 "http://josie.i3.com.hk/FG/json/m_garage.php",
           params,
           new RequestCallBack<String>() {

				private String GarageID;
				private String GarageTitle;
				private String GarageRemark;
				private String GaragePhoto;

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
						String msg = jsonObject.getString("msg");
						
						 int  num_code=Integer.valueOf(string_code);
						 if (num_code==1) {
							 //保存到本地
							 JSONObject array = jsonObject.getJSONObject("data");
								progressBar_sale.setVisibility(View.GONE);
								 GarageID = array.getString("GarageID");
								 GarageTitle = array.getString("GarageTitle");
								 GarageRemark = array.getString("GarageRemark");
								 GaragePhoto = array.getString("GaragePhoto");
									initImageLoaderOptions();
									imageLoader.displayImage(GaragePhoto,
											mIvNn1, options);

						 /**
						  * {"code":"1","msg":"Login success","data":{"UserID":"2","LoginName":"ljppff","UserEmail":"ljppff@163.com","UserName":"","UserGender":"1","UserBirtyYear":""}}
						  */
						 }
						
						 else {
								 Toast.makeText(getApplicationContext(),msg, 0).show();
									progressBar_sale.setVisibility(View.GONE);

							//new AlertInfoDialog(SaleActivity.this).show();
						}
					} catch (JSONException e) {
						//new Dialog_noInternet(SaleActivity.this).show();
						progressBar_sale.setVisibility(View.GONE);

							 Toast.makeText(getApplicationContext(),  getResources().getString(R.string.abc36), 0).show();
						e.printStackTrace();
					}
						
				
					
				}

			
     
   });
}
   
	
	
	private void initData() {
		downloadsearch("0");
	}
	public void downloadsearch(String area11){
		 RequestParams params = new RequestParams();
	   List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
	   nameValuePairs.add(new BasicNameValuePair("PropertyLocation", area11));
	   nameValuePairs.add(new BasicNameValuePair("RentSale", "1"));
	   params.addBodyParameter(nameValuePairs);
	   HttpUtils http = new HttpUtils();
	   http.send(HttpRequest.HttpMethod.POST,
	  		 com.example.utils.Content.URL_Search,
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
							 int  num_code=Integer.valueOf(string_code);
							 if (num_code==1) {
								 //保存到本地
								 mDataList_origin.clear();
								 JSONArray array = jsonObject.getJSONArray("data");
								  for (int i = 0; i < array.length(); i++) {
									  if(i>1){
										  break;
									  }
									  Data  data=new Data();
									  
									 JSONObject jsonObject2 = array.getJSONObject(i);
									 data.ID= jsonObject2.getString("ID");
									 data.Name= jsonObject2.getString("Name");
									 data.StreetName = jsonObject2.getString("StreetName");
									 data.AreaGross=jsonObject2.getString("AreaGross");
									 data.AreaNet=jsonObject2.getString("AreaNet");
									 data.CoverPic=jsonObject2.getString("CoverPic");
									 data.SellingPrice=jsonObject2.getString("SellingPrice");
									 data.RentPrice=jsonObject2.getString("RentPrice");
									 mDataList_origin.add(data);
									 
			                          data.toString();						 
								}
								  mDataList.clear();
								  mDataList.addAll(mDataList_origin);
									progressBar_sale.setVisibility(View.GONE);
									mIvNn1.setVisibility(View.VISIBLE);
									mRlNn1.setVisibility(View.VISIBLE);
									
								  initListView();
							}
							 else {
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
			initImageLoaderOptions();
			if(mDataList.size()>2){
			imageLoader.displayImage(mDataList.get(1).CoverPic,
					mIvNn1, options);
			}
	       mLv1.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent,
						View view, int position, long id) {
					if(position==1){
					startActivity(new Intent(getApplicationContext(), WoDeCheFangDetailActivity.class));
					}
					if(position==0){
					startActivity(new Intent(getApplicationContext(), WoDeCheFangDetail1Activity.class));
					}
					
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
		TextView mTvri10,mTvri11,mTvri12;
		ImageView imageView;
	}
	class  Myadapter extends   BaseAdapter{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			        
			
			Holder holder = null;
			if(convertView==null){
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.item_listview_6, null);
				holder = new Holder();
				holder.mTvri10 =(TextView)convertView.findViewById(R.id.mTvri10);
				holder.mTvri11 =(TextView)convertView.findViewById(R.id.mTvri11);
				holder.imageView =(ImageView)convertView.findViewById(R.id.iv_listview_rent_pic);
				convertView.setTag(holder);

			}else{
				holder =(Holder)convertView.getTag();
			}
			if(position==0){
			holder.mTvri10.setText(3+"");
			holder.mTvri11.setText(getResources().getString(R.string.bz23));
			}else{
				holder.mTvri10.setText(4+"");
				holder.mTvri11.setText(getResources().getString(R.string.bz231));

			}
			
			//holder.mTvri12.setText(mDataList.get(position).Name);
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
