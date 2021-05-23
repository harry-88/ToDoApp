package com.atmostsoft.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvDate;
    FloatingActionButton btnFloat;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ImageView ivImg;
    TextView tvEmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        ivImg = findViewById(R.id.ivEmptyPic);
        tvEmp = findViewById(R.id.tvEmptyLabel);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new itemAdaptor(this,new ArrayList<>());
        recyclerView.setAdapter(adapter);

        tvDate = findViewById(R.id.tvData);
        Calendar calendar = Calendar.getInstance();
        String dateFormate = DateFormat.getDateInstance().format(calendar.getTime());

        tvDate.setText(dateFormate);

        btnFloat = findViewById(R.id.tvEmptyLabel);
        btnFloat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(com.atmostsoft.todoapp.MainActivity.this,com.atmostsoft.todoapp.AddItem.class);
                startActivity(intent);
                return false;
            }
        });
    }


    private ArrayList<showList> readFromFile(Context context) {

        String ret = "";
        ArrayList<showList>  arrayList = new ArrayList<>();

        try {
            InputStream inputStream = context.openFileInput("accounts.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                String token[] = null;
                int i = 0;
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    token = receiveString.split(",");
//                    arrayList.add(new AccountInfo(token[0],token[1],token[3],token[4]));
//                    arrayList.get(i++).setPref(Integer.parseInt(token[2]));
                    stringBuilder.append("\n").append(receiveString);

                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return arrayList;

    }

}