package com.sust.adminkinblood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.sust.adminkinblood.notification.APIService;
import com.sust.adminkinblood.notification.Client;
import com.sust.adminkinblood.notification.Data;
import com.sust.adminkinblood.notification.MyResponse;
import com.sust.adminkinblood.notification.NotificationSender;
import com.sust.adminkinblood.notification.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notification extends AppCompatActivity {

    private EditText title, message;
    private APIService apiService;
    private String title_, message_;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        title = findViewById(R.id.et_title);
        message = findViewById(R.id.et_message);
        Button send = findViewById(R.id.btn_send);

        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title_ = title.getText().toString().trim();
                message_ = message.getText().toString().trim();
                FirebaseDatabase.getInstance().getReference().child("Tokens").child("ONEJp9mzAIdaetfvK7C3d4tcRGH2").child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String usertoken=dataSnapshot.getValue(String.class);
                        sendNotifications(usertoken, title_, message_);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        updateToken();
    }

    private void updateToken() {
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String refreshToken= FirebaseInstanceId.getInstance().getToken();
        Token token= new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
    }


    private void sendNotifications(String token, String title, String message) {
        Data data = new Data(title, message);
        NotificationSender sender = new NotificationSender(data, token);
        apiService.sendNotification(sender).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (response.code() == 200){
                    if (response.body().success != 1){
                        Toast.makeText(Notification.this, "Failed to send notification", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {

            }
        });
    }
}
