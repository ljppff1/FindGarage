package com.example.wfindgarage;

import java.util.ArrayList;

import com.tianshicoffeeom.app.imgscroll.MyImgScroll;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class Main1Activity extends Activity {
	


	private MyImgScroll myPager;
	private LinearLayout ovalLayout;
	private ArrayList<View> listViews;
    private int[] a ={R.string.b1,R.string.b2,R.string.b3,R.string.b4,R.string.b5,R.string.b6,R.string.b7,R.string.b8,R.string.b9,R.string.b10,R.string.b11,R.string.b12,};
    private int[] b ={R.drawable.bb1,R.drawable.bb2,R.drawable.bb10,R.drawable.bb11,R.drawable.bb6,R.drawable.bb9};
    private int[] b1 ={R.drawable.bb8,R.drawable.bb12,R.drawable.bb4,R.drawable.bb3,R.drawable.bb5};
	private Myadapter adapter;
	private com.example.view.HorizontalListViewAdapter hListViewAdapter;
	private com.example.view.HorizontalListView hListView;
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
		hListView = (com.example.view.HorizontalListView)findViewById(R.id.horizon_listview);
	
		String[] titles = {"車房搜尋", "附近車房","我的房車", "最新評論", "排行榜","FACEBOOK專页"};
		String[] titles1 ={"推廣優惠", "汽車誌","聯系我們","提供車房資料","設定"};
		hListViewAdapter = new com.example.view.HorizontalListViewAdapter(getApplicationContext(),titles,b,titles1,b1);
		hListView.setAdapter(hListViewAdapter);

		/*mGvm1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					startActivity(new Intent(getApplicationContext(), SearchYaocheActivity.class));
					break;
				case 1:
					startActivity(new Intent(getApplicationContext(), JianJiechefangActivity.class));
					break;
				case 2:
					startActivity(new Intent(getApplicationContext(), chefangActivity.class));
					break;
				case 3:
					startActivity(new Intent(getApplicationContext(), NewNoticeActivity.class));
					break;
				case 4:
					startActivity(new Intent(getApplicationContext(), NewDiscussActivity.class));
					break;
				case 5:
					startActivity(new Intent(getApplicationContext(), fujinchefangActivity.class));
					break;
				case 6:
					startActivity(new Intent(getApplicationContext(), MyZiliaoActivity.class));
					break;
				case 7:
					startActivity(new Intent(getApplicationContext(), MeiYuePaiHangActivity.class));
					break;
				case 11:
					startActivity(new Intent(getApplicationContext(), ZiliaochefangActivity.class));
     
					
					break;

				default:
					break;
				}
			}
		});
		adapter= new Myadapter();
		mGvm1.setAdapter(adapter);*/
		
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
