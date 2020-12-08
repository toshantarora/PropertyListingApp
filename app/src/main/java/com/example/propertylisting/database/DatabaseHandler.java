package com.example.propertylisting.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.propertylisting.models.AddBooth;
import com.example.propertylisting.models.AddHousingBoard;
import com.example.propertylisting.models.AddKothi;
import com.example.propertylisting.models.AddSco;
import com.example.propertylisting.models.AddSociety;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final String TAG = "DatabaseHandler";

    private static final String KEY_DATABASE = "property_listing_database.db";

    private static final String KEY_ROWID           = "id";
    public static final String KEY_NAME             = "sellername";
    public static final String KEY_ADDRESS          = "address";
    public static final String KEY_NUMBER           = "phone";
    public static final String KEY_PRICE            = "price";
    private static final String KEY_TYPE            = "type";
    public static final String KEY_CITY             = "city";
    public static final String KEY_SECTOR           = "sector";
    public static final String KEY_CONDITION        = "condition";
    public static final String KEY_CATEGORY         = "category";
    public static final String KEY_FLOOR            = "floor";
    private static final String KEY_FORSALE         = "forsale";
    private static final String KEY_BASEMENT        = "basement";
    private static final String KEY_STOREY          = "storey";

    public static final String KEY_SOCIETY          = "society";
    private static final String TABLE_HB            = "hb";
    private static final String TABLE_SOCIETY       = "society";
    private static final String TABLE_KOTHI         = "kothi";
    private static final String TABLE_SCO           = "sco";
    private static final String TABLE_BOOTH         = "booth";
    private static final String TABLE_CHD           = "chandigarh";
    private static final String TABLE_MOHALI        = "mohali";
    private static final String TABLE_PKL           = "panchkula";

    private static final int DATABASE_VERSION = 1;

    public DatabaseHandler(Context context)
    {
        super(context, KEY_DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE chandigarh (id INTEGER PRIMARY KEY AUTOINCREMENT, sector VARCHAR)");

        db.execSQL("CREATE TABLE mohali (id INTEGER PRIMARY KEY AUTOINCREMENT, sector VARCHAR)");
        db.execSQL("CREATE TABLE panchkula (id INTEGER PRIMARY KEY AUTOINCREMENT, sector VARCHAR)");
        db.execSQL("CREATE TABLE signup (full_name TEXT, user_id TEXT PRIMARY KEY, password TEXT, retype TEXT, dob TEXT, mobile_no TEXT)");

        db.execSQL("insert into chandigarh(sector) values('All')");
        db.execSQL("insert into chandigarh(sector) values('Sector 1')");
        db.execSQL("insert into chandigarh(sector) values('Sector 2')");
        db.execSQL("insert into chandigarh(sector) values('Sector 3')");
        db.execSQL("insert into chandigarh(sector) values('Sector 4')");
        db.execSQL("insert into chandigarh(sector) values('Sector 5')");
        db.execSQL("insert into chandigarh(sector) values('Sector 6')");
        db.execSQL("insert into chandigarh(sector) values('Sector 7')");
        db.execSQL("insert into chandigarh(sector) values('Sector 8')");
        db.execSQL("insert into chandigarh(sector) values('Sector 9')");
        db.execSQL("insert into chandigarh(sector) values('Sector 10')");
        db.execSQL("insert into chandigarh(sector) values('Sector 11')");
        db.execSQL("insert into chandigarh(sector) values('Sector 12')");
        db.execSQL("insert into chandigarh(sector) values('Sector 14')");
        db.execSQL("insert into chandigarh(sector) values('Sector 15')");
        db.execSQL("insert into chandigarh(sector) values('Sector 16')");

        db.execSQL("insert into mohali(sector) values('All')");
        db.execSQL("insert into mohali(sector) values('Sector 66')");
        db.execSQL("insert into mohali(sector) values('Sector 67')");
        db.execSQL("insert into mohali(sector) values('Sector 68')");
        db.execSQL("insert into mohali(sector) values('Sector 69')");
        db.execSQL("insert into mohali(sector) values('Sector 70')");
        db.execSQL("insert into mohali(sector) values('Sector 71')");
        db.execSQL("insert into mohali(sector) values('Sector 72')");
        db.execSQL("insert into mohali(sector) values('Sector 73')");

        db.execSQL("insert into panchkula(sector) values('All')");
        db.execSQL("insert into panchkula(sector) values('Sector 1')");
        db.execSQL("insert into panchkula(sector) values('Sector 2')");
        db.execSQL("insert into panchkula(sector) values('Sector 3')");
        db.execSQL("insert into panchkula(sector) values('Sector 4')");
        db.execSQL("insert into panchkula(sector) values('Sector 5')");
        db.execSQL("insert into panchkula(sector) values('Sector 6')");
        db.execSQL("insert into panchkula(sector) values('Sector 7')");
        db.execSQL("insert into panchkula(sector) values('Sector 8')");
        db.execSQL("insert into panchkula(sector) values('Sector 9')");

        db.execSQL("CREATE TABLE kothi (id INTEGER PRIMARY KEY AUTOINCREMENT, sellername VARCHAR," +
                " phone VARCHAR, address VARCHAR, city VARCHAR, sector VARCHAR, condition VARCHAR, price INTEGER," +
                " storey VARCHAR, basement VARCHAR, forsale VARCHAR)");

        db.execSQL("CREATE TABLE sco (id INTEGER PRIMARY KEY AUTOINCREMENT, sellername VARCHAR," +
                " phone VARCHAR, address VARCHAR, city VARCHAR, sector VARCHAR, condition VARCHAR, price INTEGER," +
                " storey VARCHAR, basement VARCHAR, forsale VARCHAR)");

        db.execSQL("CREATE TABLE hb (id INTEGER PRIMARY KEY AUTOINCREMENT, sellername VARCHAR," +
                " phone VARCHAR, address VARCHAR, city VARCHAR, sector VARCHAR, condition VARCHAR, price INTEGER," +
                " floor VARCHAR, category VARCHAR)");

        db.execSQL("CREATE TABLE society (id INTEGER PRIMARY KEY AUTOINCREMENT, sellername VARCHAR," +
                " phone VARCHAR, society VARCHAR, address VARCHAR, city VARCHAR, sector VARCHAR, condition VARCHAR, price INTEGER," +
                " floor VARCHAR, category VARCHAR)");

        db.execSQL("CREATE TABLE booth (id INTEGER PRIMARY KEY AUTOINCREMENT, sellername VARCHAR," +
                " phone VARCHAR, address VARCHAR, city VARCHAR, sector VARCHAR, condition VARCHAR, price INTEGER)");
    }

    public long insertSignup(String fullName, String userId, String password, String retype, String dob, String mobileNo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("full_name", fullName);
        cv.put("user_id", userId);  // UserId is nothing but just a username
        cv.put("password", password);
        cv.put("retype", retype);
        cv.put("dob", dob);
        cv.put("mobile_no", mobileNo);

        long n = db.insert("signup", null, cv);
        return n;
    }

    public boolean ifUserExist(String userId, String phone, String COLUMN_NAME)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from signup where user_id='"+userId+"' and "+COLUMN_NAME+"='"+phone+"'",null );

        // Looping through all rows and adding to list
        if (cursor.getCount() != 0)
            return true;
        else
            return false;
    }

    public boolean findUserByPhone(String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from signup where mobile_no='"+phone+"'",null );

        // Looping through all rows and adding to list
        if (cursor.getCount() != 0)
            return true;
        else
            return false;
    }

    public int updatePassword(String new_password, String new_retype, String whereClause, String[] args)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", new_password);
        values.put("retype", new_retype);

        int n = db.update("signup", values, whereClause, args);
        return n;
    }

    public String[] getOldPassword(String phone, String userId)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        if(userId == null) {
            Cursor cursor = db.rawQuery("select password, retype from signup where mobile_no='"+phone+"'",null);
            if(cursor.moveToFirst()) {
                return new String[]{cursor.getString(cursor.getColumnIndex("password")), cursor.getString(cursor.getColumnIndex("retype"))};
            }
        } else {
            Cursor cursor1 = db.rawQuery("select password, retype from signup where mobile_no='"+phone+"' and user_id='"+userId+"'",null);
            if(cursor1.moveToFirst()) {
                return new String[]{cursor1.getString(cursor1.getColumnIndex("password")), cursor1.getString(cursor1.getColumnIndex("retype"))};
            }
        }
        return null;
    }

    public String getName(String phone, String userId)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        if(userId == null) {
            Cursor cursor = db.rawQuery("select full_name from signup where mobile_no='"+phone+"'",null);
            if(cursor.moveToFirst()) {
                return cursor.getString(cursor.getColumnIndex("full_name"));
            }
        } else {
            Cursor cursor1 = db.rawQuery("select full_name from signup where mobile_no='"+phone+"' and user_id='"+userId+"'",null);
            if(cursor1.moveToFirst()) {
                return cursor1.getString(cursor1.getColumnIndex("full_name"));
            }
        }
        return null;
    }

    public Boolean editProfile(String userId, String fullName, String dob, String mobileNo)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv=new ContentValues();

        cv.put("full_name", fullName);
        cv.put("dob", dob);
        cv.put("mobile_no", mobileNo);

        return null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BOOTH);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_HB);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_KOTHI);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SCO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CHD);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MOHALI);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PKL);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SOCIETY);
        onCreate(db);
    }

    public long newHb(AddHousingBoard housingBoard)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, housingBoard.getSellerName());                // Name
        values.put(KEY_CITY, housingBoard.getCity());                      // Area
        values.put(KEY_ADDRESS, housingBoard.getPropertyAddress());        // Address
        values.put(KEY_NUMBER, housingBoard.getNumber());                  // Number
        values.put(KEY_PRICE, housingBoard.getPrice());                    // Price
        values.put(KEY_CATEGORY, housingBoard.getCategory());              // Type
        values.put(KEY_CONDITION, housingBoard.getCondition());            // Type
        values.put(KEY_FLOOR, housingBoard.getFloor());                    // Type
        values.put(KEY_SECTOR , housingBoard.getSector());                 // Type

        long n = db.insert(TABLE_HB, null, values);
        return n;
    }

    public long newSociety(AddSociety society)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, society.getSellerName());                // Name
        values.put(KEY_CITY, society.getCity());                      // Area
        values.put(KEY_ADDRESS, society.getPropertyAddress());        // Address
        values.put(KEY_NUMBER, society.getNumber());                  // Number
        values.put(KEY_PRICE, society.getPrice());                    // Price
        values.put(KEY_CATEGORY, society.getCategory());              // Type
        values.put(KEY_CONDITION, society.getCondition());            // Type
        values.put(KEY_FLOOR, society.getFloor());                    // Type
        values.put(KEY_SECTOR , society.getSector());                 // Type
        values.put(KEY_SOCIETY, society.getSociety());                // Type

        long n = db.insert(TABLE_SOCIETY, null, values);
        return n;
    }

    public long newKothi(AddKothi kothi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, kothi.getSellerName());                // Name
        values.put(KEY_CITY, kothi.getCity());                      // Area
        values.put(KEY_ADDRESS, kothi.getPropertyAddress());        // Address
        values.put(KEY_NUMBER, kothi.getNumber());                  // Number
        values.put(KEY_PRICE, kothi.getPrice());                    // Price
        values.put(KEY_BASEMENT, kothi.getBasement());              // Type
        values.put(KEY_CONDITION, kothi.getCondition());            // Type
        values.put(KEY_FORSALE, kothi.getForSale());                // Type
        values.put(KEY_STOREY , kothi.getStorey());                 // Type
        values.put(KEY_SECTOR , kothi.getSector());                 // Type

        long n = db.insert(TABLE_KOTHI,null, values);
        return n;
    }

    public long newSco(AddSco sco)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, sco.getSellerName());                // Name
        values.put(KEY_CITY, sco.getCity());                      // Area
        values.put(KEY_ADDRESS, sco.getPropertyAddress());        // Address
        values.put(KEY_NUMBER, sco.getNumber());                  // Number
        values.put(KEY_PRICE, sco.getPrice());                    // Price
        values.put(KEY_BASEMENT, sco.getBasement());              // Type
        values.put(KEY_CONDITION, sco.getCondition());            // Type
        values.put(KEY_FORSALE, sco.getForSale());                // Type
        values.put(KEY_STOREY , sco.getStorey());                 // Type
        values.put(KEY_SECTOR , sco.getSector());                 // type

        long n = db.insert(TABLE_SCO,null, values);
        return n;
    }

    public long newBooth(AddBooth booth)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, booth.getSellerName());                // Name
        values.put(KEY_CITY, booth.getCity());                      // Area
        values.put(KEY_ADDRESS, booth.getPropertyAddress());        // Address
        values.put(KEY_NUMBER, booth.getNumber());                  // Number
        values.put(KEY_PRICE, booth.getPrice());                    // Price
        values.put(KEY_CONDITION, booth.getCondition());            // Type
        values.put(KEY_SECTOR, booth.getSector());

        long n = db.insert(TABLE_BOOTH, null, values);
        return n;
    }


    public Cursor getAllSociety()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from society",null);
        return cursor;
    }

    public Cursor getSocietyCitySector(String city, String sector, double max, double min)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from society where city='"+city+"' and sector='"+sector+"' and price " +
                "between "+min+" and "+max,null);
        return cursor;
    }

    public Cursor getSocietyCityAll(double max, double min)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from society where price between "+min+" and "+max,null);
        return cursor;
    }

    public Cursor getSocietySectorAll(String city, double max, double min)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from society where city= '"+city+"' and price between "+min+" and "+max,null);
        return cursor;
    }

    public Cursor getSocietyAddress(String address)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from society where address like'%"+address+"%'",null);
        return cursor;
    }

    public Cursor getSocietyName(String society)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from society where society like'%"+society+"%'",null);
        return cursor;
    }

    public Cursor getSocietyAddressFull(String address)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from society where address ='"+address+"'",null);
        return cursor;
    }

    public boolean SocietyDelete(String address)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_SOCIETY, KEY_ADDRESS+"='"+address+"'", null);
        return true;
    }


    public Cursor getAllHb()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from hb",null);
        return cursor;
    }

    public Cursor getHbCitySector(String city, String sector, double max, double min)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from hb where city='"+city+"' and sector='"+sector+"' and price " +
                "between "+min+" and "+max,null);
        return cursor;
    }

    public Cursor getHbCityAll(double max, double min)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from hb where price between "+min+" and "+max,null);
        return cursor;
    }

    public Cursor getHbSectorAll(String city, double max, double min)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from hb where city= '"+city+"' and price between "+min+" and "+max,null);
        return cursor;
    }

    public Cursor getHbAddress(String address)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from hb where address like'%"+address+"%'",null);
        return cursor;
    }

    public Cursor getHbAddressFull(String address)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from hb where address ='"+address+"'",null);
        return cursor;
    }

    public boolean HbDelete(String address)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_HB, KEY_ADDRESS+"='"+address+"'", null);
        return true;
    }


    public Cursor getAllSco()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from sco",null);
        return cursor;
    }

    public Cursor getScoCitySector(String city, String sector, double max, double min)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from sco where city='"+city+"' and sector='"+sector+"' and price between "+min+" and "+max,null);
        return cursor;
    }

    public Cursor getScoCityAll(double max, double min)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from sco where price between "+min+" and "+max,null);
        return cursor;
    }

    public Cursor getScoSectorAll(String city, double max, double min)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from sco where city= '"+city+"' and _price between "+min+" and "+max,null);
        return cursor;
    }

    public Cursor getScoAddress(String address)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from sco where address like'%"+address+"%'",null);
        return cursor;
    }

    public Cursor getScoAddressFull(String address)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from sco where address ='"+address+"'",null);
        return cursor;
    }

    public boolean ScoDelete(String address)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_SCO, KEY_ADDRESS+"='"+address+"'", null);
        return true;
    }


    public List<AddBooth> getBooths()
    {
        List<AddBooth> recordList = new ArrayList<>();
        String sql = "SELECT * FROM booth";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst())
        {
            do {
                String sellerName = cursor.getString(1);
                String pNumber = cursor.getString(2);
                String propertyAddress = cursor.getString(3);
                String city = cursor.getString(4);
                String sector = cursor.getString(5);
                String condition = cursor.getString(6);
                double price = Integer.parseInt(cursor.getString(7));

                AddBooth object = new AddBooth(sellerName, propertyAddress, city, sector, condition, pNumber, price);
                recordList.add(object);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recordList;
    }

    public List<AddSociety> getSocieties()
    {
        List<AddSociety> recordList = new ArrayList<>();
        String sql="SELECT * FROM society";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst())
        {
            do {
                String sellerName = cursor.getString(1);
                String pNumber = cursor.getString(2);
                String society = cursor.getString(3);
                String propertyAddress = cursor.getString(4);
                String city = cursor.getString(5);
                String sector = cursor.getString(6);
                String condition = cursor.getString(7);
                double price = Integer.parseInt(cursor.getString(8));
                String floor = cursor.getString(9);
                String category = cursor.getString(10);

                 AddSociety object = new AddSociety(sellerName, propertyAddress, society, city, sector, condition, floor, category, pNumber, price);
                 recordList.add(object);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recordList;
    }

    public List<AddKothi> getKothis()
    {
        List<AddKothi> recordList= new ArrayList<>();
        String sql="SELECT * FROM kothi";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql, null);

        if(cursor.moveToFirst())
        {
            do {
                String sellerName = cursor.getString(1);
                String pNumber = cursor.getString(2);
                String propertyAddress = cursor.getString(3);
                String city = cursor.getString(4);
                String sector = cursor.getString(5);
                String condition = cursor.getString(6);
                double price = Integer.parseInt(cursor.getString(7));
                String storey = cursor.getString(8);
                String basement = cursor.getString(9);
                String forsale = cursor.getString(10);

                AddKothi object = new AddKothi(sellerName, propertyAddress, city, sector, condition, storey, basement, forsale, pNumber, price);
                recordList.add(object);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recordList;
    }

    public List<AddHousingBoard> getHousingBoard()
    {
        List<AddHousingBoard> recordList = new ArrayList<AddHousingBoard>();
        String sql = "SELECT * FROM hb";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst())
        {
            do {
                String sellerName = cursor.getString(1);
                String pNumber = cursor.getString(2);
                String propertyAddress = cursor.getString(3);
                String city = cursor.getString(4);
                String sector = cursor.getString(5);
                String condition = cursor.getString(6);
                double price = Integer.parseInt(cursor.getString(7));
                String floor = cursor.getString(8);
                String category = cursor.getString(9);

                 AddHousingBoard object = new AddHousingBoard(sellerName, propertyAddress, city, sector, condition, category, floor, pNumber, price);
                 recordList.add(object);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recordList;
    }

    public List<AddSco> getSco()
    {
        List<AddSco> recordList = new ArrayList<AddSco>();
        String sql = "SELECT * FROM sco";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);


        if(cursor.moveToFirst())
        {
            do {
                String sellerName = cursor.getString(1);
                String pNumber = cursor.getString(2);
                String propertyAddress = cursor.getString(3);
                String city = cursor.getString(4);
                String sector = cursor.getString(5);
                String condition = cursor.getString(6);
                double price = Integer.parseInt(cursor.getString(7));
                String storey = cursor.getString(8);
                String basement = cursor.getString(9);
                String forsale = cursor.getString(10);

                AddSco object = new AddSco(sellerName, propertyAddress, city, sector, condition, storey, basement, forsale, pNumber, price);
                recordList.add(object);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recordList;
    }

    public Cursor getAllBooth()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from booth",null);
        return cursor;
    }

    public Cursor getBoothCitySector(String city, String sector, double max,double min)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from booth where city='"+city+"' and sector='"+sector+"' and price " +
                "between "+min+" and "+max,null);
        return cursor;
    }

    public Cursor getBoothCityAll(double max, double min)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from booth where price between "+min+" and "+max,null);
        return cursor;
    }

    public Cursor getBoothSectorAll(String city,double max,double min)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from booth where city= '"+city+"' and price between "+min+" and "+max,null);
        return cursor;
    }

    public Cursor getBoothAddress(String address)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from booth where address like'%"+address+"%'",null);
        return cursor;
    }

    public Cursor getBoothAddressFull(String address)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from booth where address ='"+address+"'",null);
        return cursor;
    }

    public boolean BoothDelete(String address)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_BOOTH, KEY_ADDRESS+"='"+address+"'", null);
        return true;
    }

    public Cursor getKothiCitySector(String city, String sector, double max, double min)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from kothi where city='"+city+"' and sector='"+sector+"' and price " +
                "between "+min+" and "+max,null);
        return cursor;
    }

    public Cursor getKothiSectorAll(String city, double max, double min)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from kothi where city= '"+city+"' and price between "+min+" and "+max,null);
        return cursor;
    }

    public Cursor getKothiCityAll(double max,double min)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from kothi where price between "+min+" and "+max,null);
        return cursor;
    }

    public Cursor getKothiAddress(String address)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from kothi where address like'%"+address+"%'",null);
        return cursor;
    }

    public Cursor getAllKothi()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from kothi",null);
        return cursor;
    }

    public boolean KothiDelete(String address)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_KOTHI, KEY_ADDRESS+"='"+address+"'", null);
        return true;
    }

    public Cursor getKothiAddressFull(String address)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from kothi where address='"+address+"'",null);
        return cursor;
    }

    public void resetTables()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(KEY_DATABASE, null, null);
    }

    // Getting single contact

    public Cursor getChandigarh()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from chandigarh",null);
        return cursor;
    }

    public Cursor getMohali()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from mohali",null);
        return cursor;
    }

    public Cursor getPanchkula()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from panchkula",null);
        return cursor;
    }

    public List<String> type()
    {
        List<String> typeLabel = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from type", null);
        if (cursor.moveToFirst()) {
            do {
                typeLabel.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return typeLabel;
    }

    public List<String> sector()
    {
        List<String> sectorLabel = new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from area", null);
        if (cursor.moveToFirst()) {
            do {
                sectorLabel.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return sectorLabel;
    }

    public void updateBooth(String sellerName, String address, String city, String sector, int phone,
                            double price, String condition, String adres)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, sellerName);            // Name
        values.put(KEY_CITY, city);                  // Area
        values.put(KEY_ADDRESS, address);            // Address
        values.put(KEY_NUMBER, phone);               // Number
        values.put(KEY_PRICE, price);                // Price
        values.put(KEY_CONDITION, condition);        // type
        values.put(KEY_SECTOR, sector);
        db.update(TABLE_BOOTH, values, KEY_ADDRESS+"='"+adres+"'",null);
    }

    public void UpdateSco(String sellerName, String address, String city, String sector, int number, double price, String condition,
                          String storey, String basement, String forsale, String adres)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, sellerName);            // Name
        values.put(KEY_CITY, city);                  // Area
        values.put(KEY_ADDRESS, address);            // Address
        values.put(KEY_NUMBER, number);              // Number
        values.put(KEY_PRICE, price);                // Price
        values.put(KEY_BASEMENT, basement);          // Type
        values.put(KEY_CONDITION, condition);        // Type
        values.put(KEY_FORSALE,forsale);             // Type
        values.put(KEY_STOREY ,storey);              // Type
        values.put(KEY_SECTOR ,sector);              // Type
        db.update(TABLE_SCO, values, KEY_ADDRESS+"='"+adres+"'",null);
    }

    public void UpdateKothi(String sellerName, String address, String city, String sector, int number, double price, String condition,
                            String storey, String basement, String forsale, String adres)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, sellerName);            // Name
        values.put(KEY_CITY, city);                  // Area
        values.put(KEY_ADDRESS, address);            // Address
        values.put(KEY_NUMBER, number);              // Number
        values.put(KEY_PRICE, price);                // Price
        values.put(KEY_BASEMENT, basement);          // Type
        values.put(KEY_CONDITION, condition);        // Type
        values.put(KEY_FORSALE,forsale);             // Type
        values.put(KEY_STOREY ,storey);              // Type
        values.put(KEY_SECTOR ,sector);              // Type
        db.update(TABLE_KOTHI, values, KEY_ADDRESS+"='"+adres+"'",null);
    }

    public void UpdateSociety(String sellerName, String address, String society, String city, String sector, int number, double price,
                              String condition, String floor,String category,String adres)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, sellerName);            // Name
        values.put(KEY_CITY, city);                  // Area
        values.put(KEY_ADDRESS, address);            // Address
        values.put(KEY_NUMBER, number);              // Number
        values.put(KEY_PRICE, price);                // Price
        values.put(KEY_CATEGORY, category);          // Type
        values.put(KEY_CONDITION, condition);        // Type
        values.put(KEY_FLOOR,floor);                 // Type
        values.put(KEY_SECTOR ,sector);              // Type
        values.put(KEY_SOCIETY,society);             // Type
        db.update(TABLE_SOCIETY, values, KEY_ADDRESS+"='"+adres+"'",null);
    }

    public void UpdateHb(String sellerName, String address, String city, String sector, int number, double price, String condition,
                         String floor,String category,String adres)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, sellerName);            // Name
        values.put(KEY_CITY, city);                  // Area
        values.put(KEY_ADDRESS, address);            // Address
        values.put(KEY_NUMBER, number);              // Number
        values.put(KEY_PRICE, price);                // Price
        values.put(KEY_CATEGORY, category);          // Type
        values.put(KEY_CONDITION, condition);        // Type
        values.put(KEY_FLOOR,floor);                 // Type
        values.put(KEY_SECTOR ,sector);              // Type
        db.update(TABLE_HB, values, KEY_ADDRESS+"='"+adres+"'",null);
    }
}