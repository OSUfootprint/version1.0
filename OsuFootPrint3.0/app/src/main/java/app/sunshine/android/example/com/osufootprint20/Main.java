package app.sunshine.android.example.com.osufootprint20;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Main extends ActionBarActivity {
    private final String TAG = ((Object) this).getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "++Main created++");
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"++Main started++");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"++Main resumed++");
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.Login_main:
                Intent intent_logmenu = new Intent("android.intent.action.LogActivity");
                startActivity(intent_logmenu);

                break;
            case R.id.new_user_button:
                Intent intent_new_user = new Intent("android.intent.action.NewAccountActivity");
                startActivity(intent_new_user);

                break;
            case R.id.map_test:
                Intent intent_map_test = new Intent("android.intent.action.FootprintMap");
                startActivity(intent_map_test);

                break;


        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        Log.e(TAG,"++Main paused++");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"++Main stopped++");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"++Main destroyed++");
    }
}
