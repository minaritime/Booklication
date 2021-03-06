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

    // ????????? ??????????????? ????????? ????????????.
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
        et_sum_page.addTextChangedListener(new TextWatcher() {      // edittext ?????????
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Define.ins().TotalPage = s.toString();      // ?????? ??? ????????? ??????
//                System.out.println("?????? : " + Define.ins().TotalPage);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_current_page = (EditText) v2.findViewById(R.id.et_current_page2);
        et_current_page.addTextChangedListener(new TextWatcher() {      // edittext ?????????
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Define.ins().ReadPage = s.toString();      // ?????? ????????? ??????
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
        et_expectation3.addTextChangedListener(new TextWatcher() {      // edittext ?????????
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Define.ins().Expectation = s.toString();        // ????????? ??????
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

        //??????????????? ????????? ????????????
        //bottomSheetDialog.setCanceledOnTouchOutside(false); //????????? ??????????????? ???????????????

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
            // ?????? ??? ????????? ?????? ???????????? ?????? ??? ?????? ????????? ??????
            case R.id.btn_readbook1:
                Define.ins().temp = 1;
                bottomSheetDialog.setContentView(v1);       // ??????????????????????????? View ??????
                break;
            // ?????? ??? ????????? ?????? ???????????? ?????? ?????? ??? ?????? ????????? ??????
            case R.id.btn_readingbook1:
                Define.ins().temp = 2;
                bottomSheetDialog.setContentView(v2);       // ??????????????????????????? View ??????
                break;
            // ?????? ??? ????????? ?????? ???????????? ?????? ?????? ??? ?????? ????????? ??????
            case R.id.btn_wanttoreadbook1:
                Define.ins().temp = 3;
                bottomSheetDialog.setContentView(v3);       // ??????????????????????????? View ??????
                break;
            // ?????? ??? ????????? ?????? ???????????? ????????? ???????????? ????????? ??????
            case R.id.tv_beginning_date1:
                setStartDate();
                break;
            // ?????? ??? ????????? ?????? ???????????? ????????? ???????????? ????????? ??????
            case R.id.tv_close_date1:
                // ?????? ???????????? ???????????? ????????? ??????
                if (tv_beginning_date1.getText().equals("???????????? ??????????????????")) {
                    Toast.makeText(aBottomSheetDialog, "???????????? ?????? ????????? ?????????.", Toast.LENGTH_SHORT).show();
                } else {
                    setEndDate();
                }
                break;
            // ?????? ??? ?????? ?????? ???????????? ???????????? ?????? ????????? ??????
            case R.id.btn_save1:
                // ???????????? ???????????? ???????????? ????????? ??????
                if (tv_beginning_date1.getText().equals("???????????? ??????????????????") || tv_close_date_1.getText().equals("???????????? ??????????????????")) {
                    Toast.makeText(aBottomSheetDialog, "????????? ????????? ????????? ?????????", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Define.ins().overlap == 1) {    // ?????? ???????????? ???????????????
                        // split ??? ??????
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
                        // ?????? ?????? ?????? ?????? ??????
                        for (int i = 0; i < Define.ins().splitfile_book_name_1.length; i++) {
                            // ?????? ?????? ?????? ?????? ????????? ?????? ??? ????????? ???????????? ?????????
                            if (Define.ins().splitfile_book_name_1[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // ?????? ???????????????
                                Define.ins().temp_startday_1 = replaceLast(Define.ins().temp_startday_1, Define.ins().splitfile_startday_1[i], tv_beginning_date1.getText().toString());
                                Define.ins().temp_lastday = replaceLast(Define.ins().temp_lastday, Define.ins().splitfile_lastday[i], tv_close_date_1.getText().toString());
                                Define.ins().temp_book_name_1 = replaceLast(Define.ins().temp_book_name_1, Define.ins().splitfile_book_name_1[i] + "#", Define.ins().BookName);
                                Define.ins().temp_book_aut_1 = replaceLast(Define.ins().temp_book_aut_1, Define.ins().splitfile_book_aut_1[i] + "#", Define.ins().BookAuthor);
                                Define.ins().temp_image_url_1 = replaceLast(Define.ins().temp_image_url_1, Define.ins().splitfile_img_url_1[i] + "#", Define.ins().BookImageURL);
                                Define.ins().temp_today_1 = replaceLast(Define.ins().temp_today_1, Define.ins().splitfile_today_1[i] + "#", getTime());
                            }
                        }
                        // ?????? ?????? ?????? ?????? ?????? ??????
                        for (int i = 0; i < Define.ins().splitfile_book_name_2.length; i++) {
                            // ?????? ?????? ?????? ?????? ????????? ?????? ?????? ??? ????????? ???????????? ?????????
                            if (Define.ins().splitfile_book_name_2[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // ?????? ?????? ??????
                                Define.ins().temp_startday_1 += tv_beginning_date1.getText() + "#";
                                Define.ins().temp_lastday += tv_close_date_1.getText() + "#";
                                Define.ins().temp_book_name_1 += Define.ins().BookName;
                                Define.ins().temp_book_aut_1 += Define.ins().BookAuthor;
                                Define.ins().temp_image_url_1 += Define.ins().BookImageURL;
                                Define.ins().temp_today_1 += getTime() + "#";

                                // ?????? ?????? ?????? ?????? ????????? ??????
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
                        // ?????? ?????? ?????? ?????? ?????? ??????
                        for (int i = 0; i < Define.ins().splitfile_book_name_3.length; i++) {
                            // ?????? ?????? ?????? ?????? ????????? ?????? ?????? ??? ????????? ???????????? ?????????
                            if (Define.ins().splitfile_book_name_3[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // ?????? ?????? ??????
                                Define.ins().temp_startday_1 += tv_beginning_date1.getText() + "#";
                                Define.ins().temp_lastday += tv_close_date_1.getText() + "#";
                                Define.ins().temp_book_name_1 += Define.ins().BookName;
                                Define.ins().temp_book_aut_1 += Define.ins().BookAuthor;
                                Define.ins().temp_image_url_1 += Define.ins().BookImageURL;
                                Define.ins().temp_today_1 += getTime() + "#";

                                // ?????? ?????? ?????? ?????? ????????? ??????
                                Define.ins().temp_book_name_3 = replaceLast(Define.ins().temp_book_name_3, Define.ins().splitfile_book_name_3[i] + "#", "");
                                Define.ins().temp_book_aut_3 = replaceLast(Define.ins().temp_book_aut_3, Define.ins().splitfile_book_aut_3[i] + "#", "");
                                Define.ins().temp_image_url_3 = replaceLast(Define.ins().temp_image_url_3, Define.ins().splitfile_img_url_3[i] + "#", "");
                                Define.ins().temp_today_3 = replaceLast(Define.ins().temp_today_3, Define.ins().splitfile_today_3[i] + "#", "");
                                Define.ins().temp_book_exp = replaceLast(Define.ins().temp_book_exp, Define.ins().splitfile_book_exp[i] + "#", "");
                            }
                        }
                        Define.ins().overlap = 0;   // ???????????? ????????? ??????????????? ?????? ?????????
                    }
                    else {  // ?????? ???????????? ????????? ???????????? (????????? ???????????? ???)
                        // ?????? ??? ????????? ?????? ??? ????????? ?????????
                        Define.ins().temp_startday_1 += tv_beginning_date1.getText() + "#";
                        Define.ins().temp_lastday += tv_close_date_1.getText() + "#";
                        Define.ins().temp_book_name_1 += Define.ins().BookName;
                        Define.ins().temp_book_aut_1 += Define.ins().BookAuthor;
                        Define.ins().temp_image_url_1 += Define.ins().BookImageURL;
                        Define.ins().temp_today_1 += getTime() + "#";
                    }

                    // ?????? ??? ??????
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

                    bottomSheetDialog.dismiss();        // ??????????????? ??????
                }
                break;
            // ?????? ?????? ??? ?????? ?????? ???????????? ?????? ??? ?????? ????????? ??????
            case R.id.btn_readbook2:
                Define.ins().temp = 1;
                bottomSheetDialog.setContentView(v1);
                break;
            // ?????? ?????? ??? ?????? ?????? ???????????? ?????? ?????? ??? ?????? ????????? ??????
            case R.id.btn_readingbook2:
                Define.ins().temp = 2;
                bottomSheetDialog.setContentView(v2);
                break;
            // ?????? ?????? ??? ?????? ?????? ???????????? ?????? ?????? ??? ?????? ????????? ??????
            case R.id.btn_wanttoreadbook2:
                Define.ins().temp = 3;
                bottomSheetDialog.setContentView(v3);
                break;
            // ?????? ?????? ??? ?????? ?????? ???????????? ????????? ???????????? ????????? ??????
            case R.id.tv_beginning_date2:
                setStartDate();
                break;
            // ?????? ?????? ??? ?????? ?????? ???????????? ???????????? ?????? ????????? ??????
            case R.id.btn_save2:
                // ?????? ?????? ?????? ????????? ?????????
                if (et_sum_page.length() == 0 || et_current_page.length() == 0 | Integer.parseInt(et_current_page.getText().toString()) >= Integer.parseInt(et_sum_page.getText().toString())) {
                    Toast.makeText(aBottomSheetDialog, "????????? ?????? ????????? ????????? ?????????", Toast.LENGTH_SHORT).show();
                }
                else if (tv_beginning_date.getText().equals("???????????? ??????????????????")) {
                    Toast.makeText(aBottomSheetDialog, "???????????? ????????? ????????? ?????????", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Define.ins().overlap == 1) {        // ?????? ???????????? ???????????????
                        // split ??? ??????
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
                        // ?????? ?????? ?????? ?????? ??????
                        for (int i = 0; i < Define.ins().splitfile_book_name_1.length; i++) {
                            // ?????? ?????? ?????? ?????? ????????? ?????? ??? ????????? ???????????? ?????????
                            if (Define.ins().splitfile_book_name_1[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // ?????? ?????? ?????? ??????
                                Define.ins().temp_startday_2 += tv_beginning_date.getText() + "#";
                                Define.ins().temp_book_name_2 += Define.ins().BookName;
                                Define.ins().temp_book_aut_2 += Define.ins().BookAuthor;
                                Define.ins().temp_image_url_2 += Define.ins().BookImageURL;
                                Define.ins().temp_book_total += Define.ins().TotalPage + "#";
                                Define.ins().temp_book_reading += Define.ins().ReadPage + "#";
                                Define.ins().temp_today_2 += getTime() + "#";
                                Define.ins().percent += ((int) (Double.valueOf(Define.ins().ReadPage) / Double.valueOf(Define.ins().TotalPage) * 100)) + "#";

                                // ?????? ?????? ?????? ?????? ??????
                                Define.ins().temp_startday_1 = replaceLast(Define.ins().temp_startday_1, Define.ins().splitfile_startday_1[i] + "#", "");
                                Define.ins().temp_lastday = replaceLast(Define.ins().temp_lastday, Define.ins().splitfile_lastday[i] + "#", "");
                                Define.ins().temp_book_name_1 = replaceLast(Define.ins().temp_book_name_1, Define.ins().splitfile_book_name_1[i] + "#", "");
                                Define.ins().temp_book_aut_1 = replaceLast(Define.ins().temp_book_aut_1, Define.ins().splitfile_book_aut_1[i] + "#", "");
                                Define.ins().temp_image_url_1 = replaceLast(Define.ins().temp_image_url_1, Define.ins().splitfile_img_url_1[i] + "#", "");
                                Define.ins().temp_today_1 = replaceLast(Define.ins().temp_today_1, Define.ins().splitfile_today_1[i] + "#", "");
                            }
                        }
                        // ?????? ?????? ?????? ?????? ?????? ??????
                        for (int i = 0; i < Define.ins().splitfile_book_name_2.length; i++) {
                            // ?????? ?????? ?????? ?????? ????????? ?????? ?????? ??? ????????? ???????????? ?????????
                            if (Define.ins().splitfile_book_name_2[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // ?????? ???????????????
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
                        // ?????? ?????? ?????? ?????? ?????? ??????
                        for (int i = 0; i < Define.ins().splitfile_book_name_3.length; i++) {
                            // ?????? ?????? ?????? ?????? ????????? ?????? ?????? ??? ????????? ???????????? ?????????
                            if (Define.ins().splitfile_book_name_3[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // ?????? ?????? ?????? ??????
                                Define.ins().temp_startday_2 += tv_beginning_date.getText() + "#";
                                Define.ins().temp_book_name_2 += Define.ins().BookName;
                                Define.ins().temp_book_aut_2 += Define.ins().BookAuthor;
                                Define.ins().temp_image_url_2 += Define.ins().BookImageURL;
                                Define.ins().temp_book_total += Define.ins().TotalPage + "#";
                                Define.ins().temp_book_reading += Define.ins().ReadPage + "#";
                                Define.ins().temp_today_2 += getTime() + "#";
                                Define.ins().percent += ((int) (Double.valueOf(Define.ins().ReadPage) / Double.valueOf(Define.ins().TotalPage) * 100)) + "#";

                                // ?????? ?????? ?????? ?????? ??????
                                Define.ins().temp_book_name_3 = replaceLast(Define.ins().temp_book_name_3, Define.ins().splitfile_book_name_3[i] + "#", "");
                                Define.ins().temp_book_aut_3 = replaceLast(Define.ins().temp_book_aut_3, Define.ins().splitfile_book_aut_3[i] + "#", "");
                                Define.ins().temp_image_url_3 = replaceLast(Define.ins().temp_image_url_3, Define.ins().splitfile_img_url_3[i] + "#", "");
                                Define.ins().temp_today_3 = replaceLast(Define.ins().temp_today_3, Define.ins().splitfile_today_3[i] + "#", "");
                                Define.ins().temp_book_exp = replaceLast(Define.ins().temp_book_exp, Define.ins().splitfile_book_exp[i] + "#", "");
                            }
                        }
                        Define.ins().overlap = 0;       // ???????????? ?????? ??????
                    }
                    else {  // ???????????? ????????? ????????????
                        // ?????? ?????? ?????? ?????? ?????? ????????? ?????????.
                        Define.ins().temp_startday_2 += tv_beginning_date.getText() + "#";
                        Define.ins().temp_book_name_2 += Define.ins().BookName;
                        Define.ins().temp_book_aut_2 += Define.ins().BookAuthor;
                        Define.ins().temp_image_url_2 += Define.ins().BookImageURL;
                        Define.ins().temp_book_total += Define.ins().TotalPage + "#";
                        Define.ins().temp_book_reading += Define.ins().ReadPage + "#";
                        Define.ins().temp_today_2 += getTime() + "#";
                        Define.ins().percent += ((int) (Double.valueOf(Define.ins().ReadPage) / Double.valueOf(Define.ins().TotalPage) * 100)) + "#";
                    }

                    // ?????? ??? ??????
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

                    bottomSheetDialog.dismiss();        // ??????????????? ??????
                }
                break;
            // ?????? ?????? ??? ?????? ?????? ???????????? ?????? ??? ?????? ????????? ??????
            case R.id.btn_readbook3:
                Define.ins().temp = 1;
                bottomSheetDialog.setContentView(v1);
                break;
            // ?????? ?????? ??? ?????? ?????? ???????????? ?????? ?????? ?????? ????????? ??????
            case R.id.btn_readingbook3:
                Define.ins().temp = 2;
                bottomSheetDialog.setContentView(v2);
                break;
            // ?????? ?????? ??? ?????? ?????? ???????????? ?????? ?????? ??? ?????? ????????? ??????
            case R.id.btn_wanttoreadbook3:
                Define.ins().temp = 3;
                bottomSheetDialog.setContentView(v3);
                break;
            // ?????? ?????? ??? ?????? ?????? ???????????? ???????????? ?????? ????????? ??????
            case R.id.btn_save3:
                // ???????????? ???????????? ??????
                if (et_expectation3.getText().length() == 0) {
                    Toast.makeText(aBottomSheetDialog, "???????????? ??????????????????", Toast.LENGTH_SHORT).show();
                } else {
                    if (Define.ins().overlap == 1) {        // ???????????? ????????? ??????
                        // split ??? ??????
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
                        // ?????? ?????? ?????? ?????? ??????
                        for (int i = 0; i < Define.ins().splitfile_book_name_1.length; i++) {
                            // ?????? ?????? ?????? ?????? ????????? ?????? ??? ????????? ???????????? ?????????
                            if (Define.ins().splitfile_book_name_1[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // ?????? ?????? ?????? ??????
                                Define.ins().temp_image_url_3 += Define.ins().BookImageURL;
                                Define.ins().temp_book_name_3 += Define.ins().BookName;
                                Define.ins().temp_book_exp += Define.ins().Expectation + "#";
                                Define.ins().temp_today_3 += getTime() + "#";
                                Define.ins().temp_book_aut_3 += Define.ins().BookAuthor;

                                // ?????? ?????? ?????? ??????
                                Define.ins().temp_startday_1 = replaceLast(Define.ins().temp_startday_1, Define.ins().splitfile_startday_1[i] + "#", "");
                                Define.ins().temp_lastday = replaceLast(Define.ins().temp_lastday, Define.ins().splitfile_lastday[i] + "#", "");
                                Define.ins().temp_book_name_1 = replaceLast(Define.ins().temp_book_name_1, Define.ins().splitfile_book_name_1[i] + "#", "");
                                Define.ins().temp_book_aut_1 = replaceLast(Define.ins().temp_book_aut_1, Define.ins().splitfile_book_aut_1[i] + "#", "");
                                Define.ins().temp_image_url_1 = replaceLast(Define.ins().temp_image_url_1, Define.ins().splitfile_img_url_1[i] + "#", "");
                                Define.ins().temp_today_1 = replaceLast(Define.ins().temp_today_1, Define.ins().splitfile_today_1[i] + "#", "");
                            }
                        }
                        // ?????? ?????? ?????? ?????? ?????? ??????
                        for (int i = 0; i < Define.ins().splitfile_book_name_2.length; i++) {
                            // ?????? ?????? ?????? ?????? ????????? ?????? ?????? ??? ????????? ???????????? ?????????
                            if (Define.ins().splitfile_book_name_2[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // ?????? ?????? ?????? ??????
                                Define.ins().temp_image_url_3 += Define.ins().BookImageURL;
                                Define.ins().temp_book_name_3 += Define.ins().BookName;
                                Define.ins().temp_book_exp += Define.ins().Expectation + "#";
                                Define.ins().temp_today_3 += getTime() + "#";
                                Define.ins().temp_book_aut_3 += Define.ins().BookAuthor;

                                // ?????? ?????? ?????? ?????? ??????
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
                        // ?????? ?????? ?????? ?????? ?????? ??????
                        for (int i = 0; i < Define.ins().splitfile_book_name_3.length; i++) {
                            // ?????? ?????? ?????? ?????? ????????? ?????? ?????? ??? ????????? ???????????? ?????????
                            if (Define.ins().splitfile_book_name_3[i].equals(Define.ins().BookName.substring(0, Define.ins().BookName.length() - 1))) {
                                // ?????? ???????????????
                                Define.ins().temp_book_name_3 = replaceLast(Define.ins().temp_book_name_3, Define.ins().splitfile_book_name_3[i] + "#", Define.ins().BookName);
                                Define.ins().temp_book_aut_3 = replaceLast(Define.ins().temp_book_aut_3, Define.ins().splitfile_book_aut_3[i] + "#", Define.ins().BookAuthor);
                                Define.ins().temp_image_url_3 = replaceLast(Define.ins().temp_image_url_3, Define.ins().splitfile_img_url_3[i] + "#", Define.ins().BookImageURL);
                                Define.ins().temp_today_3 = replaceLast(Define.ins().temp_today_3, Define.ins().splitfile_today_3[i] + "#", getTime());
                                Define.ins().temp_book_exp = replaceLast(Define.ins().temp_book_exp, Define.ins().splitfile_book_exp[i], Define.ins().Expectation);
                            }
                        }
                        Define.ins().overlap = 0;   // ???????????? ?????? ??????
                    }
                    else {  // ???????????? ????????? ????????????
                        // ?????? ?????? ?????? ?????? ?????? ????????? ?????????
                        Define.ins().temp_image_url_3 += Define.ins().BookImageURL;
                        Define.ins().temp_book_name_3 += Define.ins().BookName;
                        Define.ins().temp_book_exp += Define.ins().Expectation + "#";
                        Define.ins().temp_today_3 += getTime() + "#";
                        Define.ins().temp_book_aut_3 += Define.ins().BookAuthor;
                    }

                    // ?????? ??? ??????
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

                    bottomSheetDialog.dismiss();    // ??????????????? ??????
                }
                break;
        }

    }

    // ?????? ????????? ?????????
    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        System.out.println("?????? ?????? : " + mFormat.format(mDate));
        return mFormat.format(mDate);
    }

    // ????????? ??????
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

                        if (Selected_Month < 10) {  // ?????? ?????? 10??? ????????? ??????
                            Selected_Month = Integer.parseInt("0" + Selected_Month);    // 0??? ?????? ????????????. (ex. 06)
                        }
                        if (Selected_Day < 10) {    // ?????? ?????? 10??? ????????? ??????
                            Selected_Day = Integer.parseInt("0" + Selected_Day);        // 0??? ?????? ????????????.
                        }

                        Define.ins().Year = Selected_Year;
                        Define.ins().Month = Selected_Month;
                        Define.ins().Day = Selected_Day;

                        if (Define.ins().temp == 1) {   // '?????? ???' ????????? ??????
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
                        } else if (Define.ins().temp == 2) {    // '?????? ?????? ???' ????????? ??????
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

    // ????????? ??????
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