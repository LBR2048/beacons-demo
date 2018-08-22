package br.udesc.ihcbeacons.scanner;

import android.content.Context;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;

import java.util.List;

import br.udesc.ihcbeacons.interactor.Interactor;
import br.udesc.ihcbeacons.model.BeaconStatus;
import br.udesc.ihcbeacons.utils.BeaconConstants;

/**
 * Created by unity on 10/04/17.
 */

public class ScannerImpl implements Scanner {

    private static final String CHECK_IN = "Check-in";
    private static final String CHECK_OUT = "Check-out";

    private BeaconManager mBeaconManager;
    private final Interactor mInteractor;

    public ScannerImpl(Interactor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void subscribeForBeaconStatusUpdates(final Context context) {
        // TODO verificar se bluetooth está ligado?
        // TODO verificar se a monitoração já está ocorrendo?

        mBeaconManager = new BeaconManager(context);

        mBeaconManager.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {

            @Override
            public void onEnteredRegion(BeaconRegion beaconRegion, List<Beacon> list) {
                long timestamp = System.currentTimeMillis();
                BeaconStatus beaconStatus;
                switch (beaconRegion.getIdentifier()) {
                    case BeaconConstants.ICE_REGION:
                        beaconStatus = new BeaconStatus(CHECK_IN, BeaconConstants.ICE_REGION, timestamp );
                        break;
                    case BeaconConstants.BLUEBERRY_REGION:
                        beaconStatus = new BeaconStatus(CHECK_IN, BeaconConstants.BLUEBERRY_REGION, timestamp );
                        break;
                    case BeaconConstants.MINT_REGION:
                        beaconStatus = new BeaconStatus(CHECK_IN, BeaconConstants.MINT_REGION, timestamp );
                        break;
                    default:
                        beaconStatus = new BeaconStatus(CHECK_IN, BeaconConstants.UNKNOWN_REGION, timestamp );
                        break;
                }
                mInteractor.onBeaconStatusAdded(beaconStatus);
            }

            @Override
            public void onExitedRegion(BeaconRegion beaconRegion) {
                long timestamp = System.currentTimeMillis();
                BeaconStatus beaconStatus;
                switch (beaconRegion.getIdentifier()) {
                    case BeaconConstants.ICE_REGION:
                        beaconStatus = new BeaconStatus(CHECK_OUT, BeaconConstants.ICE_REGION, timestamp);
                        break;
                    case BeaconConstants.BLUEBERRY_REGION:
                        beaconStatus = new BeaconStatus(CHECK_OUT, BeaconConstants.BLUEBERRY_REGION, timestamp);
                        break;
                    case BeaconConstants.MINT_REGION:
                        beaconStatus = new BeaconStatus(CHECK_OUT, BeaconConstants.MINT_REGION, timestamp);
                        break;
                    default:
                        beaconStatus = new BeaconStatus(CHECK_OUT, BeaconConstants.UNKNOWN_REGION, timestamp);
                }
                mInteractor.onBeaconStatusAdded(beaconStatus);
            }
        });

        mBeaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                mBeaconManager.startMonitoring(BeaconConstants.iceBeaconRegion);
                mBeaconManager.startMonitoring(BeaconConstants.blueberryBeaconRegion);
                mBeaconManager.startMonitoring(BeaconConstants.mintBeaconRegion);
            }
        });
    }

    @Override
    public void unsubscribeFromBeaconStatusUpdates() {
        if (mBeaconManager != null) {
            mBeaconManager.disconnect();
        }
    }
}
