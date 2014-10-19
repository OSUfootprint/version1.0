package app.sunshine.android.example.com.osufootprint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by Vincent on 14/10/18.
 */
public class WishlistActivity extends ActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlist);
    }

    public void onClick(View view){
        switch (view.getId()){
//            case R.id.My_wishlist:
//                Intent intent_wishlist = new Intent("com.litreily.WishlistActivity");
//                startActivity(intent_wishlist);
//            case R.id.my_footprints:
//                Intent intent_myfootprints = new Intent("com.litreily.Myfootprints");
//                startActivity(intent_myfootprints);
            case R.id.back_wishlist:
                Intent intent_logmenu = new Intent("com.litreily.LogActivity");
                startActivity(intent_logmenu);
        }
    }
}
