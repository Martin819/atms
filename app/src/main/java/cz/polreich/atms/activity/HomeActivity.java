package cz.polreich.atms.activity;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cz.polreich.atms.fragments.ATMsListFragment;
import cz.polreich.atms.fragments.BranchesListFragment;
import cz.polreich.atms.adapter.BranchesAdapter;
import cz.polreich.atms.R;
import cz.polreich.atms.model.airBank.Branch;
import cz.polreich.atms.service.AirBankService;


public class HomeActivity extends AppCompatActivity implements
        BranchesListFragment.OnFragmentInteractionListener,
        ATMsListFragment.OnFragmentInteractionListener {

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
                case R.id.navigation_notifications:

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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
