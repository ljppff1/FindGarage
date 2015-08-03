package com.example.wfindgarage;

import java.util.ArrayList;

import com.tianshicoffeeom.app.imgscroll.MyImgScroll;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class Main1Activity extends Activity {
	


	private MyImgScroll myPager;
	private LinearLayout ovalLayout;
	private ArrayList<View> listViews;
	private GridView mGvm1;
    private int[] a ={R.string.b1,R.string.b2,R.string.b3,R.string.b4,R.string.b5,R.string.b6,R.string.b7,R.string.b8,R.string.b9,R.string.b10,R.string.b11,R.string.b12,};
    private int[] b ={R.drawable.bb1,R.drawable.bb2,R.drawable.bb3,R.drawable.bb4,R.drawable.bb5,R.drawable.bb6,R.drawable.bb7,R.drawable.bb8,R.drawable.bb9,R.drawable.bb10,R.drawable.bb11,R.drawable.bb12};
	private Myadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();

    }
	private void initView() {
		myPager = (MyImgScroll)this. findViewById(R.id.myvp);
		ovalLayout = (LinearLayout) this. findViewById(R.id.vb);
		InitViewPager();//初始化图片
		//开始滚动
		myPager.start(Main1Activity.this, listViews, 4000, ovalLayout,
				R.layout.ad_bottom_item, R.id.ad_item_v,
				R.drawable.dot_focused, R.drawable.dot_normal);
		mGvm1 =(GridView)this.findViewById(R.id.mGvm1);
		adapter= new Myadapter();
		mGvm1.setAdapter(adapter);
		
	}
	class Holder{
		TextView mTvri12;
		ImageView imageView;
	}

	class  Myadapter extends   BaseAdapter{
		@SuppressLint("NewApi")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			        
			
			Holder holder = null;
			if(convertView==null){
				convertView = LayoutInflater.from(Main1Activity.this)
						.inflate(R.layout.item_gridview_1, null);
				holder = new Holder();
				holder.mTvri12 =(TextView)convertView.findViewById(R.id.mTvri12);
				holder.imageView =(ImageView)convertView.findViewById(R.id.iv_listview_rent_pic);
				convertView.setTag(holder);
	
			}else{
				holder =(Holder)convertView.getTag();
			}
			holder.mTvri12.setText(a[position]);
			holder.imageView.setImageResource(b[position]);
			return convertView;

		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return a.length;
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

	
	private void InitViewPager() {
		listViews = new ArrayList<View>();
		int[] imageResId = new int[] { R.drawable.a, R.drawable.c,
				 R.drawable.a, R.drawable.c,  R.drawable.a };
		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(getApplicationContext());
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {// 设置图片点击事件
				}
			});
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			listViews.add(imageView);
		}
	}


}
