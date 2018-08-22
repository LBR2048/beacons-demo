package br.udesc.ihcbeacons.scanner;

import android.content.Context;

/**
 * Created by unity on 10/04/17.
 */

public interface Scanner {
    void subscribeForBeaconStatusUpdates(Context context);
    void unsubscribeFromBeaconStatusUpdates();
}
