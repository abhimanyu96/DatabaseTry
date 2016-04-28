package com.example.dao;

import java.util.LinkedList;
import java.util.List;

import com.example.enquiryAccessVO.EnquiryVO;

import enquiryForm.DBAdapter.DBAdapter;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAO 
{
	private Context context;
	private DBAdapter adapter;
	private SQLiteDatabase database;
	public DAO(Context c)
	{
		this.context=c;
		adapter = new DBAdapter(this.context);
	}
	public void openDatabase()
	{
		database = adapter.getWritableDatabase();
	}
	public void closeDatabase()
	{
		database.close();
		adapter.close();
	}
	public boolean addEnquiryRecord(EnquiryVO o)
	{
		openDatabase();
		boolean isInsertSuccessful =  false;
		ContentValues values = new ContentValues();
		values.put(DBAdapter.NAME_COL, o.getName());
		values.put(DBAdapter.AGE_COL, o.getAge());
		values.put(DBAdapter.ADDRESS_COL, o.getAddress());
		values.put(DBAdapter.GENDER_COL, o.getGender());
		
		long l = database.insert(DBAdapter.ENQUIRY_TABLE,null, values);
		if(l<0)
		{
			isInsertSuccessful = false;
			
		}
		else if(l>0)
			{
				isInsertSuccessful=true;
			}
			
		return isInsertSuccessful;
	}
	public List<EnquiryVO> getEnquiryList()
	{
		List <EnquiryVO> enquiryList = new LinkedList<EnquiryVO>() ; 
		openDatabase();
		Cursor cursor = database.rawQuery("SELECT * FROM " + DBAdapter.ENQUIRY_TABLE,null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			EnquiryVO object = new EnquiryVO();
			object.setId(cursor.getString(cursor.getColumnIndex(DBAdapter.ID_COL)));
			object.setName(cursor.getString(cursor.getColumnIndex(DBAdapter.NAME_COL)));
			object.setAddress(cursor.getString(cursor.getColumnIndex(DBAdapter.ADDRESS_COL)));
			object.setAge(cursor.getString(cursor.getColumnIndex(DBAdapter.AGE_COL)));
			object.setGender(cursor.getString(cursor.getColumnIndex(DBAdapter.GENDER_COL)));
			enquiryList.add(object);
			cursor.moveToNext();
		}
		return enquiryList;
	}
	
}
