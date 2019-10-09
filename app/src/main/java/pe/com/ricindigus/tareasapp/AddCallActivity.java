package pe.com.ricindigus.tareasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddCallActivity extends AppCompatActivity {
    public static final String KEY_UUID = "key_uuid";
    private EditText edtNumero, edtDuracion, edtHora;
    private TextView btGuardar, btEliminar, txtUUID;
    private CallRepository callRepository;
    private ImageView imgClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_call);

        edtHora = findViewById(R.id.edtHora);
        edtDuracion = findViewById(R.id.edtDuracion);
        edtNumero = findViewById(R.id.edtNumero);
        btGuardar = findViewById(R.id.btGuardar);
        btEliminar = findViewById(R.id.btEliminar);
        txtUUID = findViewById(R.id.txtUUID);
        imgClock = findViewById(R.id.imgClock);

        callRepository = new CallRepository(getApplication());


        imgClock.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddCallActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edtHora.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Seleccionar Hora");
                mTimePicker.show();

            }
        });

        if (getIntent().getExtras() == null)
            createInstanceCallWorker();
        else{
            btEliminar.setVisibility(View.VISIBLE);
            btGuardar.setVisibility(View.GONE);
            String uuid = getIntent().getExtras().getString(KEY_UUID);
            final LiveData<Llamada> currentLlamada = callRepository.getCallById(uuid);
            currentLlamada.observe(this, new Observer<Llamada>() {
                @Override
                public void onChanged(Llamada llamada) {
                    currentLlamada.removeObserver(this);
                    displayCall(llamada);
                }
            });

        }

    }

    private void displayCall(Llamada call) {
        txtUUID.setText(call.getUuid());
        edtNumero.setText(call.getNumero());
        edtDuracion.setText(call.getDuracion() + "");
        edtHora.setText(call.getHora());
        edtNumero.setEnabled(false);
        edtHora.setEnabled(false);
        edtDuracion.setEnabled(false);

    }

    private void createInstanceCallWorker() {
        OneTimeWorkRequest callWork = new OneTimeWorkRequest
                .Builder(CallWorker.class).build();
        txtUUID.setText(callWork.getId().toString());
    }

    public void eliminarCall(View view) {
        callRepository.deleteCall(getLlamadaInstance());
        finish();
    }

    public void guardarCall(View view) {
        callRepository.insertCall(getLlamadaInstance());
        finish();
    }

    private Llamada getLlamadaInstance(){
        return new Llamada(txtUUID.getText().toString(),
                edtNumero.getText().toString(),
                Integer.parseInt(edtDuracion.getText().toString()),
                edtHora.getText().toString());
    }
}
