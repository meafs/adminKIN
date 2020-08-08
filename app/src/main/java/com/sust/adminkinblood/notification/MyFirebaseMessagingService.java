package com.sust.adminkinblood.notification;

import android.app.NotificationManager;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sust.adminkinblood.NotificationHelper;
import com.sust.adminkinblood.R;

import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private String bloodGroup, hospital, condition, noOfBags, uid, fullName, phoneNumber;



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
        uid = remoteMessage.getData().get("uid");
        fullName = remoteMessage.getData().get("fullName");
        phoneNumber = remoteMessage.getData().get("phoneNumber");

        Random random = new Random();

        NotificationHelper notificationHelper = new NotificationHelper(bloodGroup, hospital, condition, noOfBags, uid, fullName, phoneNumber);
        FirebaseFirestore.getInstance()
                .collection("Requests")
                .document(uid+random.nextInt(100000))
                .set(notificationHelper)
                .addOnSuccessListener(aVoid -> {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), App.EVENT_ID)
                            .setSmallIcon(R.drawable.ic_app_logo_24dp)
                            .setContentTitle("New blood request arrived!")
                            .setContentText("Blood Group: " + bloodGroup + " Hospital: " + hospital)
                            .setPriority(NotificationCompat.PRIORITY_MAX);

                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    assert manager != null;
                    manager.notify(random.nextInt(100000), builder.build());
                }).addOnFailureListener(e -> Toast.makeText(MyFirebaseMessagingService.this, e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void updateToken(String refreshToken) {
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        Token token = new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference().child("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
    }

}
