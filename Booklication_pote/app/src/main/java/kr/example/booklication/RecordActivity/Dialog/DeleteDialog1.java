package kr.example.booklication.RecordActivity.Dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import kr.example.booklication.Define;
import kr.example.booklication.FileIOStream.FileIOStreamRead;
import kr.example.booklication.FileIOStream.FileIOStreamWrite;
import kr.example.booklication.R;
import kr.example.booklication.RecordActivity.Fragment1ListView.Fragment1ListView;
import kr.example.booklication.RecordActivity.Fragment1ListView.Fragment1ListViewAdapter;
import kr.example.booklication.RecordActivity.Fragment2ListView.Fragment2ListViewAdapter;
import kr.example.booklication.RecordActivity.Fragment3ListView.Fragment3ListViewAdapter;

public class DeleteDialog1 {

    AppCompatActivity aDeleteDialog;
    FileIOStreamWrite cFileIOStreamWrite;
    FileIOStreamRead cFileIOStreamRead;
    Fragment1ListViewAdapter adapter1;

    public DeleteDialog1(AppCompatActivity appCompatActivity) {
        aDeleteDialog = appCompatActivity;
        cFileIOStreamWrite = new FileIOStreamWrite(aDeleteDialog);
        cFileIOStreamRead = new FileIOStreamRead(aDeleteDialog);
    }

    //다이얼로그
    public void OnClickHandler(int pos, Fragment1ListViewAdapter adapter) {
        adapter1 = adapter;

        AlertDialog.Builder builder = new AlertDialog.Builder(aDeleteDialog);
        builder.setTitle("책 삭제").setMessage("저장한 책을 삭제할까요?");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // 삭제
                adapter1.lstlistViewItem.clear();
                Define.ins().temp_startday_1 = "";
                Define.ins().temp_lastday = "";
                Define.ins().temp_book_name_1 = "";
                Define.ins().temp_book_aut_1 = "";
                Define.ins().temp_image_url_1 = "";
                Define.ins().temp_today_1 = "";

                Define.ins().splitfile_img_url_1 = cFileIOStreamRead.readData("image_url_1").split("#");
                Define.ins().splitfile_book_name_1 = cFileIOStreamRead.readData("book_name_1").split("#");
                Define.ins().splitfile_book_aut_1 = cFileIOStreamRead.readData("book_aut_1").split("#");
                Define.ins().splitfile_today_1 = cFileIOStreamRead.readData("today_1").split("#");
                Define.ins().splitfile_startday_1 = cFileIOStreamRead.readData("startday_1").split("#");
                Define.ins().splitfile_lastday = cFileIOStreamRead.readData("lastday").split("#");

                Define.ins().splitfile_img_url_1[pos] = Define.ins().splitfile_img_url_1[pos].replace(Define.ins().splitfile_img_url_1[pos], "");
                Define.ins().splitfile_book_name_1[pos] = Define.ins().splitfile_book_name_1[pos].replace(Define.ins().splitfile_book_name_1[pos], "");
                Define.ins().splitfile_book_aut_1[pos] = Define.ins().splitfile_book_aut_1[pos].replace(Define.ins().splitfile_book_aut_1[pos], "");
                Define.ins().splitfile_today_1[pos] = Define.ins().splitfile_today_1[pos].replace(Define.ins().splitfile_today_1[pos], "");
                Define.ins().splitfile_startday_1[pos] = Define.ins().splitfile_startday_1[pos].replace(Define.ins().splitfile_startday_1[pos], "");
                Define.ins().splitfile_lastday[pos] = Define.ins().splitfile_lastday[pos].replace(Define.ins().splitfile_lastday[pos], "");

                for (int i = 0; i < Define.ins().splitfile_book_name_1.length; i++) {
                    if (Define.ins().splitfile_img_url_1[i] != "" |
                            Define.ins().splitfile_book_name_1[i] != "" |
                            Define.ins().splitfile_book_aut_1[i] != "" |
                            Define.ins().splitfile_today_1[i] != "" |
                            Define.ins().splitfile_startday_1[i] != ""|
                            Define.ins().splitfile_lastday[i] != "") {
                        Define.ins().temp_image_url_1 += Define.ins().splitfile_img_url_1[i] + "#";
                        Define.ins().temp_book_name_1 += Define.ins().splitfile_book_name_1[i] + "#";
                        Define.ins().temp_book_aut_1 += Define.ins().splitfile_book_aut_1[i] + "#";
                        Define.ins().temp_today_1 += Define.ins().splitfile_today_1[i] + "#";
                        Define.ins().temp_startday_1 += Define.ins().splitfile_startday_1[i] + "#";
                        Define.ins().temp_lastday += Define.ins().splitfile_lastday[i] + "#";
                    }
                }
                
                cFileIOStreamWrite.writeData("startday_1", Define.ins().temp_startday_1);
                cFileIOStreamWrite.writeData("lastday", Define.ins().temp_lastday);
                cFileIOStreamWrite.writeData("book_name_1", Define.ins().temp_book_name_1);
                cFileIOStreamWrite.writeData("book_aut_1", Define.ins().temp_book_aut_1);
                cFileIOStreamWrite.writeData("image_url_1", Define.ins().temp_image_url_1);
                cFileIOStreamWrite.writeData("today_1", Define.ins().temp_today_1);
                Define.ins().splitfile_img_url_1 = cFileIOStreamRead.readData("image_url_1").split("#");
                Define.ins().splitfile_book_name_1 = cFileIOStreamRead.readData("book_name_1").split("#");
                Define.ins().splitfile_book_aut_1 = cFileIOStreamRead.readData("book_aut_1").split("#");
                Define.ins().splitfile_today_1 = cFileIOStreamRead.readData("today_1").split("#");
                Define.ins().splitfile_startday_1 = cFileIOStreamRead.readData("startday_1").split("#");
                Define.ins().splitfile_lastday = cFileIOStreamRead.readData("lastday").split("#");
                if (cFileIOStreamRead.readData("image_url_1").length() == 0 |
                        cFileIOStreamRead.readData("book_name_1").length() == 0 |
                        cFileIOStreamRead.readData("book_aut_1").length() == 0 |
                        cFileIOStreamRead.readData("startday_1").length() == 0 |
                        cFileIOStreamRead.readData("lastday").length() == 0) {
                    adapter1.addItem(null, "저장된 책이 없습니다.", "", "", "");
                } else {
                    for (int i = 0; i < Define.ins().splitfile_book_name_1.length; i++) {
                        adapter1.addItem(
                                Define.ins().splitfile_img_url_1[i],
                                Define.ins().splitfile_book_name_1[i],
                                Define.ins().splitfile_book_aut_1[i],
                                Define.ins().splitfile_startday_1[i],
                                Define.ins().splitfile_lastday[i]);
                    }
                }
                adapter1.notifyDataSetChanged();
//                    aDeleteDialog.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            adapter1.notifyDataSetChanged();
//                        }
//                    });

            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // 다이얼로그 종료
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
