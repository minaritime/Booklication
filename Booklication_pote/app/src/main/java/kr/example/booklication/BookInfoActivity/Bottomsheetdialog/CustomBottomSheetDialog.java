package kr.example.booklication.BookInfoActivity.Bottomsheetdialog;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import kr.example.booklication.Define;
import kr.example.booklication.FileIOStream.FileIOStreamRead;
import kr.example.booklication.FileIOStream.FileIOStreamWrite;
import kr.example.booklication.R;
import kr.example.booklication.RecordActivity.Percentage.Percentage;

public class CustomBottomSheetDialog implements Button.OnClickListener {

    AppCompatActivity aBottomSheetDialog;
    BottomSheetDialog bottomSheetDialog;
    Percentage percentage;

    Button btn_readbook1;
    Button btn_readingbook1;
    Button btn_wanttoreadbook1;
    TextView tv_beginning_date1;
    TextView tv_close_date_1;
    Button btn_savimage_url_1;

    Button btn_readbook2;
    Button btn_readingbook2;
    Button btn_wanttoreadbook2;
    EditText et_sum_page;
    EditText et_current_page;
    TextView tv_beginning_date;
    Button btn_savbook_total;

    Button btn_readbook3;
    Button btn_readingbook3;
    Button btn_wanttoreadbook3;
    EditText et_expectation3;
    Button btn_save3;

    FileIOStreamRead cFileIOStreamRead;
    FileIOStreamWrite cFileIOStreamWrite;

    View v1;
    View v2;
    View v3;

    private int Current_Year = 0;
    private int Selected_Year = 0;
    private int Current_Month = 0;
    private int Selected_Month = 0;
    private int Current_Day = 0;
    private int Selected_Day = 0;

    private long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy.MM.dd");
//    SimpleDateFormat Format = new SimpleDateFormat("yyyy.MM.dd");

