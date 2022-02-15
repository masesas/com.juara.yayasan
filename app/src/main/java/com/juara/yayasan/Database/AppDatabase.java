package com.juara.yayasan.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {
        LayananEntity.class,
        DataAnakEntity.class,
        DataPetugasEntity.class,
        BukaBersamaEntity.class,
        SantunanEntity.class,

}, version = 2, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase database;

    public static AppDatabase getInstance(Context context) {
        synchronized (AppDatabase.class) {
            if (database == null) {
                database = Room.databaseBuilder(context,
                        AppDatabase.class,
                        "yayasan")
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return database;
    }

    static final Migration FROM_1_TO_2 = new Migration(1, 2) {
        @Override
        public void migrate(final SupportSQLiteDatabase database) {
            //database.execSQL("CREATE TABLE IF NOT EXISTS DataAnakEntity (ID INTEGER )");
        }
    };

    //layanan
    public abstract LayananDAO layananDAO();
    //end layanan

    //master data
    public abstract DataAnakDAO dataAnakDAO();
    public abstract DataPetugasDAO dataPetugasDAO();
    //end master data

    //program
    public abstract BukaBersamaDAO bukaBersamaDAO();
    public abstract SantunanDAO santunanDAO();
    //end program
}
