package com.example.wfindgarage;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.fragment.Fragment1a;
import com.example.fragment.FragmentFJ1a;
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

public class SearchResultActivity extends BaseActivity {

	private ImageView mIvtt1;

	private ProgressBar progressBar_sale;

	private ListView mLv1;
	private DisplayImageOptions options;
	private ArrayList<Data> mDataList_origin=new ArrayList<SearchResultActivity.Data>();
	private ArrayList<Data> mDataList=new ArrayList<SearchResultActivity.Data>();
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	private Myadapter myadapter;

	private String item1;
	private String item2;
	private String item3;
    
	
	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.searchresult);
		
		item1 = getIntent().getExtras().getString("item_first");
		item2 = getIntent().getExtras().getString("item_second");
		item3 = getIntent().getExtras().getString("item_third");
		mIvtt1 =(ImageView)this.findViewById(R.id.mIvtt1);
		mIvtt1.setOnClickListener(listener);
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
	   nameValuePairs.add(new BasicNameValuePair("keyLocationOne", item1));
	   nameValuePairs.add(new BasicNameValuePair("keyLocationTwo", item2));
	   nameValuePairs.add(new BasicNameValuePair("keyWord", item3));
	   
	   params.addBodyParameter(nameValuePairs);
	   HttpUtils http = new HttpUtils();
	   http.send(HttpRequest.HttpMethod.POST,
	  		 "http://josie.i3.com.hk/FG/json/garage_list.php",
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
								 //���浽����
								 mDataList_origin.clear();
								 JSONArray array = jsonObject.getJSONArray("data");
								  for (int i = 0; i < array.length(); i++) {
									  
									  Data  data=new Data();
									  
									 JSONObject jsonObject2 = array.getJSONObject(i);
									 data.ID= jsonObject2.getString("GarageID");
									 data.Name= jsonObject2.getString("GarageTitle");
									 data.CoverPic=jsonObject2.getString("GaragePhoto");
									 data.hao =jsonObject2.getString("PraiseNum");
									 data.bad =jsonObject2.getString("BadNum");
									 data.loca1 =jsonObject2.getString("LocationOne");
									 data.loca2 =jsonObject2.getString("LocationTwo");
									 
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
									 Toast.makeText(getApplicationContext(), "���o���P����", 0).show();

								//new AlertInfoDialog(SaleActivity.this).show();
							}
						} catch (JSONException e) {
							 if(mDataList.isEmpty())
							//new Dialog_noInternet(SaleActivity.this).show();
								 Toast.makeText(getApplicationContext(), "���o���P����", 0).show();
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
					Intent intent =new Intent(getApplicationContext(), chefangActivity.class);
					intent.putExtra("GarageID", mDataList.get(position).ID+"");
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
		TextView mTvri10,mTvri11,mTvri12,mljt2,mljt1;
		ImageView imageView;
	}
	class  Myadapter extends   BaseAdapter{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			        
			
			Holder holder = null;
			if(convertView==null){
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.item_listview_4, null);
				holder = new Holder();
				holder.mTvri10 =(TextView)convertView.findViewById(R.id.mTvri10);
				holder.mTvri11 =(TextView)convertView.findViewById(R.id.mTvri11);
				holder.mTvri12 =(TextView)convertView.findViewById(R.id.mTvri12);
				holder.mljt1 =(TextView)convertView.findViewById(R.id.mljt1);
				holder.mljt2 =(TextView)convertView.findViewById(R.id.mljt2);
				
				holder.imageView =(ImageView)convertView.findViewById(R.id.iv_listview_rent_pic);
				convertView.setTag(holder);

			}else{
				holder =(Holder)convertView.getTag();
			}
			holder.mTvri12.setText(mDataList.get(position).Name);
			String str="";
			if(!TextUtils.isEmpty(mDataList.get(position).loca1)&&!("null"!=mDataList.get(position).loca1)){
				str =str +mDataList.get(position).loca1;
			}
			if(!TextUtils.isEmpty(mDataList.get(position).loca2)&&!("null"!=mDataList.get(position).loca2)){
				str =str +mDataList.get(position).loca2;
			}
			holder.mljt1.setText(mDataList.get(position).hao);
			holder.mljt2.setText(mDataList.get(position).bad);
			
			holder.mTvri11.setText(str);
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
