package app.sunshine.android.example.com.osufootprint20;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Method;


public class WishlistActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.listContainer);
        if (fragment == null) {
            fragment=new WishlistFragment();
            fm.beginTransaction()
                    .add(R.id.listContainer, fragment)
                    .commit();
        }
    }



}
