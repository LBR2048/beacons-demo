package br.udesc.ihcbeacons;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.estimote.coresdk.service.BeaconManager;

import br.udesc.ihcbeacons.service.AlarmReceiver;


public class BeaconsApplication extends Application {

    private BeaconManager iceBeaconManager;

    @Override
    public void onCreate() {
        super.onCreate();

//        scheduleAlarm();
//        cancelAlarm();

//        iceBeaconManager = new BeaconManager(getApplicationContext());
//        iceBeaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
//            @Override
//            public void onEnteredRegion(Region region, List<Beacon> list) {
//                BeaconUtils.showNotification(BeaconsApplication.this, "Check-in", "Ice beacon");
//            }
//            @Override
//            public void onExitedRegion(Region region) {
//                BeaconUtils.showNotification(BeaconsApplication.this, "Check-out", "Ice beacon");
//            }
//        });
//        iceBeaconManager.connect(new BeaconManager.ServiceReadyCallback() {
//            @Override
//            public void onServiceReady() {
//                iceBeaconManager.startMonitoring(new Region(BeaconConstants.ICE_REGION,
//                        java.util.ICE_UUID.fromString(BeaconConstants.ICE_UUID), BeaconConstants.ICE_MAJOR, BeaconConstants.ICE_MINOR));
//            }
//        });
    }

    // Setup a recurring alarm every half hour
    public void scheduleAlarm() {
        Log.v(getClass().getSimpleName(), "Scheduling alarm");

        // Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);

        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, AlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Setup periodic alarm every every half hour from this point onwards
        long firstMillis = System.currentTimeMillis(); // alarm is set right away
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        // First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
        // Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, pIntent);
    }

    public void cancelAlarm() {
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, AlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pIntent);
    }

}
