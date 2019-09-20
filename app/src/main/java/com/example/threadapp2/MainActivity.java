package com.example.threadapp2;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

import static android.view.View.GONE;
import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private ProgressBar mProgress;

    private final static int UPDATE_COUNT = 100;
    private final static int END_COUNT = 101;
    private final static int PROGRESS_MAX = 10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);
        mProgress = findViewById(R.id.progressBar);
        mProgress.setMax(PROGRESS_MAX);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                runner.execute();
            }
        });
    }
    private class AsyncTaskRunner extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] objects) {
            for(int k=1;k<11;k++) {
                try {
                    Thread.sleep(1000);
                    publishProgress(k);
                }catch(InterruptedException ie){
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            mProgress.setProgress((int)values[0]);
        }

        @Override
        protected void onPostExecute(Object o) {
            mButton.setEnabled(true);
            mProgress.setVisibility(View.GONE);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mButton.setEnabled(false);
            mProgress.setVisibility(View.VISIBLE);
        }
    }
}
