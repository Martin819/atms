package cz.polreich.banks.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cz.polreich.banks.R;
import cz.polreich.banks.adapter.ATMsAdapter;
import cz.polreich.banks.controller.AirBankController;
import cz.polreich.banks.model.airBank.AirBankATM;
import cz.polreich.banks.service.AirBankService;

public class ATMsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private TextView mTextMessage;
    private static Context context;
    private AirBankController controller;
    private String airbank_apikey;
    private RecyclerView mRecyclerView;
    private ATMsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private AirBankService airBankService;
    private List<AirBankATM> atmsList = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atms_list, container, false);
        airbank_apikey = view.getResources().getString(R.string.airbank_apikey);
        Activity activity = getActivity();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.atms_list_recycler_view);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ATMsAdapter(atmsList, mRecyclerView);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        controller = new AirBankController(activity);
        controller.getATMsList(airbank_apikey, mAdapter, true);
        mSwipeRefreshLayout = view.findViewById(R.id.atms_list_swipe_refresh);
        return view;
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
            getActivity().runOnUiThread(() -> mSwipeRefreshLayout.setRefreshing(false));
        }).start();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
