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

//���������ʵ��
public class PublishActivity extends Activity {
	private String baseurl = "http://127.0.0.1/";
	private EditText javapocontent;
	private EditText javapojewal;
	private Button timePicker;
	private int hour1;
	private int minute1;
	private static final String[] canteeList={"һ��","����","����","�ķ�","����","����"};
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.placeorder);	
		spinner = (Spinner)findViewById(R.id.spinnerCantee);
		//����ѡ������ArrayAdapter��������
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,canteeList);
        //���������б�ķ��
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         
        //��adapter ��ӵ�spinner��
        spinner.setAdapter(adapter);
		javapocontent = (EditText)findViewById(R.id.POConetntET);
		javapojewal = (EditText)findViewById(R.id.POJewalET);
		String url = baseurl + "release";
		String pocontentstr = javapocontent.getText().toString();
		String pojewalstr = javapojewal.getText().toString();
		//���õص�
		String poaddressstr = spinner.getSelectedItem().toString();
		List <NameValuePair> params = new ArrayList <NameValuePair>();   //Post�������ͱ���������NameValuePair[]���鴢�� 
		params.add(new BasicNameValuePair("description",pocontentstr));
		params.add(new BasicNameValuePair("price",pojewalstr));
		try{
			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(params);
			HttpPost httpRequest = new HttpPost(url);
			 // �����������ݼ���������
			httpRequest.setEntity(requestHttpEntity);
			// ��Ҫ�ͻ��˶�������������
            HttpClient httpClient = new DefaultHttpClient();
            // ��������
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
	
	//��ת
	public void POMainPublishBtnClick(View v){
		Intent intent = new Intent();
  		intent.setClass(PublishActivity.this, MainPage.class);
  		startActivity(intent);
	}
	
	//��ת
	public void PONewsPostBtnClick(View v){
		Intent intent = new Intent();
  		intent.setClass(PublishActivity.this, PublishActivity.class);
  		startActivity(intent);
	}
	
	//��ת
	public void PONewsPulishBtnClick(View v){
		Intent intent = new Intent();
  		intent.setClass(PublishActivity.this, NewsActivity.class);
  		startActivity(intent);
	}
	
}
