package com.example.myfishingnote.ui.tide;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import com.example.myfishingnote.MainActivity;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
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
    private String postid;
    private String buid;



    private LatLng mylatLng;
    ObsDTO dto = new ObsDTO();
    private AssetManager assetManager;


    public OpenAPI(Context context, String postid, String buid) {
        this.context = context;
        this.postid = postid;
        this.buid = buid;
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

        //해양 데이터 서비스키
        String ServiceKey = "T7qAa8L36BfkMpmavM9hsw==";

        /*//관측소 위치의 json파일을 불러와서 JSONArray에 담아준다.
        String obsFileName = "tideObsRecent.json";
        assetManager = ((MainActivity)context).getAssets();

        JSONObject obsStationList = new JSONObject();
        obsStationList = getObsStationList(obsFileName );

        Log.d(TAG, "json: " + obsStationList);*/





        //현재 위치를 기반으로 가까운 관측소의 위치를 찾는다.




        //찾은 관측소의 ID를 이용해 해양 정보를 받아온다

        try {

            //조위관측 최신데이터
            String DataType = "tideObsRecent";
            String ObsCode = postid;     //임시값 인천


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

            Log.d(TAG, "TEST Async : " + data);

            conn.disconnect();
            bufferedReader.close();
            return data;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }//doinbackgound()

    private JSONObject getObsStationList(String obsFileName) {

        JSONObject object = null;
        InputStream inputStream = null;
        String StationList = "";
        try{
            //asset manager에게서 inputstream 가져오기
            //asset보다 law폴더를 활용해서 사용하는것이 추천됨
            //asset명령어는 거의 사라졌다고 함
            inputStream = context.getAssets().open(obsFileName, AssetManager.ACCESS_BUFFER);

            //문자로 읽어들이기
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            //파일 읽기

            String line = "";
            while ((line = reader.readLine()) != null) {
                StationList += line;

            }//while


            object = new JSONObject(StationList);
            Log.d(TAG, "TEST Async: " + object.get("obs_post_id"));


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }//try&catch
            }//if
        }//finally*/

//       try {
//           String json =
//       }catch (Exception e){
//
//       }


        return object;
    }//getObsStationList()

    @Override
    protected void onPostExecute(String result) {

    }

    private String findObsCode(LatLng mylatLng) {

       return "";
    }//findObsCode()
}//class
