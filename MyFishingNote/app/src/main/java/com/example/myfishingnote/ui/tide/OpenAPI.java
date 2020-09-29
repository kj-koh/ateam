package com.example.myfishingnote.ui.tide;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OpenAPI extends AsyncTask<String, Void, ObsDTO> {
    private static final String TAG = "OpenAPI";
    private Context context;
    private String postId;
    private String buId;
    private String obsPreId;



    private LatLng curLatLng;
    ObsDTO dto = new ObsDTO();
    private AssetManager assetManager;


    public OpenAPI(Context context, String postId, String buId, String obsPreId, LatLng curLatLng) {
        this.context = context;
        this.postId = postId;
        this.buId = buId;
        this.obsPreId = obsPreId;
        this.curLatLng = curLatLng;
    }//생성자

    @Override
    protected ObsDTO doInBackground(String... strUrls) {

        ObsDTO obsDTO = new ObsDTO();

        //현재 시간을 받아온다
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String Today = sdf.format(date);
        sdf = new SimpleDateFormat("hhmmss");
        String Time = sdf.format(date);

        //현재 위치를 DTO에 넣어준다
        obsDTO.setCur_lat(String.valueOf(curLatLng.latitude));
        obsDTO.setCur_lng(String.valueOf(curLatLng.longitude));

        //해양 데이터 서비스키
        String ServiceKey = "T7qAa8L36BfkMpmavM9hsw==";

        /*//관측소 위치의 json파일을 불러와서 JSONArray에 담아준다.
        String obsFileName = "tideObsRecent.json";
        assetManager = ((MainActivity)context).getAssets();

        JSONObject obsStationList = new JSONObject();
        obsStationList = getObsStationList(obsFileName );

        Log.d(TAG, "json: " + obsStationList);*/

        //현재 위치를 기반으로 가까운 관측소의 위치를 찾는다.
        // >>메인 액티비티에서 위치를 찾아서 넘겨줌


        //찾은 관측소의 ID를 이용해 해양 정보를 받아온다

        try {

            //조위관측 최신데이터
            String DataType = "tideObsRecent";
            String ObsCode = postId;

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
            JSONObject jsonObject = new JSONObject(data);


            //dto에 값을 넣어준다.
            obsDTO.setObs_post_id(jsonObject.getJSONObject("result").getJSONObject("meta").getString("obs_post_id"));
            obsDTO.setObs_post_name(jsonObject.getJSONObject("result").getJSONObject("meta").getString("obs_post_name"));
            obsDTO.setTide_level(jsonObject.getJSONObject("result").getJSONObject("data").getString("tide_level"));
            obsDTO.setAir_temp(jsonObject.getJSONObject("result").getJSONObject("data").getString("air_temp"));
            obsDTO.setAir_press(jsonObject.getJSONObject("result").getJSONObject("data").getString("air_press"));

            obsDTO.setWind_dir(jsonObject.getJSONObject("result").getJSONObject("data").getString("wind_dir"));
            obsDTO.setWind_speed(jsonObject.getJSONObject("result").getJSONObject("data").getString("wind_speed"));
            obsDTO.setWind_gust(jsonObject.getJSONObject("result").getJSONObject("data").getString("wind_gust"));
            obsDTO.setRecord_time(jsonObject.getJSONObject("result").getJSONObject("data").getString("record_time"));

            //Log.d(TAG, "TEST !!!!!!!!!!!!!!! JSON: " + obsDTO.getObs_post_id());


            //조위관측 예측정보
            DataType = "tideObsPreTab";
            ObsCode = obsPreId;

            strUrl = "http://www.khoa.go.kr/oceangrid/grid/api/" +
                     DataType + "/search.do?ServiceKey=" + ServiceKey + "&ObsCode=" + ObsCode +
                    "&Date=" + Today + "&ResultType=json";

            url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(conn.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            builder = new StringBuffer();

            inputString = null;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }


            data = builder.toString();
            jsonObject = new JSONObject(data);

            //Log.d(TAG, "TEST : doInBackground: " + jsonObject);
            JSONArray pre = jsonObject.getJSONObject("result").getJSONArray("data");


            //Log.d(TAG, "TEST : doInBackground: PRE = " + pre.getJSONObject(0).getString("tph_level"));
            obsDTO.setTph_level1(pre.getJSONObject(0).getString("tph_level"));
            //Log.d(TAG, "TEST : doInBackground: PRE = " + obsDTO.getTph_level1());
            obsDTO.setTph_time1(pre.getJSONObject(0).getString("tph_time"));
            obsDTO.setHl_code1(pre.getJSONObject(0).getString("hl_code"));
            //Log.d(TAG, "TEST : doInBackground: " + pre.length());

            obsDTO.setTph_level2(pre.getJSONObject(1).getString("tph_level"));
            obsDTO.setTph_time2(pre.getJSONObject(1).getString("tph_time"));
            obsDTO.setHl_code2(pre.getJSONObject(1).getString("hl_code"));

            obsDTO.setTph_level3(pre.getJSONObject(2).getString("tph_level"));
            obsDTO.setTph_time3(pre.getJSONObject(2).getString("tph_time"));
            obsDTO.setHl_code3(pre.getJSONObject(2).getString("hl_code"));

            if (pre.length() !=3) {
                obsDTO.setTph_level4(pre.getJSONObject(3).getString("tph_level"));
                obsDTO.setTph_time4(pre.getJSONObject(3).getString("tph_time"));
                obsDTO.setHl_code4(pre.getJSONObject(3).getString("hl_code"));
            }
            //Log.d(TAG, "TEST : doInBackground: PRE = " + pre.getJSONObject(0).getString("tph_level"));
            //Log.d(TAG, "TEST : doInBackground: PRE = " + pre.getJSONObject(1).getString("tph_level"));

            //Log.d(TAG, "TEST + DTO : "+obsDTO.getTph_level1()+ " / " + obsDTO.getTph_time1() + " / " + obsDTO.getHl_code1());
            //Log.d(TAG, "TEST + DTO : "+obsDTO.getTph_level2()+ " / " + obsDTO.getTph_time2() + " / " + obsDTO.getHl_code2());
            //Log.d(TAG, "TEST + DTO : "+obsDTO.getTph_level3()+ " / " + obsDTO.getTph_time3() + " / " + obsDTO.getHl_code3());
            //Log.d(TAG, "TEST + DTO : "+obsDTO.getTph_level4()+ " / " + obsDTO.getTph_time4() + " / " + obsDTO.getHl_code4());



            //부이데이터
            DataType = "buObsRecent";
            ObsCode = buId;

            strUrl = "http://www.khoa.go.kr/oceangrid/grid/api/" +
            DataType + "/search.do?ServiceKey=" +
            ServiceKey +"&ObsCode=" + ObsCode + "+&Date="+Today+"&ResultType=json";

            url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(conn.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            builder = new StringBuffer();

            inputString = null;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }


            data = builder.toString();
            jsonObject = new JSONObject(data);

            //받아온 json을 DTO에 넣어준다.
            obsDTO.setBu_post_id(jsonObject.getJSONObject("result").getJSONObject("meta").getString("obs_post_id"));
            obsDTO.setBu_post_name(jsonObject.getJSONObject("result").getJSONObject("meta").getString("obs_post_name"));
            obsDTO.setSalinity(jsonObject.getJSONObject("result").getJSONObject("data").getString("Salinity"));
            obsDTO.setWater_temp(jsonObject.getJSONObject("result").getJSONObject("data").getString("water_temp"));
            obsDTO.setWave_height(jsonObject.getJSONObject("result").getJSONObject("data").getString("wave_height"));

            //KASI 한국천문연구원 OpenAPI
            //출몰시간정보

            String latitude = String.valueOf(curLatLng.latitude * 100).substring(0, 4);
            String longitude = String.valueOf(curLatLng.longitude * 100).substring(0, 5);

            //Log.d(TAG, "TEST : String value of =  " + latitude);
            //Log.d(TAG, "TEST : String value of =  " + longitude);

            ServiceKey = "v5t5d2%2FksCxDRL%2B8LXT8PuDRUcrAew4FddjX%2BZ4Iq8k8OxwMeke60EcX8DrRv8qhwU2%2B2FwJ7LyaikUvZQGYjw%3D%3D";
            strUrl = "http://apis.data.go.kr/B090041/openapi/service/RiseSetInfoService/getLCRiseSetInfo?longitude=" +
                    longitude + "&latitude=" + latitude + "&locdate=" + Today + "&dnYn=N&ServiceKey="
                    +ServiceKey;

            url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(conn.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            builder = new StringBuffer();

            inputString = null;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            data = builder.toString();

            //XML을 JSON으로 변환해서 처리
            //외부 라이브러리 사용
            JSONObject json = XML.toJSONObject(data);

            obsDTO.setSunrise("0"+json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONObject("item").getString("sunrise"));
            obsDTO.setSunset(json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONObject("item").getString("sunset"));
            obsDTO.setMoonrise(json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONObject("item").getString("moonrise"));
            obsDTO.setMoonset("0"+json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONObject("item").getString("moonset"));



            //Log.d(TAG, "TEST : XML : SUNRISE " +obsDTO.getSunrise());
            //Log.d(TAG, "TEST : XML : SUNSET " +obsDTO.getSunset());
            //Log.d(TAG, "TEST : XML : MOONRISE " +obsDTO.getMoonrise());
            //Log.d(TAG, "TEST : XML : MOONSET " +obsDTO.getMoonset());
            //Log.d(TAG, "TEST : XML " + json);


            //KASI 한국천문연구원 OpenAPI
            //음양력

            String solYear = Today.substring(0, 4);
            String solMonth = Today.substring(4, 6);
            String solDay = Today.substring(6);

            /*Log.d(TAG, "TEST solYear: " + solYear);
            Log.d(TAG, "TEST solMonth: " + solMonth);
            Log.d(TAG, "TEST solDay: " + solDay);*/

            strUrl = "http://apis.data.go.kr/B090041/openapi/service/LrsrCldInfoService/getLunCalInfo?solYear=" +
                    solYear + "&solMonth=" + solMonth + "&solDay=" + solDay + "&ServiceKey=" + ServiceKey;

            url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(conn.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            builder = new StringBuffer();

            inputString = null;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            data = builder.toString();

            json = XML.toJSONObject(data);

            //Log.d(TAG, "TEST : XML : Lunday " +json);

            obsDTO.setSolWeek(json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONObject("item").getString("solWeek"));
            obsDTO.setLunYear(json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONObject("item").getString("lunYear"));
            obsDTO.setLunMonth(json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONObject("item").getString("lunMonth"));
            obsDTO.setLunDay(json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONObject("item").getString("lunDay"));

            //Log.d(TAG, "TEST : XML : Solweek " +obsDTO.getSolWeek());
            //Log.d(TAG, "TEST : XML : LunY " +obsDTO.getLunYear());
            //Log.d(TAG, "TEST : XML : LunM " +obsDTO.getLunMonth());
            //Log.d(TAG, "TEST : XML : Lunday " +obsDTO.getLunDay());


            //OpenWeatherMap.org
            //날씨정보

            /*String appid="dce792887e1a5e9ca4b890cfd0cdc0e6";

            strUrl = "http://api.openweathermap.org/data/2.5/weather?lat=" +
                    curLatLng.latitude + "&lon=" + curLatLng.longitude + "&lang=kr&appid="+ appid;

            url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(conn.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            builder = new StringBuffer();

            inputString = null;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            data += builder.toString(); */

            // Gson gson = new Gson();
            // DTO dto = gson.fromJson( 스트링, DTO.class );
            // DTO[] dtos = gson.fromJson( 스트링, DTO[].class );
            // List<DTO> list = Arrays.asList(dtos);
            // ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(arr));

            //Log.d(TAG, "TEST Async : " + data);

            conn.disconnect();
            bufferedReader.close();
            Log.d(TAG, "TEST : DTO 반환 : " + obsDTO.getObs_post_name());
            return obsDTO;

        } catch (IOException | JSONException  e) {
            e.printStackTrace();
        }
        return null;
    }//doinbackgound()


    @Override
    protected void onPostExecute(ObsDTO result) {

    }

    private String findObsCode(LatLng mylatLng) {

       return "";
    }//findObsCode()
}//class
