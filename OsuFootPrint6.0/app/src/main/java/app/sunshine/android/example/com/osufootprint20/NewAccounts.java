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
import android.widget.Toast;

import java.util.ArrayList;

import app.sunshine.android.example.com.osufootprint20.R;

public class NewAccounts extends ActionBarActivity {
    private final String TAG = ((Object) this).getClass().getSimpleName();
    private EditText newUsername;
    private EditText newPassword;
    private EditText passwordConfirm;
    private DatabaseHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.e(TAG, "++NewAccounts created++");
        setContentView(R.layout.activity_new_accounts);
        newUsername = (EditText) findViewById(R.id.new_username);
        newPassword = (EditText) findViewById(R.id.new_password);
        passwordConfirm = (EditText) findViewById(R.id.confirm_password);

    }

    private void CreateAccount(){
        String username = newUsername.getText().toString();
        String password = newPassword.getText().toString();
        String confirm = passwordConfirm.getText().toString();
        if ((password.equals(confirm)) && (!username.equals(""))
                && (!password.equals("")) && (!confirm.equals(""))) {
            this.dh = new DatabaseHelper(this);
            Person person = new Person(this.getApplicationContext(),username,password);
            dh.add(person);
            // this.labResult.setText("Added");
            Toast.makeText(NewAccounts.this, "new record inserted",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(NewAccounts.this, LoginActivity.class);
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
            finish();
        } else if ((username.equals("")) || (password.equals(""))
                || (confirm.equals(""))) {
            Toast.makeText(NewAccounts.this, "Missing entry", Toast.LENGTH_SHORT)
                    .show();
        } else if (!password.equals(confirm)) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("passwords do not match")
                    .setNeutralButton("Try Again",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            })

                    .show();
        }
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
                CreateAccount();
                finish();
                break;
            case R.id.cancel_button:
                finish();
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
