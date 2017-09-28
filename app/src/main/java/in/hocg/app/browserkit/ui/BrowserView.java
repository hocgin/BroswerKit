package in.hocg.app.browserkit.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import in.hocg.app.browserkit.BrowserActivity;
import in.hocg.app.browserkit.XWebChromeClient;
import in.hocg.app.browserkit.XWebViewClient;

/**
 * Created by hocgin on 2017/9/26.
 */

public class BrowserView extends WebView {
	
	private static WebViewClient WEB_VIEW_CLIENT;
	private static WebChromeClient WEB_Chrome_CLIENT;
	private BrowserActivity browserActivity;
	
	public BrowserView(Context context) {
		super(context);
	}
	
	public BrowserView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public BrowserView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	public BrowserView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}
	
	public BrowserView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
		super(context, attrs, defStyleAttr, privateBrowsing);
	}
	
	public BrowserView init(BrowserActivity browserActivity) {
		this.browserActivity = browserActivity;
		setWebViewClient(WEB_VIEW_CLIENT = new XWebViewClient(this));
		setWebChromeClient(WEB_Chrome_CLIENT = new XWebChromeClient(this));
		return this;
	}
	
	public BrowserActivity getBrowserActivity() {
		return browserActivity;
	}
	
	
	private float posX;
	private static final float mistake = 10f; // 精确系数
	
	
	/**
	 * 左右滑动 前进和后退
	 * @param event
	 * @return
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				posX = event.getX();
				break;
			case MotionEvent.ACTION_UP:
				if (posX < (getLeft() + mistake)
						&& (posX - event.getX()) < 0) {
					if (canGoBack()) {
						goBack();
						Toast.makeText(this.getContext(), "后退", Toast.LENGTH_SHORT).show();
					}
				} else if (posX > (getRight() - mistake)
						&& (posX - event.getX()) > 0) {
					if (canGoForward()) {
						goForward();
						Toast.makeText(this.getContext(), "前进", Toast.LENGTH_SHORT).show();
					}
				}
//				Logger.i(String.format("点击位置 (%f, %f)", event.getX(), event.getY()));
//				Logger.i(String.format("WebView (t: %d, r: %d, b: %d, l: %d)", getTop(), getRight(), getBottom(), getLeft()));
//				Logger.i(String.format("WebView (width: %d, height: %d)", getWidth(), getHeight()));
				break;
		}
		return super.onTouchEvent(event);
	}
}
