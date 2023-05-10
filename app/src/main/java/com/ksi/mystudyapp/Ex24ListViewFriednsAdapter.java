package com.ksi.mystudyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Ex24ListViewFriednsAdapter extends BaseAdapter {
    ArrayList<Ex24FriendsItem> items = new ArrayList<Ex24FriendsItem>();

    //Ex20FriendsItem항목으로 생성해서 추가하기...

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(Ex24FriendsItem item) {
        items.add(item);
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        final Ex24FriendsItem friendsItem = items.get(position);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_ex24_friendlistview_list_item, parent, false);

        } else {
            View view = new View(context);
            view = (View) convertView;

        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHp = (TextView) convertView.findViewById(R.id.tvHp);
        TextView tvAddr = (TextView) convertView.findViewById(R.id.tvAddr);
        TextView tvSex = (TextView) convertView.findViewById(R.id.tvSex);
        TextView tvAge = (TextView) convertView.findViewById(R.id.tvAge);

        tvName.setText(friendsItem.getName());
        tvHp.setText(friendsItem.getHp());
        tvAddr.setText(friendsItem.getAddr());
        tvSex.setText(friendsItem.getSex());
        tvAge.setText(friendsItem.getAge()+"");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return convertView;
    }
}
