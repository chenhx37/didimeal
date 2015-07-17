package com.example.dididafan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class PublishActivity extends Activity {
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
         
        //����¼�Spinner�¼�����  
        //spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
         
      //ʹ��������ʽ����
      /*  class SpinnerSelectedListener implements OnItemSelectedListener{
     
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                view.setText("���Ѫ���ǣ�"+m[arg2]);
            }
     
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        }*/       
	}
	public void MainPublishBtn(View v){
		Intent intent = new Intent();
		intent.setClass(PublishActivity.this, MainPage.class);
		startActivity(intent);
		//MainPage.this.finish();
	}
	public void NewsPulishBtn(View v){
		Intent intent = new Intent();
		intent.setClass(PublishActivity.this, NewsActivity.class);
		startActivity(intent);
		//MainPage.this.finish();
	}
	public void PersonNewsPublishBtn(View v){
		Intent intent = new Intent();
		intent.setClass(PublishActivity.this, PersonNews.class);
		startActivity(intent);
		//MainPage.this.finish();
	}

}
