package app.sunshine.android.example.com.osufootprint20;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WishlistFragment extends ListFragment {

    private static final int NEW_WISH = 2;
    private MyPlace newWish;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        WishAdaptor adapter =new WishAdaptor(Person.getPerson(getActivity().getApplicationContext()).getWish().getMySet());
        setListAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((WishAdaptor)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        setMenuEnable(menu,true);
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.wishlist, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_new_wish:
                Intent intent_select_place=new Intent("android.intent.action.SelectPlace");
                startActivityForResult(intent_select_place, NEW_WISH);
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void setMenuEnable(Menu menu, boolean enable)
    {
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);

            m.invoke(menu, enable);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        MyPlace mp = ((WishAdaptor)getListAdapter()).getItem(position);
        Intent wish_map_intent=new Intent("android.intent.action.WishMap");
        wish_map_intent.putExtra(WishMap.PLACE_NAME,mp.getName());
        startActivity(wish_map_intent);
    }

    private class WishAdaptor extends ArrayAdapter<MyPlace> {
        public WishAdaptor(ArrayList<MyPlace> wl) {
            super(getActivity(), 0, wl);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_wish, null);
            }

            MyPlace c = getItem(position);
            TextView activityText=(TextView) convertView.findViewById(R.id.wish_list_item_place);
            activityText.setText(c.getName());
            return convertView;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        else if (requestCode == NEW_WISH) {
            String placeName = data
                    .getStringExtra(SelectPlace.Selected_Place);
            if(Person.getPerson(getActivity().getApplicationContext()).getWish().getByName(placeName)==null) {
                newWish = new MyPlace(placeName, PlaceInfoQueue.get(getActivity().getApplicationContext()).findLocByName(placeName));
                Person.getPerson(getActivity().getApplicationContext()).getWish().insert(newWish);
                DatabaseHelper dh=new DatabaseHelper(getActivity());
                dh.setWishlists();
            }
            else {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Error")
                        .setMessage("This Place Is In Your WishList")
                        .setPositiveButton("Choose Another",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        Intent intent_select_place = new Intent("android.intent.action.SelectPlace");
                                        startActivityForResult(intent_select_place, NEW_WISH);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }
                                }).show();
            }
        }
    }



}
