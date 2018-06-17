package cz.polreich.banks.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

import cz.polreich.banks.AppDatabase;
import cz.polreich.banks.R;
import cz.polreich.banks.activity.BranchActivity;
import cz.polreich.banks.dao.BranchDao;
import cz.polreich.banks.model.UniBranch;
import cz.polreich.banks.model.airBank.AirBankBranch;
import cz.polreich.banks.utils;

public class BranchesAdapter extends RecyclerView.Adapter<BranchesAdapter.BranchesViewHolder>{

    private final String DEBUG_TAG_INFO = "[INFO     ] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_ERROR = "[    ERROR] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_WARNING = "[ WARNING ] " + this.getClass().getSimpleName();
    private List<UniBranch> branchesList;
    private RecyclerView mRecyclerView;
    private Activity activity;
    private AppDatabase database;
    private BranchDao branchDao;

    public class BranchesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, address, phone, distance;
        public ImageView logo;

        public BranchesViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.branchList_name);
            address = view.findViewById(R.id.branchList_address);
            phone = view.findViewById(R.id.branchList_phone);
            logo = view.findViewById(R.id.branchList_bank_logo);
            distance = view.findViewById(R.id.branchList_distance);
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

    public BranchesAdapter(List<UniBranch> branchesList, RecyclerView mRecyclerView, Activity activity) {
        this.branchesList = branchesList;
        this.mRecyclerView = mRecyclerView;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BranchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.branch_list_row, parent, false);
/*        itemView.setOnClickListener(mOnClickListener);*/
        return new BranchesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BranchesViewHolder holder, int position) {
        UniBranch branch = branchesList.get(position);
        holder.name.setText(branch.getName());
        holder.address.setText(utils.getFullAddress(branch.getAddress()));
        holder.phone.setText(utils.getAllPhones(branch.getPhones()));
        if (branch.getBank().equals("Air Bank")) {
            holder.logo.setImageResource(R.drawable.ic_ab_circle);
        }
        if (branch.getDistance() != -1) {
            holder.distance.setText(utils.formatDistance(branch.getDistance()));
        } else {
            holder.distance.setText("N/A");
        }
    }

    @Override
    public int getItemCount() {
        return branchesList.size();
    }

    public void updateItems(List<UniBranch> branchesList) {
        this.branchesList = branchesList;
        notifyDataSetChanged();
    }

    public void updateItemsFromDB() {
        database = AppDatabase.getInstance(activity.getApplicationContext());
        branchDao = database.branchDao();
        new Thread(() -> {
            List<UniBranch> branches = branchDao.getAllBranchesByDistance();
            activity.runOnUiThread(() -> updateItems(branches));
        }).start();
    }

}
