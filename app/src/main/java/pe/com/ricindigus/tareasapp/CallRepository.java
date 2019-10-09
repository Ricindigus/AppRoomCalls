package pe.com.ricindigus.tareasapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import java.util.List;

public class CallRepository {
    private volatile CallRepository instance;
    private CallDao callDao;

    public CallRepository(Application application) {
        CallDatabase callDatabase = CallDatabase.getInstance(application);
        callDao = callDatabase.callDao();
    }

    public void insertCall(Llamada call){
        new InsertAsyncTask(callDao).execute(call);
    }

    public void deleteCall(Llamada call){
        new DeleteAsyncTask(callDao).execute(call);
    }

    public LiveData<List<Llamada>> getAllCall(){
        return callDao.getCalls();
    }

    public LiveData<Llamada> getCallById(String uuid){
        return callDao.getCallById(uuid);
    }

    private class InsertAsyncTask extends AsyncTask<Llamada,Void,Void>{
        private CallDao callDao;

        public InsertAsyncTask(CallDao callDao) {
            this.callDao = callDao;
        }

        @Override
        protected Void doInBackground(Llamada... llamadas) {
            callDao.insertCall(llamadas[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends AsyncTask<Llamada,Void,Void>{
        private CallDao callDao;

        public DeleteAsyncTask(CallDao callDao) {
            this.callDao = callDao;
        }

        @Override
        protected Void doInBackground(Llamada... llamadas) {
            callDao.deleteCall(llamadas[0]);
            return null;
        }
    }
}
