package br.udesc.ihcbeacons.service;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by unity on 03/09/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String START = "start";
    private static final String STOP = "stop";
    private static final String MAIN = "main";
    private static String LOG_TAG = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(LOG_TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(LOG_TAG, "Message data payload: " + remoteMessage.getData());
            String action = remoteMessage.getData().get("action");
            long duration = Long.valueOf(remoteMessage.getData().get("duration"));
            switch (action) {
                case MAIN:
                    startMainBeaconService();
                    break;

                case START:
//                    startBeaconService();
                    startBeaconService(duration);
                    break;

                case STOP:
                    stopBeaconService();
                    break;
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(LOG_TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

    }

    public void startMainBeaconService() {
        Intent intent = new Intent(getBaseContext(), BeaconService.class);
        intent.setAction(BeaconService.MAIN_ACTION);
        getBaseContext().startService(intent);
    }

    public void startBeaconService() {
        Intent intent = new Intent(getBaseContext(), BeaconService.class);
        intent.setAction(BeaconService.START_SCAN_ACTION);
        getBaseContext().startService(intent);
    }

    public void startBeaconService(long duration) {
        Intent intent = new Intent(getBaseContext(), BeaconService.class);
        intent.setAction(BeaconService.START_SCAN_ACTION);
        intent.putExtra(BeaconService.INTENT_EXTRA_DURATION, duration);
        getBaseContext().startService(intent);
    }

    public void stopBeaconService() {
        Intent intent = new Intent(getBaseContext(), BeaconService.class);
        intent.setAction(BeaconService.STOP_SCAN_ACTION);
        getBaseContext().startService(intent);
    }
}
