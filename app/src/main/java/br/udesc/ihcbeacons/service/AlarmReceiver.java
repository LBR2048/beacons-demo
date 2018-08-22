package br.udesc.ihcbeacons.service;

import static com.estimote.coresdk.common.config.EstimoteSDK.getApplicationContext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by unity on 17/08/17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "com.codepath.example.servicesdemo.alarm";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm received", Toast.LENGTH_SHORT).show();
        Log.v(getClass().getSimpleName(), "Alarm received");

    }

    public void startService() {
        Intent intent = new Intent(getApplicationContext(), BeaconService.class);
        getApplicationContext().startService(intent);
    }
}
