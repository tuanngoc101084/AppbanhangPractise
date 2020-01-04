package com.example.appbuyit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbuyit.R;
import com.example.appbuyit.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Loaispadapter extends BaseAdapter
{
    ArrayList<Loaisp> arrayListloaisp;
    Context context;
    @Override
    public int getCount() {
        return arrayListloaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListloaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder
    {
       TextView txtTenloaisp;
       ImageView imgloaisp;
    }

    public Loaispadapter(ArrayList<Loaisp> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null)
        {
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.dong_listview,null);
            viewHolder.txtTenloaisp= convertView.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp= convertView.findViewById(R.id.imageviewloaisp);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder) convertView.getTag();
            Loaisp loaisp= (Loaisp) getItem(position);
            viewHolder.txtTenloaisp.setText(loaisp.getTenloaisp());
            Picasso.get().load(loaisp.getHinhanhloaisp())
                    .placeholder(R.drawable.none)
                    .error(R.drawable.loadfail)
                    .into(viewHolder.imgloaisp);

        }
        return convertView;
    }
}
