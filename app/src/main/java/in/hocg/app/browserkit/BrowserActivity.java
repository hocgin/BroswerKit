package in.hocg.app.browserkit;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import in.hocg.app.browserkit.ui.BrowserFeaturesBar;
import in.hocg.app.browserkit.ui.BrowserToolbar;
import in.hocg.app.browserkit.ui.BrowserView;
import in.hocg.app.kit.LangKit;
import in.hocg.app.kit.OnClickListener;
import in.hocg.app.ui.MoreSettingDialog;


/**
 * Created by hocgin on 2017/9/26.
 */

public class BrowserActivity extends AppCompatActivity {
	public static final String LAUNCH = "in.hocg.app.browserkit.intent.action.LAUNCH";
	protected String url;
	protected BrowserView browserView;
	protected NestedScrollView nestedScrollView;
	private BrowserToolbar toolbar;
	private AppBarLayout appBarLayout;
	private ProgressBar progressBar;
	private MoreSettingDialog moreDialog;
	private BrowserFeaturesBar bfb;
	private DisplayMetrics metric;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browser);
		Logger.init("hocgin");
		
		findAll();
		initData();
		initView();
		bindEvents();
		
	}
	
	private void bindEvents() {
		moreDialog.addButton("复制链接", R.drawable.ic_action_link, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LangKit.copy(getBaseContext(), browserView.getUrl());
				Toast.makeText(v.getContext(), "复制成功", Toast.LENGTH_SHORT).show();
				moreDialog.cancel();
			}
		});
		moreDialog.addButton("浏览器打开", R.drawable.ic_action_globe, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LangKit.openBrowser(getBaseContext(), browserView.getUrl());
				moreDialog.cancel();
			}
		});
		
		toolbar.setOnClickListener(new OnClickListener() {
			@Override
			public void onSingleClick(View v) {
			}
			
			@Override
			public void onDoubleClick(View v) {
				ObjectAnimator anim = ObjectAnimator.ofInt(nestedScrollView, "scrollY",
						nestedScrollView.getScrollY(), 0);
				anim.setDuration(400);
				anim.start();
			}
		});
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				moreDialog.show();//显示对话框
				return false;
			}
		});
	}
	
	public void findAll() {
		toolbar = (BrowserToolbar) findViewById(R.id.toolbar);
		browserView = (BrowserView) findViewById(R.id.web_view);
		appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
		progressBar = (ProgressBar) findViewById(R.id.progress_bar);
		nestedScrollView = (NestedScrollView) findViewById(R.id.nested_scroll_view);
		bfb = new BrowserFeaturesBar(this);
		
	}
	
	private void initData() {
		url = "http://blog.csdn.net/carson_ho/article/details/52693322";
	}
	
	private void initView() {
		setSupportActionBar(toolbar);
		browserView.init(this);
		
		bfb.addButton(R.mipmap.ic_launcher, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getBaseContext(), "可进行点赞类的操作..", Toast.LENGTH_SHORT).show();
			}
		});
		
		toolbar.style();
		//声明WebSettings子类
		WebSettings webSettings = browserView.getSettings();
		//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
		webSettings.setJavaScriptEnabled(true);
		//支持插件
		webSettings.setPluginState(WebSettings.PluginState.ON);
		//设置自适应屏幕，两者合用
		webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
		webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
		//缩放操作
		webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
		webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
		webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
		//其他细节操作
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
		webSettings.setAllowFileAccess(true); //设置可以访问文件
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
		webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
		webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
		moreDialog = new MoreSettingDialog(this);
		browserView.loadUrl(url);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_toolbar, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		if (browserView.canGoBack()) {
			browserView.goBack();
		} else {
			Toast.makeText(this, "不能再退了囧..", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		// 进行需要窗口数据的初始化
		
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		metric = new DisplayMetrics();
		getWindow().getWindowManager()
				.getDefaultDisplay()
				.getMetrics(metric);
		bfb.setY(metric.heightPixels - 250);
		addContentView(bfb, layoutParams);
	}
	
	public String getUrl() {
		return url;
	}
	
	public BrowserView getBrowserView() {
		return browserView;
	}
	
	public Toolbar getToolbar() {
		return toolbar;
	}
	
	public ProgressBar getProgressBar() {
		return progressBar;
	}
	
	public AppBarLayout getAppBarLayout() {
		return appBarLayout;
	}
	
	public MoreSettingDialog getMoreDialog() {
		return moreDialog;
	}
}
