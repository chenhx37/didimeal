package com.example.dididafan;
//util
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//http,json
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
//app
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
//主页
public class MainPage extends Activity {
	//控件初始化
		private Button javamainpersonnewsbtn;
		private ListView ep;
		private List<Map<String,Object>> mDataList = new ArrayList<Map<String,Object>>();
		private long temp_id = -1;
		private final int SELECT_A_CONTACT_DIALOG = 1;
		//服务器地址
		private String baseurl = "http://ddmeal.sinaapp.com/";
		private String mNamestr;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.mainpersonpage);	
			/*得到用户名进行显示*/
			Bundle mBundle = this.getIntent().getExtras();
			mNamestr = mBundle.getString("name");
			javamainpersonnewsbtn = (Button)findViewById(R.id.mainpersonnewsbtn);
			javamainpersonnewsbtn.setText(mNamestr);
			setData();
			//绑定数据到listview
			ep = (ListView)findViewById(R.id.mainpersonLV);
			final SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,mDataList,R.layout.mainpagelistitem,new String[]{"content","time","price"},new int[]{R.id.MPitemcontentTV,R.id.MPitempriceTV,R.id.MPitemtimeTV});
			ep.setAdapter(mSimpleAdapter);
			ep.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
				@Override
				public boolean onItemLongClick(AdapterView<?> parent,
						View view, int position, long id) {
					temp_id = position;
					showDialog(SELECT_A_CONTACT_DIALOG);
					//return true;
					// TODO Auto-generated method stub
					return false;
				}
			});
			
		}
		
		//点击发布按钮，跳转到发布界面
		public void PublishMainBtn(View v){		
			/*
			Intent intent = new Intent();
			intent.setClass(MainPage.this, PublishActivity.class);
			startActivity(intent);*/
			try{			
	            // 发送请求
	            Thread hth = new Thread(){
	            	@Override
	            	public void run(){
	            		//先get到个人信息，再post上去
	            		System.out.println("hello111 ");
	            		          		
	            		HttpResponse response;
	            		try{	            			
	            			String url = baseurl + "ddmeal/index/allMessage/";	
	            		//	List <NameValuePair> params = new ArrayList <NameValuePair>();   //Post运作传送变量必须用NameValuePair[]数组储存 
	            			/*params.add(new BasicNameValuePair("username",namestr));
	            			params.add(new BasicNameValuePair("nickName",fnamestr));
	            			params.add(new BasicNameValuePair("phone",phonestr));
	            			params.add(new BasicNameValuePair("address",addressstr));
	            			params.add(new BasicNameValuePair("netID",netidstr));*/			
	            			//HttpEntity requestHttpEntity = new UrlEncodedFormEntity(params);
	            			HttpPost httpRequest = new HttpPost(url);
	            			System.out.println("hello222 ");
	            			//header
	            			Header headers = new BasicHeader("Content-type","application/x-www-form-urlencoded");          
	            			httpRequest.setHeader(headers);
	            			Header headers1 = new BasicHeader("Accept","text/plain");
	            			httpRequest.setHeader(headers1);
	            			Header headers2 = new  BasicHeader("cookie","username="+mNamestr);
	            			httpRequest.setHeader(headers2);
	            			Header headers3 = new  BasicHeader("cookie","lastTime=2015-6-19");
	            			httpRequest.setHeader(headers3);
	            			Header headers4 = new  BasicHeader("cookie","flag=0");
	            			httpRequest.setHeader(headers4);
	            			// 将请求体内容加入请求中
	            		//	httpRequest.setEntity(requestHttpEntity);
	            			// 需要客户端对象来发送请求
	            			HttpClient httpClient = new DefaultHttpClient();
	            			// 发送请求
	            			response = httpClient.execute(httpRequest);
	            			
	            			System.out.println("hello333 ");
	            		//	CookieSpec cookiespec = CookiePolicy;    
	            			HttpEntity httpEntity = response.getEntity();
	            			String nowstr = null;
	            	     //   if(response.getStatusLine().getStatusCode() == 200)   {
	            	        	nowstr = EntityUtils.toString(response.getEntity());   //获取字符串
	            	        	System.out.println("hello "+ nowstr);   
	            	        	JSONObject jsonObject;
	            	        	try {
	            	        		jsonObject = new JSONObject(nowstr);
	            	        		String outstr = jsonObject.getString("error");
	            	        		//result = jsonObject.getString("errorMs");	
	            	        		System.out.println("hello555 "+outstr);
	            	        		//if(outstr=="false"){         	        			
	            	        		/*	Intent intent = new Intent();
	            	        			intent.setClass(PersonNews.this, MainPage.class);
	            	        			//intent.putExtras(herebundle);
	            	        			startActivity(intent);
	            	        			PersonNews.this.finish();*/
	            	        		//}
	            	        	} catch (JSONException e1) {
	            	        		// TODO Auto-generated catch block
	            	        		e1.printStackTrace();
	            	        	}           
							//}							
	            	    }catch (Exception e){
							e.printStackTrace();
						}
					}
	            };
				hth.start();
				System.out.println("hello666 ");     
			}catch(Exception e){
	        e.printStackTrace();
			}
			//MainPage.this.finish();
		}
		
		//跳转
		public void NewsMainBtn(View v){
			Intent intent = new Intent();
			intent.setClass(MainPage.this, NewsActivity.class);
			startActivity(intent);
			//MainPage.this.finish();
		}
		//跳转
		public void PersonNewsMainBtn(View v){
			Bundle mbundlenow = new Bundle();
			mbundlenow.putString("name", javamainpersonnewsbtn.getText().toString());
			Intent intent = new Intent();
			intent.setClass(MainPage.this, PersonNews.class);
			intent.putExtras(mbundlenow);
			startActivity(intent);
			//MainPage.this.finish();
		}
		
		//get到订单信息放到list中，传递给listview
		public void setData(){
			Map<String,Object> mMap = new HashMap<String,Object>();
			mMap.put("content", "我要二烦的鸡扒饭");
			mMap.put("time", "2015/6/17");
			mMap.put("price", "10");
			mDataList.add(mMap);
			mMap.put("content", "我要二烦的鸡扒饭");
			mMap.put("time", "2015/6/17");
			mMap.put("price", "10");
			mDataList.add(mMap);
			mMap.put("content", "我要二烦的鸡扒饭");
			mMap.put("time", "2015/6/17");
			mMap.put("price", "10");
			mDataList.add(mMap);
			
		}
		
		@SuppressWarnings("deprecation")
		protected Dialog onCreateDialog(int id) {
			
			switch (id) {
			case SELECT_A_CONTACT_DIALOG:
				return new AlertDialog.Builder(MainPage.this).setTitle("JIEDAN")
						.setNegativeButton("取消", new DialogInterface.OnClickListener() {
							 
							@Override
							public void onClick(DialogInterface dialog, int which) {								
								
								
							}
						}).setPositiveButton("接单", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
								System.out.println("temp_id: "+temp_id);
								
							}
						}).create();
			default:
				break;
			}
			return super.onCreateDialog(id);
		}
/*Intent intent = new Intent();
								intent.putExtra("id", temp_id);
								intent.setClass(MainPage.this, MissionActivity.class);
								startActivity(intent);*/
		@SuppressWarnings("deprecation")
		protected void onPrepareDialog(int id, Dialog dialog) {
			super.onPrepareDialog(id, dialog);
			//WBSQLiteHelper dbHelper = new WBSQLiteHelper(MainActivity.this, MissionActivity.DATABASE_NAME);
		//	SQLiteDatabase database = dbHelper.getReadableDatabase();
		//	Cursor cursor = database.query("mission", new String[]{"missioncontext"}, 
		//			new String("_id=?"), new String[]{""+temp_id}, null, null, null);
		//	cursor.moveToNext();
			
			//dialog.setTitle(cursor.getString(cursor.getColumnIndex("missioncontext")));
		}
//
		

}
