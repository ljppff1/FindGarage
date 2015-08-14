package com.example.view;


import com.example.wfindgarage.JianJiechefangActivity;
import com.example.wfindgarage.LianxiwomenActivity;
import com.example.wfindgarage.Main1Activity;
import com.example.wfindgarage.MainActivity;
import com.example.wfindgarage.MeiYuePaiHangActivity;
import com.example.wfindgarage.MyZiliaoActivity;
import com.example.wfindgarage.NewDiscussActivity;
import com.example.wfindgarage.NewNoticeActivity;
import com.example.wfindgarage.R;
import com.example.wfindgarage.SearchYaocheActivity;
import com.example.wfindgarage.TiGongCheFangZiLiaoActivity;
import com.example.wfindgarage.WoDeCheFangActivity;
import com.example.wfindgarage.ZiliaochefangActivity;
import com.example.wfindgarage.chefangActivity;
import com.example.wfindgarage.fujinchefangActivity;
import com.example.wfindgarage.huiyuandengluActivity;
import com.example.wfindgarage.tuiguangyouhuiActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HorizontalListViewAdapter extends BaseAdapter{
	private int[] mIconIDs;
	private String[] mTitles;
	private int[] mIconIDs1;
	private String[] mTitles1;
	private Context mContext;
	private LayoutInflater mInflater;
	Bitmap iconBitmap;
	private int selectIndex = -1;
	private Bitmap iconBitmap1;

	public HorizontalListViewAdapter(Context context, String[] titles, int[] ids, String[] titles1, int[] ids1){
		this.mContext = context;
		this.mIconIDs = ids;
		this.mTitles= titles;
		this.mIconIDs1 = ids1;
		this.mTitles1= titles1;
		
		mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//LayoutInflater.from(mContext);
	}
	@Override
	public int getCount() {
		return mIconIDs.length;
	}
	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.horizontal_list_item, null);
			holder.mImage=(ImageView)convertView.findViewById(R.id.img_list_item);
			holder.mTitle=(TextView)convertView.findViewById(R.id.text_list_item);
			holder.mImage1=(ImageView)convertView.findViewById(R.id.img_list_item1);
			holder.mTitle1=(TextView)convertView.findViewById(R.id.text_list_item1);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		if(position == selectIndex){
			convertView.setSelected(true);
		}else{
			convertView.setSelected(false);
		}
		holder.mImage1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		           if(position==0){
		            	 Intent intent =new Intent(mContext, tuiguangyouhuiActivity.class);
		            	 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		             mContext.startActivity(intent);

		             }

	             if(position==1){
	            	 Intent intent =new Intent(mContext, ZiliaochefangActivity.class);
	            	 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	             mContext.startActivity(intent);
	             }
	             if(position==2){
	            	 Intent intent =new Intent(mContext, LianxiwomenActivity.class);
	            	 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	             mContext.startActivity(intent);
	             }
             if(position==3){
            	 Intent intent =new Intent(mContext, TiGongCheFangZiLiaoActivity.class);
            	 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             mContext.startActivity(intent);
             }
             if(position==4){
            	 Intent intent =new Intent(mContext, MyZiliaoActivity.class);
            	 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             mContext.startActivity(intent);
             }
         
             
             
			}
		});
		holder.mImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
	             if(position==0){
	            	 Intent intent =new Intent(mContext, SearchYaocheActivity.class);
	            	 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	             mContext.startActivity(intent);
	                 }
	                 if(position==1){
		            	 Intent intent =new Intent(mContext, fujinchefangActivity.class);
		            	 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		             mContext.startActivity(intent);
	                 }


                if(position==2){
	            	 Intent intent =new Intent(mContext, WoDeCheFangActivity.class);
	            	 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	             mContext.startActivity(intent);
                }
                if(position==3){
	            	 Intent intent =new Intent(mContext, NewDiscussActivity.class);
	            	 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	             mContext.startActivity(intent);
                }

                if(position==4){
	            	 Intent intent =new Intent(mContext, MeiYuePaiHangActivity.class);
	            	 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	             mContext.startActivity(intent);
                }
			}
		});
		
		holder.mTitle.setText(mTitles[position]);
		iconBitmap = getPropThumnail(mIconIDs[position]);
		holder.mImage.setImageBitmap(iconBitmap);
		if(position<5){
		holder.mTitle1.setText(mTitles1[position]);
		iconBitmap1 = getPropThumnail(mIconIDs1[position]);
		holder.mImage1.setImageBitmap(iconBitmap1);
		}
		return convertView;
	}

	private static class ViewHolder {
		private TextView mTitle,mTitle1 ;
		private ImageView mImage,mImage1;
	}
	private Bitmap getPropThumnail(int id){
		Drawable d = mContext.getResources().getDrawable(id);
		Bitmap b = BitmapUtil.drawableToBitmap(d);
//		Bitmap bb = BitmapUtil.getRoundedCornerBitmap(b, 100);
		int w = mContext.getResources().getDimensionPixelOffset(R.dimen.thumnail_default_width);
		int h = mContext.getResources().getDimensionPixelSize(R.dimen.thumnail_default_height);
		
		Bitmap thumBitmap = ThumbnailUtils.extractThumbnail(b, w, h);
		
		return thumBitmap;
	}
	public void setSelectIndex(int i){
		selectIndex = i;
	}
}