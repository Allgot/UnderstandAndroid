package dev_allgot.understand.doitmission_25;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    ArrayList<Picture> pictures = new ArrayList<Picture>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       View view =  inflater.inflate(R.layout.picture, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setPicture(pictures.get(position));
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public void addPicture(Picture p) {
        pictures.add(p);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        TextView dateText;
        ImageView pictureImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.titleText);
            dateText = itemView.findViewById(R.id.dateText);
            pictureImage = itemView.findViewById(R.id.pictureImage);
        }

        public void setPicture(Picture p) {
            if(p.picture != null) pictureImage.setImageBitmap(p.picture);
            String msg = Integer.toString(p.num + 1);
            titleText.setText(msg);
            if(!p.date.equals("")) dateText.setText(p.date);
        }
    }
}
