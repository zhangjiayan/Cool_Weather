package com.example.dell.cool_weather.util;

import android.text.TextUtils;

import com.example.dell.cool_weather.db.City;
import com.example.dell.cool_weather.db.County;
import com.example.dell.cool_weather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DELL on 2018/1/2.
 */

public class Utility {
    public static  boolean handleProvinResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allprovinces = new JSONArray(response);
                for (int i = 0; i < allprovinces.length(); i++){
                    JSONObject provinceObject = allprovinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

    return false;
}

public static boolean hanleCityResponse(String response, int provinceId) {
    if (!TextUtils.isEmpty(response)) {
        try {
            JSONArray allcities = new JSONArray(response);
            for (int i = 0; i < allcities.length(); i++) {
                JSONObject cityObject = allcities.getJSONObject(i);
                City city = new City();
                city.setCityName(cityObject.getString("name"));
                city.setProvinceId(provinceId);
                city.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    return false;
}

    public static boolean hanleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setCountyName(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

                }
