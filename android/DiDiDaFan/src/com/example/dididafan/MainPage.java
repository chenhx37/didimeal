package com.example.dididafan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainPage extends Activity {
		private Button javamainpersonnewsbtn;
		private ListView ep;
		private List<Map<String,Object>> mDataList = new ArrayList<Map<String,Object>>();
		private List<Map<String,Object>> showList = new ArrayList<Map<String,Object>>();
		private int temp_id = -1;
		private final int SELECT_A_CONTACT_DIALOG = 1;
		private String baseurl = "http://ddmeal.sinaapp.com/";
		private Map<String,Object> hereList = new HashMap<String,Object>();
		private String mNamestr;
		private String result = null;
		//ʱ���
		private String timelast = "1317091800.0";
		//flag
		private String flag = "0";
		private SimpleAdapter adapter;
		private String showhere = null;
		//���pkֵ
		private String pkidstr = null;
		private List<Map<String,String>> pklist = new ArrayList<Map<String,String>>();
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.mainpersonpage);	
			/*�õ��û���������ʾ*/
			javamainpersonnewsbtn = (Button)findViewById(R.id.mainpersonnewsbtn);
			mNamestr = MainActivity.UserBigStr;
			showhere = MainActivity.UserBigStr;
			javamainpersonnewsbtn.setText(showhere);
		//	setData();
			mDataList.clear();
			showList.clear();
			getOrdernow();
			//�����ݵ�listview
			ep = (ListView)findViewById(R.id.mainpersonLV);
			adapter = new SimpleAdapter(this,mDataList,R.layout.mainpagelistitem,new String[]{"content","time","price","mealPrice","postUserName","diningRoomName"},new int[]{R.id.MPitemcontentTV,R.id.MPitempriceTV,R.id.MPitemtimeTV,R.id.MPitemmealpriceTV,R.id.MPitempostbyTV,R.id.MPitemdiningroomTV});
			ep.setAdapter(adapter);
			ep.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
				@Override
				public boolean onItemLongClick(AdapterView<?> parent,
						View view, int position, long id) {
					temp_id = position;
				//	getOrdernow();
					showDialog(SELECT_A_CONTACT_DIALOG);
					//return true;
					// TODO Auto-generated method stub
					return true;
				}
			});
		/*	ep.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					temp_id = position;
					
				}
			});*/
		}
		protected void onStart(){
			super.onStart();
			//�����ݵ�listview	
			adapter.notifyDataSetChanged();
			ep.setAdapter(adapter);
			
		}
		//���������ť����ת����������
		public void PublishMainBtn(View v){				
			Intent intent = new Intent();
			intent.setClass(MainPage.this, PublishActivity.class);
			startActivity(intent);
		}
		
		//������õ����еĶ���,����ʱ�����flag
		public void getOrdernow(){
			mDataList.clear();
			try{			
	            // ��������
	            Thread hth = new Thread(){
	            	@Override
	            	public void run(){
	            		//��get��������Ϣ����post��ȥ
	            		System.out.println("hello111 ");            		          		
	            		HttpResponse response;
	            		try{	            			
	            			String url = baseurl + "ddmeal/index/allMessage/";	
	            			List <NameValuePair> params = new ArrayList <NameValuePair>();   //Post�������ͱ���������NameValuePair[]���鴢�� 
	            			params.add(new BasicNameValuePair("lastTime",timelast));
	            			params.add(new BasicNameValuePair("flag","0"));
	            			//ʱ�����flag
	            			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(params);
	            			HttpPost httpRequest = new HttpPost(url);
	            			System.out.println("hello222 ");
	            			//header
	            			Header headers = new BasicHeader("Content-type","application/x-www-form-urlencoded");          
	            			httpRequest.setHeader(headers);
	            			Header headers1 = new BasicHeader("Accept","text/plain");
	            			httpRequest.setHeader(headers1);
	            			Header headers2 = new  BasicHeader("cookie","username="+mNamestr);
	            			httpRequest.setHeader(headers2);
	            			// �����������ݼ���������
	            			httpRequest.setEntity(requestHttpEntity);
	            			// ��Ҫ�ͻ��˶�������������
	            			HttpClient httpClient = new DefaultHttpClient();
	            			// ��������
	            			response = httpClient.execute(httpRequest);         			
	            			System.out.println("hello333 ");   
	            			String nowstr = null;
	            	     //   if(response.getStatusLine().getStatusCode() == 200)   {
	            	        	nowstr = EntityUtils.toString(response.getEntity());   //��ȡ�ַ���
	            	        	System.out.println("hello "+ nowstr);   
	            	        	JSONObject jsonObject;
	            	        	try {
	            	        		jsonObject = new JSONObject(nowstr);
	            	        		String outstr = jsonObject.getString("orders");
	            	        		
	            	        		System.out.println("hello555 "+outstr);
	            	        		//������������������
	            	        		JSONArray arr = new JSONArray(outstr);
	            	        		System.out.println("����shi"+arr.length());
	            	        		for(int i=0;i<arr.length();i++){
	            	        			JSONObject temp = (JSONObject)arr.get(i);
	            	        			Map<String,String> pkstr = new HashMap<String,String>();
	            	        			pkstr.put("pkid",temp.getString("pk"));
	            	        			pklist.add(pkstr);
	            	        			result = temp.getString("fields");
	            	        			System.out.println("fields " + result);
	            	        	
	            	        			JSONObject smalltemp;       			
	            	        			try {
	            							smalltemp = new JSONObject(result);					
	            							Map<String,Object> mMap = new HashMap<String,Object>();
	            		        			mMap.put("content", "����: "+smalltemp.getString("description"));
	            		        			mMap.put("time", "����ʱ��: "+smalltemp.getString("endTime"));
	            		        			mMap.put("price","������: "+smalltemp.getString("price"));
	            		        			mMap.put("mealPrice", "���˼۸�:"+smalltemp.getString("mealPrice"));
	            		        			mMap.put("acceptBy",smalltemp.getString("acceptBy"));
	            		        			mMap.put("postUserName", "��������:"+smalltemp.getString("postUserName"));
	            		        			mMap.put("diningRoomName", "ʳ����:"+smalltemp.getString("diningRoomName"));
	            		        			mDataList.add(mMap);
	            		        			showList.add(mMap);
	            		        			//adapter = 	            		        				            		    				
	            		    				System.out.println("����"+mDataList.get(i).get("content").toString());
	            						} catch (JSONException e) {
	            							// TODO Auto-generated catch block
	            							e.printStackTrace();
	            						}
	            	        			System.out.println("item��ô"+mDataList.get(i).get("content").toString());
	            	        		}
	            	        		adapter.notifyDataSetChanged();
        		    				ep.setAdapter(adapter);
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
		}
		//��ת����Ϣ
		public void NewsMainBtn(View v){
			Intent intent = new Intent();
			intent.setClass(MainPage.this, NewsActivity.class);
			startActivity(intent);
			MainPage.this.finish();
		}
		//��ת�����˽���
		public void PersonNewsMainBtn(View v){
			Bundle mbundlenow = new Bundle();
			mbundlenow.putString("name", javamainpersonnewsbtn.getText().toString());
			Intent intent = new Intent();
			intent.setClass(MainPage.this, PersonNews.class);
			intent.putExtras(mbundlenow);
			startActivity(intent);
			MainPage.this.finish();
		}
		
		@SuppressWarnings("deprecation")
		protected Dialog onCreateDialog(int id) {			
			switch (id) {
			case SELECT_A_CONTACT_DIALOG:
				return new AlertDialog.Builder(MainPage.this).setTitle("JIEDAN")
						.setNegativeButton("����", new DialogInterface.OnClickListener() {
							 
							@Override
							public void onClick(DialogInterface dialog, int which) {
								//�鿴����
								LookDetailAndroid(temp_id);						
							}
						}).setPositiveButton("�ӵ�", new DialogInterface.OnClickListener() {							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								AcceptOrderAndroid(temp_id);								
								System.out.println("temp_id: "+temp_id);							
							}
						}).create();
			default:
				break;
			}
			return super.onCreateDialog(id);
		}
		@SuppressWarnings("deprecation")
		protected void onPrepareDialog(int id, Dialog dialog) {
			super.onPrepareDialog(id, dialog);
		}
		//���ܶ���
		public void AcceptOrderAndroid(final int itemid){
			try{
	            // ��������
	            Thread hth = new Thread(){
	            	@Override
	            	public void run(){
	            		//��get��������Ϣ����post��ȥ
	            		System.out.println("hello111 ");       		          		
	            		HttpResponse response;
	            		try{	            			
	            			String url = baseurl + "ddmeal/index/accept/";	
	            			List <NameValuePair> params = new ArrayList <NameValuePair>();   //Post�������ͱ���������NameValuePair[]���鴢�� 
	            			int idnow = itemid;
	            			String orderid = pklist.get(idnow).get("pkid").toString();
	            			params.add(new BasicNameValuePair("id",orderid));
	            			System.out.println("orderid:"+orderid);
	            			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(params);
	            			HttpPost httpRequest = new HttpPost(url);
	            			System.out.println("hello222 ");
	            			//header
	            			Header headers = new BasicHeader("Content-type","application/x-www-form-urlencoded");          
	            			httpRequest.setHeader(headers);
	            			Header headers1 = new BasicHeader("Accept","text/plain");
	            			httpRequest.setHeader(headers1);
	            			Header headers2 = new  BasicHeader("cookie","username="+mNamestr);
	            			httpRequest.setHeader(headers2);
	            			// �����������ݼ���������
	            			httpRequest.setEntity(requestHttpEntity);
	            			// ��Ҫ�ͻ��˶�������������
	            			HttpClient httpClient = new DefaultHttpClient();
	            			// ��������
	            			response = httpClient.execute(httpRequest);          			
	            			System.out.println("hello333 ");   
	            			String nowstr = null;
	            	     //   if(response.getStatusLine().getStatusCode() == 200)   {
	            	        	nowstr = EntityUtils.toString(response.getEntity());   //��ȡ�ַ���
	            	        	System.out.println("hello8 "+ nowstr);
	            	        	JSONObject jsonObject;
	            	        	try {
	            	        		jsonObject = new JSONObject(nowstr);
	            	        		String outstr = jsonObject.getString("error");
	            	        		
	            	        		System.out.println("hello555 "+outstr);
	            	        		if(outstr.equalsIgnoreCase("false")){
	            	        			//���ܶ���
	            	        			hereList = mDataList.get(itemid);
	            	        			System.out.println("�ѽ��ܶ���");
	            	        			mDataList.remove(itemid);
	            	        			pklist.remove(itemid);
	            	        			adapter.notifyDataSetChanged();
	            	        			ep.setAdapter(adapter);
	            	        			temp_id = itemid;
	            	        			handler.obtainMessage(1,temp_id).sendToTarget();
	            	        			System.out.println("��ת�ɹ���?");
	            	        			//�޸�������	            	        				            	        			
	            	        		}
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
		}
		//���Բ���handler
		Handler handler = new Handler(){
				public void handleMessage(android.os.Message msg){
						if(msg.what==1){
							LookDetailAndroid(temp_id);
						}
						
					};
				};
		//�鿴��������
		public void LookDetailAndroid(final int itemid){
			Map<String,Object> here = new HashMap<String,Object>();
			here = showList.get(itemid);
			Bundle mbundle = new Bundle();			
			mbundle.putString("postUserName", here.get("postUserName").toString());
			mbundle.putString("price", here.get("price").toString());
			mbundle.putString("content", here.get("content").toString());
			mbundle.putString("time", here.get("time").toString());
			mbundle.putString("mealPrice", here.get("mealPrice").toString());
			mbundle.putString("acceptBy", here.get("acceptBy").toString());
			
			//��ת
			Intent intent = new Intent();
			intent.setClass(MainPage.this, OrderInfoActivity.class);
			intent.putExtras(mbundle);
			startActivity(intent);
			MainPage.this.finish();
		}
}
