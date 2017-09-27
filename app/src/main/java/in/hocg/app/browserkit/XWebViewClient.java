package in.hocg.app.browserkit;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.orhanobut.logger.Logger;

import java.util.Objects;

import in.hocg.app.browserkit.ui.BrowserView;

/**
 * Created by hocgin on 2017/9/26.
 */

public class XWebViewClient extends WebViewClient {
	private BrowserView browserView;
	
	public XWebViewClient(BrowserView browserView) {
		this.browserView = browserView;
	}
	
	// shouldOverrideUrlLoading
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		
		// 使得打开网页时不调用系统浏览器
		Uri uri = Uri.parse(url);
		CharSequence urlString = uri.getHost();
		if (Objects.equals(uri.getScheme().toUpperCase(), "HTTPS")) {
			SpannableStringBuilder urlStringBuilder = new SpannableStringBuilder(String.format("%s://%s", uri.getScheme(), urlString));
			//			#288241
			ForegroundColorSpan httpsColorSpan = new ForegroundColorSpan(Color.rgb(40, 130, 65));
			urlStringBuilder.setSpan(httpsColorSpan, 0, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			ForegroundColorSpan separatedColorSpan = new ForegroundColorSpan(Color.rgb(160, 161, 162));
			urlStringBuilder.setSpan(separatedColorSpan, 5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			
			urlString = urlStringBuilder;
		}
		
		browserView.getBrowserActivity()
				.getMoreDialog()
				.setTitle(String.format("以上内容由 %s 提供", urlString));
		// 设置副标题
		BrowserActivity browserActivity = browserView.getBrowserActivity();
		// 显示顶部toolbar
		browserActivity.getAppBarLayout()
				.setExpanded(true, true);
		browserActivity.getToolbar()
				.setSubtitle(urlString);
		view.loadUrl(uri.toString());
		return true;
	}
	
	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		// 设定加载开始的操作
		// 我们可以设定一个loading的页面，告诉用户程序在等待网络响应
		Logger.i("加载页面开始");
	}
	
	@Override
	public void onPageFinished(WebView view, String url) {
		//设定加载结束的操作
		// 我们可以关闭loading 条
		Logger.i("加载页面结束");
	}
	
	@Override
	public void onLoadResource(WebView view, String url) {
		//设定加载资源的操作
//		Logger.i("加载资源");
	}
	
	
	@Override
	public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
		//步骤1：写一个html文件（error_handle.html），用于出错时展示给用户看的提示页面
		//步骤2：将该html文件放置到代码根目录的assets文件夹下
		
		//步骤3：复写WebViewClient的onRecievedError方法
		//该方法传回了错误码，根据错误类型可以进行不同的错误分类处理
		super.onReceivedError(view, request, error);
	}
}
