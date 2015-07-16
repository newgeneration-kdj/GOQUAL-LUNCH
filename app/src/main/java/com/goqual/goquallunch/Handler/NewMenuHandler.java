package com.goqual.goquallunch.Handler;

import android.content.Context;
import android.util.Log;

import com.goqual.goquallunch.Adapter.RecyclerViewAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Created by ladmusician on 15. 7. 15..
 */
public class NewMenuHandler {
    private static final String TAG = "HANDLER_NEW_MENU";
    private Context mContext;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    public NewMenuHandler(Context ctx, RecyclerViewAdapter adapter) {
        mContext = ctx;
        mRecyclerViewAdapter = adapter;
    }

    public void createMenu(String label, String num) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("label", label);
        params.put("num", num);
        client.post("http://go-qual.co.kr/index.php/Menuapi/menu", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String jsonStr = new String(responseBody, "UTF-8");
                    JSONObject arrJson = new JSONObject(jsonStr);
                } catch(Exception e) {

                }
                //mRecyclerViewAdapter.addMenu();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG, error.getLocalizedMessage());
            }
        });
    }
}
