package com.example.easymarket;


import com.example.easymarket.Notifications.MyResponse;
import com.example.easymarket.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAszuCDmA:APA91bG6Jr9ma-B4DI-2oKCU2hJCXHNfidNuAPgaEdLwUxf9Zuvh2fO-Anf8FaKgbN-Z4HzkdJc_g_7Jvgj0XljiNma8ndx_e-wEhB9k0qcsjmyb0WkVsnWSTbaf0kwTD3Ls96PMkDHB"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
