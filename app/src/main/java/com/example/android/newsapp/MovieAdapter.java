package com.example.android.newsapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MovieAdapter extends ArrayAdapter<Movie> {


    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_list_item, parent, false);
        }

        // Find the movie at the given position in the list of movies
        Movie currentMovie = getItem(position);

        // Find the ImageView with view ID image
        ImageView movieView = (ImageView) listItemView.findViewById(R.id.movie);
        // Display the image of the current movie in that ImageView
        movieView.setImageResource(currentMovie.getImageResourceId());


        // Find the TextView with view ID webTitle
        TextView titleView = (TextView) listItemView.findViewById(R.id.web_title);
        // Display the Title of the current article in that TextView
        titleView.setText(currentMovie.getWebTitle());

        // Find the TextView with view ID section name
        TextView sectionView = (TextView) listItemView.findViewById(R.id.section_name);
        // Display the section name of the current article in that TextView
        sectionView.setText(currentMovie.getSectionName());

        TextView authorView = (TextView) listItemView.findViewById(R.id.author_name);
        // Display the section name of the current article in that TextView
        authorView.setText(currentMovie.getAuthorName());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Display the date of the current movie in that TextView
        dateView.setText(currentMovie.getPublicationDate());


        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

}


