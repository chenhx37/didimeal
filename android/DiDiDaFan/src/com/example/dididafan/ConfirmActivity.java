package com.example.dididafan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ConfirmActivity extends Activity{
	private EditText netidConfirmjava;
	private EditText passwordConfirmjava;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirmationpage);
		//�󶨣������ֱ��ʹ��netidConfirmjava.getText().toString()�õ���������
		netidConfirmjava = (EditText)findViewById(R.id.netidConfirm);
		passwordConfirmjava = (EditText)findViewById(R.id.passwordConfirm);
	}
	//�ϴ���֤��Ϣ
	//�����������֮ǰ˵��netid��֤��
	public void confirmInfoClick(View v){
		
	}

}
