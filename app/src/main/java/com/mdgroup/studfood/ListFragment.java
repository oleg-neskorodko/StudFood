package com.mdgroup.studfood;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {


    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FragmentInteractionListener listener;
    private ListAdapter listAdapter;
    private ArrayList<RecipeModel> recipeList;

    public void setListener(FragmentInteractionListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.list_layout, null);
        Log.d(MainActivity.TAG, "ListFragment onCreateView");

        recipeList = new ArrayList<>();
        Bundle bundle = getArguments();
        String json = bundle.getString("recipeList");

        Gson gson = new Gson();
        Type listType = new TypeToken<List<RecipeModel>>(){}.getType();
        Log.d(MainActivity.TAG, "input = " + json.toString());
        recipeList = gson.fromJson(json, listType);

        Log.d(MainActivity.TAG, "ListFragment list = " + recipeList.size());

        listAdapter = new ListAdapter(getActivity(), recipeList);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recyclerView.setAdapter(listAdapter);

        listAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                Bundle bundle = new Bundle();
                bundle.putString("name", recipeList.get(position).getName());
                bundle.putInt("pictureID", recipeList.get(position).getPictureID());
                bundle.putString("ingredients", recipeList.get(position).getIngredients());
                bundle.putString("technology", recipeList.get(position).getTechnology());
                listener.onItemClick(bundle);
            }
        });

        Log.d(MainActivity.TAG, "ListFragment onCreateView end");
        return v;
    }

    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {
            mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}
