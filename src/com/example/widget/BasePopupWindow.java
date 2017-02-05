package com.example.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import com.example.utils.Utils;


/**
 * 弹出窗口的基类
 * @author Jack
 * @version 创建时间：2014年7月11日  下午1:36:07
 */
public abstract class BasePopupWindow extends PopupWindow {
	
	protected Activity mContext;
	protected BasePopupWindow mPopupWindow = this;
	protected DisplayMetrics mOutMetrics;
	
	public BasePopupWindow(Activity context, Bundle bundle) {
		super(context);
		this.mContext = context;
		mOutMetrics = new DisplayMetrics();
		mContext.getWindowManager().getDefaultDisplay().getMetrics(mOutMetrics);
		mPopupWindow.setBackgroundDrawable(new ColorDrawable(-00000));
		mPopupWindow.setContentView(initContentView(bundle));
	}
	
	/**
	 *显示在anchor下方,且水平方向居中
	 * @param anchor
	 */
	public void showAsDropDownHorizontalCenter(View anchor) {
		int[] location = {0,0};
		anchor.getLocationOnScreen(location);
		int offsetX = Utils.getScreenWidth(mContext)/ 2 - (location[0] + mPopupWindow.getWidth() / 2);
		resetTopArrowLocation(anchor.getWidth()/2 - offsetX);
		showAsDropDown(anchor, offsetX, 0);
	}
	
	public void showAsCenter() {
		
		showAtLocation(mContext.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
	}
	
	/**
	 * 显示在anchor正下方
	 * @param anchor
	 */
	public void showAsAnchorBelow(View anchor) {
		int offsetX = (anchor.getWidth() - mPopupWindow.getWidth())/2;
		showAsDropDown(anchor, offsetX, 0);
	}
	
	public void showAsAnchorBed(View anchor) {
	    int offsetX = anchor.getWidth() - mPopupWindow.getWidth();
	    showAsDropDown(anchor, offsetX + (int)(2 * mOutMetrics.density), 0);
	}

	/**
	 * 显示在anchor正上方
	 * @param anchor
	 */
	public void showAsAnchorAbove(View anchor) {
		int offsetX = (anchor.getWidth() - mPopupWindow.getWidth())/2;
		int offsetY = - (anchor.getHeight() + mPopupWindow.getHeight());
		showAsDropDown(anchor, offsetX, offsetY);
	}

	/**
	 * 显示在anchor正左边
	 * @param anchor
	 */
	public void showAsAnchorLeft(View anchor) {
		int offsetX = - mPopupWindow.getWidth();
		int offsetY = - (anchor.getHeight() + mPopupWindow.getHeight())/2;
		showAsDropDown(anchor, offsetX, offsetY);
	}

	/**
	 * 显示在anchor正右边
	 * @param anchor
	 */
	public void showAsAnchorRight(View anchor) {
		int offsetX = anchor.getWidth();
		int offsetY = - (anchor.getHeight() + mPopupWindow.getHeight())/2;
		showAsDropDown(anchor, offsetX, offsetY);
	}
	
	/**
	 * 初始化popupwindow布局
	 * @param queue 
	 * @param bundle 
	 * @return
	 */
	public abstract View initContentView(Bundle bundle);
	
	/**
	 * 确定顶部小箭头在popupwindow里的水平方向位置
	 * @param offsetX 左上角到小箭头的距离px
	 */
	public abstract void resetTopArrowLocation(int offsetX);
	
}
