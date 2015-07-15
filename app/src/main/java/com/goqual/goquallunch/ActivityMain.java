package com.goqual.goquallunch;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gc.materialdesign.views.ButtonFloat;
import com.goqual.goquallunch.Adapter.RecyclerViewAdapter;
import com.goqual.goquallunch.DTO.MenuDTO;
import com.goqual.goquallunch.Handler.NewMenuHandler;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityMain extends AppCompatActivity {
    private static final String TAG = "ACTIVITY_MAIN";
    private Context mContext;
    private NewMenuHandler mNewMenuHandler;

    private boolean WRAP_IN_SCROLL_VIEW = true;


    @Bind(R.id.float_btn_add_menu) ButtonFloat mFloatBtnAddMenu;
    private RecyclerView mRecyclerView;

    @OnClick({ R.id.float_btn_add_menu }) void onClick(View v) {
        switch (v.getId()) {
            case R.id.float_btn_add_menu:
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
        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.list_menu);

        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        mNewMenuHandler = getNewMenuHandler();
    }

    public NewMenuHandler getNewMenuHandler () {
        if(mNewMenuHandler == null) {
            mNewMenuHandler = new NewMenuHandler(mContext);
        }

        return mNewMenuHandler;
    }
}
