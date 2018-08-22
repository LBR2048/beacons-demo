package br.udesc.ihcbeacons.interactor;

import android.content.Context;

import br.udesc.ihcbeacons.model.BeaconStatus;
import br.udesc.ihcbeacons.model.ScanStatus;
import br.udesc.ihcbeacons.presenter.Presenter;
import br.udesc.ihcbeacons.repository.Repository;
import br.udesc.ihcbeacons.repository.RepositoryImpl;
import br.udesc.ihcbeacons.scanner.Scanner;
import br.udesc.ihcbeacons.scanner.ScannerImpl;

/**
 * Created by unity on 10/04/17.
 */

public class InteractorImpl implements Interactor {

    private Scanner mScanner;
    private Repository mRepository;
    private Presenter mPresenter;

    public InteractorImpl(Presenter presenter) {
        mScanner = new ScannerImpl(this);
        mRepository = new RepositoryImpl();
        mPresenter = presenter;
    }

    public InteractorImpl() {
        mScanner = new ScannerImpl(this);
        mRepository = new RepositoryImpl();
    }

    @Override
    public void subscribeForBeaconStatusUpdates(Context context) {
        mScanner.subscribeForBeaconStatusUpdates(context);
        mRepository.addScanStatus(new ScanStatus(true, System.currentTimeMillis()));
    }

    @Override
    public void unsubscribeFromBeaconStatusUpdates() {
        mScanner.unsubscribeFromBeaconStatusUpdates();
        mRepository.addScanStatus(new ScanStatus(false, System.currentTimeMillis()));
    }

    @Override
    public void onBeaconStatusAdded(BeaconStatus beaconStatus) {
        mRepository.addBeaconStatus(beaconStatus);
//        mPresenter.onBeaconStatusAdded(beaconStatus);
    }
}
