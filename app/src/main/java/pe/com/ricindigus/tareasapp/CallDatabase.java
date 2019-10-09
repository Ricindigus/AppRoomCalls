package pe.com.ricindigus.tareasapp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Llamada.class} ,version = 1,exportSchema = false)
public abstract class CallDatabase extends RoomDatabase {
    private static volatile CallDatabase instance;
    private static final String DATABASE_NAME = "calls_db";

    public static CallDatabase getInstance(Context context) {
        if (instance == null){
            synchronized (CallDatabase.class){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            CallDatabase.class,DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

    public abstract CallDao callDao();
}
