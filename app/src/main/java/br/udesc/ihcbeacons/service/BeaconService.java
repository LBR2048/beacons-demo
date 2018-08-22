package br.udesc.ihcbeacons.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import br.udesc.ihcbeacons.R;
import br.udesc.ihcbeacons.interactor.Interactor;
import br.udesc.ihcbeacons.interactor.InteractorImpl;
import br.udesc.ihcbeacons.view.MainActivity;

/**
 * Created by unity on 29/07/17.
 */

public class BeaconService extends Service {

    public static final String LOG_TAG = "Beacon Service";
    public static final int BEACONS_NOTIFICATION_ID = 2017;
    public static final String INTENT_EXTRA_DURATION = "Duration";
    public static final String MAIN_ACTION = "MainAction";
    public static final String START_SCAN_ACTION = "SubscribeAction";
    public static final String STOP_SCAN_ACTION = "UnsubscribeAction";
    public static final String MAIN_TITLE = "Bem vindo ao IHC 2017";
    public static final String STARTED_TITLE = "Buscando beacons";
    public static final String STOPPED_TITLE = "Parado";
    public static final String START_LABEL = "Buscar beacons";
    public static final String STOP_LABEL = "Parar";
    public static final String TICKER_TEXT = "Ticker text";
    private Interactor mInteractor;
    private PendingIntent pStartIntent;
    private PendingIntent pStopIntent;
    private PendingIntent pMainIntent;
    private NotificationManager mNotificationManager;
    private boolean mStarted;

    @Override
    public void onCreate() {
        super.onCreate();

        mNotificationManager = (NotificationManager) getApplicationContext()
                .getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        switch (intent.getAction()) {
            case MAIN_ACTION:
                startServiceOnForeground();
                break;

            case START_SCAN_ACTION:
                if (intent.hasExtra(INTENT_EXTRA_DURATION)) {
                    long duration = intent.getExtras().getLong(INTENT_EXTRA_DURATION);
                    startScan(duration);
                } else {
                    startScan();
                }
                break;

            case STOP_SCAN_ACTION:
                stopScan();
                break;
        }

        return START_STICKY;
    }

    private void startServiceOnForeground() {
        if (!mStarted) {
            // Notification action setup
            Intent mainIntent = new Intent(this, MainActivity.class);
            pMainIntent = PendingIntent.getActivity(this, 0, mainIntent, 0);

            // Start scan button action setup
            Intent startIntent = new Intent(this, BeaconService.class);
            startIntent.setAction(START_SCAN_ACTION);
            pStartIntent = PendingIntent.getService(this, 0, startIntent, 0);

            // Stop scan button action setup
            Intent stopIntent = new Intent(this, BeaconService.class);
            stopIntent.setAction(STOP_SCAN_ACTION);
            pStopIntent = PendingIntent.getService(this, 0, stopIntent, 0);

            Notification notification = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_tap_and_play_white_24dp)
                    .setContentTitle(MAIN_TITLE)
                    .setTicker(TICKER_TEXT)
                    .addAction(R.drawable.ic_play_arrow_black_24dp, START_LABEL, pStartIntent)
                    .setContentIntent(pMainIntent)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .build();

            startForeground(BEACONS_NOTIFICATION_ID, notification);

            mStarted = true;
        }

        if (mInteractor == null) {
            mInteractor = new InteractorImpl();
        }
    }

    private void startScan() {
        startServiceOnForeground();

        if (mInteractor != null) {
            mInteractor.subscribeForBeaconStatusUpdates(this);

            Notification startedNotification = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_tap_and_play_white_24dp)
                    .setContentTitle(STARTED_TITLE)
                    .setTicker(TICKER_TEXT)
                    .addAction(R.drawable.ic_stop_black_24dp, STOP_LABEL, pStopIntent)
                    .setContentIntent(pMainIntent)
                    .build();

            mNotificationManager.notify(BEACONS_NOTIFICATION_ID, startedNotification);
        }
    }

    private void startScan(long millis) {
        startServiceOnForeground();

        if (mInteractor != null) {
            mInteractor.subscribeForBeaconStatusUpdates(this);

            Notification startedNotification = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_tap_and_play_white_24dp)
                    .setContentTitle(STARTED_TITLE)
                    .setTicker(TICKER_TEXT)
                    .addAction(R.drawable.ic_stop_black_24dp, STOP_LABEL, pStopIntent)
                    .setContentIntent(pMainIntent)
                    .build();

            mNotificationManager.notify(BEACONS_NOTIFICATION_ID, startedNotification);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    stopScan();
//                    stopForeground(true);
                    stopSelf();
                }
            }, millis);
        }
    }

    private void stopScan() {
        if (mInteractor != null) {
            mInteractor.unsubscribeFromBeaconStatusUpdates();

            Notification stoppedNotification = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_tap_and_play_white_24dp)
                    .setContentTitle(STOPPED_TITLE)
                    .setTicker(TICKER_TEXT)
                    .addAction(R.drawable.ic_play_arrow_black_24dp, START_LABEL, pStartIntent)
                    .setContentIntent(pMainIntent)
                    .build();

            mNotificationManager.notify(BEACONS_NOTIFICATION_ID, stoppedNotification);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
