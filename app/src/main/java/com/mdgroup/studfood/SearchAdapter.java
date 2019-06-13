package com.mdgroup.studfood;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private View.OnClickListener clickListener;
    private ArrayList<RecipeModel> filteredList;
    private ArrayList<String> stringList;
    private Context context;

    public SearchAdapter(Context context, ArrayList<RecipeModel> recipeList, ArrayList<String> stringList) {
        this.context = context;
        this.filteredList = recipeList;
        this.stringList = stringList;
    }

    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }


    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.d(MainActivity.TAG, "onCreateViewHolder post size = ");

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,
                parent, false);

        SearchAdapter.ViewHolder holder = new SearchAdapter.ViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(view);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {
        //Log.d(MainActivity.TAG, "onBindViewHolder post size = ");

        holder.nameSearchTextView.setText(filteredList.get(position).getName());
        holder.pictureSearchImageView.setImageDrawable(context.getResources()
                .getDrawable(filteredList.get(position).getPictureID()));
        holder.pictureSearchImageView.setAdjustViewBounds(true);
    }


    @Override
    public int getItemCount() {
        if (filteredList == null)
            return 0;
        return filteredList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameSearchTextView;
        ImageView pictureSearchImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameSearchTextView = itemView.findViewById(R.id.nameSearchTextView);
            pictureSearchImageView = itemView.findViewById(R.id.pictureSearchImageView);
        }
    }
}
