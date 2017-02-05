package com.example.android_email_auto_popupwindow;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import com.example.adapter.PopAdapter;
import com.example.utils.Utils;
import com.example.widget.AutoPopupTextView;
import com.example.widget.AutoPopupTextView.IAutoPopSelectedListener;
import com.example.widget.EditTextWrapper;



public class MainActivity extends Activity implements IAutoPopSelectedListener{

	private EditTextWrapper mUsernameLogin;
	private AutoPopupTextView autoTextView;
	/** 下拉列表的数据源 */
	private ArrayList<String> mSources;
	/** 用户名+邮箱的列表数据源*/
	private List<String> tempList;
	private PopAdapter popAdapter;
	private ArrayList<String> tempList2;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initPopUpWindow();
		mUsernameLogin = (EditTextWrapper)findViewById(R.id.username_login);
		mUsernameLogin.unwrap().addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.toString().contains("@") && !autoTextView.isShowing()) {
					initUserSources(s.toString());
				}
			}
		});
	}
	
	public void initPopUpWindow(){
		
		autoTextView = new AutoPopupTextView(this, this);
		initSources();
		popAdapter = new PopAdapter(this, autoTextView.getDisplayMetrics());
		autoTextView.setBaseAdapter(popAdapter);
		popAdapter.setListener(this);
		autoTextView.initData();
		autoTextView.setOutsideTouchable(true);
		setWidth();
		setHeight(mSources);
	}
	
	/**
	 * 初始化提示信息的数据源
	 */
	private void initSources() {

		mSources = new ArrayList<String>();
		mSources.add("@sina.com");
		mSources.add("@163.com");
		mSources.add("@qq.com");
		mSources.add("@126.com");
		mSources.add("@vip.sina.com");
		mSources.add("@sina.cn");
		mSources.add("@hotmail.com");
		mSources.add("@gmail.com");
		mSources.add("@sohu.com");
		mSources.add("@139.com");
		mSources.add("@wo.com.cn");
		mSources.add("@189.cn");
		mSources.add("@21cn.com");
	}

	/**
	 * 初始化邮箱用户名的自动提示信息，并显示下拉列表
	 * @param s
	 */
	protected void initUserSources(String s) {
		if(TextUtils.isEmpty(s)) 
			return;
		String[] temp = s.toString().split("@");
		String tempString = temp[0];
		if (tempList == null) {
			tempList = new ArrayList<String>();
		} else {
			tempList.clear();
		}
		for (String sources1 : mSources) {   //获得所有可能的用户名+邮箱格式
			StringBuffer sb = new StringBuffer();
			tempList.add(sb.append(tempString).append(sources1).toString());
		}

		for (String sources3 : tempList) {   //若和用户输入相同就不显示列表
			if (sources3.equals(mUsernameLogin.unwrap().getText().toString())) {
				return;
			}
		}
		
		if (tempList2 == null) {
			tempList2 = new ArrayList<String>();
		} else {
			tempList2.clear();
		}
		for (String sources2 : tempList) {
			if (sources2.contains(mUsernameLogin.unwrap().getText().toString())) {
				tempList2.add(sources2);
			}
		}
		setWidth();
		setHeight(tempList2);
		popAdapter.notifyDataSetChanged(tempList2);
		autoTextView.showAsDropDown(mUsernameLogin.unwrap());
	}

	/**
	 * 选中自动弹出的下拉列表的某一项后
	 */
	@Override
	public void onAutoPopSelected(String arg) {
		
		autoTextView.dismiss();
		mUsernameLogin.unwrap().setText(arg);
		mUsernameLogin.unwrap().setSelection(arg.length());
		mUsernameLogin.unwrap().setFocusable(true);
		mUsernameLogin.unwrap().setFocusableInTouchMode(true);
	}
	
	/**
	 * 设置AutoPopupTextView的高度
	 * @param args
	 */
	private void setHeight(List<String> args) {
		int height = (int) (20 * args.size() * autoTextView.getDisplayMetrics().density);
		autoTextView.setHeight(height);
	}
	
	private void setWidth() {
		autoTextView.setWidth(Utils.getScreenWidth(MainActivity.this) - 250);
	}
}
