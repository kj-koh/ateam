package com.example.myfishingnote.ui.tide;

import java.io.Serializable;

public class ObsDTO implements Serializable {

    //KHOA 스마트 조석예보

    private String obs_post_id;         //조위 관측소 아이디
    private String obs_post_name;       //조위 관측소 이름
    private String bu_post_id;          //부이 아이디
    private String bu_post_name;        //부이 이름

    //예측 조위
    private String tph_level1;          //예측조위1
    private String tph_time1;           //예측조위1
    private String hl_code1;            //예측조위1

    private String tph_level2;          //예측조위2
    private String tph_time2;           //예측조위2
    private String hl_code2;            //예측조위2

    private String tph_level3;          //예측조위3
    private String tph_time3;           //예측조위3
    private String hl_code3;            //예측조위3

    private String tph_level4;          //예측조위4
    private String tph_time4;           //예측조위4
    private String hl_code4;            //예측조위4

    private String air_temp;            //기온        27.5	℃
    private String air_press;           //기압        1009	hPa
    private String cur_position;        //현재 위치 한글명
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


    public String getCur_position() {
        return cur_position;
    }

    public void setCur_position(String cur_position) {
        this.cur_position = cur_position;
    }

    public String getObs_post_id() {
        return obs_post_id;
    }

    public void setObs_post_id(String obs_post_id) {
        this.obs_post_id = obs_post_id;
    }

    public String getObs_post_name() {
        return obs_post_name;
    }

    public void setObs_post_name(String obs_post_name) {
        this.obs_post_name = obs_post_name;
    }

    public String getBu_post_id() {
        return bu_post_id;
    }

    public void setBu_post_id(String bu_post_id) {
        this.bu_post_id = bu_post_id;
    }

    public String getBu_post_name() {
        return bu_post_name;
    }

    public void setBu_post_name(String bu_post_name) {
        this.bu_post_name = bu_post_name;
    }

    public String getTph_level1() {
        return tph_level1;
    }

    public void setTph_level1(String tph_level1) {
        this.tph_level1 = tph_level1;
    }

    public String getTph_time1() {
        return tph_time1;
    }

    public void setTph_time1(String tph_time1) {
        this.tph_time1 = tph_time1;
    }

    public String getHl_code1() {
        return hl_code1;
    }

    public void setHl_code1(String hl_code1) {
        this.hl_code1 = hl_code1;
    }

    public String getTph_level2() {
        return tph_level2;
    }

    public void setTph_level2(String tph_level2) {
        this.tph_level2 = tph_level2;
    }

    public String getTph_time2() {
        return tph_time2;
    }

    public void setTph_time2(String tph_time2) {
        this.tph_time2 = tph_time2;
    }

    public String getHl_code2() {
        return hl_code2;
    }

    public void setHl_code2(String hl_code2) {
        this.hl_code2 = hl_code2;
    }

    public String getTph_level3() {
        return tph_level3;
    }

    public void setTph_level3(String tph_level3) {
        this.tph_level3 = tph_level3;
    }

    public String getTph_time3() {
        return tph_time3;
    }

    public void setTph_time3(String tph_time3) {
        this.tph_time3 = tph_time3;
    }

    public String getHl_code3() {
        return hl_code3;
    }

    public void setHl_code3(String hl_code3) {
        this.hl_code3 = hl_code3;
    }

    public String getTph_level4() {
        return tph_level4;
    }

    public void setTph_level4(String tph_level4) {
        this.tph_level4 = tph_level4;
    }

    public String getTph_time4() {
        return tph_time4;
    }

    public void setTph_time4(String tph_time4) {
        this.tph_time4 = tph_time4;
    }

    public String getHl_code4() {
        return hl_code4;
    }

    public void setHl_code4(String hl_code4) {
        this.hl_code4 = hl_code4;
    }

    public String getAir_temp() {
        return air_temp;
    }

    public void setAir_temp(String air_temp) {
        this.air_temp = air_temp;
    }

    public String getAir_press() {
        return air_press;
    }

    public void setAir_press(String air_press) {
        this.air_press = air_press;
    }

    public String getCur_lat() {
        return cur_lat;
    }

    public void setCur_lat(String cur_lat) {
        this.cur_lat = cur_lat;
    }

    public String getCur_lng() {
        return cur_lng;
    }

    public void setCur_lng(String cur_lng) {
        this.cur_lng = cur_lng;
    }

    public String getRecord_time() {
        return record_time;
    }

    public void setRecord_time(String record_time) {
        this.record_time = record_time;
    }

    public String getSalinity() {
        return Salinity;
    }

    public void setSalinity(String salinity) {
        Salinity = salinity;
    }

    public String getTide_level() {
        return tide_level;
    }

    public void setTide_level(String tide_level) {
        this.tide_level = tide_level;
    }

    public String getWater_temp() {
        return water_temp;
    }

    public void setWater_temp(String water_temp) {
        this.water_temp = water_temp;
    }

    public String getWave_height() {
        return wave_height;
    }

    public void setWave_height(String wave_height) {
        this.wave_height = wave_height;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getWind_gust() {
        return wind_gust;
    }

    public void setWind_gust(String wind_gust) {
        this.wind_gust = wind_gust;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }

    public String getMoonset() {
        return moonset;
    }

    public void setMoonset(String moonset) {
        this.moonset = moonset;
    }

    public String getLunYear() {
        return lunYear;
    }

    public void setLunYear(String lunYear) {
        this.lunYear = lunYear;
    }

    public String getLunMonth() {
        return lunMonth;
    }

    public void setLunMonth(String lunMonth) {
        this.lunMonth = lunMonth;
    }

    public String getLunDay() {
        return lunDay;
    }

    public void setLunDay(String lunDay) {
        this.lunDay = lunDay;
    }

    public String getSolWeek() {
        return solWeek;
    }

    public void setSolWeek(String solWeek) {
        this.solWeek = solWeek;
    }
}