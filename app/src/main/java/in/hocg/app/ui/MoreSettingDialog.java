package in.hocg.app.ui;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.hocg.app.browserkit.R;

/**
 * Created by hocgin on 2017/9/27.
 */

public class MoreSettingDialog extends Dialog implements View.OnClickListener {
	private TextView cancelView;
	private TextView titleView;
	private LinearLayout buttonPanel;
	
	public MoreSettingDialog(@NonNull Context context) {
		super(context, R.style.BottomDialogStyle);
		//将布局设置给Dialog
		setContentView(R.layout.dialog_bottom_more);
		DisplayMetrics metric = new DisplayMetrics();
		//设置Dialog从窗体底部弹出
		getWindow().setGravity(Gravity.BOTTOM);
		getWindow().getWindowManager()
				.getDefaultDisplay()
				.getMetrics(metric);
		
		WindowManager.LayoutParams lp = this.getWindow().getAttributes();
		lp.width = metric.widthPixels;
		//将属性设置给窗体
		getWindow().setAttributes(lp);
		
		findViews();
		bindEvents();
	}
	
	private void bindEvents() {
		cancelView.setOnClickListener(this);
	}
	
	private void findViews() {
		cancelView = ((TextView) findViewById(R.id.cancel_button));
		titleView = ((TextView) findViewById(R.id.title));
		buttonPanel = ((LinearLayout) findViewById(R.id.button_panel));
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.cancel_button:
				cancel();
				break;
		}
	}
	
	public void setTitle(CharSequence title) {
		titleView.setText(title);
		titleView.setVisibility(View.VISIBLE);
	}
	
	public MoreSettingDialog addButton(CharSequence title, @DrawableRes int resId, View.OnClickListener listener) {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.item_grid, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.image);
		TextView titleView = (TextView) view.findViewById(R.id.title);
		imageView.setImageResource(resId);
		titleView.setText(title);
		view.setOnClickListener(listener);
		buttonPanel.addView(view);
		return this;
	}
}
