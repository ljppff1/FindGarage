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

public class NewDiscussActivity extends FragmentActivity {

	private ViewPager vp;
	private RadioGroup rg1;
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private ArrayList<Fragment> list;
	private ImageView mIvtt1;
	private ListView mLv1;
	private ProgressBar progressBar_sale;
	private Myadapter myadapter;
	private DisplayImageOptions options;
	private ArrayList<Data> mDataList_origin=new ArrayList<NewDiscussActivity.Data>();
	private ArrayList<Data> mDataList=new ArrayList<NewDiscussActivity.Data>();
	private ArrayList<Data> mDataList1=new ArrayList<NewDiscussActivity.Data>();
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private RelativeLayout mRl1;
	private RelativeLayout mRl2;
	private RelativeLayout mRl3;

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.newdiscuss);
		mIvtt1 =(ImageView)this.findViewById(R.id.mIvtt1);
		mIvtt1.setOnClickListener(listener);
		
		mRl1=(RelativeLayout)this.findViewById(R.id.mRl1);
		mRl2=(RelativeLayout)this.findViewById(R.id.mRl2);
		mRl3=(RelativeLayout)this.findViewById(R.id.mRl3);
		mRl1.setOnClickListener(listener);
		mRl2.setOnClickListener(listener);
		mRl3.setOnClickListener(listener);
		
		
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
	   params.addBodyParameter(nameValuePairs);
	   HttpUtils http = new HttpUtils();
	   http.send(HttpRequest.HttpMethod.POST,
	  		"http://josie.i3.com.hk/FG/json/comment_list.php",
	           params,
	           new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						
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
								 String data1 = jsonObject.getString("data");
							//	 [{"CommentScore":"1","GarageTitle":"tttt","GarageID":"2","CommentDetail":"asdf"}]
									//JSONObject jsonObject1 = new JSONObject(data1);
									JSONArray array = new JSONArray(data1);
								// JSONArray array = jsonObject.getJSONArray(data);
								  for (int i = 0; i < array.length(); i++) {
									  
									  Data  data=new Data();
									 JSONObject jsonObject2 = array.getJSONObject(i);
									 data.ID= jsonObject2.getString("GarageID");
									 data.Name= jsonObject2.getString("GarageTitle");
									 data.StreetName = jsonObject2.getString("CommentScore");
									 data.AreaGross=jsonObject2.getString("CommentDetail");
									// data.CoverPic=jsonObject2.getString("CoverPic");
									 mDataList_origin.add(data);
									 
			                          data.toString();						 
								}
								  mDataList.clear();
								  mDataList.addAll(mDataList_origin);
								  mDataList1.addAll(mDataList);
									progressBar_sale.setVisibility(View.GONE);
								  initListView();
							}
							 else {
									progressBar_sale.setVisibility(View.GONE);

								//new AlertInfoDialog(SaleActivity.this).show();
							}
						} catch (JSONException e) {
							 if(mDataList.isEmpty())
							//new Dialog_noInternet(SaleActivity.this).show();
									progressBar_sale.setVisibility(View.GONE);

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
		ImageView imageView,mIvll3;
	}
	class  Myadapter extends   BaseAdapter{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			        
			
			Holder holder = null;
			if(convertView==null){
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.item_listview_3, null);
				holder = new Holder();
				holder.mTvri10 =(TextView)convertView.findViewById(R.id.mTvri10);
				holder.mTvri11 =(TextView)convertView.findViewById(R.id.mTvri11);
				holder.mTvri12 =(TextView)convertView.findViewById(R.id.mTvri12);
				holder.imageView =(ImageView)convertView.findViewById(R.id.iv_listview_rent_pic);
				holder.mIvll3 =(ImageView)convertView.findViewById(R.id.mIvll3);
				convertView.setTag(holder);

			}else{
				holder =(Holder)convertView.getTag();
			}
			if(mDataList.get(position).StreetName.equals("-1")){
				holder.mIvll3.setImageResource(R.drawable.bba3);
			}
			if(mDataList.get(position).StreetName.equals("1")){
				holder.mIvll3.setImageResource(R.drawable.bba1);
			}
			if(mDataList.get(position).StreetName.equals("-1")){
				holder.mIvll3.setImageResource(R.drawable.ok2);
			}
			holder.mTvri12.setText(mDataList1.get(position).Name);
			holder.mTvri11.setText(mDataList1.get(position).AreaGross);
		//	initImageLoaderOptions();
			//imageLoader.displayImage(mDataList.get(position).CoverPic,holder.imageView, options);
			
			return convertView;

		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mDataList1.size();
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
			case R.id.mRl1:
				mDataList1.clear();

				mDataList1.addAll(mDataList);
			       myadapter = new Myadapter();
			       mLv1.setAdapter(myadapter);
                   myadapter.notifyDataSetChanged();
				break;
			case R.id.mRl2:
				mDataList1.clear();
				for(int i=0;i<mDataList.size();i++){
					if (mDataList.get(i).StreetName.equals("1")) {
						mDataList1.add(mDataList.get(i));
					}
				}
			       myadapter = new Myadapter();
			       mLv1.setAdapter(myadapter);
                   myadapter.notifyDataSetChanged();
				break;

			case R.id.mRl3:
				mDataList1.clear();

				for(int i=0;i<mDataList.size();i++){
					if (mDataList.get(i).StreetName.equals("-1")) {
						mDataList1.add(mDataList.get(i));
					}
				}
			       myadapter = new Myadapter();
			       mLv1.setAdapter(myadapter);
                   myadapter.notifyDataSetChanged();

				break;
			default:
				break;
			}
		}
	};

private Handler handler =new Handler();
	
}
