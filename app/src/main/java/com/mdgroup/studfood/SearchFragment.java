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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FragmentInteractionListener listener;
    private SearchAdapter searchAdapter;
    private ArrayList<RecipeModel> recipeList;
    private ArrayList<RecipeModel> filteredList;
    private ArrayList<String> stringList;
    private EditText searchEditText;
    private String searchText;

    public void setListener(FragmentInteractionListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.search_layout, null);
        Log.d(MainActivity.TAG, "SearchFragment onCreateView");

        recipeList = new ArrayList<>();
        filteredList = new ArrayList<>();
        stringList = new ArrayList<>();
        searchText = "";
        stringList.add(searchText);

        Bundle bundle = getArguments();
        String json = bundle.getString("recipeList");

        Gson gson = new Gson();
        Type listType = new TypeToken<List<RecipeModel>>(){}.getType();
        Log.d(MainActivity.TAG, "input = " + json.toString());
        recipeList = gson.fromJson(json, listType);
        filteredList.addAll(recipeList);

        searchEditText = v.findViewById(R.id.searchEditText);
        searchEditText.setHint(R.string.search_hint);
        searchEditText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                filteredList.clear();
                stringList.clear();
                for (int i = 0; i < recipeList.size(); i++) {
                    if (recipeList.get(i).getName().toLowerCase().contains(s.toString().toLowerCase())
                    || recipeList.get(i).getIngredients().toLowerCase().contains(s.toString().toLowerCase())) {
                        filteredList.add(recipeList.get(i));
                        final StringBuilder builder = new StringBuilder(s.length());
                        builder.append(s);
                        searchText = builder.toString();
                        stringList.add(searchText);
                    }
                }
                //Toast.makeText(getActivity(), "text = " + searchText, Toast.LENGTH_SHORT).show();
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        Log.d(MainActivity.TAG, "SearchFragment list = " + recipeList.size());

        searchAdapter = new SearchAdapter(getActivity(), filteredList, stringList);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) v.findViewById(R.id.searchRecyclerView);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recyclerView.setAdapter(searchAdapter);

        searchAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                Bundle bundle = new Bundle();
                bundle.putString("name", filteredList.get(position).getName());
                bundle.putInt("pictureID", filteredList.get(position).getPictureID());
                bundle.putString("ingredients", filteredList.get(position).getIngredients());
                bundle.putString("technology", filteredList.get(position).getTechnology());
                listener.onItemClick(bundle);
            }
        });

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
