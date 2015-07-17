package com.example.dididafan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
//订单信息
public class OrderInfoActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderinfo);
		//显示订单的listview
		
	}
	
	//跳转到主页
	public void MessagePageMPBtnClick(View v){
		Intent intent = new Intent();
		intent.setClass(OrderInfoActivity.this, MainPage.class);
		startActivity(intent);
	}
	
	//跳转到发布
	public void MessagePagePublishBtnClick(View v){
		Intent intent = new Intent();
		intent.setClass(OrderInfoActivity.this, PublishActivity.class);
		startActivity(intent);
	}
	
	//跳转到消息
	public void MessagePageNewsBtnClick(View v){
		Intent intent = new Intent();
		intent.setClass(OrderInfoActivity.this, NewsActivity.class);
		startActivity(intent);
	}
}
