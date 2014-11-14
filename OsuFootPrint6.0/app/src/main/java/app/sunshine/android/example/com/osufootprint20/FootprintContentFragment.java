package app.sunshine.android.example.com.osufootprint20;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.UUID;


public class FootprintContentFragment extends Fragment {

    public static final String FOOTPRINT_ID ="app.sunshine.android.example.com.osufootprint20.footprint_id";

    private Footprint fp;

    TextView mDateField;
    TextView mPlaceField;
    TextView mActivityField;
    TextView mCommentField;


    public static FootprintContentFragment newInstance(UUID footprintId) {
        Bundle args = new Bundle();
        args.putSerializable(FOOTPRINT_ID, footprintId);
        FootprintContentFragment fragment = new FootprintContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID footprintID = (UUID)getArguments().getSerializable(FOOTPRINT_ID);
        fp=Person.getPerson(getActivity().getApplicationContext()).getFootprint().getByID(footprintID);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_footprint_content, parent, false);
        mDateField = (TextView)v.findViewById(R.id.footprint_content_date);
        SimpleDateFormat sdf = new SimpleDateFormat("E MMM.d yyyy");
        mDateField.setText(sdf.format(fp.getDate()));
        mPlaceField = (TextView)v.findViewById(R.id.footprint_content_place);
        mPlaceField.setText(fp.getPlace());
        mActivityField = (TextView)v.findViewById(R.id.footprint_content_activity);
        mActivityField.setText(fp.getActivity());
        mCommentField = (TextView)v.findViewById(R.id.footprint_content_comment);
        mCommentField.setText(fp.getComment());
        return v;
    }
}
