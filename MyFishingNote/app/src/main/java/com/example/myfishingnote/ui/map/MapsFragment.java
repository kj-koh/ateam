package com.example.myfishingnote.ui.map;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myfishingnote.MainActivity;
import com.example.myfishingnote.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import static com.example.myfishingnote.MainActivity.location;

public class MapsFragment extends Fragment {



   /* @Override
    public void onDestroyView() {
        //프래그먼트 종료시 리스너 삭제
        //manager.removeUpdates(listener);
        super.onDestroyView();
    }*/

    private static final String TAG = "MapsFragment";

    //SupportMapFragment mapFragment;
    GoogleMap map;
    MarkerOptions myMarker;
    MainActivity activity ;
    LocationManager manager;
    private Context context;


    //LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //maps프래그먼트를 home프래그먼트에 붙여 초기에 보일때 context에 에러가 생김
        //context = container.getContext();
        context = getContext();





        return inflater.inflate(R.layout.fragment_maps, container, false);

    }//onCreateView()

    //onViewCreated는 onCreateView와의 혼동을 막기위해 나는 잠시 접어둔다.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);

        }//if
    }//onViewCreated()

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            activity = (MainActivity) getActivity();

            /*LatLng sydney = new LatLng(-34, 151);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
            Log.d(TAG, "onMapReady: 지도 준비 완료");
            map = googleMap;
            //map.setMyLocationEnabled(true);

            startLocationService(context);




        }//onMapReady()
    };

    private void showCurrentLocation(Location location) {
        LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());
        String msg = "\n위도 : " + curPoint.latitude + "\n경도 : " + curPoint.longitude;

        Log.d(TAG, "showCurrentLocation: " + msg);

        //Toast.makeText(this, "msg", Toast.LENGTH_SHORT).show();

        //지도 초기위치와 크기
        //map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 8));


        showMyLocationMarker(location);
        //showMyListMarker(location);

        //마커찍기
       /* Location markerLocation = new Location("");
        markerLocation.setLatitude(35.153817);
        markerLocation.setLongitude(126.8889);
        showMyLocationMarker(markerLocation);*/

    }//showCurrentLocation()

    //내위치
    private void showMyLocationMarker(Location location) {
        Geocoder geocoder = new Geocoder(context);

        //내위치 주소변환
        try {
            List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (myMarker != null) map.clear();
            myMarker = new MarkerOptions();
            myMarker.position(new LatLng(location.getLatitude(), location.getLongitude()));
            myMarker.title("♥내 위치♥");
            myMarker.snippet(list.get(0).getAddressLine(0));
            myMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_red));
            map.addMarker(myMarker);

            myMarker = new MarkerOptions();
            myMarker.position(new LatLng(34.7538, 127.5800));
            myMarker.title("2019-12-30 PM 06:30");
            myMarker.snippet(list.get(0).getAddressLine(0));
            myMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_blue));
            map.addMarker(myMarker);

            myMarker = new MarkerOptions();
            myMarker.position(new LatLng(34.6974, 127.2642));
            myMarker.title("2020-01-03 PM 08:15");
            myMarker.snippet(list.get(0).getAddressLine(0));
            myMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_blue));
            map.addMarker(myMarker);

            myMarker = new MarkerOptions();
            myMarker.position(new LatLng(34.8467, 126.3561));
            myMarker.title("2020-04-28 PM 11:30");
            myMarker.snippet(list.get(0).getAddressLine(0));
            myMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_blue));
            map.addMarker(myMarker);

            myMarker = new MarkerOptions();
            myMarker.position(new LatLng(34.4546, 126.3778));
            myMarker.title("2020-06-06 AM 11:45");
            myMarker.snippet(list.get(0).getAddressLine(0));
            myMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_blue));
            map.addMarker(myMarker);


        } catch (IOException e) {
            e.printStackTrace();
        }//try&catch

    }//showMyLocationMaker()

    //내 글위치
    private void showMyListMarker(Location location) {
        Geocoder geocoder = new Geocoder(context);

        //내위치 주소변환
        try {
            List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (myMarker != null) map.clear();
            myMarker = new MarkerOptions();
            myMarker.position(new LatLng(34.7538, 127.5800));
            myMarker.title("2019-12-30 PM 06:30");
            myMarker.snippet(list.get(0).getAddressLine(0));
            myMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_marker_50px));
            map.addMarker(myMarker);

            myMarker = new MarkerOptions();
            myMarker.position(new LatLng(34.6974, 127.2642));
            myMarker.title("2020-01-03 PM 08:15");
            myMarker.snippet(list.get(0).getAddressLine(0));
            myMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_marker_50px));
            map.addMarker(myMarker);
        } catch (IOException e) {
            e.printStackTrace();
        }//try&catch
    }//showMyListMarker()

    private void startLocationService(Context context) {
        //위치관리자 객체 참조
        //위에 선언
        manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        //위치정보를 받을 리스너 생성
        GPSListener gpsListener = new GPSListener();
        //단위 : 밀리세컨드?
        long minTime = 1000 * 60 * 60;
        //단위 : Meter
        float minDistance = 10;

        try{
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, gpsListener);

            location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location != null) {
                Double latitude = location.getLatitude();
                Double longgitude = location.getLongitude();




                String msg = "Latitude : " + latitude + "\nLongitude : " + longgitude;

                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }//if


        } catch (SecurityException e) {
            e.getMessage();
        }//try&catch
    }//startLocationService()

    private class GPSListener implements LocationListener {
        @Override
        public void onLocationChanged(@NonNull Location location) {

            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String msg = "New Latitude : " + latitude + "\nNew Longitude : " + longitude;

            showCurrentLocation(location);

            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();


            //showCurrentLocation(location);
            //showMyLocationMarker(location);
            //startLocationService(context);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }//GPSListener()





}//class