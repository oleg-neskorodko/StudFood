package com.mdgroup.studfood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentInteractionListener{

    public static String TAG = "tag";
    private ListFragment listFragment;
    private RecipeFragment recipeFragment;
    private SearchFragment searchFragment;
    private ArrayList<RecipeModel> recipeList;
    private String json;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MainActivity onCreate");
        setContentView(R.layout.activity_main);



        database = new Database(this);
        database.deleteBase();
        if (!database.baseNotEmpty()) {
            database.fillBase();
        }
        recipeList = database.readBase();

        Gson gson = new Gson();
        json = gson.toJson(recipeList);
        Log.d(TAG, "output = " + json);

        listFragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("recipeList", json);
        listFragment.setArguments(bundle);
        listFragment.setListener(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, listFragment, "listFragment").addToBackStack("main_stack").commit();
        } else {
            ListFragment fragment = (ListFragment) getSupportFragmentManager().findFragmentByTag("listFragment");
            if (fragment != null) {
                fragment.setListener(this);
            }
        }
        Log.d(TAG, "MainActivity onCreate end");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_search:
                onSearchClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(Bundle bundle) {
        Log.d(TAG, "MainActivity onItemClick");
        recipeFragment = new RecipeFragment();
        recipeFragment.setArguments(bundle);
        recipeFragment.setListener(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, recipeFragment, "recipeFragment").addToBackStack("main_stack").commit();
    }

    @Override
    public void onSearchClick() {
        Log.d(TAG, "MainActivity onSearchClick");

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            int backstack = fm.getBackStackEntryCount();
            for (int i = 1; i < backstack; i++) {
                fm.popBackStack();
            }
        }

        searchFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString("recipeList", json);
        searchFragment.setArguments(bundle);
        searchFragment.setListener(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, searchFragment, "searchFragment").addToBackStack("main_stack").commit();
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "MainActivity onBackPressed");
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() <= 1) {
            finish();
        } else super.onBackPressed();
    }
}
