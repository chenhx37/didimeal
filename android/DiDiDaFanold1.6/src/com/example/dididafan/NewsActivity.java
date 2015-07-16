package com.example.dididafan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class NewsActivity extends Activity {
	private ListView OIWlv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messagepage);
		//显示订单的listview
		OIWlv = (ListView)findViewById(R.id.MessagePagelistView);
	}
	//跳转到主页
	public void MessagePageMPBtnClick(View v){
		Intent intent = new Intent();
		intent.setClass(NewsActivity.this, MainPage.class);
		startActivity(intent);
	}
	//跳转到发布
	public void MessagePagePublishBtnClick(View v){
		Intent intent = new Intent();
		intent.setClass(NewsActivity.this, PublishActivity.class);
		startActivity(intent);
	}
	//跳转到消息
	public void MessagePageNewsBtnClick(View v){
		Intent intent = new Intent();
		intent.setClass(NewsActivity.this, NewsActivity.class);
		startActivity(intent);
	}
}
