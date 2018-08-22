package br.udesc.ihcbeacons.presenter;

import android.content.Context;

import br.udesc.ihcbeacons.interactor.Interactor;
import br.udesc.ihcbeacons.interactor.InteractorImpl;
import br.udesc.ihcbeacons.model.BeaconStatus;
import br.udesc.ihcbeacons.view.BeaconsAdapterView;

/**
 * Created by unity on 10/04/17.
 */

public class PresenterImpl implements Presenter {

    private Interactor interactor;
    private BeaconsAdapterView beaconsAdapterView;

    public PresenterImpl(BeaconsAdapterView beaconsAdapterView ) {
        interactor = new InteractorImpl(this);
        this.beaconsAdapterView = beaconsAdapterView;
    }

    @Override
    public void subscribeForBeaconStatusUpdates(Context context) {
        interactor.subscribeForBeaconStatusUpdates(context);
    }

    @Override
    public void unsubscribeFromBeaconStatusUpdates() {
        interactor.unsubscribeFromBeaconStatusUpdates();
    }

    @Override
    public void onBeaconStatusAdded(BeaconStatus beaconStatus) {
        beaconsAdapterView.onBeaconStatusAdded(beaconStatus);
    }
}
