package com.project.harsh.precticeall;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by harsh on 08-02-2017.
 */

public class JsonDataAdapter extends BaseAdapter {
    Context context;
    ArrayList<Post> posts;
    LayoutInflater inflater;
    TextView TV1,TV2,TV3,TV4;

    public JsonDataAdapter(Context context,ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
//    static class ViewHolder{
//        TextView textView;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder;

        if(convertView == null)
            //holder = new ViewHolder();
            convertView =inflater.inflate(R.layout.list_view,null);
        TV1 = (TextView)convertView.findViewById(R.id.U_id);
        TV2 = (TextView)convertView.findViewById(R.id.in_no);
        TV3= (TextView)convertView.findViewById(R.id.title1);
        TV4= (TextView)convertView.findViewById(R.id.desc);

        Post m = posts.get(position);
        TV1.setText(String.valueOf(m.getUserId()));
        TV2.setText(String.valueOf(m.getId()));
        TV3.setText(m.getTitle());
        TV4.setText(m.getBody());

        return convertView;
    }
}
