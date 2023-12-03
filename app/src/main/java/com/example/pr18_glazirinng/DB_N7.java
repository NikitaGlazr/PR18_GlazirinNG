package com.example.pr18_glazirinng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_N7 {
    private static final String DB_NAME = "mydb2";
    private static final int DB_VERSION = 1;

    private static final String COMPANY_TABLE = "company";
    public static final String COMPANY_COLUMN_ID = "_id";
    public static final String COMPANY_COLUMN_NAME = "name";
    private static final String COMPANY_TABLE_CREATE = "create table "
            + COMPANY_TABLE + "(" + COMPANY_COLUMN_ID
            + " integer primary key, " + COMPANY_COLUMN_NAME + " text" + ");";

    private static final String PC_TABLE = "PC";
    public static final String PC_COLUMN_ID = "_id";
    public static final String PC_COLUMN_NAME = "name";
    public static final String PC_COLUMN_COMPANY = "company";
    private static final String PC_TABLE_CREATE = "create table "
            + PC_TABLE + "(" + PC_COLUMN_ID
            + " integer primary key autoincrement, " + PC_COLUMN_NAME
            + " text, " + PC_COLUMN_COMPANY + " integer" + ");";

    private final Context mCtx;
    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DB_N7(Context ctx) {
        mCtx = ctx;
    }

    public void open() {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }
    public void close() {
        if (mDBHelper != null)
            mDBHelper.close();
    }
    public Cursor getCompanyData() {
        return mDB.query(COMPANY_TABLE, null, null, null, null, null, null);
    }
    public Cursor getPhoneData(long companyID) {
        return mDB.query(PC_TABLE, null, PC_COLUMN_COMPANY + " = "
                + companyID, null, null, null, null);
    }
    private class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            ContentValues cv = new ContentValues();

            String[] companies = new String[] { "Plait", "Asus", "MSI" };

            db.execSQL(COMPANY_TABLE_CREATE);
            for (int i = 0; i < companies.length; i++) {
                cv.put(COMPANY_COLUMN_ID, i + 1);
                cv.put(COMPANY_COLUMN_NAME, companies[i]);
                db.insert(COMPANY_TABLE, null, cv);
            }
            String[] pcPlait = new String[] { "GamePro", "Gamerocke",
                    "Meddle" };
            String[] pcAsus = new String[] { "Evangelion", "Foundet",
                    "ProGaming" };
            String[] pcMSI = new String[] { "Zefirus", "Suprime"};

            db.execSQL(PC_TABLE_CREATE);
            cv.clear();
            for (int i = 0; i < pcPlait.length; i++) {
                cv.put(PC_COLUMN_COMPANY, 1);
                cv.put(PC_COLUMN_NAME, pcPlait[i]);
                db.insert(PC_TABLE, null, cv);
            }
            for (int i = 0; i < pcAsus.length; i++) {
                cv.put(PC_COLUMN_COMPANY, 2);
                cv.put(PC_COLUMN_NAME, pcAsus[i]);
                db.insert(PC_TABLE, null, cv);
            }
            for (int i = 0; i < pcMSI.length; i++) {
                cv.put(PC_COLUMN_COMPANY, 3);
                cv.put(PC_COLUMN_NAME, pcMSI[i]);
                db.insert(PC_TABLE, null, cv);
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
