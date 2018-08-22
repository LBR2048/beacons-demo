package br.udesc.ihcbeacons.utils;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;

import java.util.UUID;

/**
 * Created by unity on 15/03/17.
 */

public class BeaconConstants {

    public static final String ICE_REGION = "ice";
    private static final String ICE_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    private static final int ICE_MAJOR = 20331;
    private static final int ICE_MINOR = 40472;

    public static final String BLUEBERRY_REGION = "blueberry";
    private static final String BLUEBERRY_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    private static final int BLUEBERRY_MAJOR = 14002;
    private static final int BLUEBERRY_MINOR = 51049;
    public static final String BLUEBERRY_MAJOR_MINOR = String.format("%d:%d", BLUEBERRY_MAJOR, BLUEBERRY_MINOR);

    public static final String MINT_REGION = "mint";
    private static final String MINT_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    private static final int MINT_MAJOR = 10292;
    private static final int MINT_MINOR = 40965;
    public static final String MINT_MAJOR_MINOR = String.format("%d:%d", MINT_MAJOR, MINT_MINOR);

    public static final String UNKNOWN_REGION = "unknown";

    public static final BeaconRegion iceBeaconRegion =
            new BeaconRegion(
                    BeaconConstants.ICE_REGION,
                    UUID.fromString(BeaconConstants.ICE_UUID),
                    BeaconConstants.ICE_MAJOR, BeaconConstants.ICE_MINOR
            );

    public static final BeaconRegion blueberryBeaconRegion =
            new BeaconRegion(
                    BeaconConstants.BLUEBERRY_REGION,
                    UUID.fromString(BeaconConstants.BLUEBERRY_UUID),
                    BeaconConstants.BLUEBERRY_MAJOR, BeaconConstants.BLUEBERRY_MINOR
            );

    public static final BeaconRegion mintBeaconRegion =
            new BeaconRegion(
                    BeaconConstants.MINT_REGION,
                    UUID.fromString(BeaconConstants.MINT_UUID),
                    BeaconConstants.MINT_MAJOR, BeaconConstants.MINT_MINOR
            );
}
