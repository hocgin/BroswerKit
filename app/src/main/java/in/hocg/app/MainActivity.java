package in.hocg.app;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;

import in.hocg.app.browserkit.BrowserActivity;
import in.hocg.app.browserkit.R;
import in.hocg.app.browserkit.ui.BrowserFeaturesBar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	Button button;
	BrowserFeaturesBar bfb;
	TextView mm;
	Toolbar tt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.button);
		bfb = (BrowserFeaturesBar) findViewById(R.id.bfb);
		mm = (TextView) findViewById(R.id.mm);
		tt = (Toolbar) findViewById(R.id.tt);
		setSupportActionBar(tt);
		
		tt.setTitle("7-22244/in.hocg.app.broserkit D/EGL_emulation: eglMakeCurrent: 0xb43a7580: v");
		mm.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		mm.setSingleLine(true);
		mm.setMovementMethod(new MovementMethod() {
			@Override
			public void initialize(TextView widget, Spannable text) {
				
			}
			
			@Override
			public boolean onKeyDown(TextView widget, Spannable text, int keyCode, KeyEvent event) {
				return false;
			}
			
			@Override
			public boolean onKeyUp(TextView widget, Spannable text, int keyCode, KeyEvent event) {
				return false;
			}
			
			@Override
			public boolean onKeyOther(TextView view, Spannable text, KeyEvent event) {
				return false;
			}
			
			@Override
			public void onTakeFocus(TextView widget, Spannable text, int direction) {
				
			}
			
			@Override
			public boolean onTrackballEvent(TextView widget, Spannable text, MotionEvent event) {
				return false;
			}
			
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
						ObjectAnimator objectAnimator = ObjectAnimator.ofInt(widget, "scrollY", 0);
						objectAnimator.setDuration(400);
						objectAnimator.start();
						break;
				}
				return true;
			}
			
			@Override
			public boolean onGenericMotionEvent(TextView widget, Spannable text, MotionEvent event) {
				return false;
			}
			
			@Override
			public boolean canSelectArbitrarily() {
				return false;
			}
		});
		bfb.addButton(R.mipmap.ic_launcher, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		
		button.setOnClickListener(this);
		//	必要的权限申请
		new RxPermissions(this).request(
				Manifest.permission.INTERNET)
				.subscribe(new io.reactivex.functions.Consumer<Boolean>() {
					@Override
					public void accept(@io.reactivex.annotations.NonNull Boolean aBoolean) throws Exception {
						
					}
				});
	}
	
	@Override
	public void onClick(View v) {
		startActivity(new Intent(this, BrowserActivity.class));
	}
}
