package app.sunshine.android.example.com.osufootprint20;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class FootprintListFragment extends ListFragment {

    private PriorityQueue mySet;
    private ArrayList mFootprints;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.footprints_title);
        mySet=new PriorityQueue(FootprintQueue.get(getActivity()).getMySet());
        mFootprints=new ArrayList();
        while(mySet.size()>0) {
            Footprint fp= new Footprint();
            fp=(Footprint)mySet.poll();
            if(fp!=null) {
                mFootprints.add(fp);
            }
        }


        FootprintAdaptor adapter =new FootprintAdaptor(mFootprints);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Footprint fp = ((FootprintAdaptor)getListAdapter()).getItem(position);
        Intent footprintContent_intent=new Intent("android.intent.action.FootprintContent");
        footprintContent_intent.putExtra(FootprintContentFragment.FOOTPRINT_ID,fp.getID());
        startActivity(footprintContent_intent);
    }

    private class FootprintAdaptor extends ArrayAdapter<Footprint> {
        public FootprintAdaptor(ArrayList<Footprint> fp) {
            super(getActivity(), 0, fp);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("E MMM.d yyyy");


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_footprint, null);
            }

            Footprint c = getItem(position);
            TextView activityText=(TextView) convertView.findViewById(R.id.footprint_list_item_activity);
            activityText.setText(c.getActivity());
            TextView dateText=(TextView) convertView.findViewById(R.id.footprint_list_item_date);

            dateText.setText(sdf.format(c.getDate()));
            return convertView;
        }
    }

}
