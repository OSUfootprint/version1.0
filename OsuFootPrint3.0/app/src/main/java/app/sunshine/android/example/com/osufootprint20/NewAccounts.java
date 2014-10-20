package app.sunshine.android.example.com.osufootprint20;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import app.sunshine.android.example.com.osufootprint20.R;

public class NewAccounts extends ActionBarActivity {
    private final String TAG = ((Object) this).getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "++NewAccounts created++");
        setContentView(R.layout.activity_new_accounts);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"++NewAccounts started++");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"++NewAccounts resumed++");
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.create_user_button:
                Intent intent_create_user = new Intent("android.intent.action.LogActivity");
                startActivity(intent_create_user);
                break;
            case R.id.cancel_button:
                Intent intent_cancel_to_main = new Intent("android.intent.action.MAIN");
                startActivity(intent_cancel_to_main);
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_accounts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "++NewAccounts paused++");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"++NewAccounts stopped++");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"++NewAccounts destroyed++");
    }
}
