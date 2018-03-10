package cz.polreich.banks.activity;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;
import java.util.List;

import cz.polreich.banks.AppDatabase;
import cz.polreich.banks.AppDatabase_Impl;
import cz.polreich.banks.fragments.ATMsListFragment;
import cz.polreich.banks.fragments.BranchesListFragment;
import cz.polreich.banks.adapter.BranchesAdapter;
import cz.polreich.banks.R;
import cz.polreich.banks.fragments.MapFragment;
import cz.polreich.banks.model.airBank.Branch;
import cz.polreich.banks.service.AirBankService;

public class HomeActivity extends AppCompatActivity implements
        BranchesListFragment.OnFragmentInteractionListener,
        ATMsListFragment.OnFragmentInteractionListener,
        MapFragment.OnFragmentInteractionListener {

    private final String DEBUG_TAG_INFO = "[INFO     ] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_ERROR = "[    ERROR] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_WARNING = "[ WARNING ] " + this.getClass().getSimpleName();
    private TextView mTextMessage;
    private static Context context;
    private RecyclerView mRecyclerView;
    private BranchesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private AirBankService airBankService;
    private List<Branch> branchesList = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_branches:
                    switchToBranchesList();
                    return true;
                case R.id.navigation_atms:
                    switchToATMsList();
                    return true;
                case R.id.navigation_map:
                    switchToMapList();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_branches);
        AppDatabase database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "banksDB").build();
    }

    public static Context getContext(){
        return context;
    }

    public void switchToBranchesList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new BranchesListFragment()).commit();
    }

    public void switchToATMsList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new ATMsListFragment()).commit();
    }

    public void switchToMapList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new MapFragment()).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
