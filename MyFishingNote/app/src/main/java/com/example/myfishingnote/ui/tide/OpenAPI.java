package com.example.myfishingnote.ui.tide;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import com.example.myfishingnote.MainActivity;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OpenAPI extends AsyncTask<String, Void, String> {
    private static final String TAG = "OpenAPI";
    private Context context;



    private LatLng mylatLng;
    ObsDTO dto = new ObsDTO();
    private AssetManager assetManager;


    public OpenAPI(LatLng latLng, Context context) {
        this.mylatLng = latLng;
        this.context = context;
    }//생성자

    @Override
    protected String doInBackground(String... strUrls) {

        //현재 시간을 받아온다
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String Today = sdf.format(date);
        sdf = new SimpleDateFormat("hhmmss");
        String Time = sdf.format(date);

        //서비스키
        String ServiceKey = "T7qAa8L36BfkMpmavM9hsw==";

        //관측소 위치의 json파일을 불러온다.
        String obsFileName = "tideObsRecent.json";
        assetManager = ((MainActivity)context).getAssets();

        JSONArray obsStationList = new JSONArray();
        obsStationList = getObsStationList(mylatLng, obsFileName );




        //가까운 관측소의 위치를 찾는다.

        try {

            //조위관측 최신데이터
            String DataType = "tideObsRecent";
            String ObsCode = "DT_0001";     //임시값 인천
            //String ObsCode = findObsCode(mylatLng, );     //임시값 인천

            String strUrl = "http://www.khoa.go.kr/oceangrid/grid/api/" +
                    DataType + "/search.do?ServiceKey=" +
                    ServiceKey +"&ObsCode=" + ObsCode + "+&Date="+Today+"&ResultType=json";


            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuffer builder = new StringBuffer();

            String inputString = null;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            String data = builder.toString();

            // Gson gson = new Gson();
            // DTO dto = gson.fromJson( 스트링, DTO.class );
            // DTO[] dtos = gson.fromJson( 스트링, DTO[].class );
            // List<DTO> list = Arrays.asList(dtos);
            // ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(arr));

            Log.d(TAG, "Async: " + data);



            conn.disconnect();
            bufferedReader.close();
            return data;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }//doinbackgound()

    private JSONArray getObsStationList(LatLng mylatLng, String obsFileName) {

                InputStream inputStream = null;
        String obsStationList = "";
        try{
            inputStream = assetManager.open(obsFileName, AssetManager.ACCESS_BUFFER);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String result) {

    }

    private String findObsCode(LatLng mylatLng) {

       return "";
    }//findObsCode()
}//class
