package com.example.dididafan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class NewsActivity extends Activity {
	private Button btn_receive,btn_publish;
	private List<Map<String, String>> messageList = new ArrayList<Map<String, String>>();
	private void setReceiveData() {	
		Map<String, String> mMap = new HashMap<String, String>();
		mMap.put("name", "小明");
		messageList.add(mMap);
		
		mMap = new HashMap<String, String>();
		mMap.put("name", "小红");
		messageList.add(mMap);
		
		mMap = new HashMap<String, String>();
		mMap.put("name", "小刚");
		messageList.add(mMap);
	}
	private void setPublishData() {		
		Map<String, String> mMap = new HashMap<String, String>();
		mMap.put("name", "小里");
		messageList.add(mMap);
		
		mMap = new HashMap<String, String>();
		mMap.put("name", "小网");
		messageList.add(mMap);
		
		mMap = new HashMap<String, String>();
		mMap.put("name", "小若");
		messageList.add(mMap);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messagepage);	
		btn_receive = (Button)findViewById(R.id.btn_receive);
		btn_publish = (Button)findViewById(R.id.btn_publish);
		btn_receive.setOnClickListener(onClicker);
		btn_publish.setOnClickListener(onClicker);
		setReceiveData();
        final SimpleAdapter adapter = new SimpleAdapter(this, messageList,
        			R.layout.itemdetail, 
        			new String[]{"name"}, 
        			new int[]{R.id.messageContent});
        ListView list = (ListView)findViewById(R.id.MessagePagelistView);
        list.setAdapter(adapter);   
        //item项点击跳转到orderinfo页面
        list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
				// TODO Auto-generated method stub
				ListView list = (ListView)parent;
				HashMap<String, String> map = (HashMap<String, String>)list.getItemAtPosition(pos);
				String name = map.get("name");
				String classname = map.get("class");
				//还没有跟orderinfo.xml关联的activity
				Intent intent = new Intent(NewsActivity.this,PersonNews.class);
				Bundle mBundle = new Bundle();
				mBundle.putString("name", name);
				intent.putExtras(mBundle);
				startActivity(intent);
			}});
	}
	public void MessagePageMPBtnClick(View v){
		Intent intent = new Intent();
		intent.setClass(NewsActivity.this, MainPage.class);
		startActivity(intent);
		//MainPage.this.finish();
	}
	public void MessagePagePublishBtnClick(View v){
		Intent intent = new Intent();
		intent.setClass(NewsActivity.this, PublishActivity.class);
		startActivity(intent);
		//MainPage.this.finish();
	}
	private OnClickListener onClicker = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_receive:
				btn_receive.setTextColor(Color.parseColor("#df3031"));
				btn_publish.setTextColor(Color.WHITE);
				btn_receive.setBackgroundResource(R.drawable.baike_btn_pink_left_f_96);
				btn_publish.setBackgroundResource(R.drawable.baike_btn_trans_right_f_96);
				//listview显示自己接的单
				setReceiveData();
				break;
			case R.id.btn_publish:
				
				btn_receive.setTextColor(Color.WHITE);
				btn_publish.setTextColor(Color.parseColor("#df3031"));
				btn_receive.setBackgroundResource(R.drawable.baike_btn_trans_left_f_96);
				btn_publish.setBackgroundResource(R.drawable.baike_btn_pink_right_f_96);
				//listview显示别人接的单
				setPublishData();			
				break;			
			}
		}
	};

	

}
