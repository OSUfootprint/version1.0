package app.sunshine.android.example.com.osufootprint20;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class FootprintListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footprint_list);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.listContainer);
        if (fragment == null) {
            fragment = new FootprintListFragment();
            fm.beginTransaction()
                    .add(R.id.listContainer, fragment)
                    .commit();
        }
    }

}
