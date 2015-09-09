package com.example.wfindgarage;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
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
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class TuiDetailActivity extends Activity {

	private ImageView mIvtt1;
	private ProgressBar progressBar_sale;
	private ImageView mIvtd2;
	private TextView mTvtdt1;
	private TextView mTvtdt2;
	private String ID;
	private String UserID;
	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tuidetail);
		progressBar_sale =(ProgressBar)this.findViewById(R.id.progressBar_sale);
		progressBar_sale.setVisibility(View.VISIBLE);
		mIvtd2 =(ImageView)this.findViewById(R.id.mIvtd11);
		mIvtt1 =(ImageView)this.findViewById(R.id.mIvtt1);
		mIvtt1.setOnClickListener(listener);
		mIvtd2.setOnClickListener(listener);
		mTvtdt1 =(TextView)this.findViewById(R.id.mTvtdt1);
		mTvtdt2 =(TextView)this.findViewById(R.id.mTvtdt2);
		ID =getIntent().getExtras().getString("ID");
		SharedPreferences sharedPreferences=getSharedPreferences("USER", 
				Activity.MODE_PRIVATE); 
		  UserID = sharedPreferences.getString("UserID", ""); 

		initData();
	}
	OnClickListener listener =new  OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mIvtt1:
				finish();
				break;
			case R.id.mIvtd11:
       	     if(!TextUtils.isEmpty(UserID)){
				initData1();
    	     }else{
	             startActivity(new Intent(getApplicationContext(), huiyuandengluActivity.class));
    	     }

				break;

			default:
				break;
			}
		}
	};
	private DisplayImageOptions options;
	
	

    
private void initData() {
	downloadsearch("0");
}
private void initImageLoaderOptions() {
	options = new DisplayImageOptions.Builder()
			.showImageForEmptyUri(R.drawable.a)
			.cacheInMemory()
			.cacheOnDisc().displayer(new FadeInBitmapDisplayer(2000))
			.bitmapConfig(Bitmap.Config.RGB_565).build();
}
public void downloadsearch(String area11){
	progressBar_sale.setVisibility(View.VISIBLE);

	 RequestParams params = new RequestParams();
   List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
   nameValuePairs.add(new BasicNameValuePair("SaleID",ID));
   
   params.addBodyParameter(nameValuePairs);
   HttpUtils http = new HttpUtils();
   http.send(HttpRequest.HttpMethod.POST,
  		 "http://josie.i3.com.hk/FG/json/sale_detail.php",
           params,
           new RequestCallBack<String>() {

				private String SaleTitle;
				private String SaleDetail;

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					JSONObject jsonObject;
					String msg = null;
					try {
						jsonObject = new JSONObject(arg0.result);
						String string_code = jsonObject.getString("code");
						 msg = jsonObject.getString("msg");
						 JSONObject data =jsonObject.getJSONObject("data");
						 SaleTitle=	 data.getString("SaleTitle");
						 SaleDetail=	 data.getString("SaleDetail");
						 mTvtdt1.setText(SaleTitle);
						 mTvtdt2.setText(SaleDetail);
/*							initImageLoaderOptions();
							imageLoader.displayImage(GaragePhoto, mIvcf1,
									options);
*/                         
						 int  num_code=Integer.valueOf(string_code);
						 if (num_code==1) {
							 //保存到本地
						//	 JSONObject array = jsonObject.getJSONObject("data");
					//		 Toast.makeText(getApplicationContext(),  getResources().getString(R.string.abca2a), 0).show();
								progressBar_sale.setVisibility(View.GONE);
						 /**
						  * {"code":"1","msg":"Login success","data":{"UserID":"2","LoginName":"ljppff","UserEmail":"ljppff@163.com","UserName":"","UserGender":"1","UserBirtyYear":""}}
						  */
						 }
						
						 else {
							//	 Toast.makeText(getApplicationContext(),msg, 0).show();
									progressBar_sale.setVisibility(View.GONE);
							//new AlertInfoDialog(SaleActivity.this).show();
						}
					} catch (JSONException e) {
						//new Dialog_noInternet(SaleActivity.this).show();
						progressBar_sale.setVisibility(View.GONE);
					//	 Toast.makeText(getApplicationContext(),msg, 0).show();
						e.printStackTrace();
					}
						
				
					
				}

			
     
   });
}
    


private void initData1() {
	downloadsearch1("0");
}
public void downloadsearch1(String area11){
	progressBar_sale.setVisibility(View.VISIBLE);

	 RequestParams params = new RequestParams();
   List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
   nameValuePairs.add(new BasicNameValuePair("SaleID",ID));
   nameValuePairs.add(new BasicNameValuePair("UserID", UserID));
   params.addBodyParameter(nameValuePairs);
   HttpUtils http = new HttpUtils();
   http.send(HttpRequest.HttpMethod.POST,
  		 "http://josie.i3.com.hk/FG/json/sale_to_fav.php",
           params,
           new RequestCallBack<String>() {
				@Override
				public void onFailure(HttpException arg0, String arg1) {
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					JSONObject jsonObject;
					String msg = null;
					try {
						jsonObject = new JSONObject(arg0.result);
						String string_code = jsonObject.getString("code");
						 msg = jsonObject.getString("msg");
						
						 int  num_code=Integer.valueOf(string_code);
						 if (num_code==1) {
							 //保存到本地
							 Toast.makeText(getApplicationContext(),msg, 0).show();
								progressBar_sale.setVisibility(View.GONE);

						 }
						
						 else {
								 Toast.makeText(getApplicationContext(),msg, 0).show();
									progressBar_sale.setVisibility(View.GONE);
							//new AlertInfoDialog(SaleActivity.this).show();
						}
					} catch (JSONException e) {
						progressBar_sale.setVisibility(View.GONE);
						 Toast.makeText(getApplicationContext(),msg, 0).show();
						e.printStackTrace();
					}
						
				
					
				}

			
     
   });
}

private Handler handler =new Handler();
	
}
