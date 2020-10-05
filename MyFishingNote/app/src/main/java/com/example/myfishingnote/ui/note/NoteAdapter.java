package com.example.myfishingnote.ui.note;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfishingnote.R;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>
        implements OnNoteItemClickListener {

    ArrayList<NoteDTO> items = new ArrayList<NoteDTO>();

    OnNoteItemClickListener listener;

    int layoutType = 0;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.note_item, viewGroup, false);

        return new ViewHolder(itemView, this, layoutType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        NoteDTO item = items.get(position);
        viewHolder.setItem(item);
        viewHolder.setLayoutType(layoutType);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(NoteDTO item) {
        items.add(item);
    }

    public void setItems(ArrayList<NoteDTO> items) {
        this.items = items;
    }

    public NoteDTO getItem(int position) {
        return items.get(position);
    }

    public void setOnItemClickListener(OnNoteItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }//if
    }//onItemClick

    public void switchLayout(int position) {
        layoutType = position;
    }//switchLayout

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout1;

        ImageView moodImageView;

        ImageView pictureExistsImageView;

        ImageView weatherImageView;

        TextView contentsTextView;

        TextView locationTextView;

        TextView dateTextView;

        public  ViewHolder(View itemView, final OnNoteItemClickListener listener, int layoutType) {
            super(itemView);

            layout1 = itemView.findViewById(R.id.item_layout1);
            moodImageView = itemView.findViewById(R.id.item_image_view);
            pictureExistsImageView = itemView.findViewById(R.id.pictureExistImageView);
            weatherImageView = itemView.findViewById(R.id.weatherImageView);
            contentsTextView = itemView.findViewById(R.id.item_contentsTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }//if
                }
            });
            setLayoutType(layoutType);


        }//viewholder

        public void setItem(NoteDTO item) {
            String mood = item.getMood();
            int moodIndex = Integer.parseInt(mood);
            setMoodImage(moodIndex);

            String picturePath = item.getPicture();
            if (picturePath != null && !picturePath.equals("")) {
                pictureExistsImageView.setVisibility(View.VISIBLE);
                //pictureImageView.setVisibility(View.VISIBLE);
                //pictureImageView.setImageURI(Uri.parse("file://"+picturePath));
            } else {
                pictureExistsImageView.setVisibility(View.GONE);
                //pictureImageView.setVisivility(View.GONE);
                //pictureImageView.setImageResource(R.drawable.noimagefound);
            }//if&else

            String weather = item.getWeather();
            int weatherIndex = Integer.parseInt(weather);
            setWeatherImage(weatherIndex);

            contentsTextView.setText(item.getContents());
            locationTextView.setText(item.getAddress());
            dateTextView.setText(item.getCreateDateStr());


        }//setItem

        public void setMoodImage(int moodIndex) {
            switch (moodIndex){
                case 0:
                    moodImageView.setImageResource(R.drawable.smile1_48);
                    break;

                case 1:
                    moodImageView.setImageResource(R.drawable.smile2_48);
                    break;

                case 2:
                    moodImageView.setImageResource(R.drawable.smile3_48);
                    break;

                case 3:
                    moodImageView.setImageResource(R.drawable.smile4_48);
                    break;

                default:
                    moodImageView.setImageResource(R.drawable.smile3_48);
            }
        }//setMoodImage

        public void setWeatherImage(int weatherIndex) {
            switch (weatherIndex) {
                case 0:
                    weatherImageView.setImageResource(R.drawable.weather_icon_1);
                case 1:
                    weatherImageView.setImageResource(R.drawable.weather_icon_2);
                case 2:
                    weatherImageView.setImageResource(R.drawable.weather_icon_3);
                case 3:
                    weatherImageView.setImageResource(R.drawable.weather_icon_4);
                case 4:
                    weatherImageView.setImageResource(R.drawable.weather_icon_5);
                case 5:
                    weatherImageView.setImageResource(R.drawable.weather_icon_6);
                case 6:
                    weatherImageView.setImageResource(R.drawable.weather_icon_7);
                default:
                    weatherImageView.setImageResource(R.drawable.weather_icon_1);
            }
        }//setWeatherImage

        public void setLayoutType(int layoutType) {
            if (layoutType == 0) {
                layout1.setVisibility(View.VISIBLE);
                //layout2.setVisibility(View.GONE);
            } else if (layoutType == 1 ) {
                layout1.setVisibility(View.GONE);
                //layout2.setVisibility(View.VISIBLE);
            }//if&else
        }//setLayoutType



    }



}//class

