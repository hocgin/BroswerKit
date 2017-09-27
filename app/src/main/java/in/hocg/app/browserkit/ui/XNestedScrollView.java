package in.hocg.app.browserkit.ui;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hocgin on 2017/9/27.
 * 扩展了 监听滚动的状态
 */

public class XNestedScrollView extends NestedScrollView {
	private int mState = RecyclerView.SCROLL_STATE_IDLE;
	
	public interface NestedScrollViewScrollStateListener {
		void onNestedScrollViewStateChanged(int state);
	}
	
	
	public void setScrollListener(NestedScrollViewScrollStateListener scrollListener) {
		this.mScrollListener = scrollListener;
	}
	
	private NestedScrollViewScrollStateListener mScrollListener;
	
	public XNestedScrollView(Context context) {
		super(context);
	}
	
	public XNestedScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public XNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	@Override
	public void stopNestedScroll() {
		super.stopNestedScroll();
		dispatchScrollState(RecyclerView.SCROLL_STATE_IDLE);
	}
	
	@Override
	public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
		dispatchScrollState(RecyclerView.SCROLL_STATE_DRAGGING);
		return super.onStartNestedScroll(child, target, nestedScrollAxes);
	}
	
	
	@Override
	public boolean startNestedScroll(int axes) {
		boolean superScroll = super.startNestedScroll(axes);
		dispatchScrollState(RecyclerView.SCROLL_STATE_DRAGGING);
		return superScroll;
	}
	
	
	private void dispatchScrollState(int state) {
		if (mScrollListener != null && mState != state) {
			mScrollListener.onNestedScrollViewStateChanged(state);
			mState = state;
		}
	}
	
}
