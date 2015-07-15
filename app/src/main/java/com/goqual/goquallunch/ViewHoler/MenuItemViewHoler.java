package com.goqual.goquallunch.ViewHoler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFlat;
import com.goqual.goquallunch.R;

/**
 * Created by ladmusician on 15. 7. 13..
 */
public class MenuItemViewHoler extends RecyclerView.ViewHolder {
    public TextView mTxtMenu;
    public TextView mTxtNum;
    public TextView mTxtHit;
    public ButtonFlat mBtnSelect;

    public MenuItemViewHoler(View itemView) {
        super(itemView);
        mTxtMenu = (TextView) itemView.findViewById(R.id.card_txt_menu_name);
        mTxtNum = (TextView) itemView.findViewById(R.id.card_txt_menu_num);
        mTxtHit = (TextView) itemView.findViewById(R.id.card_txt_menu_hit);
        mBtnSelect = (ButtonFlat) itemView.findViewById(R.id.card_btn_select);
    }
}
