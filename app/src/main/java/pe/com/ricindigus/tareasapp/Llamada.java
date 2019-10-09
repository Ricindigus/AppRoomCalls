package pe.com.ricindigus.tareasapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "call_table")
public class Llamada {
    @PrimaryKey
    @NonNull
    private String uuid;
    @ColumnInfo(name = "call_number")
    private String numero;
    @ColumnInfo(name = "call_duration")
    private int duracion;
    @ColumnInfo(name = "call_time")
    private String hora;

    public Llamada(@NonNull String uuid, String numero, int duracion, String hora) {
        this.uuid = uuid;
        this.numero = numero;
        this.duracion = duracion;
        this.hora = hora;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }

    public String getNumero() {
        return numero;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getHora() {
        return hora;
    }

    public void setUuid(@NonNull String uuid) {
        this.uuid = uuid;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
