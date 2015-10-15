package com.example.wfindgarage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class XiePinLunActivity extends BaseActivity {

	private ImageView mIvtt1;
	private LinearLayout mWhatbad;
	private String GarageTitle;
	private TextView mTv1;
	private EditText mEtxp1;
	private LinearLayout mLLwhat1;
	private Button ui_login_btn;
	private ProgressBar progressBar_sale;
	private String UserID;
	private String GarageID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.xiepinlun);
		SharedPreferences mySharedPreferences= getSharedPreferences("USER", Activity.MODE_PRIVATE); 
		SharedPreferences.Editor editor = mySharedPreferences.edit(); 
		editor.putString("content", ""); 
		editor.putString("fen", ""); 
		
		editor.commit(); 
		UserID=	mySharedPreferences.getString("UserID", "");
		
		progressBar_sale=(ProgressBar)this.findViewById(R.id.progressBar_sale);
		progressBar_sale.setVisibility(View.GONE);
		
		GarageTitle =getIntent().getExtras().getString("GarageTitle");
		GarageID =getIntent().getExtras().getString("GarageID");
		
		mIvtt1 =(ImageView)this.findViewById(R.id.mIvtt1);
		mIvtt1.setOnClickListener(listener);
		mWhatbad=(LinearLayout)this.findViewById(R.id.mWhatbad);
		mWhatbad.setOnClickListener(listener);
		mTv1 =(TextView)this.findViewById(R.id.mTv1);
		mTv1.setText(GarageTitle);
		mEtxp1 =(EditText)this.findViewById(R.id.mEtxp1);
		
		mLLwhat1=(LinearLayout)this.findViewById(R.id.mLLwhat1);
		mLLwhat1.setOnClickListener(listener);

		ui_login_btn =(Button)this.findViewById(R.id.ui_login_btn);
		ui_login_btn.setOnClickListener(listener);
		
	}
	private String con;

	private String fen;
	private String Path;
	private com.example.view.LoadingDialog dialogll;
	private String Coms1;
	private String Coms2;
	private String Coms3;
	private String Coms4;

	OnClickListener listener =new  OnClickListener() {
		




		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mIvtt1:
				finish();
				break;
			case R.id.mWhatbad:
				startActivity(new Intent(getApplicationContext(), PingFenActivity.class));
				break;
			case R.id.mLLwhat1:
				
             
				startActivity(new Intent(getApplicationContext(), PinLunXiangQingActivity.class));
				break;
			case R.id.ui_login_btn:
				SharedPreferences mySharedPreferences= getSharedPreferences("USER", Activity.MODE_PRIVATE); 
				 con=	mySharedPreferences.getString("content", ""); 
				 Path=	mySharedPreferences.getString("Path", ""); 
                 fen=mySharedPreferences.getString("fen", ""); 
                 Coms1=mySharedPreferences.getString("Coms1", ""); 
                 Coms2=mySharedPreferences.getString("Coms2", ""); 
                 Coms3=mySharedPreferences.getString("Coms3", ""); 
                 Coms4=mySharedPreferences.getString("Coms4", ""); 

				if(TextUtils.isEmpty(mEtxp1.getEditableText().toString())){
					Toast.makeText(getApplicationContext(), R.string.ze17, 0).show();
				}else if(TextUtils.isEmpty(con)){
					Toast.makeText(getApplicationContext(), R.string.ze18, 0).show();
				}else if(TextUtils.isEmpty(fen)){
					Toast.makeText(getApplicationContext(), R.string.ze21, 0).show();
				}else{
					if(TextUtils.isEmpty(Path)){
					initData();
					}else{
						dialogll = new com.example.view.LoadingDialog(XiePinLunActivity.this, getString(R.string.ze26));
						dialogll.show();

						new Thread(new Runnable() {
							
							@Override
							public void run() {
								Looper.prepare(); 
								String actionUrl ="http://josie.i3.com.hk/FG/json/comment_post.php";
								Map<String, String> params = new HashMap<String, String>();
								params.put("UserID", UserID);
								params.put("GarageID", GarageID);
								
								params.put("CommentTitle", mEtxp1.getEditableText().toString());
								params.put("CommentScore",fen);
								params.put("CommentScore1",Coms1);
								params.put("CommentScore2",Coms2);
								params.put("CommentScore3",Coms3);
								params.put("CommentScore4",Coms4);
								params.put("CommentDetail",con);
								params.put("SPhotoNum", "1");
								Map<String, File> files = new HashMap<String, File>();
								files.put( new File(Path).getName(), new File(Path));

								try {
									com.example.utils.HttpFileUpTool.post(actionUrl, params, files,handler);
								} catch (IOException e) {
									Message msg =new Message();
									msg.what=2;
									msg.obj=e.getMessage().toString();
								    handler.sendMessage(msg);
								}
							}
						}).start();

					}
				}
				break;

			default:
				break;
			}
		}
	};
	

private void initData() {
	downloadsearch("0");
}
public void downloadsearch(String area11){
	progressBar_sale.setVisibility(View.VISIBLE);
	 RequestParams params = new RequestParams();
   List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
   nameValuePairs.add(new BasicNameValuePair("UserID", UserID));
   nameValuePairs.add(new BasicNameValuePair("GarageID",GarageID));
   nameValuePairs.add(new BasicNameValuePair("CommentTitle", mEtxp1.getEditableText().toString()));
   nameValuePairs.add(new BasicNameValuePair("CommentScore",fen));
   nameValuePairs.add(new BasicNameValuePair("CommentScore1",Coms1));
   nameValuePairs.add(new BasicNameValuePair("CommentScore2",Coms2));
   nameValuePairs.add(new BasicNameValuePair("CommentScore3",Coms3));
   nameValuePairs.add(new BasicNameValuePair("CommentScore4",Coms4));
   nameValuePairs.add(new BasicNameValuePair("CommentDetail",con));   
   params.addBodyParameter(nameValuePairs);
   
  
   
   HttpUtils http = new HttpUtils();
   http.send(HttpRequest.HttpMethod.POST,
  		 "http://josie.i3.com.hk/FG/json/comment_post.php",
           params,
           new RequestCallBack<String>() {

				private String msg;

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
						}
					} catch (JSONException e) {
						 Toast.makeText(getApplicationContext(),msg, 0).show();
							progressBar_sale.setVisibility(View.GONE);

						e.printStackTrace();
					}
					
				}
   });
}
   
	
private Handler handler =new Handler(){

	public void handleMessage(android.os.Message msg) {
	JSONObject jsonObject;
	switch (msg.what) {
	case 2:
		dialogll.dismiss();

		Log.e("MY", (String) msg.obj);
		 Toast.makeText(getApplicationContext(),(String)msg.obj, 0).show();
      break;
	case 1:
		dialogll.dismiss();

		String str = (String)msg.obj;
		Log.e("MY1", (String) msg.obj);
        
	    try {
			jsonObject = new JSONObject(str);
			String string_code = jsonObject.getString("code");
			String msg1 = jsonObject.getString("msg");
			 int  num_code=Integer.valueOf(string_code);
			 if (num_code==1) {
			 Toast.makeText(getApplicationContext(), msg1, 1).show();
			 }else{
				 Toast.makeText(getApplicationContext(), msg1, 1).show();

			 }
	    }catch (Exception e) {

	    }

     default:
     break;
}
	}
};


}


