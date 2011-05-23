package com.vandals.maps;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BuildingOpenHelper extends SQLiteOpenHelper{
    private static final String TAG = "BuildingOpenHelper";
    static final int VERSION = 2;
    static final String DBNAME = "buildinglist.db";
    static final String TABLE = "buildinglist";
    static final String _ID = "_id";
    static final String C_XCOORD = "xcoord";
    static final String C_YCOORD = "ycoord";
    static final String C_NAME = "name";
    static final String C_ABBRV = "abbrv";
    static final String C_DISPLAYED = "displayed";
    static final String DB_CREATE_QUERY = 
        "CREATE TABLE " + TABLE + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C_XCOORD + " INTEGER, " + C_YCOORD + " INTEGER, " + 
        C_NAME + " TEXT, " + C_ABBRV + " TEXT, " + C_DISPLAYED + " INTEGER)";
   static final String DB_INSERT_QUERY = "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117011202,46727433,'Art & Architecture','A&A',0)";
   static final String DB_INSERT_QUERY2 = "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117011368,46727676,'A&A Interior Design','AAID',1)";
   Context context;

   //To insert new location into the DB. Enter it in this array; following the conventions laid out here.
   static final String[] INSERTS = {
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117011202,46727433,'Art & Architecture','A&A',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117011368,46727676,'Art & Architecture Interior Design','AAID',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117011143,46725948,'Administration','AD',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117018760,46730066,'Ag & Ext Education','AEEB',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117011143,46725948,'Agricultural Biotechnology','AGBIO',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117013890,46729044,'Agricultural Science','AG SCI',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117011497,46725624,'J.A. Albertson/Business','ALB',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117024983,46728904,'Animal Research Pavilion','ARP',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117010649,46729529,'Buchanan Engineering Lab','BEL',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117012334,46728139,'Carol Ryrie Brink Hall','BRI',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117012302,46726043,'Education','ED',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117009834,46729264,'Engineering/Physics','EP',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117020295,46730897,'Athletic Fields','FIELDS',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117010220,46724602,'Graduate Art Studio','GAS',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117016786,46723351,'Golf Clubhouse','GCH',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117176020,46730184,'Greenhouse','GH',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117011400,46729485,'GaussJohnson Engineering Lab','GJ',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117018009,46728897,'Hartung Theatre','HRTNG',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117012398,46727463,'Idaho Commons','IDC',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117002195,46724477,'Business Technology Incubator','INCUB',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117005768,46726109,'Industrial Technology Education','ITED',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117011529,46728934,'Janssen Engineering','JEB',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117020220,46729617,'J.W. Martin Laboratory','JML',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117017044,46726345,'KibbieASUI Activity Center','KIBBIE',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117015810,46728154,'Menard Law','LAW',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117013986,46727441,'Library','LIB',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117010446,46727154,'Life Science South','LIFE',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117012645,46730478,'Living & Learning Community','LLC',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117010671,46728985,'McClure Hall','MCCL',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117013664,46726624,'Memorial Gym','M GYM',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117011057,46728470,'Mines','MINES',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117010843,46728066,'Morrill Hall','MORR',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117007828,46725676,'Lionel Hampton School of Music','MUSIC',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117012602,46729389,'Natural Resources','CNR',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117012388,46728904,'Navy','NAVY',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117010006,46724852,'Niccolls','NICCOL',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117013729,46725521,'Physical Education','PEB',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117012806,46728514,'Archie Phinney Hall','PHI',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117013686,46726051,'Swim Center','POOL',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117012141,46724764,'RadioTV Center','RADTV',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117014018,46728022,'Renfrew Hall','REN',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117008815,46724491,'Ridenbaugh Hall','RIDH',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117015435,46730162,'Shoup Hall','SHOUP',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117009212,4672722,'Student Health','SHS',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117013514,46731956,'Student Recreation Center','SRC',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117007205,46728683,'Student Union Building','SUB',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117012902,46727433,'Teaching & Learning Center','TLC',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117016293,4673039,'Wallace Residential Center','WALLC',0)",
       "INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-117019372,46730993,'Wicks Field','WICKS',0)"
   };
   
    
    public BuildingOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
        this.context = context;
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"Calling overrided onCreate.");
        try {
            Log.d(TAG, "Creating DB");
            db.execSQL(DB_CREATE_QUERY);
            Log.d(TAG, "Inserting into DB");
            for (String token : INSERTS){
                db.execSQL(token);
            }
        } catch (Exception e) {
            Log.d(TAG, "Error Creating DB");
        } 
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Entering onUgrade for some reason.");
    }
}
