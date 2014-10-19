package app.sunshine.android.example.com.osufootprint;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by Vincent on 14/10/18.
 */
public class LogActivity extends ActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.My_wishlist:
                Intent intent_wishlist = new Intent("com.litreily.WishlistActivity");
                startActivity(intent_wishlist);
                break;
            case R.id.my_footprints:
                Intent intent_myfootprints = new Intent("com.litreily.Myfootprints");
                startActivity(intent_myfootprints);
                break;
            case R.id.pop_footprints:
                Intent intent_popfootprints = new Intent("com.litreily.PopActivity");
                startActivity(intent_popfootprints);
                break;
        }
    }
}
