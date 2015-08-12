package com.example.fragment;import java.util.ArrayList;import java.util.List;import org.apache.http.NameValuePair;import org.apache.http.message.BasicNameValuePair;import org.json.JSONArray;import org.json.JSONException;import org.json.JSONObject;import com.example.wfindgarage.R;import com.lidroid.xutils.HttpUtils;import com.lidroid.xutils.exception.HttpException;import com.lidroid.xutils.http.RequestParams;import com.lidroid.xutils.http.ResponseInfo;import com.lidroid.xutils.http.callback.RequestCallBack;import com.lidroid.xutils.http.client.HttpRequest;import com.nostra13.universalimageloader.core.DisplayImageOptions;import com.nostra13.universalimageloader.core.ImageLoader;import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;import android.app.AlertDialog;import android.app.AlertDialog.Builder;import android.content.DialogInterface;import android.graphics.Bitmap;import android.os.Bundle;import android.support.annotation.Nullable;import android.support.v4.app.Fragment;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.view.View.OnClickListener;import android.widget.AdapterView;import android.widget.BaseAdapter;import android.widget.ImageView;import android.widget.ListView;import android.widget.ProgressBar;import android.widget.TextView;import android.widget.Toast;import android.widget.AdapterView.OnItemClickListener;public class FragmentRegister extends Fragment  {private TextView mTvreg2;private TextView mTvreg1;@Override@Nullablepublic View onCreateView(LayoutInflater inflater,		@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {		View view =inflater.inflate(R.layout.fragmentregister, container, false);		mTvreg2 = (TextView)view.findViewById(R.id.mTvreg2);		mTvreg2.setOnClickListener(listener);		mTvreg1 = (TextView)view.findViewById(R.id.mTvreg1);		mTvreg1.setOnClickListener(listener);					return view;}OnClickListener listener =new OnClickListener() {		@Override	public void onClick(View v) {		switch (v.getId()) {		case R.id.mTvreg2:			showDialog();			break;		case R.id.mTvreg1:			showDialog1();			break;		default:			break;		}	}}; private void showDialog() {    Builder builder=new Builder(getActivity());    //设置对话框的图标    builder.setIcon(R.drawable.ic_launcher);    //设置对话框的标题    builder.setTitle("請選擇出生年份");    //0: 默认第一个单选按钮被选中    builder.setSingleChoiceItems(R.array.years, 0, new DialogInterface.OnClickListener(){        public void onClick(DialogInterface dialog, int which) {            String hoddy=getResources().getStringArray(R.array.years)[which];            mTvreg2.setText(""+hoddy);        }    });        //添加一个确定按钮    builder.setPositiveButton(" 確 定 ", new DialogInterface.OnClickListener(){        public void onClick(DialogInterface dialog, int which) {                    }    });    //创建一个单选按钮对话框    AlertDialog dialog = builder.create();    dialog.show();}private void showDialog1() {    Builder builder=new Builder(getActivity());    //设置对话框的图标    builder.setIcon(R.drawable.ic_launcher);    //设置对话框的标题    builder.setTitle("請選擇稱謂");    //0: 默认第一个单选按钮被选中    builder.setSingleChoiceItems(R.array.people, 0, new DialogInterface.OnClickListener(){        public void onClick(DialogInterface dialog, int which) {            String hoddy=getResources().getStringArray(R.array.people)[which];            mTvreg1.setText(""+hoddy);        }    });        //添加一个确定按钮    builder.setPositiveButton(" 確 定 ", new DialogInterface.OnClickListener(){        public void onClick(DialogInterface dialog, int which) {                    }    });    //创建一个单选按钮对话框    AlertDialog dialog = builder.create();    dialog.show();}   }