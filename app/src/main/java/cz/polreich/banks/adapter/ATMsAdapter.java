package cz.polreich.banks.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cz.polreich.banks.R;
import cz.polreich.banks.activity.ATMActivity;
import cz.polreich.banks.model.airBank.ATM;
import cz.polreich.banks.utils;

public class ATMsAdapter extends RecyclerView.Adapter<ATMsAdapter.ATMsViewHolder> {

    private static final String DEBUG_TAG_INFO = "[INFO     ] ATMsAdapter";
    private static final String DEBUG_TAG_ERROR = "[    ERROR] ATMsAdapter";
    private List<ATM> ATMsList;
    private RecyclerView mRecyclerView;

    public class ATMsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, address;

        public ATMsViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.atmsList_name);
            address = (TextView) view.findViewById(R.id.atmsList_address);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(DEBUG_TAG_INFO, "OnClick called");
            Log.d(DEBUG_TAG_INFO, "AdapterPosition: " + String.valueOf(mRecyclerView.getChildAdapterPosition(v)));
            Log.d(DEBUG_TAG_INFO, "LayoutPosition: " + String.valueOf(mRecyclerView.getChildLayoutPosition(v)));
            int itemPosition = mRecyclerView.getChildLayoutPosition(v);
            ATM atm = ATMsList.get(itemPosition);
            String atmId = atm.getId();
            Log.d(DEBUG_TAG_INFO, "ATMId: " + atmId);
            ATMActivity.start(v.getContext(), atmId);
        }
    }

    public ATMsAdapter(List<ATM> ATMsList, RecyclerView mRecyclerView) {
        this.ATMsList = ATMsList;
        this.mRecyclerView = mRecyclerView;
    }

    @Override
    public ATMsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.atm_list_row, parent, false);
        return new ATMsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ATMsViewHolder holder, int position) {
        ATM atm = ATMsList.get(position);
        holder.name.setText(R.string.title_atm);
        holder.address.setText(utils.getFullAddress(atm.getAddress()));
    }

    @Override
    public int getItemCount() {
        return ATMsList.size();
    }

    public void updateItems(List<ATM> ATMsList) {
        this.ATMsList = ATMsList;
        notifyDataSetChanged();
    }
}

