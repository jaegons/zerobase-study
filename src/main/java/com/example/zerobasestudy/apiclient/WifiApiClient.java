package com.example.zerobasestudy.apiclient;

import com.example.zerobasestudy.apiclient.dto.WifiApiResponse;
import com.example.zerobasestudy.apiclient.dto.WifiInfo;
import com.example.zerobasestudy.gsonutils.LocalDateTimeDeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.time.LocalDateTime;
import java.util.List;

public class WifiApiClient {
    private static final String apiUrl = "http://openapi.seoul.go.kr:8088/7166466479696d6a32306259587a54/json/TbPublicWifiInfo";

    public int getTotalCount() {
        StringBuffer urlBuilder = new StringBuffer(apiUrl)
                .append("/").append(1).append("/").append(1);

        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(urlBuilder.toString()).get().build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String result = response.body().string();

                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeSerializer());
                Gson gson = gsonBuilder.setPrettyPrinting().create();

                WifiApiResponse wifiApiResponse = gson.fromJson(result, WifiApiResponse.class);

                return wifiApiResponse.getTbPublicWifiInfo().getList_total_count();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    public List<WifiInfo> requestData(int start, int end) {
        StringBuffer urlBuilder = new StringBuffer(apiUrl)
                .append("/").append(start).append("/").append(end);

        List<WifiInfo> wifiInfos = null;
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(urlBuilder.toString()).get().build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String result = response.body().string();
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeSerializer());
                Gson gson = gsonBuilder.setPrettyPrinting().create();
                WifiApiResponse wifiApiResponse = gson.fromJson(result, WifiApiResponse.class);
                wifiInfos = wifiApiResponse.getTbPublicWifiInfo().row;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return wifiInfos;
    }


}
