package com.android.zyys.retrofit2demo.model;

import com.android.zyys.retrofit2demo.bean.PhoneResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by pangrongxian on 16/3/26.
 *
 * http://apis.baidu.com/apistore/mobilenumber/mobilenumber
 *
 *                      /apistore/mobilenumber/mobilenumber
 */
public interface PhoneService {//"/apistore/mobilenumber/mobilenumber"
    @GET("/apistore/mobilenumber/mobilenumber")
    Call<PhoneResult> getResult(@Header("apikey") String apikey, @Query("phone") String phone);
}
