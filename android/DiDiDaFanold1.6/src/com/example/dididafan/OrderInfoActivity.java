package com.example.dididafan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
//������Ϣ
public class OrderInfoActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderinfo);
		//��ʾ������listview
		
	}
	
	//��ת����ҳ
	public void MessagePageMPBtnClick(View v){
		Intent intent = new Intent();
		intent.setClass(OrderInfoActivity.this, MainPage.class);
		startActivity(intent);
	}
	
	//��ת������
	public void MessagePagePublishBtnClick(View v){
		Intent intent = new Intent();
		intent.setClass(OrderInfoActivity.this, PublishActivity.class);
		startActivity(intent);
	}
	
	//��ת����Ϣ
	public void MessagePageNewsBtnClick(View v){
		Intent intent = new Intent();
		intent.setClass(OrderInfoActivity.this, NewsActivity.class);
		startActivity(intent);
	}
}
