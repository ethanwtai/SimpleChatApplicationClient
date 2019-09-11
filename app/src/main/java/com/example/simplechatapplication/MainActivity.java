package com.example.simplechatapplication;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
//import android.widget.EditText;
import android.widget.TextView;

import java.io.*;
import java.net.*;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.statusBox);
        String newText = "Starting Up";
        text.setText(newText);
        new Connection().execute();
    }

    public void sendMessage(View view) {

        // Change screen to second part
        /*
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        */


    }


    private class Connection extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String host = "10.0.2.2";
            Socket socket = null;
            try {
                TextView status = findViewById(R.id.statusBox);
                String newText = "Trying to Connect&#8230;";
                status.setText(newText);

                socket = new Socket(host, 8008);

                newText = "Connected";
                status.setText(newText);
            } catch (IOException e) {
                e.printStackTrace();
            }

            PrintWriter out = null;
            try {
                out = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream()));

                out.println(EXTRA_MESSAGE);
                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException i) {
                i.printStackTrace();

            }
            return null;}


        @Override
        protected void onPostExecute (String result){
        }

        @Override
        protected void onPreExecute () {
        }

        @Override
        protected void onProgressUpdate (Void...values){
        }


    }
}
