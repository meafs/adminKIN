package com.sust.adminkinblood.notification;

import android.app.NotificationManager;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sust.adminkinblood.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private String bloodGroup, hospital, condition, noOfBags;



    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        if (firebaseUser != null) {
            updateToken(refreshToken);
        }
    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        bloodGroup = remoteMessage.getData().get("bloodGroup");
        hospital = remoteMessage.getData().get("hospital");
        condition = remoteMessage.getData().get("condition");
        noOfBags = remoteMessage.getData().get("noOfBags");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), App.EVENT_ID)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("New blood request arrived!")
                .setContentText("Blood Group: " + bloodGroup + " " + noOfBags + "Bags Condition: " + condition + " " + hospital)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        assert manager != null;
        manager.notify(0, builder.build());
    }

    private void updateToken(String refreshToken) {
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        Token token = new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference().child("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
    }

}
