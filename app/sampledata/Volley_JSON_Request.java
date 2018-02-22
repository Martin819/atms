import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cz.polreich.atms.R;

/**
 * Created by Martin on 21.02.2018.
 */

public class Volley_JSON_Request {

    final TextView mTextView = (TextView) findViewById(R.id.response);
    RequestQueue queue = Volley.newRequestQueue(this);
    String url ="https://api.airbank.cz/openapi/public/v1/branches/own";
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            mTextView.setText("Response: " + response.toString());
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mTextView.setText("That didn't work!");
        }
    }) {
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("apikey", "d0ffe291af21401681f1539365d15cb7");
            return headers;
        }
    };

        queue.add(jsonObjectRequest);

}
