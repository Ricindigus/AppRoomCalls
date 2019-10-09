package pe.com.ricindigus.tareasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton fabAdd;
    private CallAdapter callAdapter;
    private CallRepository callRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        fabAdd = findViewById(R.id.fab);

        callRepository = new CallRepository(getApplication());
        callAdapter = new CallAdapter(new CallAdapter.CallOnClick() {
            @Override
            public void onClick(String uuid) {
                startActivity(new Intent(MainActivity.this,AddCallActivity.class)
                        .putExtra(AddCallActivity.KEY_UUID,uuid));
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(callAdapter);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this,
                        AddCallActivity.class);
                startActivity(mIntent);
            }
        });


        callRepository.getAllCall().observe(MainActivity.this,
                new Observer<List<Llamada>>() {
                    @Override
                    public void onChanged(List<Llamada> llamadas) {
                        callAdapter.setmLlamadas(llamadas);
                    }
                });
    }
}
