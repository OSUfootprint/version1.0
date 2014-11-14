package app.sunshine.android.example.com.osufootprint20;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class Main extends ActionBarActivity {
    private final String TAG = ((Object) this).getClass().getSimpleName();
    private EditText userNamefield;
    private EditText passwordfield;
    private DatabaseHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "++Main created++");
        setContentView(R.layout.activity_main);

        userNamefield = (EditText) findViewById(R.id.new_username);
        passwordfield = (EditText) findViewById(R.id.new_password);
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

    private void checkLogin(){
        String username = this.userNamefield.getText().toString();
        String password = this.passwordfield.getText().toString();
        this.dh = new DatabaseHelper(this);
        List<String> names = this.dh.selectAll(username, password);
        if(names.size() > 0){

            Intent intent = new Intent(Main.this, LoginActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("username",username);
//            ArrayList footprint_list = new ArrayList();
//            ArrayList wish_list = new ArrayList();
//            footprint_list.add(this.dh.queryOne(username).show_myfoorprints());
//            wish_list.add(this.dh.queryOne(username).show_mywishlists());
//            bundle.putParcelableArrayList("foorprint_list",footprint_list);
//            bundle.putParcelableArrayList("wish_list",wish_list);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            // Try again?
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Login failed")
                    .setNeutralButton("Try Again",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            }).show();
        }

    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.Login_main:
//                Intent intent_logmenu = new Intent("android.intent.action.LogActivity");
//                startActivity(intent_logmenu);
                checkLogin();
                break;
            case R.id.new_user_button:
                Intent intent_new_user = new Intent("android.intent.action.NewAccountActivity");
                startActivity(intent_new_user);

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
