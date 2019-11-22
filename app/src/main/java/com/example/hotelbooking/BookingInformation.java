package com.example.hotelbooking;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BookingInformation {

    public static final String LOCATION = "location";
    public static final String ROOM_TYPE = "roomType";
    public static final String CHECK_IN_DATE = "checkInDate";
    public static final String CHECK_OUT_DATE = "checkOutDate";
    public static final String NO_OF_ROOMS = "noOfRooms";

    public static Map<String, Float> ROOM_MAP = new HashMap<String, Float>() {
        {
            put("Deluxe", 2000.0f);
            put("AC", 3000.0f);
            put("Platinum", 4000.0f);
        }
    };

    public static String[] LOCATION_MAP = new String[] {
            "Kathmandu",
            "Bhaktapur",
            "Chitwan"
    };

    public static long dateDiff(Date date1, Date date2) {
        long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
