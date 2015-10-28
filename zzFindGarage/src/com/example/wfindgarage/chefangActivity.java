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
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.view.MyGridView;
import com.example.view.MyListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

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
	private String LocationTwo;
	private String GarageCategory;
	private String LocationOne;
	private String GarageAddress;
	private String GarageType;

	private TextView mTvae1;

	private ImageView mIvcf1;

	private TextView mTvaa1;
	private TextView mTvaa2;

	private String GarageID;

	private TextView mTvfjc1;

	private RelativeLayout mRlzz1;

	private TextView mljTv2;

	private TextView mljTv1;

	private MyListView mLv1;

	private List<Datap> mDataList_originp =new ArrayList<chefangActivity.Datap>();
	private List<Datapp> mDataList_originpp =new ArrayList<chefangActivity.Datapp>();

	private MyGridView mGvMy1;

	private LinearLayout mLLCf3;
	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.aatest);
		AppManager.getAppManager().addActivity(chefangActivity.this);
		mLLCf1 =(LinearLayout)this.findViewById(R.id.mLLCf1);
		mLLCf2 =(LinearLayout)this.findViewById(R.id.mLLCf2);
		mLLCf3 =(LinearLayout)this.findViewById(R.id.mLLCf3);
		mLLaa1 =(LinearLayout)this.findViewById(R.id.mLLaa1);
		mLLaa1.setOnClickListener(listener);
		mTvfjc1 =(TextView)this.findViewById(R.id.mTvfjc1);
		mTvfjc1.setOnClickListener(listener);
		mRlzz1 =(RelativeLayout)this.findViewById(R.id.mRlzz1);
		mGvMy1 =(com.example.view.MyGridView)this.findViewById(R.id.mGvMy1);

		
		mIvtt1 =(ImageView)this.findViewById(R.id.mIvtt1);
		mIvcf1 =(ImageView)this.findViewById(R.id.mIvcf1);
		mIvtt1.setOnClickListener(listener);
		GarageID = getIntent().getExtras().getString("GarageID");
		progressBar_sale =(ProgressBar)this.findViewById(R.id.progressBar_sale);
		progressBar_sale.setVisibility(View.GONE);
		mTvae1 =(TextView)this.findViewById(R.id.mTvae1);
		mTvaa1 =(TextView)this.findViewById(R.id.mTvaa1);
		mTvaa2 =(TextView)this.findViewById(R.id.mTvaa2);
		mljTv2 =(TextView)this.findViewById(R.id.mljTv2);
		mljTv1 =(TextView)this.findViewById(R.id.mljTv1);
		
		rg1 = (RadioGroup) this.findViewById(R.id.rg1cf);
		rb1 = (RadioButton) this.findViewById(R.id.rb1cf);
		rb2 = (RadioButton) this.findViewById(R.id.rb2cf);
		rb3 = (RadioButton) this.findViewById(R.id.rb3cf);		
	
		rg1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int cheakedId) {
				if (cheakedId == rb1.getId()) {
					mLLCf1.setVisibility(View.VISIBLE);
					mLLCf3.setVisibility(View.GONE);
					mLLCf2.setVisibility(View.GONE);
				} else if (cheakedId == rb2.getId()) {
					mLLCf1.setVisibility(View.GONE);
					mLLCf3.setVisibility(View.GONE);
					mLLCf2.setVisibility(View.VISIBLE);
					initDatap();
				}else if (cheakedId == rb3.getId()) {
					mLLCf1.setVisibility(View.GONE);
					mLLCf2.setVisibility(View.GONE);
					mLLCf3.setVisibility(View.VISIBLE);
					initDatapp();
				}
							
			}
		});
		rb1.setChecked(true);
		
		//初始化评论
		mLv1 =(com.example.view.MyListView)this.findViewById(R.id.mLv1);
		
		
		
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
			case R.id.mTvfjc1:
				ShowWindow();
				break;

			default:
				break;
			}
		}
	};

	private View layout;
	private PopupWindow mPop;
	private ListView mLvNavi;
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
	private Handler handler =new Handler()
	{
		public void handleMessage(android.os.Message msg) {
			
		};
	};
	

	
	private String UserID;

    //为弹出窗口实现监听类
    private OnClickListener  itemsOnClick = new OnClickListener(){


		public void onClick(View v) {
			menuWindow.dismiss();
			switch (v.getId()) {
			case R.id.mRlpw4:
				break;
			case R.id.mRlpw3:	
				Intent intent =new Intent(getApplicationContext(), XiePinLunActivity.class);
				intent.putExtra("GarageTitle", GarageTitle);
				intent.putExtra("GarageID", GarageID);
				
			startActivity(intent);
				break;
			case R.id.mRlpw2:	
        		SharedPreferences sharedPreferences=getSharedPreferences("USER", 
        				Activity.MODE_PRIVATE); 
        		  UserID = sharedPreferences.getString("UserID", ""); 
        	     if(!TextUtils.isEmpty(UserID)){
				initData1();
        	     }else{
    	             startActivity(new Intent(getApplicationContext(), huiyuandengluActivity.class));
        	     }
				break;
			case R.id.mIvwww1:
		         

				break;
				
			case R.id.mRlpw1:
		         intent=new Intent(Intent.ACTION_SEND); 
		         intent.setType("text/plain"); 
		         intent.putExtra(Intent.EXTRA_SUBJECT, "分享"); 
		         intent.putExtra(Intent.EXTRA_TEXT,  "FindGarage");  
		         startActivity(Intent.createChooser(intent, "分享到")); 

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
       nameValuePairs.add(new BasicNameValuePair("GarageID",GarageID));
       nameValuePairs.add(new BasicNameValuePair("UserID", UserID));
       params.addBodyParameter(nameValuePairs);
       HttpUtils http = new HttpUtils();
       http.send(HttpRequest.HttpMethod.POST,
      		 "http://josie.i3.com.hk/FG/json/garage_to_fav.php",
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
   nameValuePairs.add(new BasicNameValuePair("GarageID",GarageID));
   //nameValuePairs.add(new BasicNameValuePair("UserID", "2"));
   
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
/**
 * {"data":{"GarageTitle":"tttt","GarageType":null,"GarageAddress":null,"LocationOne":null
 * ,"PraiseNum":"2","GarageCategory":null,"GarageDetail":"<p>\r\n\teeeeeeeeee<\/p>\r\n","BadNum":"0","LocationTwo":null},"msg":"","code":"1"}
 */
						
						String string_code = jsonObject.getString("code");
						 msg = jsonObject.getString("msg");
						 JSONObject data =jsonObject.getJSONObject("data");
						 
							GarageTitle=	 data.getString("GarageTitle");
							GarageType=	 data.getString("GarageType");
							GarageAddress=	 data.getString("GarageAddress");
							LocationOne=	 data.getString("LocationOne");
							PraiseNum=	 data.getString("PraiseNum");
							GarageCategory=	 data.getString("GarageCategory");
							GarageDetail=	 data.getString("GarageDetail");
							BadNum=	 data.getString("BadNum");
							LocationTwo=	 data.getString("LocationTwo");

							String str="";
							if(!TextUtils.isEmpty(LocationOne)&&("null"!=LocationOne)){
								str =str +LocationOne;
							}
							if(!TextUtils.isEmpty(LocationTwo)&&("null"!=LocationTwo)){
								str =str +LocationTwo;
							}
							mljTv1.setText(str);
							mljTv2.setText(GarageDetail);
						 
						 
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

	private Myadapterp myadapterp;

	private Myadapterpp myadapterpp;

	 class SelectPicPopupWindow extends PopupWindow {



			private ImageView mIvwww1;

			public SelectPicPopupWindow(Activity context,OnClickListener itemsOnClick) {
				super(context);
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				mMenuView = inflater.inflate(R.layout.popwindow2, null);
				mRlpw4 = (RelativeLayout) mMenuView.findViewById(R.id.mRlpw4);
				mRlpw3 = (RelativeLayout) mMenuView.findViewById(R.id.mRlpw3);
				mRlpw2 = (RelativeLayout) mMenuView.findViewById(R.id.mRlpw2);
				mRlpw1 = (RelativeLayout) mMenuView.findViewById(R.id.mRlpw1);
				mIvwww1 =(ImageView)mMenuView.findViewById(R.id.mIvwww1);
				mIvwww1.setOnClickListener(itemsOnClick);
				mRlpw1.setOnClickListener(itemsOnClick);
				mRlpw2.setOnClickListener(itemsOnClick);
				mRlpw3.setOnClickListener(itemsOnClick);
				mRlpw4.setOnClickListener(itemsOnClick);
				//设置SelectPicPopupWindow的View
				this.setContentView(mMenuView);
				//设置SelectPicPopupWindow弹出窗体的宽
				this.setWidth(LayoutParams.FILL_PARENT);
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
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

		private void initDatap() {
			progressBar_sale.setVisibility(View.VISIBLE);
			downloadsearchp("0");
		}
		public void downloadsearchp(String area11){
			 RequestParams params = new RequestParams();
		   List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
		   nameValuePairs.add(new BasicNameValuePair("GarageID", GarageID));
		   params.addBodyParameter(nameValuePairs);
		   
		   HttpUtils http = new HttpUtils();
		   http.send(HttpRequest.HttpMethod.POST,
		  		"http://josie.i3.com.hk/FG/json/garage_comment_list.php",
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
									 mDataList_originp.clear();
									 String data1 = jsonObject.getString("data");
								//	 [{"CommentScore":"1","GarageTitle":"tttt","GarageID":"2","CommentDetail":"asdf"}]
										//JSONObject jsonObject1 = new JSONObject(data1);
										JSONArray array = new JSONArray(data1);
									// JSONArray array = jsonObject.getJSONArray(data);
									  for (int i = 0; i < array.length(); i++) {
										  
										  Datap  data=new Datap();
										 JSONObject jsonObject2 = array.getJSONObject(i);
											
										 data.CommentTitle=jsonObject2.getString("CommentTitle");
										 data.CommentDetail=jsonObject2.getString("CommentDetail");
										 data.CommentScore=jsonObject2.getString("CommentScore");
										 data.CommentScore1=jsonObject2.getString("CommentScore1");
										 data.CommentScore2=jsonObject2.getString("CommentScore2");
										 data.CommentScore3=jsonObject2.getString("CommentScore3");
										 data.CommentScore4=jsonObject2.getString("CommentScore4");
										 data.GaragePhoto=jsonObject2.getString("GaragePhoto");

										 data.CommentTitle= jsonObject2.getString("CommentTitle");
										 data.CommentDetail= jsonObject2.getString("CommentDetail");
										 data.CommentDate= jsonObject2.getString("CommentDate");
										 data.CommentScore= jsonObject2.getString("CommentScore");
										 data.CommentScore1= jsonObject2.getString("CommentScore1");
										 data.CoverPic= jsonObject2.getString("GaragePhoto");
										 mDataList_originp.add(data);
									}
										progressBar_sale.setVisibility(View.GONE);
									  initListView();
								}
								 else {
										progressBar_sale.setVisibility(View.GONE);

									//new AlertInfoDialog(SaleActivity.this).show();
								}
							} catch (JSONException e) {
										progressBar_sale.setVisibility(View.GONE);
							}
						}

					
		     
		   });
		}
		private void initListView() {
		       myadapterp = new Myadapterp();
		       
		       mLv1.setAdapter(myadapterp);
		       mLv1.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent,
							View view, int position, long id) {
						
						Intent intent =new Intent(getApplicationContext(), NewDiscussXiangqing.class);
						intent.putExtra("CommentTitle", mDataList_originp.get(position).CommentTitle);
						intent.putExtra("CommentDetail", mDataList_originp.get(position).CommentDetail);
						intent.putExtra("CommentScore", mDataList_originp.get(position).CommentScore);
						intent.putExtra("CommentScore1", mDataList_originp.get(position).CommentScore1);
						intent.putExtra("CommentScore2", mDataList_originp.get(position).CommentScore2);
						intent.putExtra("CommentScore3", mDataList_originp.get(position).CommentScore3);
						intent.putExtra("CommentScore4", mDataList_originp.get(position).CommentScore4);
						startActivity(intent);

					}
				});
			}
		class Holder{
			TextView mTvri10,mTvri11,mTvri12,mTvri14;
			ImageView imageView,mIvll3;
		}
		class  Myadapterp extends   BaseAdapter{
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				        
				
				Holder holder = null;
				if(convertView==null){
					convertView = LayoutInflater.from(getApplicationContext())
							.inflate(R.layout.item_listview_3p, null);
					holder = new Holder();
					holder.mTvri10 =(TextView)convertView.findViewById(R.id.mTvri10);
					holder.mTvri11 =(TextView)convertView.findViewById(R.id.mTvri11);
					holder.mTvri12 =(TextView)convertView.findViewById(R.id.mTvri12);
					holder.mTvri14 =(TextView)convertView.findViewById(R.id.mTvri14);
					
					holder.imageView =(ImageView)convertView.findViewById(R.id.iv_listview_rent_pic);
					holder.mIvll3 =(ImageView)convertView.findViewById(R.id.mIvll3);
					convertView.setTag(holder);

				}else{
					holder =(Holder)convertView.getTag();
				}
				if(mDataList_originp.get(position).CommentScore.equals("-1")){
					holder.mIvll3.setImageResource(R.drawable.bba3);
				}
				if(mDataList_originp.get(position).CommentScore.equals("1")){
					holder.mIvll3.setImageResource(R.drawable.bba1);
				}
				if(mDataList_originp.get(position).CommentScore.equals("-1")){
					holder.mIvll3.setImageResource(R.drawable.ok2);
				}
				holder.mTvri12.setText(mDataList_originp.get(position).CommentTitle);
				holder.mTvri11.setText(mDataList_originp.get(position).CommentDetail);
				holder.mTvri14.setText(mDataList_originp.get(position).CommentDate);
			//	initImageLoaderOptions();
				//imageLoader.displayImage(mDataList.get(position).CoverPic,holder.imageView, options);
				
				return convertView;

			}
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mDataList_originp.size();
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
		class Datap{
			String   ID;
			String   CommentDate;
			String   CoverPic;
			String CommentTitle;
			String CommentDetail;
			String CommentScore;
			String CommentScore1;
			String CommentScore2;
			String CommentScore3;
			String CommentScore4;
			String GaragePhoto;

		}

		
		
		
		
		
		
		
		
		
		
		
		

		private void initDatapp() {
			progressBar_sale.setVisibility(View.VISIBLE);

			downloadsearchpp("0");
		}
		public void downloadsearchpp(String area11){
			 RequestParams params = new RequestParams();
		   List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
		   nameValuePairs.add(new BasicNameValuePair("GarageID", GarageID));
		   params.addBodyParameter(nameValuePairs);
		   HttpUtils http = new HttpUtils();
		   http.send(HttpRequest.HttpMethod.POST,
		  		 "http://josie.i3.com.hk/FG/json/garage_photo.php",
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
									 mDataList_originpp.clear();
									 JSONArray array = jsonObject.getJSONArray("data");
									  for (int i = 0; i < array.length(); i++) {
										  Datapp  data=new Datapp();
										 JSONObject jsonObject2 = array.getJSONObject(i);
										 data.CoverPic=jsonObject2.getString("PhotoPath");
										 mDataList_originpp.add(data);
									}
										progressBar_sale.setVisibility(View.GONE);
										initListViewpp();
									  }
								 else {
									 progressBar_sale.setVisibility(View.GONE);
									 Toast.makeText(getApplicationContext(), "暫無相關內容", 0).show();

								}
							} catch (JSONException e) {
							}
						}
		   });
		}
		private void initListViewpp() {
		       myadapterpp = new Myadapterpp();
		       mGvMy1.setAdapter(myadapterpp);
		       mGvMy1.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent,
							View view, int position, long id) {
/*						Intent intent =new Intent(getApplicationContext(), chefangActivity.class);
						intent.putExtra("GarageID", mDataListpp.get(position+1).ID);
						startActivity(intent);
*/					}
				});
		    
			}
		class Holderpp{
			TextView mTvri10,mTvri11,mTvri12;
			ImageView imageView;
		}
		class  Myadapterpp extends   BaseAdapter{
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				        
				
				Holderpp holder = null;
				if(convertView==null){
					convertView = LayoutInflater.from(getApplicationContext())
							.inflate(R.layout.item_gridview_3, null);
					holder = new Holderpp();
					holder.imageView =(ImageView)convertView.findViewById(R.id.iv_listview_rent_pic);
					convertView.setTag(holder);

				}else{
					holder =(Holderpp)convertView.getTag();
				}
				initImageLoaderOptions();
				imageLoader.displayImage(mDataList_originpp.get(position+1).CoverPic,
						holder.imageView, options);
				
				return convertView;

			}
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mDataList_originpp.size()-1;
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
		class Datapp{
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
		
		
		
		
		
		
		
	 
	 
	 
	 
	 
	 
}
