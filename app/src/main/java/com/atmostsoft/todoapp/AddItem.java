package com.atmostsoft.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;

public class AddItem extends AppCompatActivity {

    ArrayList<String> list;
    private TextView tvCalender;
    private TextView tvTime;

    CheckBox hBox,MBox,LBox;
    EditText etItem;
    Button btnSave;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ImageView ivEmpty;
    TextView tvEmpty;
    Button btnAdd;
//    private DatePickerDialog datePickerDialog;

    private TextView tvClose;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        init();
    }

    private void init() {
        list = new ArrayList<>();
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hBox = findViewById(R.id.hightPr);
                MBox = findViewById(R.id.midPre);
                LBox = findViewById(R.id.lowPr);
                String line = etItem.getText().toString()+",";
                if (hBox.isChecked())
                    line += "H";
                else if (MBox.isChecked())
                    line += "M";
                else
                    line += "L";
                line += ","+tvCalender.getText().toString()+","+tvTime.getText().toString();
                for (int i = 0;i<list.size();i++)
                {
                    line += ","+list.get(i);
                }

                writeToFile("itemList.txt",line,getApplicationContext());
            }
        });

        etItem = findViewById(R.id.etItem);
        btnAdd = findViewById(R.id.btnAddItem);
        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new itemAdaptor(this,list);
        recyclerView.setAdapter(adapter);
        ivEmpty = findViewById(R.id.ivEmpty);
        tvEmpty = findViewById(R.id.tvEmpty);

        recyclerView.setVisibility(View.INVISIBLE);

        tvClose = findViewById(R.id.tvClose);

        tvTime =findViewById(R.id.tvTime);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.VISIBLE);
                ivEmpty.setVisibility(View.INVISIBLE);
                tvEmpty.setVisibility(View.INVISIBLE);
                if (etItem.getText().toString()!="add Item" || !etItem.getText().toString().isEmpty())
                {
                    list.add(etItem.getText().toString());
                    adapter.notifyDataSetChanged();
                    etItem.setText("add Item");
                }
            }
        });

        int hour = 0,min = 0;

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay+":"+minute;
                tvTime.setText(time);
            }
        };
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(com.atmostsoft.todoapp.AddItem.this,
                        timeSetListener,0,0,true);
                timePickerDialog.show();
            }
        });
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.atmostsoft.todoapp.AddItem.this,com.atmostsoft.todoapp.MainActivity.class);
                startActivity(intent);
            }
        });
        tvCalender = findViewById(R.id.tvCalender);
        tvCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);



                 DatePickerDialog datePickerDialog = new DatePickerDialog(com.atmostsoft.todoapp.AddItem.this,

                        dateSetListener,
                        year,month,day);
                 datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                 datePickerDialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String date = dayOfMonth+"/"+(month+1)+"/"+year;
                tvCalender.setText(date);
            }
        };


    }


    private void writeToFile(String fileName,String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}