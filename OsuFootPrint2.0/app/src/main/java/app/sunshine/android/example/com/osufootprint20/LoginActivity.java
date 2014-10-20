package app.sunshine.android.example.com.osufootprint20;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import app.sunshine.android.example.com.osufootprint20.R;

public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.My_wishlist:
                Intent intent_wishlist = new Intent("android.intent.action.MywishlistActivity");
                startActivity(intent_wishlist);
                break;
            case R.id.my_footprints:
                Intent intent_myfootprints = new Intent("android.intent.action.MyfootprintActivity");
                startActivity(intent_myfootprints);
                break;
            case R.id.pop_footprints:
                Intent intent_popfootprints = new Intent("android.intent.action.PopfootprintActivity");
                startActivity(intent_popfootprints);
                break;
            case R.id.log_out:
                Intent intent_menu = new Intent("android.intent.action.MAIN");
                startActivity(intent_menu);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
}
