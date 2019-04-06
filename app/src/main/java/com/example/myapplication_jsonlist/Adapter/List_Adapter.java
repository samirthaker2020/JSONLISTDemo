package com.example.myapplication_jsonlist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication_jsonlist.Modal.Student;
import com.example.myapplication_jsonlist.R;

import java.util.ArrayList;

public class List_Adapter extends BaseAdapter {

    private Context context;

    public List_Adapter(Context context, ArrayList<Student> studentsArrayList) {
        this.context = context;
        this.studentsArrayList = studentsArrayList;
    }

    private ArrayList<Student> studentsArrayList;
    @Override
    public int getCount() {
        return studentsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.customlist,parent, false);
        }

        TextView txtEid = convertView.findViewById(R.id.rid);
        TextView txtEnm = convertView.findViewById(R.id.rname);
        TextView txtECountry = convertView.findViewById(R.id.rgender);

        Student mEmployee = (Student) getItem(position);

        txtEid.setText(String.valueOf(mEmployee.getSid()));
        txtEnm.setText(mEmployee.getSname());
        txtECountry.setText(mEmployee.getGender());


        return convertView;

    }
}
