package in.hocg.app.browserkit.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import in.hocg.app.browserkit.R;

/**
 * Created by hocgin on 2017/9/26.
 * todo x 实现顶部标题 横向滚动
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
	}
}
