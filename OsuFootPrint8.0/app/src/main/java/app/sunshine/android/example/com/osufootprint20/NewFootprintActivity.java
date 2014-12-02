package app.sunshine.android.example.com.osufootprint20;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.UUID;


public class NewFootprintActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_footprint_content);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.editorContainer);
        if (fragment == null) {
            String placeName = getIntent().getStringExtra(NewFootprintFragment.DEFAULT_PLACE );
            fragment=NewFootprintFragment.newInstance(placeName);
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }

}
