package com.goqual.goquallunch;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gc.materialdesign.views.ButtonFloat;
import com.goqual.goquallunch.Adapter.RecyclerViewAdapter;
import com.goqual.goquallunch.DTO.MenuDTO;
import com.goqual.goquallunch.Handler.JsonHandler;
import com.goqual.goquallunch.Handler.NewMenuHandler;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityMain extends AppCompatActivity {
    private static final String TAG = "ACTIVITY_MAIN";
    private Context mContext;
    private NewMenuHandler mNewMenuHandler;

    private boolean WRAP_IN_SCROLL_VIEW = true;

    private RecyclerViewAdapter mRecyclerViewAdapter;
    private static ArrayList<MenuDTO> mListItems = new ArrayList<MenuDTO>() {};

    //private MenuList mListItems = new MenuList<MenuDTO>();


    @Bind(R.id.float_btn_add_menu) ButtonFloat mFloatBtnAddMenu;

    private RecyclerView mRecyclerView;

    @OnClick({ R.id.float_btn_add_menu }) void onClick(View v) {
        switch (v.getId()) {
            case R.id.float_btn_add_menu:
                openDialog();
                break;
            default:
                break;
        }
    }

    void getMenues () {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://newgeneration.kr/index.php/Menuapi/menu", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String objectsStr = new String(responseBody, "UTF-8");
                    JSONArray arrJson = new JSONArray(objectsStr);

                    mListItems = new JsonHandler<MenuDTO>().ConvertJsonToClass(arrJson, MenuDTO.class);
                    mRecyclerViewAdapter.setItems(mListItems);
                    mRecyclerViewAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e(TAG, "FAIL TO CONVERT BINARY DATA TO JSON");
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    void openDialog () {
        new MaterialDialog.Builder(this)
                .title(mContext.getString(R.string.DIALOG_NEW_MENU_TITLE))
                .customView(R.layout.dialog_new_menu, WRAP_IN_SCROLL_VIEW)
                .negativeText(mContext.getString(R.string.DIALOG_NEW_MENU_BTN_CANCEL))
                .positiveText(mContext.getString(R.string.DIALOG_NEW_MENU_BTN_SAVE))
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        String menuName = ((EditText) dialog.findViewById(R.id.dialog_edit_new_menu)).getText().toString();
                        String menuNum = ((EditText) dialog.findViewById(R.id.dialog_edit_new_num)).getText().toString();

                        mNewMenuHandler.createMenu(menuName, menuNum);
                    }
                })
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setElement();
        getMenues();
    }

    void setElement () {
        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.list_menu);

        mRecyclerViewAdapter = new RecyclerViewAdapter(mContext, mListItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mNewMenuHandler = getNewMenuHandler();
    }

    public NewMenuHandler getNewMenuHandler () {
        if (mNewMenuHandler == null) {
            mNewMenuHandler = new NewMenuHandler(mContext, mRecyclerViewAdapter);
        }

        return mNewMenuHandler;
    }
}
