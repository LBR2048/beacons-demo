package br.udesc.ihcbeacons.presenter;

import android.content.Context;

import br.udesc.ihcbeacons.model.BeaconStatus;

/**
 * Created by unity on 10/04/17.
 */

public interface Presenter {
    void subscribeForBeaconStatusUpdates(Context context);
    void unsubscribeFromBeaconStatusUpdates();
    void onBeaconStatusAdded(BeaconStatus beaconStatus);
}
