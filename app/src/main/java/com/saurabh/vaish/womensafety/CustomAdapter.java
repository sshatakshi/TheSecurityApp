package com.saurabh.vaish.womensafety;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<User> {

    public CustomAdapter(Activity context, ArrayList<User> users)
    {
        super(context,0,users);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        User current = getItem(position);

        TextView name = (TextView) listItemView.findViewById(R.id.name);
        name.setText(current.getName());

        TextView num = (TextView) listItemView.findViewById(R.id.num);
        num.setText(current.getNumber());
        return listItemView;
    }
}
