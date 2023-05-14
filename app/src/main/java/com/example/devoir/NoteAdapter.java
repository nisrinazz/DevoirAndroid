package com.example.devoir;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {

    private static class ViewHolder {
        TextView labelTextView;
        TextView scoreTextView;

        ImageView iconImageView;
    }

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note note = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
            viewHolder.iconImageView = (ImageView) convertView.findViewById(R.id.iconT);
            viewHolder.labelTextView = (TextView) convertView.findViewById(R.id.label_text_view);
            viewHolder.scoreTextView = (TextView) convertView.findViewById(R.id.score_text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.labelTextView.setText(note.getLabel());
        viewHolder.scoreTextView.setText(String.valueOf(note.getScore()));
        if(note.getScore() > 10)
            viewHolder.iconImageView.setImageResource(R.drawable.like);
        else
            viewHolder.iconImageView.setImageResource(R.drawable.dislike);
        return convertView;
    }
}