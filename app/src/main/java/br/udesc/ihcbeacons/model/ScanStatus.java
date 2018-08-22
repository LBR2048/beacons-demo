package br.udesc.ihcbeacons.model;

/**
 * Created by unity on 03/09/17.
 */

public class ScanStatus {
    private boolean scanning;
    private long timestamp;

    public ScanStatus(boolean scanning, long timestamp) {
        this.scanning = scanning;
        this.timestamp = timestamp;
    }

    public boolean isScanning() {
        return scanning;
    }

    public void setScanning(boolean scanning) {
        this.scanning = scanning;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
