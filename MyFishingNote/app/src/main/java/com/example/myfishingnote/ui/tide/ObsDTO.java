package com.example.myfishingnote.ui.tide;

import java.io.Serializable;

public class ObsDTO implements Serializable {

    //KHOA 스마트 조석예보

    private String obs_post_id;         //조위 관측소 아이디
    private String obs_post_name;       //조위 관측소 이름
    private String bu_post_id;          //부이 아이디
    private String bu_post_name;        //부이 이름

    //예측 조위
    private String tph_level1;
    private String tph_time1;
    private String hl_code1;

    private String tph_level2;
    private String tph_time2;
    private String hl_code2;

    private String tph_level3;
    private String tph_time3;
    private String hl_code3;

    private String tph_level4;
    private String tph_time4;
    private String hl_code4;

    private String air_temp;            //기온        27.5	℃
    private String air_press;           //기압        1009	hPa
    private String cur_lat;             //현재 위도
    private String cur_lng;             //현재 경도
    private String record_time;         //관측시간    2016-01-01 00:01:00
    private String Salinity;            //염분        25.2	psu
    private String tide_level;          //조위        23.5	cm
    private String water_temp;          //수온        4.6	℃
    private String wave_height;         //파고        0.58	m
    private String wind_dir;            //풍향        16	deg
    private String wind_speed;          //풍속        7.1	m/s
    private String wind_gust;       	//돌풍        9	m/s


    //KASI 한국 천문 연구원 자료 : 출몰시각 (공공데이터 포탈)
    private String sunrise;             //일출
    private String sunset;              //일몰
    private String moonrise;            //월출
    private String moonset;             //월몰

    //KASI 한국 천문 연구원 자료 : 음양력 (공공데이터 포탈)
    private String lunYear;             //음력 년
    private String lunMonth;            //음력 월
    private String lunDay;              //음력 일
    private String solWeek;             //요일

    //날씨정보 https://openweathermap.org/







}