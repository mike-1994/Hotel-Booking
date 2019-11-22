package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spLocation, spRoomType;
    private EditText etCountRoom;
    private TextView tvCheckInDateValue, tvCheckOutDateValue;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindControls();
        fillValues();
    }

    private void bindControls() {
        this.spLocation = findViewById(R.id.spLocation);
        this.spRoomType = findViewById(R.id.spRoomType);
        this.tvCheckInDateValue = findViewById(R.id.tvCheckInDateValue);
        this.tvCheckOutDateValue = findViewById(R.id.tvCheckOutDateValue);
        this.etCountRoom = findViewById(R.id.etCountRoom);
        this.btnCalculate = findViewById(R.id.btnCalculate);

        this.tvCheckInDateValue.setOnClickListener(this);
        this.tvCheckOutDateValue.setOnClickListener(this);
        this.btnCalculate.setOnClickListener(this);
    }

    private void fillValues() {
        ArrayAdapter locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, BookingInformation.LOCATION_MAP);
        this.spLocation.setAdapter(locationAdapter);

        ArrayAdapter roomAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(BookingInformation.ROOM_MAP.keySet()));
        this.spRoomType.setAdapter(roomAdapter);
    }

    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        switch (v.getId()) {
            case R.id.tvCheckInDateValue:
                DatePickerDialog pickCheckIn = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvCheckInDateValue.setText(month + "/" + dayOfMonth + "/" + year);
                    }
                }, year, month, day);
                pickCheckIn.show();
                break;
            case R.id.tvCheckOutDateValue:
                DatePickerDialog pickCheckOut = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvCheckOutDateValue.setText(month + "/" + dayOfMonth + "/" + year);
                    }
                }, year, month, day);
                pickCheckOut.show();
                break;
            case R.id.btnCalculate:
                Intent intent = new Intent(MainActivity.this, BillActivity.class);
                intent.putExtra(BookingInformation.LOCATION, spLocation.getSelectedItem().toString());
                intent.putExtra(BookingInformation.ROOM_TYPE, spRoomType.getSelectedItem().toString());
                intent.putExtra(BookingInformation.CHECK_IN_DATE, tvCheckInDateValue.getText().toString());
                intent.putExtra(BookingInformation.CHECK_OUT_DATE, tvCheckOutDateValue.getText().toString());
                intent.putExtra(BookingInformation.NO_OF_ROOMS, etCountRoom.getText().toString());
                startActivity(intent);
                break;
        }
    }
}
