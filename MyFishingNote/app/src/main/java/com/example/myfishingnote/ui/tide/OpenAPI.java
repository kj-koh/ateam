package com.example.myfishingnote.ui.tide;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

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

    private LatLng mylatLng;


    public OpenAPI(LatLng latLng) {
        this.mylatLng = latLng;
    }//생성자

    @Override
    protected String doInBackground(String... strUrls) {

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String Today = sdf.format(date);
        sdf = new SimpleDateFormat("hhmmss");
        String Time = sdf.format(date);

        String ServiceKey = "T7qAa8L36BfkMpmavM9hsw==";

        try {

            //조석예보
            String DataType = "tideObsPreTab";
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

            Log.d(TAG, "Async: " + data);



            conn.disconnect();
            bufferedReader.close();
            return data;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }//doinbackgound()

    @Override
    protected void onPostExecute(String result) {

    }

    private String findObsCode(LatLng mylatLng) {

       return "";
    }//findObsCode()
}//class
