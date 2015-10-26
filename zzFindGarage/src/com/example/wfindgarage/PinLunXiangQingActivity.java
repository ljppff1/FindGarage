package com.example.wfindgarage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class PinLunXiangQingActivity extends BaseActivity {

	private ImageView mIvtt1;
	private LinearLayout ll_imgs;
	private ImageView mcamera;
	private Button ui_login_btn;
	private EditText mEd1d;
	private AlertDialog alertDialog;
	public static String SDPATH = Environment.getExternalStorageDirectory()
			+ "/FindGarage/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pinlunxiangqing);
		ui_login_btn =(Button)this.findViewById(R.id.ui_login_btn);
		ui_login_btn.setOnClickListener(listener);
		mEd1d =(EditText)this.findViewById(R.id.mEd1d);
		
		mIvtt1 =(ImageView)this.findViewById(R.id.mIvtt1);
		mIvtt1.setOnClickListener(listener);
		ll_imgs = (LinearLayout) findViewById(R.id.ll_imgs);
		mcamera = (ImageView) findViewById(R.id.camera);
		mcamera.setOnClickListener(new OnClickListener() { 

			@Override
			public void onClick(View v) {

				showCustomAlertDialog();
			}

		});

	}
	private void showCustomAlertDialog() {
 alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.show();
		Window win = alertDialog.getWindow();

		WindowManager.LayoutParams lp = win.getAttributes();
		win.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		//lp.alpha = 0.7f;
		win.setAttributes(lp);
		win.setContentView(R.layout.dialog);

		Button cancelBtn = (Button) win.findViewById(R.id.camera_cancel);
		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialog.cancel();
			}
		});
		Button camera_phone = (Button) win.findViewById(R.id.camera_phone);
		camera_phone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				systemPhoto();
			}

		});
		Button camera_camera = (Button) win.findViewById(R.id.camera_camera);
		camera_camera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cameraPhoto();
			}

		});

	}	private final int SYS_INTENT_REQUEST = 0XFF01;
	private final int CAMERA_INTENT_REQUEST = 0XFF02;
	private Bitmap bitmap;

	/**
	 * ��ϵͳ���
	 */
	private void systemPhoto() {

		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, SYS_INTENT_REQUEST);

	}

	/**
	 * �����������
	 */
	private void cameraPhoto() {
		String sdStatus = Environment.getExternalStorageState();
		/* ���sd�Ƿ���� */
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
			//Toast.makeText(this, "SD�������ã�", Toast.LENGTH_SHORT).show();
			return;
		}
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		startActivityForResult(intent, CAMERA_INTENT_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == SYS_INTENT_REQUEST && resultCode == RESULT_OK
				&& data != null) {
			try {
				Uri uri = data.getData();
				Cursor cursor = getContentResolver().query(uri, null, null,
						null, null);
				cursor.moveToFirst();

				String imageFilePath = cursor.getString(1);
				System.out.println("File path is----->" + imageFilePath);

				FileInputStream fis = new FileInputStream(imageFilePath);
				bitmap = BitmapFactory.decodeStream(fis);

				int width = bitmap.getWidth();
				int height = bitmap.getHeight();

				/* ѹ����ȡ��ͼ�� */
				showImgs(bitmap, false);

				fis.close();
				cursor.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (requestCode == CAMERA_INTENT_REQUEST
				&& resultCode == RESULT_OK && data != null) {
			cameraCamera(data);
		}
		super.onActivityResult(requestCode, resultCode, data);

	}

	/**
	 * @param bitmap
	 * @return ѹ�����bitmap
	 */
	private Bitmap compressionBigBitmap(Bitmap bitmap, boolean isSysUp) {
		Bitmap destBitmap = null;
		/* ͼƬ��ȵ���Ϊ100��������������ģ���һ���������ŵ����Ϊ100 */
		if (bitmap.getWidth() > 80) {
			float scaleValue = (float) (80f / bitmap.getWidth());
			System.out.println("���ű���---->" + scaleValue);

			Matrix matrix = new Matrix();
			/* ���ϵͳ���գ���ת90�� */
		//	if (isSysUp)
		//		matrix.setRotate(90);
			matrix.postScale(scaleValue, scaleValue);

			destBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);
			int widthTemp = destBitmap.getWidth();
			int heightTemp = destBitmap.getHeight();
			Log.i("zhiwei.zhao", "ѹ����Ŀ��----> width: " + heightTemp
					+ " height:" + widthTemp);
		} else {
			return bitmap;
		}
		return destBitmap;

	}

	/**
	 * @param data
	 *            ���պ��ȡ��Ƭ
	 */
	private void cameraCamera(Intent data) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String name = formatter.format(System.currentTimeMillis()) + ".jpg";
		Log.i("zhiwei.zhao", "image name:" + name);
		//Toast.makeText(this, name, Toast.LENGTH_LONG).show();
		Bundle bundle = data.getExtras();
		/* ��ȡ������ص����ݣ���ת��ΪBitmapͼƬ��ʽ */
		Bitmap bitmap = (Bitmap) bundle.get("data");
		FileOutputStream b = null;

		String path = Environment.getExternalStorageDirectory().getPath();
		File file = new File(path + "/myImage/");
		/** ����ļ����Ƿ���ڣ��������򴴽��ļ��� **/
		if (!file.exists() && !file.isDirectory())
			file.mkdirs();
		String fileName = file.getPath() + "/" + name;
		Log.i("zhiwei.zhao", "camera file path:" + fileName);
		try {
			b = new FileOutputStream(fileName);
			/* ������д���ļ� */
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (b == null)
					return;
				b.flush();
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		showImgs(bitmap, true);
	}

	/**
	 * չʾѡ���ͼƬ
	 * 
	 * @param bitmap
	 * @param isSysUp
	 */
	private void showImgs(Bitmap bitmap, boolean isSysUp) {/*
		if (ll_imgs.getChildCount() > 2) {
			Toast.makeText(this, R.string.ze20, Toast.LENGTH_SHORT)
					.show();
			return;
		}
		Bitmap _bitmap = compressionBigBitmap(bitmap, isSysUp);
		final ImageView im = new ImageView(this);
		
		im.setPadding(10, 0, 0, 0);
		im.setImageBitmap(_bitmap);
		im.setLayoutParams(new LayoutParams(120, 120));
		im.setScaleType(ScaleType.FIT_XY);
		ll_imgs.addView(im);
		
		
		
		SaveBitmap(_bitmap);
		im.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				BitmapDrawable bitmapDrawable = (BitmapDrawable) im
						.getDrawable();
				if (bitmapDrawable != null
						&& !bitmapDrawable.getBitmap().isRecycled()) {
					bitmapDrawable.getBitmap().recycle();
				}
				ll_imgs.removeView(im);
			}
		});
	*/
		
   	Bitmap _bitmap = compressionBigBitmap(bitmap, isSysUp);
		SaveBitmap(_bitmap);
		mcamera.setImageBitmap(bitmap);
		alertDialog.cancel();

	}
	String path1="";
	//���浽����  
    public void SaveBitmap(Bitmap bmp) {  
    	  Date date = new Date();
    	  SimpleDateFormat format = new SimpleDateFormat("hh-mm-ss");
    	  String newDate = format.format(date);
    	  String path = "/sdcard/namecard";
/*    	  if (!destDir.exists()) {
    	   destDir.mkdirs();
    	  }
*/    	  makeRootDirectory(SDPATH);
    	  File f = new File(SDPATH+"pic"+newDate+".png");
    	  try {
	    	   FileOutputStream out = new FileOutputStream(f);
	    	   bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
	    	   out.flush();
	    	   out.close();
	    	   path1=f.getPath();
	    	 //  Toast.makeText(getApplicationContext(), "����ɹ�", Toast.LENGTH_SHORT).show();
    	  } catch (FileNotFoundException e) {

    	  } catch (IOException e) {
    	  }
    }
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {

        }
}
	OnClickListener listener =new  OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mIvtt1:
				finish();
				break;
			case R.id.ui_login_btn:
				if(!TextUtils.isEmpty(mEd1d.getEditableText().toString())){
				SharedPreferences mySharedPreferences= getSharedPreferences("USER", Activity.MODE_PRIVATE); 
				SharedPreferences.Editor editor = mySharedPreferences.edit(); 
				editor.putString("content", mEd1d.getEditableText().toString()); 
				editor.putString("Path", path1); 
				editor.commit(); 
				finish();
				}else{
					Toast.makeText(getApplicationContext(), R.string.ze28, 0).show();
				}
				break;
			default:
				break;
			}
		}
	};
private Handler handler =new Handler();
	
}
