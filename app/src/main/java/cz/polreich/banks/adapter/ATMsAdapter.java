package cz.polreich.banks.adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cz.polreich.banks.AppDatabase;
import cz.polreich.banks.R;
import cz.polreich.banks.activity.ATMActivity;
import cz.polreich.banks.dao.ATMDao;
import cz.polreich.banks.dao.BranchDao;
import cz.polreich.banks.model.UniATM;
import cz.polreich.banks.model.airBank.AirBankATM;
import cz.polreich.banks.utils;

public class ATMsAdapter extends RecyclerView.Adapter<ATMsAdapter.ATMsViewHolder> {

    private final String DEBUG_TAG_INFO = "[INFO     ] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_ERROR = "[    ERROR] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_WARNING = "[ WARNING ] " + this.getClass().getSimpleName();
    private List<UniATM> ATMsList;
    private RecyclerView mRecyclerView;
    private Activity activity;
    private AppDatabase database;
    private ATMDao atmDao;

    public class ATMsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, address, distance;
        public ImageView logo;

        public ATMsViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.atmsList_name);
            address = view.findViewById(R.id.atmsList_address);
            distance = view.findViewById(R.id.atmsList_distance);
            logo = view.findViewById(R.id.atmsList_bank_logo);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(DEBUG_TAG_INFO, "OnClick called");
            Log.d(DEBUG_TAG_INFO, "AdapterPosition: " + String.valueOf(mRecyclerView.getChildAdapterPosition(v)));
            Log.d(DEBUG_TAG_INFO, "LayoutPosition: " + String.valueOf(mRecyclerView.getChildLayoutPosition(v)));
            int itemPosition = mRecyclerView.getChildLayoutPosition(v);
            UniATM atm = ATMsList.get(itemPosition);
            String atmId = atm.getId();
            Log.d(DEBUG_TAG_INFO, "ATMId: " + atmId);
            ATMActivity.start(v.getContext(), atmId);
        }
    }

    public ATMsAdapter(List<UniATM> ATMsList, RecyclerView mRecyclerView, Activity activity) {
        this.ATMsList = ATMsList;
        this.mRecyclerView = mRecyclerView;
        this.activity = activity;
    }

    @Override
    public ATMsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.atm_list_row, parent, false);
        return new ATMsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ATMsViewHolder holder, int position) {
        UniATM atm = ATMsList.get(position);
        holder.name.setText(R.string.title_atm);
        holder.address.setText(utils.getFullAddress(atm.getAddress()));
        if (atm.getBank().equals("Air Bank")) {
            holder.logo.setBackgroundColor(ContextCompat.getColor(activity.getApplicationContext(),R.color.colorAirBankGreen));
            holder.logo.setImageResource(R.drawable.ic_ab_circle);
        }
        if (atm.getBank().equals("Ceska Sporitelna")) {
            holder.logo.setBackgroundColor(ContextCompat.getColor(activity.getApplicationContext(),R.color.colorErsteRed));
            holder.logo.setImageResource(R.drawable.ic_cs_circle);
        }
        if (atm.getDistance() != -1) {
            holder.distance.setText(utils.formatDistance(atm.getDistance()));
        } else {
            holder.distance.setText("N/A");
        }
    }

    @Override
    public int getItemCount() {
        return ATMsList.size();
    }

    public void updateItems(List<UniATM> ATMsList) {
        this.ATMsList = ATMsList;
        notifyDataSetChanged();
    }

    public void updateItemsFromDB() {
        database = AppDatabase.getInstance(activity.getApplicationContext());
        atmDao = database.atmDao();
        new Thread(() -> {
            List<UniATM> atms = atmDao.getAllATMsByDistance();
            activity.runOnUiThread(() -> updateItems(atms));
        }).start();
    }
}

