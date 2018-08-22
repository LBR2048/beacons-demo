package br.udesc.ihcbeacons.repository;

import com.google.firebase.database.DatabaseReference;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import br.udesc.ihcbeacons.Utils;
import br.udesc.ihcbeacons.model.BeaconStatus;
import br.udesc.ihcbeacons.model.ScanStatus;

/**
 * Created by unity on 06/06/17.
 */

public class RepositoryImpl implements Repository {

    // Firebase
    private FirebaseHelper mFirebaseHelperInstance = FirebaseHelper.getInstance();
    private DatabaseReference mBeaconsReference = mFirebaseHelperInstance.getBeaconsReference();
    private DatabaseReference mScansReference = mFirebaseHelperInstance.getScansReference();

    @Override
    public void addBeaconStatus(BeaconStatus beaconStatus) {
        DatabaseReference key = mBeaconsReference.push();
        key.setValue(beaconStatus);

        addHumanFormattedDateToKey(key, beaconStatus.getTimestamp());
    }

    @Override
    public void addScanStatus(ScanStatus scanStatus) {
        DatabaseReference key = mScansReference.push();
        key.setValue(scanStatus);

        addHumanFormattedDateToKey(key, scanStatus.getTimestamp());
    }

    private static void addHumanFormattedDateToKey(DatabaseReference key, long timestamp) {
        String time = Utils.timestampToDate(new Timestamp(timestamp));
        Map timeData = new HashMap();
        timeData.put("time", time);
        key.updateChildren(timeData);
    }
}