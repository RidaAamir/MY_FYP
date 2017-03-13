package com.example.emad.splashscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HP Pavilion 13 on 3/4/2017.
 */


public class customAdapter  extends ArrayAdapter<Item_Player> {
    private List<Item_Player> l;


    public customAdapter(Context context, int resource, List<Item_Player> items) {
        super(context, resource, items);
        l=items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.clayout, null);
        }

        Item_Player p = getItem(position);

        if (p != null) {
            CheckedTextView tt1 = (CheckedTextView) v.findViewById(R.id.txtvw);


            if (tt1 != null) {
                tt1.setText(p.name);
            }



        }
        return v;
    }

}
