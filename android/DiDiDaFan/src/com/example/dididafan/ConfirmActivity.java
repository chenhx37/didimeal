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
		//绑定，你可以直接使用netidConfirmjava.getText().toString()得到它的内容
		netidConfirmjava = (EditText)findViewById(R.id.netidConfirm);
		passwordConfirmjava = (EditText)findViewById(R.id.passwordConfirm);
	}
	//上传验证信息
	//这个就是你们之前说的netid验证啦
	public void confirmInfoClick(View v){
		
	}

}
