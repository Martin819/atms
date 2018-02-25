package cz.polreich.atms;

/**
 * Created by Martin on 25.02.2018.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cz.polreich.atms.model.airBank.Branch;

public class BranchesAdapter extends RecyclerView.Adapter<BranchesAdapter.BranchesViewHolder>{

    private List<Branch> branchesList;

    public class BranchesViewHolder extends RecyclerView.ViewHolder {
        public TextView name, address, phone;

        public BranchesViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.branch_name);
            address = (TextView) view.findViewById(R.id.branch_address);
            phone = (TextView) view.findViewById(R.id.branch_phone);
        }
    }

    public BranchesAdapter(List<Branch> branchesList) {
        this.branchesList = branchesList;
    }

    @Override
    public BranchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.branch_list_row, parent, false);

        return new BranchesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BranchesViewHolder holder, int position) {
        Branch branch = branchesList.get(position);
        holder.name.setText(branch.getName());
        holder.address.setText(utils.getFullAddress(branch.getAddress()));
        holder.phone.setText(utils.getAllPhones(branch.getPhones()));
    }

    @Override
    public int getItemCount() {
        return branchesList.size();
    }

    public void updateItems(List<Branch> branchesList) {
        this.branchesList = branchesList;
        notifyDataSetChanged();
    }

}
