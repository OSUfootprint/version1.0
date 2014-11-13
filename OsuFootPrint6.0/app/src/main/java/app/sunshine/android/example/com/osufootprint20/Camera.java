package app.sunshine.android.example.com.osufootprint20;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class Camera extends ActionBarActivity {
    private
    ImageView imageView=null;
    public static int IMAGE_CAPTURED = 1;
    static Uri imageFileURI=null;
    String imageFilePath="/mnt/sdcard/SampleImage.jpg";
    Bitmap imageBitmap=null;
    private DatabaseHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageBitmap = BitmapFactory.decodeFile(imageFilePath);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.buttonImageCapture:
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, IMAGE_CAPTURED);
                break;
//            case R.id.buttonImagereCapture:
//                Intent recameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(recameraIntent, IMAGE_CAPTURED);
//                break;
            case R.id.camera_confirm:
                finish();
                break;

        }
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent cameraIntent) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_CAPTURED) {
            Bundle extras = cameraIntent.getExtras() ;
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
//            this.dh = new DatabaseHelper(this);
//            dh.savePhotp(username,imageBitmap);
            // imageBitmap=null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.camera, menu);
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