    public CustomBottomSheetDialog(AppCompatActivity appCompatActivity) {
        aBottomSheetDialog = appCompatActivity;
        bottomSheetDialog = new BottomSheetDialog(aBottomSheetDialog);
        percentage = new Percentage(aBottomSheetDialog);
        cFileIOStreamRead = new FileIOStreamRead(aBottomSheetDialog);
        cFileIOStreamWrite = new FileIOStreamWrite(aBottomSheetDialog);
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction() {
        Define.ins().temp = 1;
        v1 = aBottomSheetDialog.getLayoutInflater().inflate(R.layout.bottomsheetdialog1, null);
        v2 = aBottomSheetDialog.getLayoutInflater().inflate(R.layout.bottomsheetdialog2, null);
        v3 = aBottomSheetDialog.getLayoutInflater().inflate(R.layout.bottomsheetdialog3, null);

        btn_readbook1 = (Button) v1.findViewById(R.id.btn_readbook1);
        btn_readingbook1 = (Button) v1.findViewById(R.id.btn_readingbook1);
        btn_wanttoreadbook1 = (Button) v1.findViewById(R.id.btn_wanttoreadbook1);
        tv_beginning_date1 = (TextView) v1.findViewById(R.id.tv_beginning_date1);
        tv_close_date_1 = (TextView) v1.findViewById(R.id.tv_close_date1);
        btn_savimage_url_1 = (Button) v1.findViewById(R.id.btn_save1);

        btn_readbook2 = (Button) v2.findViewById(R.id.btn_readbook2);
        btn_readingbook2 = (Button) v2.findViewById(R.id.btn_readingbook2);
        btn_wanttoreadbook2 = (Button) v2.findViewById(R.id.btn_wanttoreadbook2);
        et_sum_page = (EditText) v2.findViewById(R.id.et_sum_page2);
        et_sum_page.addTextChangedListener(new TextWatcher() {      // edittext 이벤트
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Define.ins().TotalPage = s.toString();      // 책의 총 페이지 저장
//                System.out.println("토탈 : " + Define.ins().TotalPage);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_current_page = (EditText) v2.findViewById(R.id.et_current_page2);
        et_current_page.addTextChangedListener(new TextWatcher() {      // edittext 이벤트
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Define.ins().ReadPage = s.toString();      // 읽은 페이지 저장
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tv_beginning_date = (TextView) v2.findViewById(R.id.tv_beginning_date2);
        btn_savbook_total = (Button) v2.findViewById(R.id.btn_save2);

        btn_readbook3 = (Button) v3.findViewById(R.id.btn_readbook3);
        btn_readingbook3 = (Button) v3.findViewById(R.id.btn_readingbook3);
        btn_wanttoreadbook3 = (Button) v3.findViewById(R.id.btn_wanttoreadbook3);
        et_expectation3 = (EditText) v3.findViewById(R.id.et_expectation3);
        et_expectation3.addTextChangedListener(new TextWatcher() {      // edittext 이벤트
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Define.ins().Expectation = s.toString();        // 기대평 저장
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn_save3 = (Button) v3.findViewById(R.id.btn_save3);

        btn_readbook1.setOnClickListener(this);
        btn_readingbook1.setOnClickListener(this);
        btn_wanttoreadbook1.setOnClickListener(this);
        tv_beginning_date1.setOnClickListener(this);
        tv_close_date_1.setOnClickListener(this);
        btn_savimage_url_1.setOnClickListener(this);

        btn_readbook2.setOnClickListener(this);
        btn_readingbook2.setOnClickListener(this);
        btn_wanttoreadbook2.setOnClickListener(this);
        tv_beginning_date.setOnClickListener(this);
        btn_savbook_total.setOnClickListener(this);

        btn_readbook3.setOnClickListener(this);
        btn_readingbook3.setOnClickListener(this);
        btn_wanttoreadbook3.setOnClickListener(this);
        btn_save3.setOnClickListener(this);

        bottomSheetDialog.setContentView(v1);

        //아웃사이드 터치로 캔슬여부
        //bottomSheetDialog.setCanceledOnTouchOutside(false); //바깥쪽 터치했을때 안꺼지도록

        bottomSheetDialog.show();

        Current_Year = Integer.parseInt(getTime().substring(0, 4));         // yyyy
        Current_Month = Integer.parseInt(getTime().substring(5, 7));        // MM
        Current_Day = Integer.parseInt(getTime().substring(getTime().length() - 2, getTime().length()));        // dd

        System.out.println("Current_Year : " + Current_Year);
        System.out.println("Current_Month : " + Current_Month);
        System.out.println("Current_Day : " + Current_Day);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 읽은 책 버튼을 누른 상태에서 읽은 책 버튼 눌렀을 경우
            case R.id.btn_readbook1:
                Define.ins().temp = 1;
                bottomSheetDialog.setContentView(v1);       // 바텀시트다이얼로그 View 변경
                break;
            // 읽은 책 버튼을 누른 상태에서 읽고 있는 책 버튼 눌렀을 경우
            case R.id.btn_readingbook1:
                Define.ins().temp = 2;
                bottomSheetDialog.setContentView(v2);       // 바텀시트다이얼로그 View 변경
                break;
            // 읽은 책 버튼을 누른 상태에서 읽고 싶은 책 버튼 눌렀을 경우
            case R.id.btn_wanttoreadbook1:
                Define.ins().temp = 3;
                bottomSheetDialog.setContentView(v3);       // 바텀시트다이얼로그 View 변경
                break;
            // 읽은 책 버튼을 누른 상태에서 시작일 텍스트뷰 눌렀을 경우
            case R.id.tv_beginning_date1:
                setStartDate();
                break;
            // 읽은 책 버튼을 누른 상태에서 종료일 텍스트뷰 눌렀을 경우
            case R.id.tv_close_date1:
                // 만약 시작일을 선택하지 않았을 경우
                if (tv_beginning_date1.getText().equals("시작일을 선택해주세요")) {
                    Toast.makeText(aBottomSheetDialog, "시작일을 먼저 선택해 주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    setEndDate();
                }
                break;
            // 읽은 책 버튼 누른 상태에서 저장하기 버튼 눌렀을 경우
            case R.id.btn_save1:
                // 시작일과 종료일을 선택하지 않았을 경우
                if (tv_beginning_date1.getText().equals("시작일을 선택해주세요") || tv_close_date_1.getText().equals("종료일을 선택해주세요")) {
                    Toast.makeText(aBottomSheetDialog, "날짜를 제대로 선택해 주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Define.ins().overlap == 1) {    // 만약 덮어쓰기 과정이라면
                        // split 값 갱신
                        Define.ins().splitfile_img_url_1 = cFileIOStreamRead.readData("image_url_1").split("#");
                        Define.ins().splitfile_book_name_1 = cFileIOStreamRead.readData("book_name_1").split("#");
                        Define.ins().splitfile_book_aut_1 = cFileIOStreamRead.readData("book_aut_1").split("#");
                        Define.ins().splitfile_today_1 = cFileIOStreamRead.readData("today_1").split("#");
                        Define.ins().splitfile_startday_1 = cFileIOStreamRead.readData("startday_1").split("#");
                        Define.ins().splitfile_lastday = cFileIOStreamRead.readData("lastday").split("#");

                        Define.ins().splitfile_startday_2 = cFileIOStreamRead.readData("startday_2").split("#");
                        Define.ins().splitfile_img_url_2 = cFileIOStreamRead.readData("image_url_2").split("#");
                        Define.ins().splitfile_book_name_2 = cFileIOStreamRead.readData("book_name_2").split("#");
                        Define.ins().splitfile_book_aut_2 = cFileIOStreamRead.readData("book_aut_2").split("#");
                        Define.ins().splitfile_today_2 = cFileIOStreamRead.readData("today_2").split("#");
                        Define.ins().splitfile_book_total = cFileIOStreamRead.readData("book_total").split("#");
                        Define.ins().splitfile_book_reading = cFileIOStreamRead.readData("book_reading").split("#");
                        Define.ins().splitfile_persent = cFileIOStreamRead.readData("percentage").split("#");

                        Define.ins().splitfile_img_url_3 = cFileIOStreamRead.readData("image_url_3").split("#");
                        Define.ins().splitfile_book_name_3 = cFileIOStreamRead.readData("book_name_3").split("#");
                        Define.ins().splitfile_book_aut_3 = cFileIOStreamRead.readData("book_aut_3").split("#");
                        Define.ins().splitfile_today_3 = cFileIOStreamRead.readData("today_3").split("#");
                        Define.ins().splitfile_book_exp = cFileIOStreamRead.readData("book_exp").split("#");
                        // 읽은 책의 갯수 만큼 반복
                        for (int i = 0; i < Define.ins().splitfile_book_name_1.length; i++) {
                            // 만약 현재 찾은 책의 이름이 읽은 책 서재에 저장되어 있다면
                            if (Define.ins().splitfile_book_name_1[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // 값을 덮어써준다
                                Define.ins().temp_startday_1 = replaceLast(Define.ins().temp_startday_1, Define.ins().splitfile_startday_1[i], tv_beginning_date1.getText().toString());
                                Define.ins().temp_lastday = replaceLast(Define.ins().temp_lastday, Define.ins().splitfile_lastday[i], tv_close_date_1.getText().toString());
                                Define.ins().temp_book_name_1 = replaceLast(Define.ins().temp_book_name_1, Define.ins().splitfile_book_name_1[i] + "#", Define.ins().BookName);
                                Define.ins().temp_book_aut_1 = replaceLast(Define.ins().temp_book_aut_1, Define.ins().splitfile_book_aut_1[i] + "#", Define.ins().BookAuthor);
                                Define.ins().temp_image_url_1 = replaceLast(Define.ins().temp_image_url_1, Define.ins().splitfile_img_url_1[i] + "#", Define.ins().BookImageURL);
                                Define.ins().temp_today_1 = replaceLast(Define.ins().temp_today_1, Define.ins().splitfile_today_1[i] + "#", getTime());
                            }
                        }
                        // 읽고 있는 책의 갯수 만큼 반복
                        for (int i = 0; i < Define.ins().splitfile_book_name_2.length; i++) {
                            // 만약 현재 찾은 책의 이름이 읽고 있는 책 서재에 저장되어 있다면
                            if (Define.ins().splitfile_book_name_2[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // 읽은 책에 저장
                                Define.ins().temp_startday_1 += tv_beginning_date1.getText() + "#";
                                Define.ins().temp_lastday += tv_close_date_1.getText() + "#";
                                Define.ins().temp_book_name_1 += Define.ins().BookName;
                                Define.ins().temp_book_aut_1 += Define.ins().BookAuthor;
                                Define.ins().temp_image_url_1 += Define.ins().BookImageURL;
                                Define.ins().temp_today_1 += getTime() + "#";

                                // 읽고 있는 책에 있는 정보를 삭제
                                Define.ins().temp_startday_2 = replaceLast(Define.ins().temp_startday_2, Define.ins().splitfile_startday_2[i] + "#", "");
                                Define.ins().temp_book_name_2 = replaceLast(Define.ins().temp_book_name_2, Define.ins().splitfile_book_name_2[i] + "#", "");
                                Define.ins().temp_book_aut_2 = replaceLast(Define.ins().temp_book_aut_2, Define.ins().splitfile_book_aut_2[i] + "#", "");
                                Define.ins().temp_image_url_2 = replaceLast(Define.ins().temp_image_url_2, Define.ins().splitfile_img_url_2[i] + "#", "");
                                Define.ins().temp_book_total = replaceLast(Define.ins().temp_book_total, Define.ins().splitfile_book_total[i] + "#", "");
                                Define.ins().temp_book_reading = replaceLast(Define.ins().temp_book_reading, Define.ins().splitfile_book_reading[i] + "#", "");
                                Define.ins().temp_today_2 = replaceLast(Define.ins().temp_today_2, Define.ins().splitfile_today_2[i] + "#", "");
                                Define.ins().percent = replaceLast(Define.ins().percent, Define.ins().splitfile_persent[i] + "#", "");
                            }
                        }
                        // 읽고 싶은 책의 갯수 만큼 반복
                        for (int i = 0; i < Define.ins().splitfile_book_name_3.length; i++) {
                            // 만약 현재 찾은 책의 이름이 읽고 싶은 책 서재에 저장되어 있다면
                            if (Define.ins().splitfile_book_name_3[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // 읽은 책에 저장
                                Define.ins().temp_startday_1 += tv_beginning_date1.getText() + "#";
                                Define.ins().temp_lastday += tv_close_date_1.getText() + "#";
                                Define.ins().temp_book_name_1 += Define.ins().BookName;
                                Define.ins().temp_book_aut_1 += Define.ins().BookAuthor;
                                Define.ins().temp_image_url_1 += Define.ins().BookImageURL;
                                Define.ins().temp_today_1 += getTime() + "#";

                                // 읽고 싶은 책에 있는 정보를 삭제
                                Define.ins().temp_book_name_3 = replaceLast(Define.ins().temp_book_name_3, Define.ins().splitfile_book_name_3[i] + "#", "");
                                Define.ins().temp_book_aut_3 = replaceLast(Define.ins().temp_book_aut_3, Define.ins().splitfile_book_aut_3[i] + "#", "");
                                Define.ins().temp_image_url_3 = replaceLast(Define.ins().temp_image_url_3, Define.ins().splitfile_img_url_3[i] + "#", "");
                                Define.ins().temp_today_3 = replaceLast(Define.ins().temp_today_3, Define.ins().splitfile_today_3[i] + "#", "");
                                Define.ins().temp_book_exp = replaceLast(Define.ins().temp_book_exp, Define.ins().splitfile_book_exp[i] + "#", "");
                            }
                        }
                        Define.ins().overlap = 0;   // 덮어쓰기 과정을 끝냈으므로 변수 초기화
                    }
                    else {  // 만약 덮어쓰기 과정이 아니라면 (새롭게 저장하는 책)
                        // 읽은 책 정보에 현재 책 정보를 더한다
                        Define.ins().temp_startday_1 += tv_beginning_date1.getText() + "#";
                        Define.ins().temp_lastday += tv_close_date_1.getText() + "#";
                        Define.ins().temp_book_name_1 += Define.ins().BookName;
                        Define.ins().temp_book_aut_1 += Define.ins().BookAuthor;
                        Define.ins().temp_image_url_1 += Define.ins().BookImageURL;
                        Define.ins().temp_today_1 += getTime() + "#";
                    }

                    // 파일 값 갱신
                    cFileIOStreamWrite.writeData("startday_1", Define.ins().temp_startday_1);
                    cFileIOStreamWrite.writeData("lastday", Define.ins().temp_lastday);
                    cFileIOStreamWrite.writeData("book_name_1", Define.ins().temp_book_name_1);
                    cFileIOStreamWrite.writeData("book_aut_1", Define.ins().temp_book_aut_1);
                    cFileIOStreamWrite.writeData("image_url_1", Define.ins().temp_image_url_1);
                    cFileIOStreamWrite.writeData("today_1", Define.ins().temp_today_1);

                    cFileIOStreamWrite.writeData("startday_2", Define.ins().temp_startday_2);
                    cFileIOStreamWrite.writeData("book_name_2", Define.ins().temp_book_name_2);
                    cFileIOStreamWrite.writeData("book_aut_2", Define.ins().temp_book_aut_2);
                    cFileIOStreamWrite.writeData("image_url_2", Define.ins().temp_image_url_2);
                    cFileIOStreamWrite.writeData("book_total", Define.ins().temp_book_total);
                    cFileIOStreamWrite.writeData("book_reading", Define.ins().temp_book_reading);
                    cFileIOStreamWrite.writeData("today_2", Define.ins().temp_today_2);
                    cFileIOStreamWrite.writeData("percentage", Define.ins().percent);

                    cFileIOStreamWrite.writeData("image_url_3", Define.ins().temp_image_url_3);
                    cFileIOStreamWrite.writeData("book_name_3", Define.ins().temp_book_name_3);
                    cFileIOStreamWrite.writeData("book_exp", Define.ins().temp_book_exp);
                    cFileIOStreamWrite.writeData("today_3", Define.ins().temp_today_3);
                    cFileIOStreamWrite.writeData("book_aut_3", Define.ins().temp_book_aut_3);

                    bottomSheetDialog.dismiss();        // 다이얼로그 종료
                }
                break;
            // 읽고 있는 책 버튼 누른 상태에서 읽은 책 버튼 눌렀을 경우
            case R.id.btn_readbook2:
                Define.ins().temp = 1;
                bottomSheetDialog.setContentView(v1);
                break;
            // 읽고 있는 책 버튼 누른 상태에서 읽고 있는 책 버튼 눌렀을 경우
            case R.id.btn_readingbook2:
                Define.ins().temp = 2;
                bottomSheetDialog.setContentView(v2);
                break;
            // 읽고 있는 책 버튼 누른 상태에서 읽고 싶은 책 버튼 눌렀을 경우
            case R.id.btn_wanttoreadbook2:
                Define.ins().temp = 3;
                bottomSheetDialog.setContentView(v3);
                break;
            // 읽고 있는 책 버튼 누른 상태에서 시작일 텍스트뷰 눌렀을 경우
            case R.id.tv_beginning_date2:
                setStartDate();
                break;
            // 읽고 있는 책 버튼 누른 상태에서 저장하기 버튼 눌렀을 경우
            case R.id.btn_save2:
                // 만약 적지 않은 정보가 있다면
                if (et_sum_page.length() == 0 || et_current_page.length() == 0 | Integer.parseInt(et_current_page.getText().toString()) >= Integer.parseInt(et_sum_page.getText().toString())) {
                    Toast.makeText(aBottomSheetDialog, "페이지 수를 제대로 입력해 주세요", Toast.LENGTH_SHORT).show();
                }
                else if (tv_beginning_date.getText().equals("시작일을 선택해주세요")) {
                    Toast.makeText(aBottomSheetDialog, "시작일을 제대로 선택해 주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Define.ins().overlap == 1) {        // 만약 덮어쓰기 과정이라면
                        // split 값 갱신
                        Define.ins().splitfile_img_url_1 = cFileIOStreamRead.readData("image_url_1").split("#");
                        Define.ins().splitfile_book_name_1 = cFileIOStreamRead.readData("book_name_1").split("#");
                        Define.ins().splitfile_book_aut_1 = cFileIOStreamRead.readData("book_aut_1").split("#");
                        Define.ins().splitfile_today_1 = cFileIOStreamRead.readData("today_1").split("#");
                        Define.ins().splitfile_startday_1 = cFileIOStreamRead.readData("startday_1").split("#");
                        Define.ins().splitfile_lastday = cFileIOStreamRead.readData("lastday").split("#");

                        Define.ins().splitfile_startday_2 = cFileIOStreamRead.readData("startday_2").split("#");
                        Define.ins().splitfile_img_url_2 = cFileIOStreamRead.readData("image_url_2").split("#");
                        Define.ins().splitfile_book_name_2 = cFileIOStreamRead.readData("book_name_2").split("#");
                        Define.ins().splitfile_book_aut_2 = cFileIOStreamRead.readData("book_aut_2").split("#");
                        Define.ins().splitfile_today_2 = cFileIOStreamRead.readData("today_2").split("#");
                        Define.ins().splitfile_book_total = cFileIOStreamRead.readData("book_total").split("#");
                        Define.ins().splitfile_book_reading = cFileIOStreamRead.readData("book_reading").split("#");
                        Define.ins().splitfile_persent = cFileIOStreamRead.readData("percentage").split("#");

                        Define.ins().splitfile_img_url_3 = cFileIOStreamRead.readData("image_url_3").split("#");
                        Define.ins().splitfile_book_name_3 = cFileIOStreamRead.readData("book_name_3").split("#");
                        Define.ins().splitfile_book_aut_3 = cFileIOStreamRead.readData("book_aut_3").split("#");
                        Define.ins().splitfile_today_3 = cFileIOStreamRead.readData("today_3").split("#");
                        Define.ins().splitfile_book_exp = cFileIOStreamRead.readData("book_exp").split("#");
                        // 읽은 책의 갯수 만큼 반복
                        for (int i = 0; i < Define.ins().splitfile_book_name_1.length; i++) {
                            // 만약 현재 찾은 책의 이름이 읽은 책 서재에 저장되어 있다면
                            if (Define.ins().splitfile_book_name_1[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // 읽고 있는 책에 저장
                                Define.ins().temp_startday_2 += tv_beginning_date.getText() + "#";
                                Define.ins().temp_book_name_2 += Define.ins().BookName;
                                Define.ins().temp_book_aut_2 += Define.ins().BookAuthor;
                                Define.ins().temp_image_url_2 += Define.ins().BookImageURL;
                                Define.ins().temp_book_total += Define.ins().TotalPage + "#";
                                Define.ins().temp_book_reading += Define.ins().ReadPage + "#";
                                Define.ins().temp_today_2 += getTime() + "#";
                                Define.ins().percent += ((int) (Double.valueOf(Define.ins().ReadPage) / Double.valueOf(Define.ins().TotalPage) * 100)) + "#";

                                // 읽은 책에 있는 정보 삭제
                                Define.ins().temp_startday_1 = replaceLast(Define.ins().temp_startday_1, Define.ins().splitfile_startday_1[i] + "#", "");
                                Define.ins().temp_lastday = replaceLast(Define.ins().temp_lastday, Define.ins().splitfile_lastday[i] + "#", "");
                                Define.ins().temp_book_name_1 = replaceLast(Define.ins().temp_book_name_1, Define.ins().splitfile_book_name_1[i] + "#", "");
                                Define.ins().temp_book_aut_1 = replaceLast(Define.ins().temp_book_aut_1, Define.ins().splitfile_book_aut_1[i] + "#", "");
                                Define.ins().temp_image_url_1 = replaceLast(Define.ins().temp_image_url_1, Define.ins().splitfile_img_url_1[i] + "#", "");
                                Define.ins().temp_today_1 = replaceLast(Define.ins().temp_today_1, Define.ins().splitfile_today_1[i] + "#", "");
                            }
                        }
                        // 읽고 있는 책의 갯수 만큼 반복
                        for (int i = 0; i < Define.ins().splitfile_book_name_2.length; i++) {
                            // 만약 현재 찾은 책의 이름이 읽고 있는 책 서재에 저장되어 있다면
                            if (Define.ins().splitfile_book_name_2[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // 값을 덮어써준다
                                Define.ins().temp_startday_2 = replaceLast(Define.ins().temp_startday_2, Define.ins().splitfile_startday_2[i], tv_beginning_date.getText().toString());
                                Define.ins().temp_book_name_2 = replaceLast(Define.ins().temp_book_name_2, Define.ins().splitfile_book_name_2[i] + "#", Define.ins().BookName);
                                Define.ins().temp_book_aut_2 = replaceLast(Define.ins().temp_book_aut_2, Define.ins().splitfile_book_aut_2[i] + "#", Define.ins().BookAuthor);
                                Define.ins().temp_image_url_2 = replaceLast(Define.ins().temp_image_url_2, Define.ins().splitfile_img_url_2[i] + "#", Define.ins().BookImageURL);
                                Define.ins().temp_book_total = replaceLast(Define.ins().temp_book_total, Define.ins().splitfile_book_total[i], Define.ins().TotalPage);
                                Define.ins().temp_book_reading = replaceLast(Define.ins().temp_book_reading, Define.ins().splitfile_book_reading[i], Define.ins().ReadPage);
                                Define.ins().temp_today_2 = replaceLast(Define.ins().temp_today_2, Define.ins().splitfile_today_2[i] + "#", getTime());
                                Define.ins().percent = replaceLast(Define.ins().percent, Define.ins().splitfile_persent[i] + "#", String.valueOf((int) (Double.valueOf(Define.ins().ReadPage) / Double.valueOf(Define.ins().TotalPage) * 100)));
                            }
                        }
                        // 읽고 싶은 책의 갯수 만큼 반복
                        for (int i = 0; i < Define.ins().splitfile_book_name_3.length; i++) {
                            // 만약 현재 찾은 책의 이름이 읽고 싶은 책 서재에 저장되어 있다면
                            if (Define.ins().splitfile_book_name_3[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // 읽고 있는 책에 저장
                                Define.ins().temp_startday_2 += tv_beginning_date.getText() + "#";
                                Define.ins().temp_book_name_2 += Define.ins().BookName;
                                Define.ins().temp_book_aut_2 += Define.ins().BookAuthor;
                                Define.ins().temp_image_url_2 += Define.ins().BookImageURL;
                                Define.ins().temp_book_total += Define.ins().TotalPage + "#";
                                Define.ins().temp_book_reading += Define.ins().ReadPage + "#";
                                Define.ins().temp_today_2 += getTime() + "#";
                                Define.ins().percent += ((int) (Double.valueOf(Define.ins().ReadPage) / Double.valueOf(Define.ins().TotalPage) * 100)) + "#";

                                // 읽고 싶은 책의 정보 삭제
                                Define.ins().temp_book_name_3 = replaceLast(Define.ins().temp_book_name_3, Define.ins().splitfile_book_name_3[i] + "#", "");
                                Define.ins().temp_book_aut_3 = replaceLast(Define.ins().temp_book_aut_3, Define.ins().splitfile_book_aut_3[i] + "#", "");
                                Define.ins().temp_image_url_3 = replaceLast(Define.ins().temp_image_url_3, Define.ins().splitfile_img_url_3[i] + "#", "");
                                Define.ins().temp_today_3 = replaceLast(Define.ins().temp_today_3, Define.ins().splitfile_today_3[i] + "#", "");
                                Define.ins().temp_book_exp = replaceLast(Define.ins().temp_book_exp, Define.ins().splitfile_book_exp[i] + "#", "");
                            }
                        }
                        Define.ins().overlap = 0;       // 덮어쓰기 과정 종료
                    }
                    else {  // 덮어쓰기 과정이 아니라면
                        // 읽고 있는 책에 현재 책의 정보를 더한다.
                        Define.ins().temp_startday_2 += tv_beginning_date.getText() + "#";
                        Define.ins().temp_book_name_2 += Define.ins().BookName;
                        Define.ins().temp_book_aut_2 += Define.ins().BookAuthor;
                        Define.ins().temp_image_url_2 += Define.ins().BookImageURL;
                        Define.ins().temp_book_total += Define.ins().TotalPage + "#";
                        Define.ins().temp_book_reading += Define.ins().ReadPage + "#";
                        Define.ins().temp_today_2 += getTime() + "#";
                        Define.ins().percent += ((int) (Double.valueOf(Define.ins().ReadPage) / Double.valueOf(Define.ins().TotalPage) * 100)) + "#";
                    }

                    // 파일 값 갱신
                    cFileIOStreamWrite.writeData("startday_1", Define.ins().temp_startday_1);
                    cFileIOStreamWrite.writeData("lastday", Define.ins().temp_lastday);
                    cFileIOStreamWrite.writeData("book_name_1", Define.ins().temp_book_name_1);
                    cFileIOStreamWrite.writeData("book_aut_1", Define.ins().temp_book_aut_1);
                    cFileIOStreamWrite.writeData("image_url_1", Define.ins().temp_image_url_1);
                    cFileIOStreamWrite.writeData("today_1", Define.ins().temp_today_1);

                    cFileIOStreamWrite.writeData("startday_2", Define.ins().temp_startday_2);
                    cFileIOStreamWrite.writeData("book_name_2", Define.ins().temp_book_name_2);
                    cFileIOStreamWrite.writeData("book_aut_2", Define.ins().temp_book_aut_2);
                    cFileIOStreamWrite.writeData("image_url_2", Define.ins().temp_image_url_2);
                    cFileIOStreamWrite.writeData("book_total", Define.ins().temp_book_total);
                    cFileIOStreamWrite.writeData("book_reading", Define.ins().temp_book_reading);
                    cFileIOStreamWrite.writeData("today_2", Define.ins().temp_today_2);
                    cFileIOStreamWrite.writeData("percentage", Define.ins().percent);

                    cFileIOStreamWrite.writeData("image_url_3", Define.ins().temp_image_url_3);
                    cFileIOStreamWrite.writeData("book_name_3", Define.ins().temp_book_name_3);
                    cFileIOStreamWrite.writeData("book_exp", Define.ins().temp_book_exp);
                    cFileIOStreamWrite.writeData("today_3", Define.ins().temp_today_3);
                    cFileIOStreamWrite.writeData("book_aut_3", Define.ins().temp_book_aut_3);

                    bottomSheetDialog.dismiss();        // 다이얼로그 종료
                }
                break;
            // 읽고 싶은 책 버튼 누른 상태에서 읽은 책 버튼 눌렀을 경우
            case R.id.btn_readbook3:
                Define.ins().temp = 1;
                bottomSheetDialog.setContentView(v1);
                break;
            // 읽고 싶은 책 버튼 누른 상태에서 읽고 있는 버튼 눌렀을 경우
            case R.id.btn_readingbook3:
                Define.ins().temp = 2;
                bottomSheetDialog.setContentView(v2);
                break;
            // 읽고 싶은 책 버튼 누른 상태에서 읽고 싶은 책 버튼 눌렀을 경우
            case R.id.btn_wanttoreadbook3:
                Define.ins().temp = 3;
                bottomSheetDialog.setContentView(v3);
                break;
            // 읽고 싶은 책 버튼 누른 상태에서 저장하기 버튼 눌렀을 경우
            case R.id.btn_save3:
                // 기대평이 비어있을 경우
                if (et_expectation3.getText().length() == 0) {
                    Toast.makeText(aBottomSheetDialog, "기대평이 비어있습니다", Toast.LENGTH_SHORT).show();
                } else {
                    if (Define.ins().overlap == 1) {        // 덮어쓰기 과정일 경우
                        // split 값 갱신
                        Define.ins().splitfile_img_url_1 = cFileIOStreamRead.readData("image_url_1").split("#");
                        Define.ins().splitfile_book_name_1 = cFileIOStreamRead.readData("book_name_1").split("#");
                        Define.ins().splitfile_book_aut_1 = cFileIOStreamRead.readData("book_aut_1").split("#");
                        Define.ins().splitfile_today_1 = cFileIOStreamRead.readData("today_1").split("#");
                        Define.ins().splitfile_startday_1 = cFileIOStreamRead.readData("startday_1").split("#");
                        Define.ins().splitfile_lastday = cFileIOStreamRead.readData("lastday").split("#");

                        Define.ins().splitfile_startday_2 = cFileIOStreamRead.readData("startday_2").split("#");
                        Define.ins().splitfile_img_url_2 = cFileIOStreamRead.readData("image_url_2").split("#");
                        Define.ins().splitfile_book_name_2 = cFileIOStreamRead.readData("book_name_2").split("#");
                        Define.ins().splitfile_book_aut_2 = cFileIOStreamRead.readData("book_aut_2").split("#");
                        Define.ins().splitfile_today_2 = cFileIOStreamRead.readData("today_2").split("#");
                        Define.ins().splitfile_book_total = cFileIOStreamRead.readData("book_total").split("#");
                        Define.ins().splitfile_book_reading = cFileIOStreamRead.readData("book_reading").split("#");
                        Define.ins().splitfile_persent = cFileIOStreamRead.readData("percentage").split("#");

                        Define.ins().splitfile_img_url_3 = cFileIOStreamRead.readData("image_url_3").split("#");
                        Define.ins().splitfile_book_name_3 = cFileIOStreamRead.readData("book_name_3").split("#");
                        Define.ins().splitfile_book_aut_3 = cFileIOStreamRead.readData("book_aut_3").split("#");
                        Define.ins().splitfile_today_3 = cFileIOStreamRead.readData("today_3").split("#");
                        Define.ins().splitfile_book_exp = cFileIOStreamRead.readData("book_exp").split("#");
                        // 읽은 책의 갯수 만큼 반복
                        for (int i = 0; i < Define.ins().splitfile_book_name_1.length; i++) {
                            // 만약 현재 찾은 책의 이름이 읽은 책 서재에 저장되어 있다면
                            if (Define.ins().splitfile_book_name_1[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // 읽고 싶은 책에 저장
                                Define.ins().temp_image_url_3 += Define.ins().BookImageURL;
                                Define.ins().temp_book_name_3 += Define.ins().BookName;
                                Define.ins().temp_book_exp += Define.ins().Expectation + "#";
                                Define.ins().temp_today_3 += getTime() + "#";
                                Define.ins().temp_book_aut_3 += Define.ins().BookAuthor;

                                // 읽은 책의 정보 삭제
                                Define.ins().temp_startday_1 = replaceLast(Define.ins().temp_startday_1, Define.ins().splitfile_startday_1[i] + "#", "");
                                Define.ins().temp_lastday = replaceLast(Define.ins().temp_lastday, Define.ins().splitfile_lastday[i] + "#", "");
                                Define.ins().temp_book_name_1 = replaceLast(Define.ins().temp_book_name_1, Define.ins().splitfile_book_name_1[i] + "#", "");
                                Define.ins().temp_book_aut_1 = replaceLast(Define.ins().temp_book_aut_1, Define.ins().splitfile_book_aut_1[i] + "#", "");
                                Define.ins().temp_image_url_1 = replaceLast(Define.ins().temp_image_url_1, Define.ins().splitfile_img_url_1[i] + "#", "");
                                Define.ins().temp_today_1 = replaceLast(Define.ins().temp_today_1, Define.ins().splitfile_today_1[i] + "#", "");
                            }
                        }
                        // 읽고 있는 책의 갯수 만큼 반복
                        for (int i = 0; i < Define.ins().splitfile_book_name_2.length; i++) {
                            // 만약 현재 찾은 책의 이름이 읽고 있는 책 서재에 저장되어 있다면
                            if (Define.ins().splitfile_book_name_2[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // 읽고 싶은 책에 저장
                                Define.ins().temp_image_url_3 += Define.ins().BookImageURL;
                                Define.ins().temp_book_name_3 += Define.ins().BookName;
                                Define.ins().temp_book_exp += Define.ins().Expectation + "#";
                                Define.ins().temp_today_3 += getTime() + "#";
                                Define.ins().temp_book_aut_3 += Define.ins().BookAuthor;

                                // 읽고 있는 책의 정보 삭제
                                Define.ins().temp_startday_2 = replaceLast(Define.ins().temp_startday_2, Define.ins().splitfile_startday_2[i] + "#", "");
                                Define.ins().temp_book_name_2 = replaceLast(Define.ins().temp_book_name_2, Define.ins().splitfile_book_name_2[i] + "#", "");
                                Define.ins().temp_book_aut_2 = replaceLast(Define.ins().temp_book_aut_2, Define.ins().splitfile_book_aut_2[i] + "#", "");
                                Define.ins().temp_image_url_2 = replaceLast(Define.ins().temp_image_url_2, Define.ins().splitfile_img_url_2[i] + "#", "");
                                Define.ins().temp_book_total = replaceLast(Define.ins().temp_book_total, Define.ins().splitfile_book_total[i] + "#", "");
                                Define.ins().temp_book_reading = replaceLast(Define.ins().temp_book_reading, Define.ins().splitfile_book_reading[i] + "#", "");
                                Define.ins().temp_today_2 = replaceLast(Define.ins().temp_today_2, Define.ins().splitfile_today_2[i] + "#", "");
                                Define.ins().percent = replaceLast(Define.ins().percent, Define.ins().splitfile_persent[i] + "#", "");
                            }
                        }
                        // 읽고 싶은 책의 갯수 만큼 반복
                        for (int i = 0; i < Define.ins().splitfile_book_name_3.length; i++) {
                            // 만약 현재 찾은 책의 이름이 읽고 싶은 책 서재에 저장되어 있다면
                            if (Define.ins().splitfile_book_name_3[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // 값을 덮어써준다
                                Define.ins().temp_book_name_3 = replaceLast(Define.ins().temp_book_name_3, Define.ins().splitfile_book_name_3[i] + "#", Define.ins().BookName);
                                Define.ins().temp_book_aut_3 = replaceLast(Define.ins().temp_book_aut_3, Define.ins().splitfile_book_aut_3[i] + "#", Define.ins().BookAuthor);
                                Define.ins().temp_image_url_3 = replaceLast(Define.ins().temp_image_url_3, Define.ins().splitfile_img_url_3[i] + "#", Define.ins().BookImageURL);
                                Define.ins().temp_today_3 = replaceLast(Define.ins().temp_today_3, Define.ins().splitfile_today_3[i] + "#", getTime());
                                Define.ins().temp_book_exp = replaceLast(Define.ins().temp_book_exp, Define.ins().splitfile_book_exp[i], Define.ins().Expectation);
                            }
                        }
                        Define.ins().overlap = 0;   // 덮어쓰기 과정 종료
                    }
                    else {  // 덮어쓰기 과정이 아니라면
                        // 읽고 싶은 책에 현재 책의 정보를 더한다
                        Define.ins().temp_image_url_3 += Define.ins().BookImageURL;
                        Define.ins().temp_book_name_3 += Define.ins().BookName;
                        Define.ins().temp_book_exp += Define.ins().Expectation + "#";
                        Define.ins().temp_today_3 += getTime() + "#";
                        Define.ins().temp_book_aut_3 += Define.ins().BookAuthor;
                    }

                    // 파일 값 갱신
                    cFileIOStreamWrite.writeData("startday_1", Define.ins().temp_startday_1);
                    cFileIOStreamWrite.writeData("lastday", Define.ins().temp_lastday);
                    cFileIOStreamWrite.writeData("book_name_1", Define.ins().temp_book_name_1);
                    cFileIOStreamWrite.writeData("book_aut_1", Define.ins().temp_book_aut_1);
                    cFileIOStreamWrite.writeData("image_url_1", Define.ins().temp_image_url_1);
                    cFileIOStreamWrite.writeData("today_1", Define.ins().temp_today_1);

                    cFileIOStreamWrite.writeData("startday_2", Define.ins().temp_startday_2);
                    cFileIOStreamWrite.writeData("book_name_2", Define.ins().temp_book_name_2);
                    cFileIOStreamWrite.writeData("book_aut_2", Define.ins().temp_book_aut_2);
                    cFileIOStreamWrite.writeData("image_url_2", Define.ins().temp_image_url_2);
                    cFileIOStreamWrite.writeData("book_total", Define.ins().temp_book_total);
                    cFileIOStreamWrite.writeData("book_reading", Define.ins().temp_book_reading);
                    cFileIOStreamWrite.writeData("today_2", Define.ins().temp_today_2);
                    cFileIOStreamWrite.writeData("percentage", Define.ins().percent);

                    cFileIOStreamWrite.writeData("image_url_3", Define.ins().temp_image_url_3);
                    cFileIOStreamWrite.writeData("book_name_3", Define.ins().temp_book_name_3);
                    cFileIOStreamWrite.writeData("book_exp", Define.ins().temp_book_exp);
                    cFileIOStreamWrite.writeData("today_3", Define.ins().temp_today_3);
                    cFileIOStreamWrite.writeData("book_aut_3", Define.ins().temp_book_aut_3);

                    bottomSheetDialog.dismiss();    // 다이얼로그 종료
                }
                break;
        }

    }

    // 현재 시간을 불러옴
    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        System.out.println("날짜 확인 : " + mFormat.format(mDate));
        return mFormat.format(mDate);
    }

    // 시작일 설정
    public void setStartDate() {
        DatePickerDialog datePickerDialog;
        Calendar pickedDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        pickedDate.set(Current_Year, Current_Month - 1, Current_Day);

        datePickerDialog = new DatePickerDialog(
                aBottomSheetDialog,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Selected_Year = year;
                        Selected_Month = month + 1;
                        Selected_Day = dayOfMonth;

                        if (Selected_Month < 10) {  // 만약 월이 10월 이하일 경우
                            Selected_Month = Integer.parseInt("0" + Selected_Month);    // 0을 앞에 더해준다. (ex. 06)
                        }
                        if (Selected_Day < 10) {    // 만약 일이 10일 이하일 경우
                            Selected_Day = Integer.parseInt("0" + Selected_Day);        // 0을 앞에 더해준다.
                        }

                        Define.ins().Year = Selected_Year;
                        Define.ins().Month = Selected_Month;
                        Define.ins().Day = Selected_Day;

                        if (Define.ins().temp == 1) {   // '읽은 책' 상태일 경우
                            if (Selected_Month < 10 && Selected_Day < 10) {
                                tv_beginning_date1.setText(Selected_Year + ".0" + Selected_Month + ".0" + Selected_Day);
                                tv_beginning_date1.setTextColor(Color.BLACK);
                            } else if (Selected_Month >= 10 && Selected_Day < 10) {
                                tv_beginning_date1.setText(Selected_Year + "." + Selected_Month + ".0" + Selected_Day);
                                tv_beginning_date1.setTextColor(Color.BLACK);
                            } else if (Selected_Month < 10 && Selected_Day >= 10) {
                                tv_beginning_date1.setText(Selected_Year + ".0" + Selected_Month + "." + Selected_Day);
                                tv_beginning_date1.setTextColor(Color.BLACK);
                            } else {
                                tv_beginning_date1.setText(Selected_Year + "." + Selected_Month + "." + Selected_Day);
                                tv_beginning_date1.setTextColor(Color.BLACK);
                            }
                        } else if (Define.ins().temp == 2) {    // '읽고 있는 책' 상태일 경우
                            if (Selected_Month < 10 && Selected_Day < 10) {
                                tv_beginning_date.setText(Selected_Year + ".0" + Selected_Month + ".0" + Selected_Day);
                                tv_beginning_date.setTextColor(Color.BLACK);
                            } else if (Selected_Month >= 10 && Selected_Day < 10) {
                                tv_beginning_date.setText(Selected_Year + "." + Selected_Month + ".0" + Selected_Day);
                                tv_beginning_date.setTextColor(Color.BLACK);
                            } else if (Selected_Month < 10 && Selected_Day >= 10) {
                                tv_beginning_date.setText(Selected_Year + ".0" + Selected_Month + "." + Selected_Day);
                                tv_beginning_date.setTextColor(Color.BLACK);
                            } else {
                                tv_beginning_date.setText(Selected_Year + "." + Selected_Month + "." + Selected_Day);
                                tv_beginning_date.setTextColor(Color.BLACK);
                            }
                        }
                    }
                },
                // 2018.02.12
                pickedDate.get(Calendar.YEAR),
                pickedDate.get(Calendar.MONTH),
                pickedDate.get(Calendar.DATE)
        );

        maxDate.set(Current_Year, Current_Month - 1, Current_Day);
        datePickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());

        datePickerDialog.show();
    }

    // 종료일 설정
    public void setEndDate() {
        DatePickerDialog datePickerDialog;
        Calendar pickedDate = Calendar.getInstance();
        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        pickedDate.set(Current_Year, Current_Month - 1, Current_Day);

        datePickerDialog = new DatePickerDialog(
                aBottomSheetDialog,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Selected_Year = year;
                        Selected_Month = month + 1;
                        Selected_Day = dayOfMonth;

                        if (Selected_Month < 10 && Selected_Day < 10) {
                            tv_close_date_1.setText(Selected_Year + ".0" + Selected_Month + ".0" + Selected_Day);
                            tv_close_date_1.setTextColor(Color.BLACK);
                        } else if (Selected_Month >= 10 && Selected_Day < 10) {
                            tv_close_date_1.setText(Selected_Year + "." + Selected_Month + ".0" + Selected_Day);
                            tv_close_date_1.setTextColor(Color.BLACK);
                        } else if (Selected_Month < 10 && Selected_Day >= 10) {
                            tv_close_date_1.setText(Selected_Year + ".0" + Selected_Month + "." + Selected_Day);
                            tv_close_date_1.setTextColor(Color.BLACK);
                        } else {
                            tv_close_date_1.setText(Selected_Year + "." + Selected_Month + "." + Selected_Day);
                            tv_close_date_1.setTextColor(Color.BLACK);
                        }
                    }
                },
                //2018-2-12
                pickedDate.get(Calendar.YEAR),
                pickedDate.get(Calendar.MONTH),
                pickedDate.get(Calendar.DATE)
        );
        minDate.set(Define.ins().Year, Define.ins().Month - 1, Define.ins().Day);
        datePickerDialog.getDatePicker().setMinDate(minDate.getTime().getTime());

        maxDate.set(Current_Year, Current_Month - 1, Current_Day);
        datePickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());

        datePickerDialog.show();
    }

    public String replaceLast(String str, String regex, String replacement) {
        int regexIndexOf = str.lastIndexOf(regex);
        if (regexIndexOf == -1) {
            return str;
        } else {
            return str.substring(0, regexIndexOf) + replacement + str.substring(regexIndexOf + regex.length());
        }
    }

}