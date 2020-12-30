package com.example.edukidsbitirmeprojesi.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.edukidsbitirmeprojesi.Object.ImageItem;
import com.example.edukidsbitirmeprojesi.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.AnimalsViewHolder> {
    private Context context;
    private List<ImageItem> imageItemList;
    private Typeface jellyCrazies;

    public class AnimalsViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView image;

        public AnimalsViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.shape_name);
            image = view.findViewById(R.id.shape_image);
        }
    }

    public AnimalsAdapter(Context context, List<ImageItem> imageItemList) {
        this.context = context;
        this.imageItemList = imageItemList;
        jellyCrazies = Typeface.createFromAsset(context.getAssets(), "fonts/jelly_crazies.ttf");
    }

    @Override
    public AnimalsAdapter.AnimalsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shapes_item, parent, false);
        return new AnimalsAdapter.AnimalsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AnimalsAdapter.AnimalsViewHolder holder, int position) {
        int pos = position % imageItemList.size();

        ImageItem imageItem = imageItemList.get(pos);
        holder.name.setTypeface(jellyCrazies);
        holder.name.setText(imageItem.getName());
        Picasso.get()
                .load(imageItem.getImage())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }





}
