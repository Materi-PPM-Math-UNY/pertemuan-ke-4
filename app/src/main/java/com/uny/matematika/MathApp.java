package com.uny.matematika;

import android.app.Application;
import androidx.room.Room;
import com.uny.matematika.data.MhsDatabase;

public class MathApp extends Application {

    public static MhsDatabase mhsDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mhsDatabase = Room.databaseBuilder(getApplicationContext(), MhsDatabase.class, "math-db-1-2")
                .allowMainThreadQueries().build();
    }
}
