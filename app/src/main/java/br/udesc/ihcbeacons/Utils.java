package br.udesc.ihcbeacons;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by unity on 30/07/17.
 */

public class Utils {
    public static String timestampToDate(Timestamp timestamp) {
        Date date = new Date(timestamp.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
        return sdf.format(date);
    }
}
