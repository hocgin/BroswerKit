package in.hocg.app.browserkit;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import in.hocg.app.browserkit.ui.BrowserView;

/**
 * Created by hocgin on 2017/9/26.
 */

public class XWebChromeClient extends WebChromeClient {
	private BrowserView browserView;
	
	public XWebChromeClient(BrowserView browserView) {
		this.browserView = browserView;
	}
	
	
	@Override
	public void onReceivedTitle(WebView view, String title) {
		// 设置主标题
		browserView.getBrowserActivity()
				.getToolbar()
				.setTitle(title);
	}
	
	@Override
	public void onReceivedIcon(WebView view, Bitmap icon) {
	}
	
	@Override
	public void onProgressChanged(WebView view, int newProgress) {
		ProgressBar progressBar = browserView.getBrowserActivity()
				.getProgressBar();
		if (newProgress < 100) {
			progressBar.setVisibility(View.VISIBLE);
			progressBar.setProgress(newProgress);
		} else {
			progressBar.setVisibility(View.INVISIBLE);
		}
	}
	
}
