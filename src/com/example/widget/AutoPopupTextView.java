package com.example.widget;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.adapter.PopAdapter;
import com.example.android_email_auto_popupwindow.R;


/**
 * 自动提示下拉的窗口
 * @author Jack
 * @version 创建时间：2014年7月11日  下午1:36:28
 */
public class AutoPopupTextView extends BasePopupWindow {

    private ListView mListView;
    private PopAdapter mAdapter;
    private IAutoPopSelectedListener mListener;
    
    public DisplayMetrics getDisplayMetrics() {
        return mOutMetrics;
    }

    public AutoPopupTextView(Activity context, IAutoPopSelectedListener listener) {
        super(context, null);
        mListener = listener;
    }

    public void setBaseAdapter(PopAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public void initData() {
    	
        if (mAdapter == null){
        	
        	return;
        }
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				mListener.onAutoPopSelected(mAdapter.getItem(position));
			}
		});
    }

    @Override
    public View initContentView(Bundle bundle) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_auto_pop, null);
        mListView = (ListView) view.findViewById(R.id.auto_pop_list);
        return view;
    }

    @Override
    public void resetTopArrowLocation(int offsetX) {
    }

    public interface IAutoPopSelectedListener {
        void onAutoPopSelected(String arg);
    }
}
