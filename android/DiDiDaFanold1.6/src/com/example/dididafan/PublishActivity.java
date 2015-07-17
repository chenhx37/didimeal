package com.example.dididafan;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

//发布界面的实现
public class PublishActivity extends Activity {
	private String baseurl = "http://127.0.0.1/";
	private EditText javapocontent;
	private EditText javapojewal;
	private Button timePicker;
	private int hour1;
	private int minute1;
	private static final String[] canteeList={"一饭","二饭","三饭","四饭","清真","君城"};
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.placeorder);	
		spinner = (Spinner)findViewById(R.id.spinnerCantee);
		//将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,canteeList);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         
        //将adapter 添加到spinner中
        spinner.setAdapter(adapter);
		javapocontent = (EditText)findViewById(R.id.POConetntET);
		javapojewal = (EditText)findViewById(R.id.POJewalET);
		String url = baseurl + "release";
		String pocontentstr = javapocontent.getText().toString();
		String pojewalstr = javapojewal.getText().toString();
		//饭堂地点
		String poaddressstr = spinner.getSelectedItem().toString();
		List <NameValuePair> params = new ArrayList <NameValuePair>();   //Post运作传送变量必须用NameValuePair[]数组储存 
		params.add(new BasicNameValuePair("description",pocontentstr));
		params.add(new BasicNameValuePair("price",pojewalstr));
		try{
			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(params);
			HttpPost httpRequest = new HttpPost(url);
			 // 将请求体内容加入请求中
			httpRequest.setEntity(requestHttpEntity);
			// 需要客户端对象来发送请求
            HttpClient httpClient = new DefaultHttpClient();
            // 发送请求
            HttpResponse response = httpClient.execute(httpRequest);
          /*  HttpEntity httpEntity = response.getEntity();
            try
            {
            	
                InputStream inputStream = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        inputStream));
                String result = "";
                String line = "";
                while (null != (line = reader.readLine()))
                {
                    result += line;

                }

                System.out.println(result);
                
                if(result.contains("true")){
                	Intent intent = new Intent();
            		intent.setClass(PublishActivity.this, MainPage.class);
            		startActivity(intent);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }*/
          String nowstr = HttpUtils.sendPostMessage(url, "utf-8");
          if(nowstr.contains("true")){
        	Intent intent = new Intent();
      		intent.setClass(PublishActivity.this, MainPage.class);
      		startActivity(intent);
          }
		}catch(Exception e)
        {
            e.printStackTrace();
        }
			
	}
	
	//跳转
	public void POMainPublishBtnClick(View v){
		Intent intent = new Intent();
  		intent.setClass(PublishActivity.this, MainPage.class);
  		startActivity(intent);
	}
	
	//跳转
	public void PONewsPostBtnClick(View v){
		Intent intent = new Intent();
  		intent.setClass(PublishActivity.this, PublishActivity.class);
  		startActivity(intent);
	}
	
	//跳转
	public void PONewsPulishBtnClick(View v){
		Intent intent = new Intent();
  		intent.setClass(PublishActivity.this, NewsActivity.class);
  		startActivity(intent);
	}
	
}
