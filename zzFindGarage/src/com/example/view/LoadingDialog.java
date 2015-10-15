package com.example.view;



import com.example.wfindgarage.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class LoadingDialog extends AlertDialog {
	private View mView;
	private String mHintContent;

	public LoadingDialog(Context context, String hintContent) {
		super(context);
		mHintContent = hintContent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mView = LayoutInflater.from(getContext()).inflate(R.layout.loading_dialog, null);
		TextView hintText = (TextView) mView.findViewById(R.id.dialog_hint_text);
		hintText.setText(mHintContent);
		setContentView(mView);

		setCanceledOnTouchOutside(false);

		setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
			}
		});

	}

}
