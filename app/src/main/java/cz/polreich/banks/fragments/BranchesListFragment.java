package cz.polreich.banks.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
    private static final String DEBUG_TAG_INFO = "[INFO     ] BranchesListFragment";
    private static final String DEBUG_TAG_ERROR = "[    ERROR] BranchesListFragment";
    private static final String DEBUG_TAG_WARNING = "[ WARNING ] BranchesListFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BranchesListFragment() {

    }

    public static BranchesListFragment newInstance(String param1, String param2) {
        BranchesListFragment fragment = new BranchesListFragment();
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
        SharedPreferences prefs = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        if (prefs.getBoolean("my_banks_air_bank", true)) {
            // TODO: SHOW AIRBANK BRANCHES
            Log.d("XXXXXXXXXXXXXXXX------------------XXXXXXXXXXXXXXXXXX", "my_banks_air_bank");
        }
        View view = inflater.inflate(R.layout.fragment_branch_list, container, false);
        airbank_apikey = view.getResources().getString(R.string.airbank_apikey);
        Activity activity = getActivity();
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
            getActivity().runOnUiThread(() -> mSwipeRefreshLayout.setRefreshing(false));
        }).start();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
