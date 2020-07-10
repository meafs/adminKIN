package com.sust.adminkinblood.notification;

import android.util.Log;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                        "Content-Type:application/json",
                        "Authorization:key=AAAAq6L-cl4:APA91bG4HEveKsxOqW1lc_yWEE-sHazvmQKNAflcUvAaLzuBVZMcblhKcRLSMgFr4e9b0Rzkcq4KDAS4TVKG0ra1UTp7HrqD1v_UAao2fW1YIah0LBf8DTp07r31QYGvoRAfwUcSs7SA"
            }
            )
        @POST("fcm/send")
        Call<MyResponse> sendNotification(@Body NotificationSender body);
}
