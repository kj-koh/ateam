package com.example.myfishingnote.ui.note;

public class NoteDTO {
    int _id;
    int noteNum;            //노트번호는 현재 시간값을 가져와서 쓸것임
    String email;           //작성자를 특정하는 항목, 차후 스프링 - 커뮤니티 연동시 사용

    String weather;         //글 작성시 날씨상황
    String address;         //글 작성시 현재 위치 : 한글      cur_position
    String locationX;       //글 작성시 현재 위치 : 위도      cur_lat
    String locationY;       //글 작성시 현재 위치 : 경도      cur_lng


    //글 작성시 표시용 년도
    //글 작성시 표시용 날짜
    //글 작성시 표시용 시간
    String solWeek;         //글 작성시 표시용 요일 solWeek

    String lunYear;         //글 작성시 표시용 음력 년 lunYear
    String lunMonth;        //글 작성시 표시용 음력 월 lunMonth
    String lunDay;          //글 작성시 표시용 음력 일 lunDay


    //글 작성시 물때  음력 계산해서 표시예정
    String tide_level;      //글 작성시 조위  tide_level
    String air_press;       //글 작성시 기압  air_press
    String air_temp;        //글 작성시 기온  air_temp
    String water_temp;      //글 작성시 수온  water_temp


    //잡은물고기 종류      갯수
    //잡은장비 종류

    String contents;        //글 내용

    String mood;        //
    String picture;
    String createDateStr;

    //생성자, Getters&Setters 추후 String값 변경후 재조정


    public NoteDTO(int _id, int noteNum, String email, String weather, String address, String locationX, String locationY, String solWeek, String lunYear, String lunMonth, String lunDay, String tide_level, String air_press, String air_temp, String water_temp, String contents, String mood, String picture, String createDateStr) {
        this._id = _id;
        this.noteNum = noteNum;
        this.email = email;
        this.weather = weather;
        this.address = address;
        this.locationX = locationX;
        this.locationY = locationY;
        this.solWeek = solWeek;
        this.lunYear = lunYear;
        this.lunMonth = lunMonth;
        this.lunDay = lunDay;
        this.tide_level = tide_level;
        this.air_press = air_press;
        this.air_temp = air_temp;
        this.water_temp = water_temp;
        this.contents = contents;
        this.mood = mood;
        this.picture = picture;
        this.createDateStr = createDateStr;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getNoteNum() {
        return noteNum;
    }

    public void setNoteNum(int noteNum) {
        this.noteNum = noteNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getSolWeek() {
        return solWeek;
    }

    public void setSolWeek(String solWeek) {
        this.solWeek = solWeek;
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

    public String getTide_level() {
        return tide_level;
    }

    public void setTide_level(String tide_level) {
        this.tide_level = tide_level;
    }

    public String getAir_press() {
        return air_press;
    }

    public void setAir_press(String air_press) {
        this.air_press = air_press;
    }

    public String getAir_temp() {
        return air_temp;
    }

    public void setAir_temp(String air_temp) {
        this.air_temp = air_temp;
    }

    public String getWater_temp() {
        return water_temp;
    }

    public void setWater_temp(String water_temp) {
        this.water_temp = water_temp;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }
}//class
