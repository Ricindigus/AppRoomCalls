package pe.com.ricindigus.tareasapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.CallHolder> {

    private List<Llamada> mLlamadas;
    private CallOnClick callOnClick;

    interface CallOnClick {
        void onClick(String uuid);
    }

    public CallAdapter(CallOnClick callOnClick) {
        this.callOnClick = callOnClick;
    }


    public void setmLlamadas(List<Llamada> mLlamadas) {
        this.mLlamadas = mLlamadas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CallHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_llamada,parent,false);
        return new CallHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CallHolder holder, int position) {
        final Llamada currentLlamada = mLlamadas.get(position);
        holder.txtUUID.setText(currentLlamada.getUuid());
        holder.txtNumero.setText(currentLlamada.getNumero());
        holder.txtDuracion.setText(currentLlamada.getDuracion()+" mins");
        holder.txtHora.setText(currentLlamada.getHora());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callOnClick.onClick(currentLlamada.getUuid());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mLlamadas == null)
            return 0;
        return mLlamadas.size();
    }

    static class CallHolder extends RecyclerView.ViewHolder{
        TextView txtUUID, txtNumero, txtDuracion, txtHora;
        public CallHolder(@NonNull View itemView) {
            super(itemView);
            txtUUID = itemView.findViewById(R.id.txtUuid);
            txtNumero = itemView.findViewById(R.id.txtNumero);
            txtDuracion = itemView.findViewById(R.id.txtDuracion);
            txtHora = itemView.findViewById(R.id.txtHora);

        }
    }
}
