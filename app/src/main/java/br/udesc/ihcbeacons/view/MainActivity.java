package br.udesc.ihcbeacons.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.Region;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.udesc.ihcbeacons.R;
import br.udesc.ihcbeacons.model.BeaconStatus;
import br.udesc.ihcbeacons.repository.FirebaseHelper;
import br.udesc.ihcbeacons.utils.BeaconConstants;

public class MainActivity extends AppCompatActivity implements BeaconsFragment.OnBeaconsFragmentInteractionListener {

    private static final Map<String, List<String>> PLACES_BY_BEACONS;

    static {
        Map<String, List<String>> placesByBeacons = new HashMap<>();
        // Shops close to blueberry beacon
        placesByBeacons.put(BeaconConstants.BLUEBERRY_MAJOR_MINOR, new ArrayList<String>() {{
            add("Heavenly Sandwiches");
            // read as: "Heavenly Sandwiches" is closest
            // to the beacon with major 22504 and minor 48827
            add("Green & Green Salads");
            // "Green & Green Salads" is the next closest
            add("Mini Panini");
            // "Mini Panini" is the furthest away
        }});

        // Shops close to mint beacon
        placesByBeacons.put(BeaconConstants.MINT_MAJOR_MINOR, new ArrayList<String>() {{
            add("Mini Panini");
            add("Green & Green Salads");
            add("Heavenly Sandwiches");
        }});
        PLACES_BY_BEACONS = Collections.unmodifiableMap(placesByBeacons);
    }

    private List<String> placesNearBeacon(Beacon beacon) {
        String beaconKey = String.format("%d:%d", beacon.getMajor(), beacon.getMinor());
        if (PLACES_BY_BEACONS.containsKey(beaconKey)) {
            return PLACES_BY_BEACONS.get(beaconKey);
        }
        return Collections.emptyList();
    }

    private BeaconManager allBeaconsManager;
    private Region region;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showBeaconsFragment();

        FirebaseHelper.getInstance().subscribeToFcm();

//        allBeaconsManager = new BeaconManager(this);
//        allBeaconsManager.setRangingListener(new BeaconManager.RangingListener() {
//            @Override
//            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
//                if (!list.isEmpty()) {
//                    Beacon nearestBeacon = list.get(0);
//                    List<String> places = placesNearBeacon(nearestBeacon);
//                    // TODO: update the UI here
//                    Log.d("Airport", "Nearest places: " + places);
//                }
//            }
//        });
//        region = new Region("ranged region", ICE_UUID.fromString(BeaconConstants.ICE_UUID), null, null);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);

//        allBeaconsManager.connect(new BeaconManager.ServiceReadyCallback() {
//            @Override
//            public void onServiceReady() {
//                allBeaconsManager.startRanging(region);
//            }
//        });
    }

    @Override
    protected void onPause() {
//        allBeaconsManager.stopRanging(region);

        super.onPause();
    }

    @Override
    public void onBeaconsFragmentInteraction(BeaconStatus item) {

    }

    private void showBeaconsFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_main, new BeaconsFragment())
                .commit();
    }
}
