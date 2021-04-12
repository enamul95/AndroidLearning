package era.com.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import era.com.app.model.RegistrationModel;

public class DBHelpler extends SQLiteOpenHelper {

    private static final String DBNAME = "androidlearning.db";
    private static final int VERSION = 1;


    //    public DBHelpler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    public DBHelpler(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table registration_tb(id integer primary key autoincrement,fullname text,email text,phone text,pass text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS registration_tb");
        onCreate(db);
    }

    public long doInserReg(RegistrationModel model) {
        long rValue = -1;
        SQLiteDatabase sqldb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fullname", model.getFullName());
        cv.put("email", model.getEamil());
        cv.put("phone", model.getPhone());
        cv.put("pass", model.getPassword());
        rValue = sqldb.insert("registration_tb", null, cv);
        return rValue;

    }


    public Long doLogin(RegistrationModel model) {
        long rValue = -1;
        String sql = "SELECT * FROM registration_tb where phone='"+model.getPhone()+ "' AND pass = '"+model.getPassword()+"' ";
        SQLiteDatabase sqdb = this.getReadableDatabase();
        Cursor c = sqdb.rawQuery(sql, null);
        if (c.moveToFirst()) {
            rValue = 1;
        }
        return rValue;

    }




    public boolean isExist(RegistrationModel model) {
        boolean rValue = false;
        String sql = "SELECT * FROM registration_tb where phone='"+model.getPhone()+ "' ";
        SQLiteDatabase sqdb = this.getReadableDatabase();
        Cursor c = sqdb.rawQuery(sql, null);
        if (c.moveToFirst()) {
            rValue = true;
        }
        return rValue;

    }

    public RegistrationModel getData(String mobileNumber) {
        RegistrationModel outModel = new RegistrationModel();
        String sql = "SELECT * FROM registration_tb where phone='"+mobileNumber+ "' ";
        SQLiteDatabase sqdb = this.getReadableDatabase();
        Cursor c = sqdb.rawQuery(sql, null);
        if (c.moveToFirst()) {
            //String name = c.getString(c.getColumnIndex("fullname"));
            outModel.setFullName(c.getString(c.getColumnIndex("fullname")));
            outModel.setEamil(c.getString(c.getColumnIndex("email")));
            outModel.setPhone(c.getString(c.getColumnIndex("phone")));
        }
        return outModel;

    }
    // inster into registration_tb(fullname,email,phone,pass)values('enamul','enaul@gmail.com','','11');

}
