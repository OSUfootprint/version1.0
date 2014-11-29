package app.sunshine.android.example.com.osufootprint20;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class NewFootprintFragment extends Fragment {

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_PLACE = 1;
    private static final int REQUEST_PHOTO = 2;
    public static final String DEFAULT_PLACE ="app.sunshine.android.example.com.osufootprint20.default_place";

    private Footprint fp;
    private DatabaseHelper dh;

    Button newDateField;
    Button newPlaceField;
    EditText newActivityField;
    EditText newCommentField;
    Button confirmButton;
    Button cameraButton;
    ImageView newPhotoField;


    public static NewFootprintFragment newInstance(String PlaceName) {
        Bundle args = new Bundle();
        args.putString(SelectPlace.Selected_Place, PlaceName);
        NewFootprintFragment fragment = new NewFootprintFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fp=new Footprint();

        String place=getArguments().getString(SelectPlace.Selected_Place);
        if(place!=null)
            fp.setPlace(place);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_footprint, parent, false);
        this.dh = new DatabaseHelper(this.getActivity());
        newDateField = (Button)v.findViewById(R.id.new_footprint_date);
        newDateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(fp.getDate());
                dialog.setTargetFragment(NewFootprintFragment.this, REQUEST_DATE);
                dialog.show(fm, "date");
            }
        });
        SimpleDateFormat sdf = new SimpleDateFormat("E MMM.d yyyy");
        newDateField.setText(sdf.format(fp.getDate()));
        newPlaceField = (Button)v.findViewById(R.id.new_footprint_place);
        newPlaceField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_select_place=new Intent("android.intent.action.SelectPlace");
                startActivityForResult(intent_select_place, REQUEST_PLACE);
            }
        });
        if(fp.getPlace()!=null) {
            newPlaceField.setText(fp.getPlace());
        }
        newActivityField = (EditText)v.findViewById(R.id.new_footprint_activity);
        newCommentField = (EditText)v.findViewById(R.id.new_footprint_comment);
        newPhotoField=(ImageView)v.findViewById(R.id.new_footprint_photo);
        cameraButton = (Button) v.findViewById(R.id.camera);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent take_picture=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(take_picture, REQUEST_PHOTO);
            }
        });
        confirmButton= (Button)v.findViewById(R.id.new_footprint_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fp.setActivity(newActivityField.getText().toString());
                fp.setComment(newCommentField.getText().toString());
                if(fp.getPlace()==null) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Error")
                            .setMessage("Please Set the Place")
                            .setNeutralButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                        }
                                    }).show();
                }
                else if(fp.getActivity()==null) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Error")
                            .setMessage("Please Type in Your Activity")
                            .setNeutralButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                        }
                                    }).show();
                }
                else {
                    Person.getPerson(getActivity().getApplicationContext()).getFootprint().insert(fp);
                    Person.getPerson(getActivity().getApplicationContext()).getMyPlace().TimesChanged(fp.getPlace());
                    dh.setFootprints();
                    dh.setPlaces();
                    Intent result=new Intent();
                    getActivity().setResult(getActivity().RESULT_OK,result);
                    getActivity().finish();
                }
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date)data
                    .getSerializableExtra(DatePickerFragment.CURRENT_DATE);
            fp.setDate(date);
            SimpleDateFormat sdf = new SimpleDateFormat("E MMM.d yyyy");
            newDateField.setText(sdf.format(fp.getDate()));

        }
        else if (requestCode == REQUEST_PLACE) {
            String place = data
                    .getStringExtra(SelectPlace.Selected_Place);
            fp.setPlace(place);
            newPlaceField.setText(fp.getPlace());
        }
        else if (requestCode == REQUEST_PHOTO) {
            Bitmap photo=(Bitmap)data.getExtras().get("data");
            if(photo!=null) {
                fp.setPhoto(photo);
                newPhotoField.setImageBitmap(photo);
                cameraButton.setText("Retake");
            }
        }

    }

}
