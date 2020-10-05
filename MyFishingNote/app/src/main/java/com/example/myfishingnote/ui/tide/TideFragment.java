package com.example.myfishingnote.ui.tide;

import android.os.Bundle;
import android.service.autofill.Dataset;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myfishingnote.MainActivity;
import com.example.myfishingnote.R;


//MPAndroidChart import
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.charts.LineChart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class TideFragment extends Fragment {
    private static final String TAG = "";


    //String tidedata = bundle.getString("data", "없음");

    private TideViewModel tideViewModel;

    private TextView textView;
    private LineChart lineChart;



    /*public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tideViewModel =
                ViewModelProviders.of(this).get(TideViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tide, container, false);
        final TextView textView = root.findViewById(R.id.textViewJson);
        tideViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tide, container, false);

        MainActivity activity = (MainActivity) getActivity();
        ObsDTO obsDTO;

        //현재 시간을 받아온다
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String Today = sdf.format(date);
        //sdf = new SimpleDateFormat("hhmmss");
        sdf = new SimpleDateFormat("hhmm");
        String TTime = sdf.format(date);
        Log.d(TAG, "TEST : " + TTime);

        //Bundle bundle = getArguments();
        //obsDTO = (ObsDTO) bundle.getSerializable("obsDTO");
        //obsDTO = (ObsDTO) bundle.getSerializable("obsDTO");

        //액티비티 - 프래그먼트 데이터연동 bundle
        //메인액티비티값을 가져온다
        //MainActivity activity = (MainActivity) getActivity();

        //번들처리
        /*Bundle bundle = new Bundle();       //번들 선언

        if(activity.tidebundle != null){    //번들값이 널이 아니면 가져온다.
            bundle = activity.tidebundle;
        }*/

        //String tidedata = bundle.getString("data");

        Bundle tidebundle = new Bundle();
        if(activity.bundle !=null) {
            tidebundle = activity.bundle;
            //bundle = getArguments();

        }
        //obsDTO = (ObsDTO) getArguments().get("obsDTO");
        //obsDTO = (ObsDTO) bundle.get("obsDTO");
        //obsDTO = (ObsDTO) bundle.getSerializable("obsDTO");
        obsDTO = (ObsDTO) tidebundle.getSerializable("obsDTO");
        Log.d(TAG, "TEST : 넘어온 번들 확인 :  " + obsDTO.getObs_post_name());


        //선 그래프 그리기
        ArrayList<Entry> tide_chart = new ArrayList<>();
        ArrayList<Entry> cur_tide_chart = new ArrayList<>();

        lineChart = (LineChart) rootView.findViewById(R.id.chart);//layout의 id
        LineData chartData = new LineData();

        cur_tide_chart.add(new Entry(Float.parseFloat(TTime), 0));
        cur_tide_chart.add(new Entry(Float.parseFloat(TTime), Float.parseFloat(obsDTO.getTide_level())*10));

        //entry_chart.add(new Entry(x값, y값));
        String Tph_time1 = obsDTO.getTph_time1().substring(11,13);
        Tph_time1 += obsDTO.getTph_time1().substring(14,16);
        Log.d(TAG, "TEST: TIME1: " + Tph_time1);

        tide_chart.add(new Entry(Float.parseFloat(Tph_time1), Float.parseFloat(obsDTO.getTph_level1())));

        String Tph_time2 = obsDTO.getTph_time2().substring(11,13);
        Tph_time2 += obsDTO.getTph_time1().substring(14,16);
        tide_chart.add(new Entry(Float.parseFloat(Tph_time2), Float.parseFloat(obsDTO.getTph_level2())));

        String Tph_time3 = obsDTO.getTph_time3().substring(11,13);
        Tph_time3 += obsDTO.getTph_time1().substring(14,16);
        tide_chart.add(new Entry(Float.parseFloat(Tph_time3), Float.parseFloat(obsDTO.getTph_level3())));

        if (obsDTO.getTph_level4() != null) {
            String Tph_time4 = obsDTO.getTph_time4().substring(11,13);
            Tph_time4 += obsDTO.getTph_time1().substring(14,16);
            tide_chart.add(new Entry(Float.parseFloat(Tph_time4), Float.parseFloat(obsDTO.getTph_level4())));
        }
        /* 만약 (2, 3) add하고 (2, 5)한다고해서
        기존 (2, 3)이 사라지는게 아니라 x가 2인곳에 y가 3, 5의 점이 찍힘 */
        //LineDataSet lineDataSet = new LineDataSet(entry_chart, "예측 조류");
        LineDataSet lineDataSet = new LineDataSet(tide_chart, obsDTO.getHl_code1());
        chartData.addDataSet(lineDataSet);

        //LineDataSet lineDataSet2 = new LineDataSet(cur_tide_chart, "현재 조위");
        //chartData.addDataSet(lineDataSet2);

        //그래프 하단을 채워준다
        lineDataSet.setDrawFilled(true);

        //모난 꼭지점을 둥글게 처리
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);


        //x축 설정
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setLabelCount(4, true);
        //lineChart.setVisibleXRangeMaximum(2400);

        //그래프 값 설정
        lineChart.setData(chartData);

        //그래프 표시
        lineChart.invalidate();

        Log.d(TAG, "TEST : Tph_time1" + Tph_time1);







        //obsDTO 내용 확인
        String tidedata = " 조위 관측소 아이디 : " + obsDTO.getObs_post_id() + "\t조위 관측소 이름 : " + obsDTO.getObs_post_name() ;
        tidedata += "\n 부이 관측소 아이디 : " + obsDTO.getBu_post_id() + "\t조위 관측소 이름 : " + obsDTO.getBu_post_name() ;
        tidedata += "\n 관측 시간 : " + obsDTO.getRecord_time();
        tidedata += "\n 예측조위1 : " + obsDTO.getHl_code1() + " / " + obsDTO.getTph_time1() + " / " + obsDTO.getTph_level1() ;
        tidedata += "\n 예측조위2 : " + obsDTO.getHl_code2() + " / " + obsDTO.getTph_time2() + " / " + obsDTO.getTph_level2() ;
        tidedata += "\n 예측조위3 : " + obsDTO.getHl_code3() + " / " + obsDTO.getTph_time3() + " / " + obsDTO.getTph_level3() ;
        tidedata += "\n 예측조위4 : " + obsDTO.getHl_code4() + " / " + obsDTO.getTph_time4() + " / " + obsDTO.getTph_level4() ;
        tidedata += "\n 현재 위치 : " + obsDTO.getCur_position();
        tidedata += "\n 현재 위도 : " + obsDTO.getCur_lat() + "\t현재 경도 : " + obsDTO.getCur_lng() ;
        tidedata += "\n 기온 : " + obsDTO.getAir_temp() + "℃\t기압 : " + obsDTO.getAir_press() + "hPa" ;
        tidedata += "\n 염분 : " + obsDTO.getSalinity() + "psu\t조위 : " + obsDTO.getTide_level() + "cm" ;
        tidedata += "\n 수온 : " + obsDTO.getWater_temp() + "℃\t파고 : " + obsDTO.getWave_height() + "m" ;
        tidedata += "\n 풍향 : " + obsDTO.getWind_dir() + "deg\t풍속 : " + obsDTO.getWind_speed() + "m/s" ;
        tidedata += "\n 돌풍 : " + obsDTO.getWind_gust() + "m/s";
        tidedata += "\n 일출 : " + obsDTO.getSunrise() + "\t일몰 : " + obsDTO.getSunset();
        tidedata += "\n 월출 : " + obsDTO.getMoonrise() + "\t월몰 : " + obsDTO.getMoonset();
        tidedata += "\n 음력 년 : " + obsDTO.getLunYear() + "\t음력 월 : " + obsDTO.getLunMonth();
        tidedata += "\n 음력 일 : " + obsDTO.getLunDay() + "\t요일 : " + obsDTO.getSolWeek();

        textView = rootView.findViewById(R.id.textViewJson);
        //textView.setText(data);
        textView.setText(tidedata);

        return rootView;

    }//onCreateView
}//class