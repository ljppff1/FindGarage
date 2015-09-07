package com.example.wfindgarage;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.fragment.FragmentCheFang;
import com.example.fragment.FragmentCheFang1;
import com.example.fragment.FragmentCheFang2;
import com.example.fragment.FragmentLogin;
import com.example.fragment.FragmentRegister;
import com.example.wfindgarage.huiyuandengluActivity.ZxzcAdapter;
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
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class chefangActivity extends FragmentActivity {

	private ImageView mIvtt1;

	private RadioButton rb1;

	private RadioButton rb2;

	private RadioButton rb3;

	private RadioGroup rg1;
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	private LinearLayout mLLCf1;

	private LinearLayout mLLCf2;

	private LinearLayout mLLaa1;

	private ProgressBar progressBar_sale;
	private String GarageTitle;
	private String GarageDetail;
	private String GaragePhoto;
	private String PraiseNum;
	private String BadNum;

	private TextView mTvae1;

	private ImageView mIvcf1;

	private TextView mTvaa1;
	private TextView mTvaa2;

	
	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.aatest);
		mLLCf1 =(LinearLayout)this.findViewById(R.id.mLLCf1);
		mLLCf2 =(LinearLayout)this.findViewById(R.id.mLLCf2);
		mLLaa1 =(LinearLayout)this.findViewById(R.id.mLLaa1);
		mLLaa1.setOnClickListener(listener);
		mIvtt1 =(ImageView)this.findViewById(R.id.mIvtt1);
		mIvcf1 =(ImageView)this.findViewById(R.id.mIvcf1);
		mIvtt1.setOnClickListener(listener);
		 
		progressBar_sale =(ProgressBar)this.findViewById(R.id.progressBar_sale);
		progressBar_sale.setVisibility(View.GONE);
		mTvae1 =(TextView)this.findViewById(R.id.mTvae1);
		mTvaa1 =(TextView)this.findViewById(R.id.mTvaa1);
		mTvaa2 =(TextView)this.findViewById(R.id.mTvaa2);
		
		
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
		initData();
		
		
	}


	OnClickListener listener =new  OnClickListener() {
		

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mIvtt1:
				finish();
				break;
			case R.id.mLLaa1:
				menuWindow = new SelectPicPopupWindow(chefangActivity.this, itemsOnClick);
				//显示窗口
				menuWindow.showAtLocation(chefangActivity.this.findViewById(R.id.main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                
				break;

			default:
				break;
			}
		}
	};

    //为弹出窗口实现监听类
    private OnClickListener  itemsOnClick = new OnClickListener(){

		public void onClick(View v) {
			menuWindow.dismiss();
			switch (v.getId()) {
			case R.id.mRlpw4:
				break;
			case R.id.mRlpw3:	
			startActivity(new Intent(getApplicationContext(), PingFenActivity.class));
				break;
			case R.id.mRlpw2:	
				initData();
				break;
			case R.id.mRlpw1:				
				break;
			default:
				break;
			}
		}
    };

	private DisplayImageOptions options;
    
    private void initData1() {
    	downloadsearch1("0");
    }
    public void downloadsearch1(String area11){
    	progressBar_sale.setVisibility(View.VISIBLE);

    	 RequestParams params = new RequestParams();
       List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
       nameValuePairs.add(new BasicNameValuePair("GarageID","2"));
       params.addBodyParameter(nameValuePairs);
       HttpUtils http = new HttpUtils();
       http.send(HttpRequest.HttpMethod.POST,
      		 "http://josie.i3.com.hk/FG/json/garage_detail.php",
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
    					
    						 }
    						
    						 else {
    								 Toast.makeText(getApplicationContext(),msg, 0).show();
    									progressBar_sale.setVisibility(View.GONE);
    							//new AlertInfoDialog(SaleActivity.this).show();
    						}
    					} catch (JSONException e) {
    						//new Dialog_noInternet(SaleActivity.this).show();
    						progressBar_sale.setVisibility(View.GONE);
    						 Toast.makeText(getApplicationContext(),msg, 0).show();
    						e.printStackTrace();
    					}
    						
    				
    					
    				}

    			
         
       });
    }
    

    
    
    
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
   nameValuePairs.add(new BasicNameValuePair("GarageID","2"));
   nameValuePairs.add(new BasicNameValuePair("UserID", "2"));
   
   params.addBodyParameter(nameValuePairs);
   HttpUtils http = new HttpUtils();
   http.send(HttpRequest.HttpMethod.POST,
  		 "http://josie.i3.com.hk/FG/json/garage_detail.php",
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
						 JSONObject data =jsonObject.getJSONObject("data");
						 GarageTitle=	 data.getString("GarageTitle");
						 GarageDetail=	 data.getString("GarageDetail");
						 GaragePhoto=	 data.getString("GaragePhoto");
						 PraiseNum=	 data.getString("PraiseNum");
						 BadNum=	 data.getString("BadNum");
						 mTvae1.setText(GarageTitle);
						 mTvaa1.setText(PraiseNum);
						 mTvaa2.setText(BadNum);
							initImageLoaderOptions();
							imageLoader.displayImage(GaragePhoto,
									mIvcf1, options);
                         
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
    
    
    
    
    
	private View mMenuView;
	private RelativeLayout mRlpw4;
	private RelativeLayout mRlpw3;
	private RelativeLayout mRlpw2;
	private RelativeLayout mRlpw1;
	private SelectPicPopupWindow menuWindow;

	 class SelectPicPopupWindow extends PopupWindow {



			public SelectPicPopupWindow(Activity context,OnClickListener itemsOnClick) {
				super(context);
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				mMenuView = inflater.inflate(R.layout.popwindow2, null);
				mRlpw4 = (RelativeLayout) mMenuView.findViewById(R.id.mRlpw4);
				mRlpw3 = (RelativeLayout) mMenuView.findViewById(R.id.mRlpw3);
				mRlpw2 = (RelativeLayout) mMenuView.findViewById(R.id.mRlpw2);
				mRlpw1 = (RelativeLayout) mMenuView.findViewById(R.id.mRlpw1);
				mRlpw1.setOnClickListener(itemsOnClick);
				mRlpw2.setOnClickListener(itemsOnClick);
				mRlpw3.setOnClickListener(itemsOnClick);
				mRlpw4.setOnClickListener(itemsOnClick);
				//设置SelectPicPopupWindow的View
				this.setContentView(mMenuView);
				//设置SelectPicPopupWindow弹出窗体的宽
				this.setWidth(LayoutParams.WRAP_CONTENT);
				//设置SelectPicPopupWindow弹出窗体的高
				this.setHeight(LayoutParams.WRAP_CONTENT);
				//设置SelectPicPopupWindow弹出窗体可点击
				this.setFocusable(true);
				//设置SelectPicPopupWindow弹出窗体动画效果
				this.setAnimationStyle(R.style.AnimBottom);
				//实例化一个ColorDrawable颜色为半透明
				ColorDrawable dw = new ColorDrawable(0x00000000);
				//设置SelectPicPopupWindow弹出窗体的背景
				this.setBackgroundDrawable(dw);
				//mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
				mMenuView.setOnTouchListener(new OnTouchListener() {
					
					public boolean onTouch(View v, MotionEvent event) {
						
						int height = mMenuView.findViewById(R.id.pop_layout).getTop();
						int y=(int) event.getY();
						if(event.getAction()==MotionEvent.ACTION_UP){
							if(y<height){
								dismiss();
							}
						}				
						return true;
					}
				});

			}

private Handler handler =new Handler();
	
}
}
