package cz.polreich.banks.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import cz.polreich.banks.R;
import cz.polreich.banks.controller.AirBankController;

public class ATMActivity extends AppCompatActivity {

    private static final String ATM_ID = "ATMID";
    private final String DEBUG_TAG_INFO = "[INFO     ] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_ERROR = "[    ERROR] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_WARNING = "[ WARNING ] " + this.getClass().getSimpleName();
    public static void start(Context context, String ATMId) {
        Intent intent = new Intent(context, ATMActivity.class);
        intent.putExtra(ATM_ID, ATMId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm);
        String airbank_apikey = getResources().getString(R.string.airbank_apikey);
        Toolbar toolbar = (Toolbar) findViewById(R.id.branch_atm_toolbar);
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
        String ATMId = intent.getStringExtra(ATM_ID);
        if (ATMId.isEmpty()){
            Log.e(DEBUG_TAG_ERROR, "ATMId is empty");
        } else {
            Log.d(DEBUG_TAG_INFO, "ATMId: " + ATMId);
        }

        AirBankController controller = new AirBankController(this);
        controller.getATM(airbank_apikey, ATMId);
    }

}
