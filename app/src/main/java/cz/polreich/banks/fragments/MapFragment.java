package cz.polreich.banks.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Objects;

import cz.polreich.banks.AppDatabase;
import cz.polreich.banks.R;
import cz.polreich.banks.dao.ATMDao;
import cz.polreich.banks.dao.BranchDao;
import cz.polreich.banks.model.UniBranch;
import cz.polreich.banks.utils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private static final String DEBUG_TAG_INFO = "[INFO     ] MapFragment";
    private static final String DEBUG_TAG_ERROR = "[    ERROR] MapFragment";
    private static final String DEBUG_TAG_WARNING = "[ WARNING ] MapFragment";
    private int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 369;
    private MapView mMapView;
    private GoogleMap mMap;
    private AppDatabase database;
    private BranchDao branchDao;
    private ATMDao atmDao;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
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
        // Inflate the layout for this fragment
        Log.i(DEBUG_TAG_INFO, "MapFragment created.");
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        database = AppDatabase.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext());
        branchDao = database.branchDao();
        mMapView = view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);
        mMapView.invalidate();
        return view;
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
    public void onMapReady(GoogleMap map) {
        mMap = map;
        new Thread(() -> {
            List<UniBranch> branches = branchDao.getAllBranches();
            Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                for (UniBranch branch:branches) {
                    LatLng branchMarker = new LatLng(branch.getLocation().getLatitude(), branch.getLocation().getLongitude());
                    mMap.addMarker(new MarkerOptions().position(branchMarker)
                                                      .title(branch.getName())
                                                      .snippet(utils.getFullAddress(branch.getAddress()))
                                                      .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                }
            });
        }).start();
        LatLng cameraCenter = new LatLng(49.742, 15.337);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraCenter, 6));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
