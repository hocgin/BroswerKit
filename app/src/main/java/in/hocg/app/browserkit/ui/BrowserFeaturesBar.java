package in.hocg.app.browserkit.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import in.hocg.app.browserkit.R;

/**
 * Created by hocgin on 2017/9/27.
 */

public class BrowserFeaturesBar extends LinearLayout {
	private LinearLayout.LayoutParams layoutParams;
	private boolean isOpen = true;
	private ImageView controlButton;
	
	public BrowserFeaturesBar(Context context) {
		super(context);
		init();
	}
	
	public BrowserFeaturesBar(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public BrowserFeaturesBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	
	public BrowserFeaturesBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}
	
	private void init() {
		layoutParams = new LinearLayout.LayoutParams(100, 100);
		layoutParams.setMargins(0, 0, 10, 0);
		controlButton = new ImageView(getContext());
		controlButton.setLayoutParams(layoutParams);
		controlButton.setBackgroundResource(R.drawable.image_radius);
		controlButton.setImageResource(R.drawable.ic_chevron_left);
		controlButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isOpen()) {
					close();
				} else {
					open();
				}
			}
		});
		addView(controlButton);
		setOrientation(HORIZONTAL);
		setElevation(2f);
		setBackgroundResource(R.drawable.right_radius);
	}
	
	public void addButton(@DrawableRes int resId, OnClickListener listener) {
		ImageView imageView = new ImageView(getContext());
		imageView.setLayoutParams(layoutParams);
		imageView.setBackgroundResource(R.drawable.image_radius);
		imageView.setImageResource(resId);
		imageView.setOnClickListener(listener);
		addView(imageView, 0);
	}
	
	/**
	 * 关闭
	 */
	public void close() {
		if (isOpen()) {
			final float startValue = this.getX();
			final int endValue = ((getWidth() / getChildCount()) - getWidth()) + layoutParams.getMarginEnd();
			ValueAnimator va = ValueAnimator.ofFloat(startValue, endValue);
			va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				public void onAnimationUpdate(ValueAnimator animation) {
					Float value = (Float) animation.getAnimatedValue();
					setX(value.floatValue());
					controlButton.setRotation(-(value / Math.abs(endValue - startValue)) * 180);
					requestLayout();
				}
			});
			va.setDuration(400);
			va.start();
			isOpen = false;
		}
	}
	
	/**
	 * 打开
	 */
	public void open() {
		if (!isOpen()) {
			final float startValue = this.getX();
			final int endValue = 0;
			ValueAnimator va = ValueAnimator.ofFloat(startValue, endValue);
			va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				public void onAnimationUpdate(ValueAnimator animation) {
					Float value = (Float) animation.getAnimatedValue();
					setX(value.floatValue());
					controlButton.setRotation((value / Math.abs(endValue - startValue)) * 180);
					requestLayout();
				}
			});
			va.setDuration(400);
			va.start();
			isOpen = true;
		}
	}
	
	public boolean isOpen() {
		return isOpen;
	}
}
