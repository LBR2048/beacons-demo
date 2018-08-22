package br.udesc.ihcbeacons.repository;

import br.udesc.ihcbeacons.model.BeaconStatus;
import br.udesc.ihcbeacons.model.ScanStatus;

/**
 * Created by unity on 06/06/17.
 */

public interface Repository {
    void addBeaconStatus(BeaconStatus beaconStatus);
    void addScanStatus(ScanStatus scanStatus);
}
