package cz.polreich.banks.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cz.polreich.banks.AppDatabase;
import cz.polreich.banks.R;
import cz.polreich.banks.adapter.ATMsAdapter;
import cz.polreich.banks.controller.AirBankController;
import cz.polreich.banks.dao.ATMDao;
import cz.polreich.banks.dao.BranchDao;
import cz.polreich.banks.model.UniATM;
import cz.polreich.banks.model.airBank.AirBankATM;
import cz.polreich.banks.service.AirBankService;
import cz.polreich.banks.utils;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class ATMsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private TextView mTextMessage;
    private static Context context;
    private AirBankController controller;
    private String airbank_apikey;
    private RecyclerView mRecyclerView;
    private ATMsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private AirBankService airBankService;
    private List<UniATM> atmsList = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private AppDatabase database;
    private ATMDao atmDao;
    private LocationRequest mLocationRequest;
    private Activity activity;
    private long UPDATE_INTERVAL = 10 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */
    private int REQUEST_FINE_LOCATION = 9;
    private Location lastLoc = new Location(LocationManager.GPS_PROVIDER);
    private static final String DEBUG_TAG_INFO = "[INFO     ] ATMsListFragment";
    private static final String DEBUG_TAG_ERROR = "[    ERROR] ATMsListFragment";
    private static final String DEBUG_TAG_WARNING = "[ WARNING ] ATMsListFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ATMsListFragment() {

    }

    public static ATMsListFragment newInstance(String param1, String param2) {
        ATMsListFragment fragment = new ATMsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atms_list, container, false);
        airbank_apikey = view.getResources().getString(R.string.airbank_apikey);
        activity = getActivity();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.atms_list_recycler_view);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ATMsAdapter(atmsList, mRecyclerView, activity);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()),
                DividerItemDecoration.VERTICAL));
        controller = new AirBankController(activity);
        controller.getATMsList(airbank_apikey, mAdapter, true);
        mSwipeRefreshLayout = view.findViewById(R.id.atms_list_swipe_refresh);
        startLocationUpdates();
        Log.d(DEBUG_TAG_INFO, "LastLocation: " + lastLoc.getLatitude() + " " + lastLoc.getLongitude());

        return view;
    }

    @SuppressLint("MissingPermission")
    protected void startLocationUpdates() {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();
        SettingsClient settingsClient = LocationServices.getSettingsClient(activity);
        settingsClient.checkLocationSettings(locationSettingsRequest);
        if(checkPermissions()) {
            getFusedLocationProviderClient(activity).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            // do work here
                            onLocationChanged(locationResult.getLastLocation());
                        }
                    },
                    Looper.myLooper());
        }
    }

    @SuppressLint("MissingPermission")
    public void getLastLocation() {
        // Get last known recent location using new Google Play Services SDK (v11+)
        FusedLocationProviderClient locationClient = getFusedLocationProviderClient(activity);
        if (checkPermissions()) {
            locationClient.getLastLocation()
                    .addOnSuccessListener(location -> {
                        // GPS location can be null if GPS is switched off
                        if (location != null) {
                            onLocationChanged(location);
                        }
                    })
                    .addOnFailureListener(e -> {
                        Log.d("MapDemoActivity", "Error trying to get last GPS location");
                        e.printStackTrace();
                    });
        }
    }

    public void onLocationChanged(Location location) {

        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        lastLoc.setLatitude(location.getLatitude());
        lastLoc.setLongitude(location.getLongitude());
        calculateDistances();
        mAdapter.updateItemsFromDB();
    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissions();
            return false;
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_FINE_LOCATION);
    }

    @Override
    public void onResume() {
        super.onResume();
        controller.getATMsList(airbank_apikey, mAdapter, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
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
            controller.getATMsList(airbank_apikey, mAdapter, true);
            Log.d(DEBUG_TAG_INFO, "Refreshing...");
            mSwipeRefreshLayout.setRefreshing(false);
            activity.runOnUiThread(() -> mSwipeRefreshLayout.setRefreshing(false));
        }).start();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void calculateDistances() {
        database = AppDatabase.getInstance(activity.getApplicationContext());
        atmDao = database.atmDao();
        new Thread(() -> {
            List<UniATM> atms = atmDao.getAllATMs();
            activity.runOnUiThread(() -> {
                for (UniATM atm:atms) {
                    Location locA = lastLoc;
                    Location locB = new Location(LocationManager.GPS_PROVIDER);
                    locB.setLatitude(atm.getLocation().getLatitude());
                    locB.setLongitude(atm.getLocation().getLongitude());
                    atm.setDistance(utils.getDistance(locA, locB));
                }
            });
            atmDao.updateATMs(atms);
        }).start();
    }
}
