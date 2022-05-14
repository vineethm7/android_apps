package com.example.project3a2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class RestActivity extends AppCompatActivity {
    private static final String TAG = "RestaurantActivity";
    static String[] rest_arr;
    static String[] rest_web_arr;

    private ListViewModel model;

    FragmentManager mFragmentManager;
    private FragmentContainerView rest_list, rest_web;
    private com.example.project3a2.rest_web rest_view_frag= new rest_web();
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rest_arr = getResources().getStringArray(R.array.rest_val);
        rest_web_arr = getResources().getStringArray(R.array.rest_web);
        setContentView(R.layout.restaurant_activity);

        rest_list = findViewById(R.id.restaurant_list_id);
        rest_web = findViewById(R.id.restaurant_web_id);

        model = new ViewModelProvider(this).get(ListViewModel.class);

        mFragmentManager =  getSupportFragmentManager();

        add_view_frag();
        model.getSelectedItem().observe(this, item->{
            if(item<0 || item>=8){
                return;
            }
            else{
                set_layout();
                if(!rest_view_frag.isAdded()){
                    add_view_frag();
                }
            }
        });

        mFragmentManager.addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {
                        set_layout();
                    }
                }
        );
    }

    private void set_layout() {
        if(!rest_view_frag.isAdded()){
            rest_list.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            rest_web.setLayoutParams(new LinearLayout.LayoutParams(0,MATCH_PARENT));
        }
        else{
            rest_list.setLayoutParams(new LinearLayout.LayoutParams(0,MATCH_PARENT,1f));
            rest_web.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT,2f));
        }
    }

    private void add_view_frag() {
        FragmentTransaction frag_trans = mFragmentManager.beginTransaction();
        frag_trans.replace(R.id.restaurant_web_id,rest_view_frag);
        frag_trans.addToBackStack(null);
        frag_trans.commit();
        mFragmentManager.executePendingTransactions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.attr_menu:
                Intent intentA = new Intent(this, AttrActivity.class);
                startActivity(intentA);
                return true;
            case R.id.rest_menu:
                Intent intentR = new Intent(this, RestActivity.class);
                startActivity(intentR);
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

}
