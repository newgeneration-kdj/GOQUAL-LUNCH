package com.goqual.goquallunch.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goqual.goquallunch.DTO.MenuDTO;
import com.goqual.goquallunch.R;
import com.goqual.goquallunch.ViewHoler.MenuItemViewHoler;

import java.util.ArrayList;

/**
 * Created by ladmusician on 15. 7. 13..
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<MenuItemViewHoler> {
    private static final String TAG = "ADAPTER_RECYCLER";
    private ArrayList<MenuDTO> mListMenu = null;
    private Context mContext;

    public RecyclerViewAdapter(Context ctx, ArrayList<MenuDTO> items) {
        if (items == null) {
            throw new IllegalArgumentException("modelData must not be null");
        }
        this.mContext = ctx;
        this.mListMenu = items;
    }

    @Override
    public MenuItemViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.card_row, parent, false);
        return new MenuItemViewHoler(v);
    }

    @Override
    public void onBindViewHolder(MenuItemViewHoler viewHolder, int position) {
        MenuDTO item = mListMenu.get(position);
        viewHolder.mTxtMenu.setText(item.label);
        if (item.hit == 0) {
            viewHolder.mTxtHit.setText(mContext.getString(R.string.NEVER_EVER_EATEN));
        } else {
            viewHolder.mTxtHit.setText("eat " + item.hit + " times");
        }

        String number = item.number;
        if (!number.startsWith("0")) { number = "0" + number; }
        viewHolder.mTxtNum.setText(number);
    }

    @Override
    public int getItemCount() {
        return mListMenu.size();
    }

    public void addMenu(String label, String number) {
        mListMenu.add(new MenuDTO(label));
        notifyItemInserted(0);
    }

}
