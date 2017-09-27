package in.hocg.app;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tbruyelle.rxpermissions2.RxPermissions;

import in.hocg.app.browserkit.BrowserActivity;
import in.hocg.app.browserkit.R;
import in.hocg.app.browserkit.ui.BrowserFeaturesBar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	Button button;
	BrowserFeaturesBar bfb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.button);
		bfb = (BrowserFeaturesBar) findViewById(R.id.bfb);
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
