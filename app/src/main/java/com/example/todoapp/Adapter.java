package com.example.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class Adapter extends BaseAdapter {

    private List<Task> listTask;
    private Context context;
    private LayoutInflater inflater;

    public Adapter(Context context, List<Task> list){
        this.context = context;
        listTask = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listTask.size();
    }

    @Override
    public Object getItem(int position) {
        return listTask.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if(convertView == null){
            view = (View) inflater.inflate(R.layout.task_list_item,parent,false);

        }
        else {
            view = (View) convertView;
        }

        TextView intitule = (TextView) view.findViewById(R.id.intitule);
        TextView description = (TextView) view.findViewById(R.id.description);
        TextView duree = (TextView) view.findViewById(R.id.duree);
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView context = (TextView) view.findViewById(R.id.context);
        TextView url = (TextView) view.findViewById(R.id.url);
        intitule.setText(listTask.get(position).getIntitule());
        description.setText(listTask.get(position).getDesc());
        duree.setText(listTask.get(position).getDureeH());
        date.setText(listTask.get(position).getDate());
        context.setText(listTask.get(position).getContexte());
        url.setText(listTask.get(position).getUrl());

        return view;
    }
}