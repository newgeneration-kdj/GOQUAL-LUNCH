package com.goqual.goquallunch.Handler;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by ladmusician on 15. 7. 14..
 */
public class JsonHandler<T> {
    private static final String TAG = "HANDLER_JSON";
    private ArrayList<T> mReturnVal;

    public ArrayList<T> ConvertJsonToClass(JSONArray arrayJson, Class<T> classType) {
        mReturnVal = new ArrayList<T>();
        for(int i = 0; i < arrayJson.length(); i ++) {
            try {
                String jsonStr = arrayJson.get(i).toString();
                T val = new Gson().fromJson(jsonStr, classType);
                mReturnVal.add(val);

            } catch (Exception e) {
                Log.e(TAG, "OCCUR ERROR IN CONVERTING JSON TO OBJECT");
            }
        }

        return mReturnVal;
    }
}
