package app.sunshine.android.example.com.osufootprint20;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


/**
 * This shows how to place markers on a map.
 */
public class
        FootprintMap extends FragmentActivity
        implements
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener {
    private static MyPlaceQueue pq;
    private static final LatLng DREASE = new LatLng(40.002257134, -83.015840873);
    private static final LatLng THOMPSON = new LatLng(39.999223928, -83.0149433389306);
    private static final LatLng SHOE = new LatLng(40.002473382, -83.019639887);
    private static final LatLng TUTTLE = new LatLng(40.012186920, -83.01550258);
    private static final LatLng MEDICAL = new LatLng(40.00099584, -83.03252656);

    /** Demonstrates customizing the info window and/or its contents. */
    private void init() {
        pq = new MyPlaceQueue();
        MyPlace pDREASE = new MyPlace("Drease Lab",DREASE,20);
        pq.insert(pDREASE);
        MyPlace pTHOMPSON = new MyPlace("Thompson Library",THOMPSON,15);
        pq.insert(pTHOMPSON);
        MyPlace pSHOE = new MyPlace("OSU Stadium",SHOE,2);
        pq.insert(pSHOE);
        MyPlace pTUTTLE = new MyPlace("Tuttle Park",TUTTLE,2);
        pq.insert(pTUTTLE);
        MyPlace pMEDICAL = new MyPlace("OSU Medical Center", MEDICAL,1);
        pq.insert(pMEDICAL);
    }

    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        // These a both viewgroups containing an ImageView with id "badge" and two TextViews with id
        // "title" and "snippet".
        private final View mWindow;

        CustomInfoWindowAdapter() {
            mWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            render(marker, mWindow);
            return mWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }

        private void render(Marker marker, View view) {
            int badge, i = 0;
            // Use the equals() method on a Marker to check for equals.  Do not use ==.
            if (marker.equals(mMarker.get(0))) {
                badge = R.drawable.badge_qld;
            } else if (marker.equals(mMarker.get(1))) {
                badge = R.drawable.badge_sa;
            } else if (marker.equals(mMarker.get(2))) {
                badge = R.drawable.badge_nsw;
            } else if (marker.equals(mMarker.get(3))) {
                badge = R.drawable.badge_victoria;
            } else if (marker.equals(mMarker.get(4))) {
                badge = R.drawable.badge_wa;
            } else {
                // Passing 0 to setImageResource will clear the image view.
                badge = 0;
            }
                ((ImageView) view.findViewById(R.id.badge)).setImageResource(badge);

                String title = marker.getTitle();
                TextView titleUi = ((TextView) view.findViewById(R.id.title));
                if (title != null) {
                    // Spannable string allows us to edit the formatting of the text.
                    SpannableString titleText = new SpannableString(title);
                    titleText.setSpan(new ForegroundColorSpan(Color.RED), 0, titleText.length(), 0);
                    titleUi.setText(titleText);
                } else {
                    titleUi.setText("");
                }

                String snippet = marker.getSnippet();
                TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
                if (snippet != null) {
                    SpannableString snippetText = new SpannableString(snippet);
                    snippetText.setSpan(new ForegroundColorSpan(Color.BLUE), 0, snippet.length(), 0);
                    snippetUi.setText(snippetText);
                } else {
                    snippetUi.setText("");
                }
            }
    }

    private GoogleMap mMap;
    private ArrayList<Marker> mMarker;
    private Button newFootprintButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footprint_map);
        newFootprintButton=(Button)findViewById(R.id.new_footprint_button);
        newFootprintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_new_footprint = new Intent("android.intent.action.NewFootprint");
                startActivity(intent_new_footprint);
            }
        });

        init();
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        // Hide the zoom controls as the button panel will cover it.
        mMap.getUiSettings().setZoomControlsEnabled(false);

        // Add lots of markers to the map.


        addMarkersToMap();

        // Setting an info window adapter allows us to change the both the contents and look of the
        // info window.
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

        // Set listeners for marker events.  See the bottom of this class for their behavior.
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.0069194,-83.0266532), 13.7f));
    }

    private void addMarkersToMap() {
        mMarker=new ArrayList<Marker>();
        int i=0,last=0,length=pq.size();
        float rate=0;
        while(i<length) {
            MyPlace mp = new MyPlace();
            mp = pq.getNext();
            if(mp.getTimes()!=last) {
                rate = (float) (i + 1) / (float) (length+1);
                last = mp.getTimes();
            }
            if(rate<=0.2) {
                Marker m = mMap.addMarker(new MarkerOptions()
                        .position(mp.getLocation())
                        .title(mp.getName())
                        .snippet("Have "+mp.getTimes()+" Footprints")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                mMarker.add(m);
            }
            else if(rate<=0.4) {
                Marker m = mMap.addMarker(new MarkerOptions()
                        .position(mp.getLocation())
                        .title(mp.getName())
                        .snippet("Have "+mp.getTimes()+" Footprints")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                mMarker.add(m);
            }
            else if(rate<=0.6) {
                Marker m = mMap.addMarker(new MarkerOptions()
                        .position(mp.getLocation())
                        .title(mp.getName())
                        .snippet("Have "+mp.getTimes()+" Footprints")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                mMarker.add(m);
            }
            else if(rate<=0.8) {
                Marker m = mMap.addMarker(new MarkerOptions()
                        .position(mp.getLocation())
                        .title(mp.getName())
                        .snippet("Have "+mp.getTimes()+" Footprints")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
                mMarker.add(m);
            }
            else {
                Marker m = mMap.addMarker(new MarkerOptions()
                        .position(mp.getLocation())
                        .title(mp.getName())
                        .snippet("Have "+mp.getTimes()+" Footprints")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                mMarker.add(m);
            }
            i++;
        }
    }

    //
    // Marker related listeners.
    //

    @Override
    public boolean onMarkerClick(final Marker marker) {
            final Handler handler = new Handler();
            final long start = SystemClock.uptimeMillis();
            final long duration = 1500;

            final Interpolator interpolator = new BounceInterpolator();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    long elapsed = SystemClock.uptimeMillis() - start;
                    float t = Math.max(1 - interpolator
                            .getInterpolation((float) elapsed / duration), 0);
                    marker.setAnchor(0.5f, 1.0f + 2 * t);

                    if (t > 0.0) {
                        // Post again 16ms later.
                        handler.postDelayed(this, 16);
                    }
                }
            });
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent_footprint_list = new Intent("android.intent.action.FootprintList");
        startActivity(intent_footprint_list);
    }

}