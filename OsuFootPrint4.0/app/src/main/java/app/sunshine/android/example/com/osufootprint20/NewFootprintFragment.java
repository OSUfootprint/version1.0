package app.sunshine.android.example.com.osufootprint20;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class NewFootprintFragment extends Fragment {

    private static final int REQUEST_DATE = 0;

    private Footprint fp;

    Button newDateField;
    Button newPlaceField;
    EditText newActivityField;
    EditText newCommentField;
    Button confirmButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fp=new Footprint();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_footprint, parent, false);
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
        newActivityField = (EditText)v.findViewById(R.id.new_footprint_activity);
        newCommentField = (EditText)v.findViewById(R.id.new_footprint_comment);
        confirmButton= (Button)v.findViewById(R.id.new_footprint_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
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
    }

}
