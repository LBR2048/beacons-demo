package br.udesc.ihcbeacons.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by unity on 10/04/17.
 */

public class BeaconStatus {
    private String type;
    private String region;
    private long timestamp;

    public BeaconStatus(String type, String region, long timestamp) {
        this.type = type;
        this.region = region;
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static List<BeaconStatus> getDummyBeaconStatuses() {
        List<BeaconStatus> beaconStatuses = new ArrayList<>();
//        beaconStatuses.add(new BeaconStatus("Check-in", "Ice beacon"));
//        beaconStatuses.add(new BeaconStatus("Check-out", "Ice beacon"));
//        beaconStatuses.add(new BeaconStatus("Check-in", "Mint beacon"));
//        beaconStatuses.add(new BeaconStatus("Check-out", "Mint beacon"));
        return beaconStatuses;
    }

}
