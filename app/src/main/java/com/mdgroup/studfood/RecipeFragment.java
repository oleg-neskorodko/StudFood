package com.mdgroup.studfood;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeFragment extends Fragment {

    private FragmentInteractionListener listener;
    private TextView nameRecipeTextView;
    private TextView ingredientsRecipeTextView;
    private TextView technologyRecipeTextView;
    private ImageView pictureRecipeImageView;

    public void setListener(FragmentInteractionListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recipe_layout, null);
        Log.d(MainActivity.TAG, "RecipeFragment onCreateView");

        nameRecipeTextView = v.findViewById(R.id.nameRecipeTextView);
        ingredientsRecipeTextView = v.findViewById(R.id.ingredientsRecipeTextView);
        technologyRecipeTextView = v.findViewById(R.id.technologyRecipeTextView);
        pictureRecipeImageView = v.findViewById(R.id.pictureRecipeImageView);

        Bundle bundle = this.getArguments();
        nameRecipeTextView.setText(bundle.getString("name"));
        ingredientsRecipeTextView.setText(bundle.getString("ingredients"));
        technologyRecipeTextView.setText(bundle.getString("technology"));
        pictureRecipeImageView.setImageDrawable(getActivity().getResources().getDrawable(bundle.getInt("pictureID")));
        pictureRecipeImageView.setAdjustViewBounds(true);

        return v;
    }
}
