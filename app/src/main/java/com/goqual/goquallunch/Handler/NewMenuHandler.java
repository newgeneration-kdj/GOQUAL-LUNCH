package com.goqual.goquallunch.Handler;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

/**
 * Created by ladmusician on 15. 7. 15..
 */
public class NewMenuHandler {
    private static final String TAG = "HANDLER_NEW_MENU";
    private Context mContext;

    private EditText mEditMenuLabel;
    private EditText mEditMenuNum;

    public NewMenuHandler(Context ctx) {
        mContext = ctx;
    }

    public void createMenu(String label, String num) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("label", label);
        params.put("num", num);
        client.post("http://go-qual.co.kr/index.php/Menuapi/menu", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String test = responseBody.toString();
                Log.e(TAG, "RETURN VALUE : " + responseBody.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG, error.getLocalizedMessage());
            }
        });
    }

    /*
     AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("label", "test123");
        client.post("http://go-qual.co.kr/index.php/Menuapi/menu", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String test = new String(responseBody, "UTF-8");
                    JSONArray test1 = new JSONArray(test);

                    List<MenuDTO> rtv = new JsonHandler<MenuDTO>().ConvertJsonToClass(test1, MenuDTO.class);
                    Log.e(TAG, "RESPONSE : " + test);
                } catch(Exception e) {
                    Log.e(TAG, "FAIL TO CONVERT BINARY DATA TO JSON");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG, e.getLocalizedMessage());
            }
        });
     */
}
