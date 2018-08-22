package br.udesc.ihcbeacons.repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by unity on 12/04/17.
 */

public class FirebaseHelper {

    // Firebase database
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    // TODO generalizar para todos os usuários
    private static final String BEACONS_PATH = "user-beacons";
    // TODO generalizar para todos os usuários
    private static final String SCAN_PATH = "user-scans";

    private static class SingletonHolder {
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private FirebaseHelper(){
        if (database == null) {
            database = FirebaseDatabase.getInstance();
//            database.setPersistenceEnabled(true);
            databaseRef = database.getReference();
        }
    }


    // Realtime database

    public DatabaseReference getBeaconsReference(){
        return databaseRef.child(BEACONS_PATH).child("user01");
    }

    public DatabaseReference getScansReference(){
        return databaseRef.child(SCAN_PATH).child("user01");
    }


    // FCM

    public void subscribeToFcm() {
        FirebaseMessaging.getInstance().subscribeToTopic("fcm");
    }

}
