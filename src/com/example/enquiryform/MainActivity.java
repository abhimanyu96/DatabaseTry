package com.example.enquiryform;

import com.example.dao.DAO;
import com.example.enquiryAccessVO.EnquiryVO;

import android.location.Address;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText name,age,address;
	private Button add, getbtn;
	private RadioGroup rg;
	private void hideKeyboard() {   
	    // Check if no view has focus:
	    View view = this.getCurrentFocus();
	    if (view != null) {
	        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
	        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	    }
	}
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		name=(EditText) findViewById(R.id.name);
		age=(EditText) findViewById(R.id.age);
		address=(EditText) findViewById(R.id.address);
		hideKeyboard();
		add=(Button) findViewById(R.id.addbtn);
		getbtn=(Button) findViewById(R.id.getbtn);
		rg=(RadioGroup) findViewById(R.id.rg);
		
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(name.getText().toString().equals("")||age.getText().toString().equals("")||address.getText().toString().equals(""))
				{
					Toast.makeText(MainActivity.this, "WOW, Enter something Idiot", Toast.LENGTH_LONG).show();
				}
				else
				{
					EnquiryVO object = new EnquiryVO();
					object.setName(name.getText().toString());
					object.setAddress(address.getText().toString());
					object.setAge(age.getText().toString());
					if(rg.getCheckedRadioButtonId()==R.id.male)
					{
						object.setGender("MALE");
					}
					else
					{
						object.setGender("FEMALE");
					}
					DAO dao = new DAO(MainActivity.this);
					boolean isInsert = dao.addEnquiryRecord(object);
					if(isInsert==true)
					{
						Toast.makeText(MainActivity.this, "Successful" , Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(MainActivity.this, "UnSuccessful" , Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		getbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,List_view.class);
				startActivity(intent);
			}
		});
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		name.setText("");
		age.setText("");
		address.setText("");
		name.requestFocus();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
