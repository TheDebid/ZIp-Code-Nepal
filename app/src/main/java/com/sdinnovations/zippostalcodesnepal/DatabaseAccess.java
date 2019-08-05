package com.sdinnovations.zippostalcodesnepal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;


    public DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);

        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public ArrayList<String> getDisc() {
        ArrayList<String> subName = new ArrayList<>();
        c = db.rawQuery("select DISTINCT District from postal_codes_of_nepal ", new String[]{});

        while (c.moveToNext()) {

            subName.add(c.getString(0));
        }
        return subName;
    }

    public ArrayList<String> getSearch(String stext) {
        ArrayList<String> dname = new ArrayList<>();
        c = db.rawQuery("select District from postal_codes_of_nepal WHERE District like '%"+stext+"%'", new String[]{});

        while (c.moveToNext()) {

            dname.add(c.getString(0));
        }
        return dname;
    }


    public ArrayList<String> getInsideDistrict(String districtname) {
        ArrayList<String> insideDistrict = new ArrayList<>();
        c = db.rawQuery("select Post_Office from postal_codes_of_nepal where District = '" + districtname + "'", new String[]{});

        while (c.moveToNext()) {

            insideDistrict.add(c.getString(0));
        }
        return insideDistrict;
    }

    public String getDistrictDetails(String postalAddress, String DistrictName) {
        c = db.rawQuery("select District from postal_codes_of_nepal where Post_Office = '" + postalAddress + "' and District = '" + DistrictName + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String d = c.getString(0);
            buffer.append("" + d);
        }
        return buffer.toString();
    }
    public String getType(String postalAddress, String DistrictName) {
        c = db.rawQuery("select Post_Office_Type from postal_codes_of_nepal where Post_Office = '" + postalAddress + "' and District = '" + DistrictName + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String d = c.getString(0);
            buffer.append("" + d);
        }
        return buffer.toString();
    }
    public String getCode(String postalAddress, String DistrictName) {
        c = db.rawQuery("select Postal_Code from postal_codes_of_nepal where Post_Office = '" + postalAddress + "' and District = '" + DistrictName + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String d = c.getString(0);
            buffer.append("" + d);
        }
        return buffer.toString();
    }

}
