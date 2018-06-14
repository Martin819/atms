package cz.polreich.banks.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cz.polreich.banks.R;
import cz.polreich.banks.activity.BranchActivity;
import cz.polreich.banks.model.UniBranch;
import cz.polreich.banks.model.airBank.AirBankBranch;
import cz.polreich.banks.utils;

public class BranchesAdapter extends RecyclerView.Adapter<BranchesAdapter.BranchesViewHolder>{

    private final String DEBUG_TAG_INFO = "[INFO     ] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_ERROR = "[    ERROR] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_WARNING = "[ WARNING ] " + this.getClass().getSimpleName();
    private List<UniBranch> branchesList;
    private RecyclerView mRecyclerView;

    public class BranchesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, address, phone;

        public BranchesViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.branchList_name);
            address = (TextView) view.findViewById(R.id.branchList_address);
            phone = (TextView) view.findViewById(R.id.branchList_phone);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(DEBUG_TAG_INFO, "OnClick called");
            Log.d(DEBUG_TAG_INFO, "AdapterPosition: " + String.valueOf(mRecyclerView.getChildAdapterPosition(v)));
            Log.d(DEBUG_TAG_INFO, "LayoutPosition: " + String.valueOf(mRecyclerView.getChildLayoutPosition(v)));
            int itemPosition = mRecyclerView.getChildLayoutPosition(v);
            UniBranch branch = branchesList.get(itemPosition);
            String branchId = branch.getId();
            Log.d(DEBUG_TAG_INFO, "BranchId: " + branchId);
            BranchActivity.start(v.getContext(), branchId);
        }
    }

    public BranchesAdapter(List<UniBranch> branchesList, RecyclerView mRecyclerView) {
        this.branchesList = branchesList;
        this.mRecyclerView = mRecyclerView;
    }

    @Override
    public BranchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.branch_list_row, parent, false);
/*        itemView.setOnClickListener(mOnClickListener);*/
        return new BranchesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BranchesViewHolder holder, int position) {
        UniBranch branch = branchesList.get(position);
        holder.name.setText(branch.getName());
        holder.address.setText(utils.getFullAddress(branch.getAddress()));
        holder.phone.setText(utils.getAllPhones(branch.getPhones()));
    }

    @Override
    public int getItemCount() {
        return branchesList.size();
    }

    public void updateItems(List<UniBranch> branchesList) {
        this.branchesList = branchesList;
        notifyDataSetChanged();
    }

}
