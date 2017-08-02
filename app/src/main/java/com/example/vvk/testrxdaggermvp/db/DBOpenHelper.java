package com.example.vvk.testrxdaggermvp.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DBNAME = "cars.db";
    public  static final String TABLE = "cars";

    public  static final String ID = "_id";
    public  static final String BRAND = "brand";
    public  static final String MODEL = "model";
    public  static final String ENGINE = "engine";
    public  static final String COLOR = "color";

    public static final String CREATE_TABLE= "CREATE TABLE " + TABLE + "("
                + ID + " INTEGER NOT NULL PRIMARY KEY,"
                + BRAND + " TEXT NOT NULL,"
                + MODEL + " TEXT NOT NULL,"
                + ENGINE + " TEXT NOT NULL,"
                + COLOR + " TEXT NOT NULL"
                + ")";


    public DBOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

        db.insert(TABLE, null, new CarsItemBuilder().id(1).brand("Volvo").model("S80").engine("2.0 D").color("black").build());
        db.insert(TABLE, null, new CarsItemBuilder().id(2).brand("Skoda").model("Superb").engine("2.0 D").color("silver").build());
        db.insert(TABLE, null, new CarsItemBuilder().id(3).brand("BMW").model("320").engine("2.0 D").color("white").build());
        db.insert(TABLE, null, new CarsItemBuilder().id(4).brand("Audi").model("A8").engine("5.0").color("black").build());
        db.insert(TABLE, null, new CarsItemBuilder().id(5).brand("Mazda").model("6").engine("1.8").color("red").build());
        db.insert(TABLE, null, new CarsItemBuilder().id(6).brand("Skoda").model("Yeti").engine("2.0 D").color("silver").build());
        db.insert(TABLE, null, new CarsItemBuilder().id(7).brand("Audi").model("A4").engine("2.0").color("black").build());
        db.insert(TABLE, null, new CarsItemBuilder().id(8).brand("Mazda").model("3").engine("2.0").color("black").build());
        db.insert(TABLE, null, new CarsItemBuilder().id(9).brand("Volvo").model("S40").engine("2.0 I").color("blue").build());
        db.insert(TABLE, null, new CarsItemBuilder().id(10).brand("Volvo").model("S60").engine("3.0 D").color("white").build());
        db.insert(TABLE, null, new CarsItemBuilder().id(11).brand("Skoda").model("Octavia").engine("1.6 D").color("silver").build());
        db.insert(TABLE, null, new CarsItemBuilder().id(12).brand("BMW").model("730").engine("3.0 I").color("blue").build());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static final class CarsItemBuilder {
        private final ContentValues values = new ContentValues();

        public CarsItemBuilder id(long id) {
            values.put(ID, id);
            return this;
        }

        public CarsItemBuilder brand(String brand) {
            values.put(BRAND, brand);
            return this;
        }

        public CarsItemBuilder model(String model) {
            values.put(MODEL, model);
            return this;
        }

        public CarsItemBuilder engine(String engine) {
            values.put(ENGINE, engine);
            return this;
        }

        public CarsItemBuilder color(String color) {
            values.put(COLOR, color);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }
}
