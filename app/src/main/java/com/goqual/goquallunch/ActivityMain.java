package com.goqual.goquallunch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gc.materialdesign.views.ButtonFloat;
import com.goqual.goquallunch.Adapter.RecyclerViewAdapter;
import com.goqual.goquallunch.DTO.MenuDTO;
import com.goqual.goquallunch.Handler.JsonHandler;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityMain extends AppCompatActivity {
    private static final String TAG = "ACTIVITY_MAIN";
    private boolean WRAP_IN_SCROLL_VIEW = true;

    @Bind(R.id.float_btn_add_menu) ButtonFloat mFloatBtnAddMenu;
    private RecyclerView mRecyclerView;

    @OnClick({ R.id.float_btn_add_menu }) void onClick(View v) {
        switch (v.getId()) {
            case R.id.float_btn_add_menu:

                AsyncHttpClient client = new AsyncHttpClient();
                client.get("http://go-qual.co.kr/getLunch.php", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.e(TAG, "SUCCESS REQUEST");
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
                    public void onStart() {
                        // called before request is started
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        Log.e(TAG, "FAIL REQUEST");
                        Log.e(TAG, e.getLocalizedMessage());
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                    }
                });


                new MaterialDialog.Builder(this)
                        .title(getString(R.string.DIALOG_NEW_MENU_TITLE))
                        .customView(R.layout.dialog_new_menu, WRAP_IN_SCROLL_VIEW)
                        .negativeText(getString(R.string.DIALOG_NEW_MENU_BTN_CANCEL))
                        .positiveText(getString(R.string.DIALOG_NEW_MENU_BTN_SAVE))
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                Log.e(TAG, "CHECK SAVE");
                            }
                        })
                        .show();
                break;
            default:
                break;
        }
    }

    private static ArrayList<MenuDTO> data = new ArrayList<MenuDTO>() {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        data.add(new MenuDTO("김치찌개"));
        data.add(new MenuDTO("된장찌개"));
        data.add(new MenuDTO("돼지찌개"));
        data.add(new MenuDTO("추어탕"));
        data.add(new MenuDTO("짬뽕"));
        data.add(new MenuDTO("피자"));

        setElement();
    }

    void setElement () {
        mRecyclerView = (RecyclerView) findViewById(R.id.list_menu);

        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }
}
