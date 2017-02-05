package com.example.adapter;

import java.util.List;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android_email_auto_popupwindow.R;
import com.example.utils.Utils;
import com.example.widget.AutoPopupTextView.IAutoPopSelectedListener;


/**
 * 弹出下拉列表的adapter
 */
public class PopAdapter extends BaseAdapter {

    private Activity mActivity;
    private List<String> mDataList;
    private DisplayMetrics displayMetrics;
    private IAutoPopSelectedListener listener;
    private int backid;

    public PopAdapter(Activity context, DisplayMetrics displayMetrics) {
        this.mActivity = context;
        this.displayMetrics = displayMetrics;
    }

    public void notifyDataSetChanged(List<String> mdata) {
        this.mDataList = mdata;
        notifyDataSetChanged();
    }

    public void setListener(IAutoPopSelectedListener listener) {
        this.listener = listener;
    }
    
    @Override
    public int getCount() {
        if (mDataList == null)
            return 0;
        return mDataList.size();
    }

    @Override
    public String getItem(int position) {
        if (mDataList == null || mDataList.size() == 0)
            return null;
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    public void setBackground(int backid) {
        this.backid = backid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	final String mData = mDataList.get(position);    //数据是新的
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.item_auto_pop, null);
            if(backid != 0) convertView.setBackgroundResource(backid);
            holder.textView = (TextView) convertView.findViewById(R.id.item_pop_text);
            int width = (int) ((Utils.getScreenWidth(mActivity) / 2) * displayMetrics.density);
            int height = (int) (38 * displayMetrics.density);
            LayoutParams params = new LayoutParams(width, height);
            convertView.setLayoutParams(params);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(mData);
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAutoPopSelected(mData);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}