package com.example.wfindgarage;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import com.example.view.OutlineContainer;
import com.example.view.pager.ADInfo;
import com.example.view.pager.ViewFactory;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.tianshicoffeeom.app.imgscroll.MyImgScroll;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import com.example.view.pager.*;
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
    private List<String> list =new ArrayList<String>();
    private List<String> list1 =new ArrayList<String>();
    private int preSelImgIndex = 0;
	private DisplayImageOptions options;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private com.example.view.MyGallery gallery;
	private LinearLayout ll_focus_indicator_container;
	private RelativeLayout mRltitle;
	private static int PAGER_START_PLAY = 0x123;
	private static final int PLAY_TIME = 3 * 1000;
	private LinearLayout symbolContainer;
	private ImageView[] circleSymbols;
	private ImageView[] images;
	private com.example.view.JazzyViewPager mViewPaper;
	private List<ImageView> views = new ArrayList<ImageView>();

	private RelativeLayout mRltitle1;
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (PAGER_START_PLAY == msg.what) {
				int current = mViewPaper.getCurrentItem();
				if (current == images.length - 1) {
					current = -1;
				}
				mViewPaper.setCurrentItem(current + 1);
				mHandler.sendEmptyMessageDelayed(PAGER_START_PLAY,
						PLAY_TIME);
			}
		}
	};
	private com.example.view.pager.CycleViewPager cycleViewPager;
	private List<com.example.view.pager.ADInfo> infos = new ArrayList<com.example.view.pager.ADInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
	   downloadnavigation();
    }
    private void downloadnavigation() {
		new DownLoadAsyTask1().execute("http://josie.i3.com.hk/FG/json/photolist.php?ct=1");
	}
    
    class DownLoadAsyTask1 extends AsyncTask<String, Void, String>{  
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String string_code = jsonObject.getString("code");
				 int  num_code=Integer.valueOf(string_code);
				 if (num_code==1) {
					 JSONArray array = jsonObject.getJSONArray("data");
					  for (int i = 0; i < array.length(); i++) {
							JSONObject jsonObject2 = array.getJSONObject(i);
							String CoverPic=jsonObject2.getString("photo");
                            String PhotoLink =jsonObject2.getString("link");
					   	    list.add(CoverPic);	 
						    list1.add(PhotoLink);
				              	}

						
						initViews();
						
						
						
						
						
				}
				 else {}
			} catch (JSONException e) {
			}
				
		}
			
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return com.example.utils.getJson.getData(str);
			}
			}
    @SuppressLint("NewApi")
	private void initViews() {

		
		cycleViewPager = (com.example.view.pager.CycleViewPager) getFragmentManager()
				.findFragmentById(R.id.fragment_cycle_viewpager_content);
		
		for(int i = 0; i < list.size(); i ++){
			com.example.view.pager.ADInfo info = new com.example.view.pager.ADInfo();
			info.setUrl(list.get(i));
			info.setContent("图片-->" + i );
			infos.add(info);
		}
		
		// 将最后一个ImageView添加进来
		views.add(ViewFactory.getImageView(this, infos.get(infos.size() - 1).getUrl()));
		for (int i = 0; i < infos.size(); i++) {
			views.add(ViewFactory.getImageView(this, infos.get(i).getUrl()));
		}
		// 将第一个ImageView添加进来
		views.add(ViewFactory.getImageView(this, infos.get(0).getUrl()));
		
		// 设置循环，在调用setData方法前调用
		cycleViewPager.setCycle(true);

		// 在加载数据前设置是否循环
		cycleViewPager.setData(views, infos, mAdCycleViewListener);
		//设置轮播
		cycleViewPager.setWheel(true);

	    // 设置轮播时间，默认5000ms
		cycleViewPager.setTime(2000);
		//设置圆点指示图标组居中显示，默认靠右
		cycleViewPager.setIndicatorCenter();
	
}

	private com.example.view.pager.CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new com.example.view.pager.CycleViewPager.ImageCycleViewListener() {

		@Override
		public void onImageClick(ADInfo info, int position, View imageView) {
			if (cycleViewPager.isCycle()) {
				position = position - 1;
				Intent it = new Intent( Intent.ACTION_VIEW );
				it.setData( Uri.parse(list1.get(position)) ); //这里面是需要调转的rul
				it = Intent.createChooser( it, null );
				startActivity( it );							
			}
			
		}

	};

 /*   
    private void downloadnavigation() {
		new DownLoadAsyTask1().execute("http://josie.i3.com.hk/FG/json/photolist.php?ct=1");
	}
    
    class DownLoadAsyTask1 extends AsyncTask<String, Void, String>{  
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String string_code = jsonObject.getString("code");
				 int  num_code=Integer.valueOf(string_code);
				 if (num_code==1) {
					 JSONArray array = jsonObject.getJSONArray("data");
					  for (int i = 0; i < array.length(); i++) {
							JSONObject jsonObject2 = array.getJSONObject(i);
							String CoverPic=jsonObject2.getString("photo");
                            String PhotoLink =jsonObject2.getString("link");
					   	    list.add(CoverPic);	 
						    list1.add(PhotoLink);
				              	}
						mRltitle.setVisibility(View.GONE);
						mRltitle1 .setVisibility(View.VISIBLE);

						
						initViews();
						
						
						
						
						
				}
				 else {}
			} catch (JSONException e) {
			}
				
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return com.example.utils.getJson.getData(str);
			}
			}
	private void initViews() {
		symbolContainer = (LinearLayout) findViewById(R.id.symblo_container);
		circleSymbols = new ImageView[list.size()];
		images = new ImageView[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ImageView imageView = new ImageView(this);
			ImageView circle = new ImageView(this);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			images[i] = imageView;
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);  
			lp.setMargins(3, 0, 3,0);
			circle.setLayoutParams(lp);
			circle.setTag(i);
			circle.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_normal));
			circleSymbols[i] = circle;
			symbolContainer.addView(circleSymbols[i]);
		}
		setViewPager(com.example.view.JazzyViewPager.TransitionEffect.Standard);
	}
	
	private void setViewPager(com.example.view.JazzyViewPager.TransitionEffect effect) {
		mViewPaper = (com.example.view.JazzyViewPager) findViewById(R.id.adviewpaper);
		mViewPaper.setTransitionEffect(effect);
		mViewPaper.setAdapter(new MyPagerAdapter());
		mViewPaper.setOnPageChangeListener(new MyPageViewChangeListener());
		mViewPaper.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (list.size() == 0 || list.size() == 1) {
					return true;
				} else {
					return false;
				}
			}
		});
		circleSymbols[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_selected));
		mViewPaper.setCurrentItem(0);
		mHandler.sendEmptyMessageDelayed(PAGER_START_PLAY, PLAY_TIME);
	}

	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			if (view instanceof OutlineContainer) {
				return ((OutlineContainer) view).getChildAt(0) == obj;
			} else {
				return view == obj;
			}
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView(mViewPaper
					.findViewFromObject(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container,  int position) {
			initImageLoaderOptions();
			imageLoader.displayImage(list.get(position),
					images[position], options);

			imageLoader.displayImage(list.get(position),
					images[position]);
			container.addView(images[position], LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			final int index = position;
			images[position].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
				//	Toast.makeText(MainActivity.this, "你点击了第"+(index+1)+"张图", Toast.LENGTH_SHORT).show();
					Intent it = new Intent( Intent.ACTION_VIEW );
					it.setData( Uri.parse(list1.get(index)) ); //这里面是需要调转的rul
					it = Intent.createChooser( it, null );
					startActivity( it );							

				}
			});
			// 注意！不加这个方法要报IllegalStateException
			mViewPaper.setObjectForPosition(images[position], position);
			return images[position];
		}

	}

	private class MyPageViewChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int stateCode) {
			switch (stateCode) {
			case 0:
				// 你什么都没动
				break;
			case 1:
				// 正在滑动哦
				break;
			case 2:
				// 滑动完了
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int position) {
			setSymbolImages(position);
		}

	}
	*//**
	 * 设置圆形标签的状态
	 * @param index 当前标签的位置
	 *//*
	private void setSymbolImages(int index){
		for(ImageView image:circleSymbols){
			Integer i = (Integer) image.getTag();
			if(i==index){
				image.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_selected));
			}else{
				image.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_normal));
			}
		}
	}*/
    /*
    private void downloadnavigation() {
		new DownLoadAsyTask1().execute("http://josie.i3.com.hk/FG/json/photolist.php?ct=1");
	}
	class DownLoadAsyTask1 extends AsyncTask<String, Void, String>{  
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String string_code = jsonObject.getString("code");
				 int  num_code=Integer.valueOf(string_code);
				 if (num_code==1) {
					 JSONArray array = jsonObject.getJSONArray("data");
					  for (int i = 0; i < array.length(); i++) {
							JSONObject jsonObject2 = array.getJSONObject(i);
							String CoverPic=jsonObject2.getString("photo");
                            String PhotoLink =jsonObject2.getString("link");
					   	    list.add(CoverPic);	 
						    list1.add(PhotoLink);
				              	}
						mRltitle.setVisibility(View.GONE);
						mRltitle1 .setVisibility(View.VISIBLE);

						ll_focus_indicator_container = (LinearLayout) findViewById(R.id.ll_focus_indicator_container);
						InitFocusIndicatorContainer();
						gallery = (com.example.view.MyGallery) findViewById(R.id.banner_gallery);
						gallery.setAdapter(new ImageAdapter(getApplicationContext()));
						gallery.setFocusable(true);
						gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

						    public void onItemSelected(AdapterView<?> arg0, View arg1,
							    int selIndex, long arg3) {
							//修改上一次选中项的背景
						    	selIndex = selIndex % list.size();
						    	
							ImageView preSelImg = (ImageView) ll_focus_indicator_container.findViewById(preSelImgIndex);
						preSelImg.setImageDrawable(Main1Activity.this
							.getResources().getDrawable(R.drawable.ic_focus));
							//修改当前选中项的背景
							ImageView curSelImg = (ImageView) ll_focus_indicator_container
								.findViewById(selIndex);
							curSelImg
								.setImageDrawable(Main1Activity.this
									.getResources().getDrawable(
										R.drawable.ic_focus_select));
							preSelImgIndex = selIndex;
						    }

						    public void onNothingSelected(AdapterView<?> arg0) {
						    }
						});
                       gallery.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							Intent it = new Intent( Intent.ACTION_VIEW );
							it.setData( Uri.parse(list1.get(position)) ); //这里面是需要调转的rul
							it = Intent.createChooser( it, null );
							startActivity( it );							
						}
					});
				}
				 else {}
			} catch (JSONException e) {
			}
				
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return com.example.utils.getJson.getData(str);
			}
			}
	 public class ImageAdapter extends BaseAdapter {
			private Context _context;

			public ImageAdapter(Context context) {
			    _context = context;
			}

			public int getCount() {
			    return list.size();
			}

			public Object getItem(int position) {

			    return position;
			}

			public long getItemId(int position) {
			    return position;
			}

			public View getView(int position, View convertView, ViewGroup parent) {
				ViewHolder viewHolder = null;
				if(convertView == null)
				{
					viewHolder = new ViewHolder();
					ImageView imageView = new ImageView(_context);
				    imageView.setAdjustViewBounds(true);
				    imageView.setScaleType(ScaleType.FIT_XY);
				    imageView.setLayoutParams(new Gallery.LayoutParams(
					    LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				    convertView = imageView;
				    viewHolder.imageView = (ImageView)convertView; 
				    convertView.setTag(viewHolder);
					
				}
				else
				{
					viewHolder = (ViewHolder)convertView.getTag();
				}
			  //  viewHolder.imageView.setImageDrawable(imgList.get(position%imgList.size()));
		        initImageLoaderOptions();
					imageLoader.displayImage(list.get(position),
							viewHolder.imageView, options);

			    return convertView;
			}
			
		    }*/
		private void initImageLoaderOptions() {
			options = new DisplayImageOptions.Builder()
					.showImageForEmptyUri(R.drawable.a)
					.cacheInMemory()
					.cacheOnDisc().displayer(new FadeInBitmapDisplayer(2000))
					.bitmapConfig(Bitmap.Config.RGB_565).build();
		}

		    private static class ViewHolder
			{
				ImageView imageView;
			}
    private void InitFocusIndicatorContainer() {
	for (int i = 0; i < list.size(); i++) {
	    ImageView localImageView = new ImageView(this);
	    localImageView.setId(i);
	    ImageView.ScaleType localScaleType = ImageView.ScaleType.FIT_XY;
	    localImageView.setScaleType(localScaleType);
	    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(
		    24, 24);
	    localImageView.setLayoutParams(localLayoutParams);
	    localImageView.setPadding(5, 5, 5, 5);
	    localImageView.setImageResource(R.drawable.ic_focus);
	    this.ll_focus_indicator_container.addView(localImageView);
	    
	}
	
	
	
	
    }
	private void initView() {
	/*	myPager = (MyImgScroll)this. findViewById(R.id.myvp);
		ovalLayout = (LinearLayout) this. findViewById(R.id.vb);
		InitViewPager();//初始化图片
		//开始滚动
		myPager.start(Main1Activity.this, listViews, 4000, ovalLayout,
				R.layout.ad_bottom_item, R.id.ad_item_v,
				R.drawable.dot_focused, R.drawable.dot_normal);*/
		hListView = (com.example.view.HorizontalListView)findViewById(R.id.horizon_listview);
		//mRltitle =(RelativeLayout)findViewById(R.id.mRltitle);
	//	mRltitle1 =(RelativeLayout)findViewById(R.id.mRltitle1);

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
