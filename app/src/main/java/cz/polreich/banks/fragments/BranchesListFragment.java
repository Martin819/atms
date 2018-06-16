package cz.polreich.banks.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cz.polreich.banks.R;
import cz.polreich.banks.adapter.BranchesAdapter;
import cz.polreich.banks.controller.AirBankController;
import cz.polreich.banks.model.UniBranch;
import cz.polreich.banks.model.airBank.AirBankBranch;
import cz.polreich.banks.service.AirBankService;

public class BranchesListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private TextView mTextMessage;
    private static Context context;
    private AirBankController controller;
    private String airbank_apikey;
    private RecyclerView mRecyclerView;
    private BranchesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private AirBankService airBankService;
    private List<UniBranch> branchesList = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private long lastSuccessfulFetch = 0;
    private FusedLocationProviderClient mFusedLocationClient;
    private static final String DEBUG_TAG_INFO = "[INFO     ] BranchesListFragment";
    private static final String DEBUG_TAG_ERROR = "[    ERROR] BranchesListFragment";
    private static final String DEBUG_TAG_WARNING = "[ WARNING ] BranchesListFragment";

    private OnFragmentInteractionListener mListener;

    public BranchesListFragment() {

    }

    public static BranchesListFragment newInstance(String param1, String param2) {
        BranchesListFragment fragment = new BranchesListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences prefs = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        if (prefs.getBoolean("my_banks_air_bank", true)) {
            // TODO: SHOW AIRBANK BRANCHES
            Log.d("XXXXXXXXXXXXXXXX------------------XXXXXXXXXXXXXXXXXX", "my_banks_air_bank");
        }
        View view = inflater.inflate(R.layout.fragment_branch_list, container, false);
        airbank_apikey = view.getResources().getString(R.string.airbank_apikey);
        Activity activity = getActivity();
        Location lastLoc = getLastLocation(activity);
        mRecyclerView = view.findViewById(R.id.branches_list_recycler_view);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new BranchesAdapter(branchesList, mRecyclerView);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()),
                DividerItemDecoration.VERTICAL));
        controller = new AirBankController(activity);
        controller.getBranchesList(airbank_apikey, mAdapter, true);
        mSwipeRefreshLayout = view.findViewById(R.id.branches_list_swipe_refresh);
        return view;
    }

    public Location getLastLocation(Activity activity) {
        final Location[] loc = new Location[1];
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 9);
            }
        }



        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        loc[0] = location;
                    }
                })
                .addOnFailureListener(activity, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
        return loc[0];
    }

    @Override
    public void onResume() {
        super.onResume();
        controller.getBranchesList(airbank_apikey, mAdapter, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {
        new Thread(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            Log.d(DEBUG_TAG_INFO, "Preparing to Refresh...");
            controller.getBranchesList(airbank_apikey, mAdapter, true);
            Log.d(DEBUG_TAG_INFO, "Refreshing...");
            mSwipeRefreshLayout.setRefreshing(false);
            Objects.requireNonNull(getActivity()).runOnUiThread(() -> mSwipeRefreshLayout.setRefreshing(false));
        }).start();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 9: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // TODO:
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    //TODO:
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
