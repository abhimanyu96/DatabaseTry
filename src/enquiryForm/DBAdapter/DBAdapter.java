package enquiryForm.DBAdapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends SQLiteOpenHelper
{

	public static final String DB_NAME = "enuiry.db";
	public static final int DB_VERSION = 1;
	public static final String ID_COL = "id";
	public static final String NAME_COL = "name";
	public static final String AGE_COL = "age";
	public static final String ADDRESS_COL = "address";
	public static final String GENDER_COL = "gender";
	public static final String ENQUIRY_TABLE = "enquiry";
	
	public static final String CREATE_TABLE_ENQUIRY = "CREATE TABLE " + ENQUIRY_TABLE + "(" +ID_COL + " integer primary key autoIncrement," + NAME_COL + " text not null," + AGE_COL + " integer not null," + ADDRESS_COL + " text not null," + GENDER_COL + " text not null)";
	public DBAdapter(Context context) {
		super(context, DB_NAME,null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_ENQUIRY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// TODO Auto-generated method stub
		if(oldVersion < newVersion)
		{
			db.execSQL("DROP table" + ENQUIRY_TABLE);
			onCreate(db);
			
		}
		

	}

}
