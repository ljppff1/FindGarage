package com.example.wfindgarage;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager.LayoutParams;
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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class SearchYaocheActivity extends BaseActivity {

	private ImageView mIvtt1;
	private LinearLayout mLLnn1;
	private RelativeLayout mRlnn1;
	private RelativeLayout mRlNn3;
	private ArrayList<Data> mDataList_origin=new ArrayList<SearchYaocheActivity.Data>();
	private ArrayList<Data> mDataList=new ArrayList<SearchYaocheActivity.Data>();
	private SharedPreferences sp;
	private TextView mTVnn1;
	private String item_first;
	private LinearLayout mLLnn2;
	private TextView mTVnn2;
	private LinearLayout mLLnn5;
	private Button mBtn1;
	private Button mBtn2;
	private EditText mEtnn5;
	private String item_second;
	private View mV1;
	private View mV2;
	private RelativeLayout mRlzz1;
	private TextView mTvfjc1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.newnotice1);
		 sp =getSharedPreferences("SEARCH", 0);
		mIvtt1 =(ImageView)this.findViewById(R.id.mIvtt1);
		mIvtt1.setOnClickListener(listener);
		mLLnn1 =(LinearLayout)this.findViewById(R.id.mLLnn1);
		mLLnn1.setOnClickListener(listener);
		mLLnn2 =(LinearLayout)this.findViewById(R.id.mLLnn2);
		mLLnn2.setOnClickListener(listener);
		mLLnn5 =(LinearLayout)this.findViewById(R.id.mLLnn5);
		mLLnn5.setOnClickListener(listener);
		mBtn1 =(Button)this.findViewById(R.id.mBtn1);
		mBtn2 =(Button)this.findViewById(R.id.mBtn2);
		mBtn1.setOnClickListener(listener);
		mBtn2.setOnClickListener(listener);
		mEtnn5 =(EditText)this.findViewById(R.id.mEtnn5);
		mRlnn1 =(RelativeLayout)this.findViewById(R.id.mRlnn1);
		mRlNn3 =(RelativeLayout)this.findViewById(R.id.mRlNn3);
		mTVnn1 =(TextView)this.findViewById(R.id.mTVnn1);
		mTVnn2 =(TextView)this.findViewById(R.id.mTVnn2);
		mTvfjc1 =(TextView)this.findViewById(R.id.mTvfjc1);
		mTvfjc1.setOnClickListener(listener);
		mRlzz1 =(RelativeLayout)this.findViewById(R.id.mRlzz1);
		mV1 =this.findViewById(R.id.mV1);
	}
	
	
	OnClickListener listener =new  OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mTvfjc1:
				ShowWindow();
				break;
			case R.id.mV2:
           	 popupWindow.dismiss();  
				break;
			case R.id.mIvtt1:
				finish();
				break;
			case R.id.mLLnn1:
				showWindow(1);
				break;
			case R.id.mLLnn2:
				if(item_first!=null){
				showWindow(2);
				}else{
					Toast.makeText(getApplicationContext(), getString(R.string.abc31), 0).show();
				}
				break;
			case R.id.mBtn1:
               item_first="";
              	mTVnn1.setText(getResources().getString(R.string.t1));
               	mTVnn2.setText(getResources().getString(R.string.t2));
               	mEtnn5.setText("");

				break;
			case R.id.mBtn2:
				Intent intent =new Intent(getApplicationContext(),SearchResultActivity.class);
				intent.putExtra("item_first",item_first );
				intent.putExtra("item_second",item_second );
				intent.putExtra("item_third",mEtnn5.getEditableText().toString() );
				
               startActivity(intent);
					break;

			default:
				break;
			}
		}
	};
	

	private View layout;
	private PopupWindow mPop;
	private View mV1a;
	private View mV2a;
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
		 mV1a =layout.findViewById(R.id.mV1);
		 mV2a =layout.findViewById(R.id.mV2);
		 mV1a.setOnClickListener(listener1);
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
		 mV2a.setOnClickListener(listener1);
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
	
	private PopupWindow popupWindow;
	private View view;
	private ListView mLvp1;
	private ProgressBar progressBar_sale;
	private Myadapter myadapter;
	private ProgressBar progressBar_salew1;
	private int i1;
	private void showWindow(int i1) {
		this.i1 =i1;
		//找到设置好的layout
		 view = LayoutInflater.from(SearchYaocheActivity.this).inflate(R.layout.popup, null);
		     float scale =getResources().getDisplayMetrics().density;  
				mV2 =(View)view.findViewById(R.id.mV2);
				mV2.setOnClickListener(listener);

				// TODO Auto-generated method stub
				int screenWidth = SearchYaocheActivity.this.getWindowManager().getDefaultDisplay().getWidth()-mRlnn1.getWidth(); 
				  //  int screenHeigh = (int) (SearchYaocheActivity.this.getWindowManager().getDefaultDisplay().getHeight()-   ((int) scale*100+0.5f)); 
				    int screenHeigh = (int) (SearchYaocheActivity.this.getWindowManager().getDefaultDisplay().getHeight()); 
				
			    View downView = (RelativeLayout)view.findViewById(R.id.mLayout);
			    //启动popupWindow；
			//	PopupWindowUtils popu = new PopupWindowUtils(MainActivity.this, screenWidth, screenHeigh, downView,view);
				 popupWindow = new PopupWindow(view, screenWidth, screenHeigh);
					// view.setBackgroundResource(R.drawable.popupwindow);
					 /***  这2句很重要   ***/
					 ColorDrawable cd = new ColorDrawable(-0000);  
					 popupWindow.setBackgroundDrawable(cd);  
					 
					// popupWindow.showAsDropDown(down);
					 popupWindow.setFocusable(true);   
					 popupWindow.setOutsideTouchable(true);//设置外部能点击
					// popupWindow.showAtLocation(downView, Gravity.BOTTOM, 0, 0); 
					// popupWindow.showAsDropDown(bt);
					  int[] location = new int[2];  
					  int[] location1 = new int[2];  
					  mRlnn1.getLocationOnScreen(location);  
					  mV1.getLocationOnScreen(location1);
					 popupWindow.showAtLocation(findViewById(R.id.mRlnnpar1), Gravity.BOTTOM, location[0]+mRlnn1.getWidth(), location[1]);  
					 popupWindow.setAnimationStyle(R.style.PopupAnimation);
					 popupWindow.update();
					 mLvp1 =(ListView)view.findViewById(R.id.mLvp1);
					 //mEtp1 =(EditText)downView.findViewById(R.id.mEtp1);
					 progressBar_salew1 =(ProgressBar)view.findViewById(R.id.progressBar_sale);
					 if(i1==1){
					 downloadsearchw1("");
                  }
					 if(i1 ==2){
                	  downloadsearchw2("");
                  }
					//setting popupWindow d点击消失
					 popupWindow.setTouchInterceptor(new View.OnTouchListener() {  
				         @Override
						public boolean onTouch(View v, MotionEvent event) {  
				             /****   如果点击了popupwindow的外部，popupwindow也会消失 ****/  
				             if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {  
				            	 popupWindow.dismiss();  
				                 return true;   
				             }  
				             return false;  
				         }  
					 });
	}
	public void downloadsearchw1(String area11){
		 RequestParams params = new RequestParams();
	   List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
	   params.addBodyParameter(nameValuePairs);
	   HttpUtils http = new HttpUtils();
	   http.send(HttpRequest.HttpMethod.POST,
	  		 "http://josie.i3.com.hk/FG/json/locationonelist.php",
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
									  Data  data=new Data();
									 JSONObject jsonObject2 = array.getJSONObject(i);
									 data.ID= jsonObject2.getString("LocationOneID");
									 data.Name= jsonObject2.getString("LocationOneName");
									 mDataList_origin.add(data);
									 
			                          data.toString();						 
								}
								  mDataList.clear();
								  mDataList.addAll(mDataList_origin);
								  if(popupWindow.isShowing()){
								  progressBar_salew1.setVisibility(View.GONE);
								  initListView();
								  }
							}
							 else {
								//new AlertInfoDialog(SaleActivity.this).show();
								 Toast.makeText(getApplicationContext(), getString(R.string.abc32), 0).show();
							 popupWindow.dismiss();  

							}
						} catch (JSONException e) {
							 if(mDataList.isEmpty())
							//new Dialog_noInternet(SaleActivity.this).show();
								 Toast.makeText(getApplicationContext(), getString(R.string.abc32), 0).show();
							 popupWindow.dismiss();  
							e.printStackTrace();
						}
					}
	   });
	}
	public void downloadsearchw2(String area11){
		 RequestParams params = new RequestParams();
	   List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
	   nameValuePairs.add(new BasicNameValuePair("LocationOneID", item_first));
	   params.addBodyParameter(nameValuePairs);
	   HttpUtils http = new HttpUtils();
	   http.send(HttpRequest.HttpMethod.POST,
	  		 "http://josie.i3.com.hk/FG/json/locationtwolist.php",
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
									  Data  data=new Data();
									 JSONObject jsonObject2 = array.getJSONObject(i);
									 data.ID= jsonObject2.getString("LocationTwoID");
									 data.Name= jsonObject2.getString("LocationTwoName");
									 mDataList_origin.add(data);
									 
			                          data.toString();						 
								}
								  mDataList.clear();
								  mDataList.addAll(mDataList_origin);
								  if(popupWindow.isShowing()){
								  progressBar_salew1.setVisibility(View.GONE);
								  initListView();
								  }
							}
							 else {
								//new AlertInfoDialog(SaleActivity.this).show();
								 Toast.makeText(getApplicationContext(), getString(R.string.abc32), 0).show();
								 popupWindow.dismiss();  
							}
						} catch (JSONException e) {
							 if(mDataList.isEmpty())
							//new Dialog_noInternet(SaleActivity.this).show();
								 Toast.makeText(getApplicationContext(), getString(R.string.abc32), 0).show();
							 popupWindow.dismiss();  
							e.printStackTrace();
						}
					}
	   });
	}

	private void initListView() {
	       myadapter = new Myadapter();
	       mLvp1.setAdapter(myadapter);
	       mLvp1.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent,
						View view, int position, long id) {
				//	startActivity(new Intent(getApplicationContext(), chefangActivity.class));
/*					SharedPreferences.Editor editor = sp.edit();
					editor.putString("FIRSTID", mDataList.get(position).ID);
					editor.commit();
*/		           if(i1==1){
	mTVnn1.setText(mDataList.get(position).Name);
	item_first= mDataList.get(position).ID ;
   }  if(i1==2){
		mTVnn2.setText(mDataList.get(position).Name);
		item_second= mDataList.get(position).ID ;
      }
 
	            	 popupWindow.dismiss();  

					}
			});
	    
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
						.inflate(R.layout.item_listview_win1, null);
				holder = new Holder();
				holder.mTvri11 =(TextView)convertView.findViewById(R.id.mTvwin1a1);
				
				convertView.setTag(holder);

			}else{
				holder =(Holder)convertView.getTag();
			}
			holder.mTvri11.setText(mDataList.get(position).Name);

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
	

	
private Handler handler =new Handler();
	
}
