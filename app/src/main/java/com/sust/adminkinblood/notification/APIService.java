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
                        "Authorization:key=AAAApNEND5U:APA91bGAmpb-wAVAa-zU0IGEnGVMfoK0DFv0skqGGj25jCiAHHtJh1DiZ8WGomX7vK2jIfJKBxmlY_Yp6m9-BMNYK9qUbnSZVf9zC49HaP4ohQSY9YUiyP2MD7Yv25teTPDXW-gEcOlI"
            }
            )
        @POST("fcm/send")
        Call<MyResponse> sendNotification(@Body NotificationSender body);
}
