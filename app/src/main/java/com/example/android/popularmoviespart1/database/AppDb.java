package com.example.android.popularmoviespart1.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.example.android.popularmoviespart1.utils.Constants;
import com.example.android.popularmoviespart1.database.daos.MovieDao;
import com.example.android.popularmoviespart1.database.data.Movie;

@Database(entities = {Movie.class}, version = 4, exportSchema = false)
public abstract class AppDb extends RoomDatabase {
    public abstract MovieDao movieDao();


    private static AppDb db;

    public static AppDb getDb(final Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(),
                    AppDb.class,
                    Constants.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return db;
    }

    public static void cleanUp(){db = null;}
}
