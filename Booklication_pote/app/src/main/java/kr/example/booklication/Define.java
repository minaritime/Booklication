package kr.example.booklication;

import java.util.ArrayList;

public class Define {

    // 데이터가 있는 날짜의 리스트를 모두 담은 2차원 리스트
    public ArrayList<ArrayList<String>> temparray = new ArrayList<ArrayList<String>>();

    public String Date = "";        // 선택한 날짜를 담는 변수

    public int temp = 1;            // 책을 읽은 상태를 구별하는 함수 (읽은 책, 읽고 있는 책, 읽고 싶은 책)

    // 캘린더뷰 날짜 설정을 위한 변수
    public int Year;
    public int Month;
    public int Day;

    // 검색한 책의 정보를 담는 변수
    public String BookName;
    public String BookAuthor;
    public String BookImageURL;
    public String TotalPage;
    public String ReadPage;
    public String Expectation;

    // '읽은 책' 상태에서 저장된 책들을 모은 변수
    public String temp_startday_1;
    public String temp_lastday;
    public String temp_book_name_1;
    public String temp_book_aut_1;
    public String temp_image_url_1;
    public String temp_today_1;

    // '읽고 있는 책' 상태에서 저장된 책들을 모은 변수
    public String temp_startday_2;
    public String temp_book_name_2;
    public String temp_book_aut_2;
    public String temp_image_url_2;
    public String temp_book_total;
    public String temp_book_reading;
    public String temp_today_2;

    // '읽고 싶은 책' 상태에서 저장된 책들을 모은 변수
    public String temp_image_url_3;
    public String temp_book_name_3;
    public String temp_book_exp;
    public String temp_today_3;
    public String temp_book_aut_3;

    public String percent = "0";        // '읽고 있는 책' 상태의 퍼센트를 모은 변수

    // 위의 temp_* 변수들을 split한 변수
    public String[] splitfile_startday_1;
    public String[] splitfile_lastday;
    public String[] splitfile_today_1;
    public String[] splitfile_img_url_1;
    public String[] splitfile_book_name_1;
    public String[] splitfile_book_aut_1;

    public String[] splitfile_startday_2;
    public String[] splitfile_today_2;
    public String[] splitfile_img_url_2;
    public String[] splitfile_book_name_2;
    public String[] splitfile_book_aut_2;
    public String[] splitfile_book_total;
    public String[] splitfile_book_reading;
    public String[] splitfile_persent;

    public String[] splitfile_today_3;
    public String[] splitfile_img_url_3;
    public String[] splitfile_book_name_3;
    public String[] splitfile_book_aut_3;
    public String[] splitfile_book_exp;

    public int delete;          // 책을 삭제할 때 상황을 판단할 변수

    public int overlap;         // 중복인 책을 저장할 때 상황을 판단할 변수

    // temp 리스트를 담아 모든 책의 정보를 담는 2차원 리스트
    public ArrayList<ArrayList<String>> apiArray = new ArrayList<>();

    public String keyword = "";     // 검색어 저장 변수

    private static Define instance;
    public static Define ins(){
        if(instance == null){
            instance = new Define();
        }
        return instance;
    }
}
