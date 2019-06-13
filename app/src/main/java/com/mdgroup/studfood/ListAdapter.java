package com.mdgroup.studfood;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private View.OnClickListener clickListener;
    private View.OnLongClickListener longClickListener;
    private ArrayList<RecipeModel> recipeList;
    private Context context;

    public ListAdapter(Context context, ArrayList<RecipeModel> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }


    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.d(MainActivity.TAG, "onCreateViewHolder post size = " + getItemCount());

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,
                parent, false);

        ListAdapter.ViewHolder holder = new ListAdapter.ViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(view);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        //Log.d(MainActivity.TAG, "onBindViewHolder post size = " + getItemCount());

        holder.nameTextView.setText(recipeList.get(position).getName());
        holder.pictureImageView.setImageDrawable(context.getResources()
                .getDrawable(recipeList.get(position).getPictureID()));
        holder.pictureImageView.setAdjustViewBounds(true);
    }


    @Override
    public int getItemCount() {
        if (recipeList == null)
            return 0;
        return recipeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView pictureImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            pictureImageView = itemView.findViewById(R.id.pictureImageView);
        }
    }
}
