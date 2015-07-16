package com.example.dididafan;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicHeader;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PersonNews extends Activity {
	private EditText javaFName;
	private EditText javaName;
	private EditText javaAddress;
	private EditText javaPhone;
	private EditText javaNetID;
	private String baseurl = "http://ddmeal.sinaapp.com/";
	private String nameNow;
	private String realnamejson;
	private String netidjson;
	private String emailjson;
	private String phonejson;
	private String addressjson;
	private String nicknamejson;
	private String[] result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);	
		javaFName = (EditText)findViewById(R.id.UserInfoFNameEt);
		javaName = (EditText)findViewById(R.id.UserInfoNameEt);
		javaAddress = (EditText)findViewById(R.id.UserInfoAddressEt);
		javaPhone = (EditText)findViewById(R.id.UserInfoPhoneEt);
		//javaNetID = (EditText)findViewById(R.id.UserInfoNetIDEt);
		Bundle mbundlenow = this.getIntent().getExtras();
		nameNow = mbundlenow.getString("name");
		javaName.setText(nameNow);
		getInfofromserver();   
		
	}
	/*先从网页得到个人数据，然后放出来，方便修改 */
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg){
			if(msg.what==1){
				javaPhone.setText(phonejson);
			}
			if(msg.what==2){
				javaFName.setText(nicknamejson);
			}
			if(msg.what==3){
				javaAddress.setText(addressjson);
			}
		};
	};

	//更新按钮
	public void UserInfoUpdateBtnClick(View v){
		try{			
            // 发送请求
            Thread hth = new Thread(){
            	@Override
            	public void run(){
            		//先get到个人信息，再post上去
            		System.out.println("hello111 ");
            		       		
            		HttpResponse response;
            		try{
            			String fnamestr = javaFName.getText().toString();
            			String namestr = javaName.getText().toString();
            			String addressstr = javaAddress.getText().toString();
            			String phonestr = javaPhone.getText().toString();
            		//	String netidstr = javaNetID.getText().toString();
            			String url = baseurl + "ddmeal/index/updatePersonal/";
		
            			List <NameValuePair> params = new ArrayList <NameValuePair>();   //Post运作传送变量必须用NameValuePair[]数组储存 
            			params.add(new BasicNameValuePair("username",namestr));
            			params.add(new BasicNameValuePair("nickName",fnamestr));
            			params.add(new BasicNameValuePair("phone",phonestr));
            			params.add(new BasicNameValuePair("address",addressstr));
            		//	params.add(new BasicNameValuePair("netID",netidstr));
		
            			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(params);
            			HttpPost httpRequest = new HttpPost(url);
            			System.out.println("hello222 ");
            			//header
            			Header headers = new BasicHeader("Content-type","application/x-www-form-urlencoded");          
            			httpRequest.setHeader(headers);
            			Header headers1 = new BasicHeader("Accept","text/plain");
            			httpRequest.setHeader(headers1);
            			Header headers2 = new  BasicHeader("cookie","username="+nameNow);
            			httpRequest.setHeader(headers2);
            			// 将请求体内容加入请求中
            			httpRequest.setEntity(requestHttpEntity);
            			// 需要客户端对象来发送请求
            			HttpClient httpClient = new DefaultHttpClient();
            			// 发送请求
            			response = httpClient.execute(httpRequest);
            			System.out.println("hello333 ");
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
	}
	public void UserInfoMainPageBtnClick(View v){	
		Intent intent = new Intent();
		intent.setClass(PersonNews.this, MainPage.class);
		startActivity(intent);
	}
	public void getInfofromserver(){
		 Thread hth = new Thread(){
         	@Override
         	public void run(){
         		String psdstr = nameNow;
         		String url = baseurl + "ddmeal/index/personalMs/";
         		System.out.println("hello111 ");
         		HttpGet httpRequest = new HttpGet(url);
         		HttpClient httpClient = new DefaultHttpClient();
         		Header headers = new BasicHeader("Content-type","application/x-www-form-urlencoded");       
         		httpRequest.setHeader(headers);
         		Header headers1 = new BasicHeader("Accept","text/plain");
         		httpRequest.setHeader(headers1);
         		Header headers2 = new  BasicHeader("cookie","username="+nameNow);
         		System.out.println(nameNow);
         		httpRequest.setHeader(headers2);
         		
         		System.out.println("hello222 ");
         		//发送请求
         		try{
         			System.out.println("hello333 ");
         			HttpResponse response = httpClient.execute(httpRequest);
         			
         			System.out.println("hello444 ");
         			// 显示响应
         			String nowstr = null;
         			nowstr = EntityUtils.toString(response.getEntity());   //获取字符串
         			//get到才跳转
         			System.out.println("hellohere "+ nowstr);   
         			
         			JSONObject jsonObject;
         			jsonObject = new JSONObject(nowstr);
         			String myselfjson = jsonObject.getString("myself");
	        		//result = jsonObject.getString("errorMs");	
	        		System.out.println("hello555 "+myselfjson);
	        		JSONObject jsonObjectin = new JSONObject(myselfjson);
	        		phonejson = jsonObjectin.getString("phone");
	        		realnamejson = jsonObjectin.getString("realName");
	        		nicknamejson = jsonObjectin.getString("nickName");
	        		emailjson = jsonObjectin.getString("email");
	        		addressjson = jsonObjectin.getString("address");
	        		System.out.println(addressjson); 
	        	//	System.out.println(result[0]);
         		}catch (Exception e){
         			e.printStackTrace();
         		}
         		handler.obtainMessage(1,phonejson).sendToTarget();
         		handler.obtainMessage(2,nicknamejson).sendToTarget();
         		handler.obtainMessage(3,addressjson).sendToTarget();
         		
         	}
		 };
		 hth.start();
		System.out.println("hello666 ");     

	}
	//跳转到发布
	public void UserInfoPublishBtnClick(View v){
		Intent intent = new Intent();
		intent.setClass(PersonNews.this, PublishActivity.class);
		startActivity(intent);
	}
	//跳转到消息
	public void UserInfoNewsBtnClick(View v){
		Intent intent = new Intent();
		intent.setClass(PersonNews.this, NewsActivity.class);
		startActivity(intent);
	}
}
