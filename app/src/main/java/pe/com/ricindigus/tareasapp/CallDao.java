package pe.com.ricindigus.tareasapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;


@Dao
public interface CallDao {
    @Insert
    void insertCall(@NonNull Llamada call);

    @Delete
    void deleteCall(@NonNull Llamada call);

    @Query("select * from call_table")
    LiveData<List<Llamada>> getCalls();

    @Query("select * from call_table where uuid = :uuid")
    LiveData<Llamada> getCallById(String uuid);
}
