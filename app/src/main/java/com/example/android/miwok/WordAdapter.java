package com.example.android.miwok;

import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gamer on 14/03/2018.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int backgroundColor;

    public WordAdapter(Context context, ArrayList<Word> arrayList, int color){
        super(context,0,arrayList);
        backgroundColor= color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        final Word currentWord = getItem(position);

        TextView engTextView = (TextView) listItemView.findViewById(R.id.english_text_view);

        engTextView.setText(currentWord.getDefaultTranslation());

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);

        miwokTextView.setText(currentWord.getMiwokTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        ImageView playImage = (ImageView) listItemView.findViewById(R.id.play_icon);

        if (currentWord.hasImage()){
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(currentWord.getImageResourceId());
        }
        else {
            imageView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.words_layout);
        textContainer.setBackgroundColor(backgroundColor);
        playImage.setBackgroundColor(backgroundColor);

        return listItemView;
    }
}
