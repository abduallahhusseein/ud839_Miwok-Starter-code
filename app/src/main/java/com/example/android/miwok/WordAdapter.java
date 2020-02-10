package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.android.movie_app.R;

import java.util.ArrayList;

/**
 * Created by MY on 3/19/2018.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int idSourceForbackgroundColor;
    public WordAdapter(Activity context, ArrayList<Word> Words,int position) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, Words);
        idSourceForbackgroundColor=position;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentword = getItem(position);
        ImageView defaultEmageView=(ImageView) listItemView.findViewById(R.id.image_view);
        if(currentword.hasImage()){
            defaultEmageView.setImageResource(currentword.getPositionForImageView());
            defaultEmageView.setVisibility(View.VISIBLE);
        }
        else
        {
            defaultEmageView.setVisibility(View.GONE);
        }
        //defaultEmageView.setImageResource(currentword.getPositionForImageView());
       //  Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        miwokTextView.setText(currentword.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaulTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        defaulTextView.setText(currentword.getDefaultTranslation());
        View textContainer=listItemView.findViewById(R.id.text_container);
        int color= ContextCompat.getColor(getContext(),idSourceForbackgroundColor);
        textContainer.setBackgroundColor(color);
        return listItemView;
    }
}
