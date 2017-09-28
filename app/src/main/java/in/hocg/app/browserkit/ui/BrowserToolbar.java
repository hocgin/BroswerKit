package in.hocg.app.browserkit.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.BaseMovementMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import in.hocg.app.browserkit.R;

/**
 * Created by hocgin on 2017/9/26.
 * 顶部toolbar
 */

public class BrowserToolbar extends Toolbar {
	public BrowserToolbar(Context context) {
		super(context);
	}
	
	public BrowserToolbar(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}
	
	public BrowserToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	public void style() {
		setSubtitleTextColor(Color.WHITE);
		setTitleTextColor(Color.WHITE);
		setNavigationIcon(R.mipmap.ic_close);
		
		// titleView
		TextView toolbarTitle = ((TextView) getChildAt(0));
		toolbarTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		toolbarTitle.setMovementMethod(new BaseMovementMethod() {
			
			private float startX;
			@Override
			public boolean onTouchEvent(TextView widget, Spannable text, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						startX = event.getX();
						break;
					case MotionEvent.ACTION_MOVE:
						float endX = event.getX();
						float length = endX - startX;
						int scrollX = (int) (widget.getScrollX() - length);
						if (scrollX < 0) {
							return true;
						}
						widget.setScrollX(scrollX);
						break;
					case MotionEvent.ACTION_UP:
						ObjectAnimator objectAnimator = ObjectAnimator.ofInt(widget, "scrollX", 0);
						objectAnimator.setDuration(400);
						objectAnimator.start();
						break;
				}
				return true;
			}
		});
	}
}
