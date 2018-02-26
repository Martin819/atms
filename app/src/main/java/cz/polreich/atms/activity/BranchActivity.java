package cz.polreich.atms.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import cz.polreich.atms.R;
import cz.polreich.atms.controller.BranchController;
import cz.polreich.atms.model.airBank.Branch;
import cz.polreich.atms.utils;

public class BranchActivity extends AppCompatActivity {

    private static final String BRANCH_ID = "branchID";
    private static final String DEBUG_TAG_INFO = "[INFO     ] BranchesAdapter";
    private static final String DEBUG_TAG_ERROR = "[    ERROR] BranchesAdapter";
    private TextView mBranchName;
    private TextView mBranchAddress;
    private TextView mBranchPhone;
    private String[] mBranchPhoneLinks;

    public static void start(Context context, String branchId) {
        Log.d(DEBUG_TAG_INFO, "BranchActivity.getBranchesList called");
        Intent intent = new Intent(context, BranchActivity.class);
        intent.putExtra(BRANCH_ID, branchId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(DEBUG_TAG_INFO, "BranchActivity.onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        String airbank_apikey = getResources().getString(R.string.airbank_apikey);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = this.getIntent();
        String branchId = intent.getStringExtra(BRANCH_ID);
        if (branchId.isEmpty()){
            Log.e(DEBUG_TAG_ERROR, "branchId is empty");
        } else {
            Log.d(DEBUG_TAG_INFO, "branchId: " + branchId);
        }

        BranchController branchController = new BranchController(this);
        branchController.getBranch(airbank_apikey, branchId);
/*        Log.d(DEBUG_TAG_INFO, branch.toString());
        mBranchName = (TextView) findViewById(R.id.branch_name);
        mBranchPhone = (TextView) findViewById(R.id.branch_phone);
        mBranchName.setText(branch.getName());
        mBranchPhone.setText(utils.getAllPhones(branch.getPhones()));*/

    }

}
