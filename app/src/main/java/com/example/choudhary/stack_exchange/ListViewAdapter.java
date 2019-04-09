package com.example.choudhary.stack_exchange;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private MainActivity context;
    private ArrayList<String> Title;
    private ArrayList<String> AskerName;
    private ArrayList<String> ViewCount;
    private ArrayList<String> Score;
    private ArrayList<String> CreationDate;
    private ArrayList<String> LastActivityDate;

    public ListViewAdapter(MainActivity context, ArrayList<String> Title, ArrayList<String> AskerName,
                           ArrayList<String> ViewCount, ArrayList<String> Score, ArrayList<String> CreationDate,
                           ArrayList<String> LastActivityDate) {
        super();
        this.context = context;
        this.Title = Title;
        this.AskerName = AskerName;
        this.ViewCount =ViewCount;
        this.Score = Score;
        this.CreationDate = CreationDate;
        this.LastActivityDate = LastActivityDate;
    }

    @Override
    public int getCount() {
        return Title.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView TxtTitle;
        TextView TxtAskerName;
        TextView TxtViewCount;
        TextView TxtScore;
        TextView TxtCreationDate;
        TextView TxtLastActivityDate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.list_items, null);
            holder = new ViewHolder();
            holder.TxtTitle = convertView.findViewById(R.id.title);
            holder.TxtAskerName = convertView.findViewById(R.id.asker_name);
            holder.TxtViewCount = convertView.findViewById(R.id.view_count);
            holder.TxtScore = convertView.findViewById(R.id.score);
            holder.TxtCreationDate = convertView.findViewById(R.id.creation_date);
            holder.TxtLastActivityDate = convertView.findViewById(R.id.lat_activity_date);
            convertView.setTag(holder);
        }

        else {
            holder = (ViewHolder) convertView.getTag();

        }
        holder.TxtTitle.setText(Title.get(position));
        holder.TxtAskerName.setText(AskerName.get(position));
        holder.TxtViewCount.setText(ViewCount.get(position));
        holder.TxtScore.setText(Score.get(position));
        holder.TxtCreationDate.setText(CreationDate.get(position));
        holder.TxtLastActivityDate.setText(LastActivityDate.get(position));

        return convertView;
    }
}
