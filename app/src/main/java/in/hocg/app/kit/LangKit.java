package in.hocg.app.kit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.ClipboardManager;

/**
 * Created by hocgin on 2017/9/27.
 */

public class LangKit {
	/**
	 * 复制
	 * @param context
	 * @param text
	 */
	public static void copy(Context context, String text) {
		// 得到剪贴板管理器
		ClipboardManager cmb = (ClipboardManager) context
				.getSystemService(Context.CLIPBOARD_SERVICE);
		cmb.setText(text.trim());
	}
	
	/**
	 * 使用系统浏览器打开连接
	 * @param context
	 * @param url
	 */
	public static void openBrowser(Context context, String url) {
		
		Uri uri = Uri.parse(url);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setData(uri);
		context.startActivity(intent);
	}
}
