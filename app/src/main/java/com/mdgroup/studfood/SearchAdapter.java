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
    private String searchText;

    public SearchAdapter(Context context, ArrayList<RecipeModel> recipeList, ArrayList<String> stringList) {
        this.context = context;
        this.filteredList = recipeList;
        this.stringList = stringList;
        //this.searchText = searchText;
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

        searchText = stringList.get(0).toLowerCase();

        holder.nameSearchTextView.setText(filteredList.get(position).getName());


        if (filteredList.size() > 0) {
            String ingredients = filteredList.get(position).getIngredients().toLowerCase();

            if (searchText.length() >= 3) {
                int index = ingredients.indexOf(searchText);
                if (index >= 0) {
                    ArrayList<Integer> indexes = new ArrayList<>();
                    while (index >= 0) {
                        indexes.add(index);
                        index = ingredients.indexOf(searchText, index + 1);
                    }
                    Log.d(MainActivity.TAG, searchText + ", array size = " + indexes.size());
                    StringBuilder builder1 = new StringBuilder();
                    for (int j = 0; j < indexes.size(); j++) {

/*                        if (indexes.get(j) > 0) {
                            for (int i = 0; i <= indexes.get(j); i++) {
                                if (ingredients.charAt(indexes.get(j) - i - 1) == ' '
                                || (ingredients.charAt(indexes.get(j) - i - 1) == '\n')) {
                                    indexes.set(j, (indexes.get(j) - i));
                                    return;
                                }
                            }
                        }*/

                        for (int i = indexes.get(j); i < ingredients.length(); i++) {

                            if (ingredients.charAt(i) != '\n' && ingredients.charAt(i) != ' ') {
                                builder1.append(ingredients.charAt(i));
                            } else break;
                        }
                        builder1.append(" ");
                    }
                    String textIngredients = builder1.toString();
                    holder.ingredientsSearchTextView.setText(textIngredients);
                }
            } else holder.ingredientsSearchTextView.setText("");
        }
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
        TextView ingredientsSearchTextView;
        ImageView pictureSearchImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameSearchTextView = itemView.findViewById(R.id.nameSearchTextView);
            ingredientsSearchTextView = itemView.findViewById(R.id.ingredientsSearchTextView);
            pictureSearchImageView = itemView.findViewById(R.id.pictureSearchImageView);
        }
    }
}
