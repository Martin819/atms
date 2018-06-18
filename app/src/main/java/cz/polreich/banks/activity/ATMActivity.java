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
import cz.polreich.banks.controller.ErsteController;

public class ATMActivity extends AppCompatActivity {

    private static final String ATM_ID = "ATMID";
    private static final String BANK = "bank";
    private final String DEBUG_TAG_INFO = "[INFO     ] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_ERROR = "[    ERROR] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_WARNING = "[ WARNING ] " + this.getClass().getSimpleName();
    public static void start(Context context, String ATMId, String bank) {
        Intent intent = new Intent(context, ATMActivity.class);
        intent.putExtra(ATM_ID, ATMId);
        intent.putExtra(BANK, bank);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm);
        String airbank_apikey = getResources().getString(R.string.airbank_apikey);
        String erste_apikey = getResources().getString(R.string.erste_apikey);
        Toolbar toolbar = (Toolbar) findViewById(R.id.branch_atm_toolbar);
        setSupportActionBar(toolbar);
        Intent intent = this.getIntent();
        String ATMId = intent.getStringExtra(ATM_ID);
        String bank = intent.getStringExtra(BANK);
        if (ATMId.isEmpty()){
            Log.e(DEBUG_TAG_ERROR, "ATMId is empty");
        } else {
            Log.d(DEBUG_TAG_INFO, "ATMId: " + ATMId);
        }
        if (bank.equals("Ceska Sporitelna")) {
            ErsteController ersteController = new ErsteController(this);
            ersteController.getATM(erste_apikey, ATMId);
        }
        if (bank.equals("Air Bank")) {
            AirBankController controller = new AirBankController(this);
            controller.getATM(airbank_apikey, ATMId);
        }
    }

}
