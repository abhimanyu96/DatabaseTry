package com.example.enquiryform;

import java.util.List;

import com.example.dao.DAO;
import com.example.enquiryAccessVO.EnquiryVO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class List_view extends Activity {

	private ListView listview;
	private List<EnquiryVO> datalist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		listview = (ListView)findViewById(R.id.newlv); 
		pop();
		DatabaseAdapter o = new DatabaseAdapter(this, datalist);
		listview.setAdapter(o);
	}
	private class DatabaseAdapter extends BaseAdapter
	{
		private LayoutInflater inflater;
		private List<EnquiryVO> data;
		private Context c;
		public DatabaseAdapter(Context context,List<EnquiryVO> list)
		{
			this.c=context;
			this.data = list;
			this.inflater = ((Activity)this.c).getLayoutInflater();

		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}
		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public View getView(int pos, View view, ViewGroup viewgroup) {
			Holder h= new Holder();
			if(view==null)
			{
				view = inflater.inflate(R.layout.displaytable, null) ;
				h.a=(TextView)view.findViewById(R.id.id);
				h.b=(TextView)view.findViewById(R.id.name);
				h.c=(TextView)view.findViewById(R.id.age);
				h.d=(TextView)view.findViewById(R.id.address);
				h.e=(TextView)view.findViewById(R.id.gender);
				view.setTag(h);
			}
			else
			{
				h=(Holder) view.getTag();
			}
			h.a.setText(data.get(pos).getId());
			h.b.setText(data.get(pos).getName());
			h.c.setText(data.get(pos).getAge());
			h.d.setText(data.get(pos).getAddress());
			h.e.setText(data.get(pos).getGender());
			// TODO Auto-generated method stub
			return view;
		}

	}
	private class Holder
	{
		private TextView a,b,c,d,e;
	}
	private void pop()
	{
		DAO dao = new DAO(this);
		datalist = dao.getEnquiryList();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_view, menu);
		return true;
	}

}
