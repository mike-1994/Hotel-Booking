package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillActivity extends AppCompatActivity {

    TextView tvLocation, tvRoomType, tvCheckInDate, tvCheckOutDate, tvNoOfRoom, tvServiceCharge, tvVat, tvTotal, tvRoomCharge;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        bindControls();
        try {
            calculate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void bindControls() {
        this.tvLocation = findViewById(R.id.tvLocation);
        this.tvRoomType = findViewById(R.id.tvRoomType);
        this.tvCheckInDate = findViewById(R.id.tvCheckInDate);
        this.tvCheckOutDate = findViewById(R.id.tvCheckOutDate);
        this.tvNoOfRoom = findViewById(R.id.tvNoOfRoom);
        this.tvRoomCharge = findViewById(R.id.tvRoomCharge);
        this.tvServiceCharge = findViewById(R.id.tvServiceCharge);
        this.tvVat = findViewById(R.id.tvVat);
        this.tvTotal = findViewById(R.id.tvTotal);
    }

    private void calculate() throws ParseException {
        Bundle bundle = getIntent().getExtras();

        this.tvLocation.setText(getString(R.string.location) + bundle.getString(BookingInformation.LOCATION));
        this.tvRoomType.setText(getString(R.string.roomType) + bundle.getString(BookingInformation.ROOM_TYPE));
        this.tvCheckInDate.setText(getString(R.string.checkInDate) + bundle.getString(BookingInformation.CHECK_IN_DATE));
        this.tvCheckOutDate.setText(getString(R.string.checkOutDate) + bundle.getString(BookingInformation.CHECK_OUT_DATE));
        this.tvNoOfRoom.setText(getString(R.string.noOfRooms) + bundle.getString(BookingInformation.NO_OF_ROOMS));

        float total = 0f;
        int countRoom = Integer.parseInt(bundle.getString(BookingInformation.NO_OF_ROOMS));
        float roomCharge = countRoom * BookingInformation.ROOM_MAP.get(bundle.getString(BookingInformation.ROOM_TYPE));
        total += roomCharge;
        Date inDate = simpleDateFormat.parse(bundle.getString(BookingInformation.CHECK_IN_DATE));
        Date outDate = simpleDateFormat.parse(bundle.getString(BookingInformation.CHECK_OUT_DATE));
        long dateDiff = BookingInformation.dateDiff(inDate, outDate);
        float daysCharge = total * (dateDiff > 1 ? dateDiff : 1);
        total = daysCharge;
        float serviceCharge = (10 / 100f) * total;
        total += serviceCharge;
        float vat = (13 / 100f) * total;
        total += vat;

        this.tvRoomCharge.setText(getString(R.string.roomCharge) + roomCharge);
        this.tvServiceCharge.setText(getString(R.string.serviceTax) + serviceCharge);
        this.tvVat.setText(getString(R.string.vat) + vat);
        this.tvTotal.setText(getString(R.string.total) + total);
    }
}
