package kr.example.booklication.RecordActivity.Dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatActivity;

import kr.example.booklication.Define;
import kr.example.booklication.FileIOStream.FileIOStreamRead;
import kr.example.booklication.FileIOStream.FileIOStreamWrite;
import kr.example.booklication.RecordActivity.Fragment2ListView.Fragment2ListViewAdapter;

public class DeleteDialog2 {

    AppCompatActivity aDeleteDialog;
    FileIOStreamWrite cFileIOStreamWrite;
    FileIOStreamRead cFileIOStreamRead;
    Fragment2ListViewAdapter adapter2;

    public DeleteDialog2(AppCompatActivity appCompatActivity) {
        aDeleteDialog = appCompatActivity;
        cFileIOStreamWrite = new FileIOStreamWrite(aDeleteDialog);
        cFileIOStreamRead = new FileIOStreamRead(aDeleteDialog);
    }

    public void OnClickHandler(int pos, Fragment2ListViewAdapter adapter) {
        adapter2 = adapter;


        AlertDialog.Builder builder = new AlertDialog.Builder(aDeleteDialog);
        builder.setTitle("책 삭제").setMessage("저장한 책을 삭제할까요?");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                adapter2.lstlistViewItem.clear();
                Define.ins().temp_startday_2 = "";
                Define.ins().temp_book_name_2 = "";
                Define.ins().temp_book_aut_2 = "";
                Define.ins().temp_image_url_2 = "";
                Define.ins().temp_book_total = "";
                Define.ins().temp_book_reading = "";
                Define.ins().temp_today_2 = "";
                Define.ins().percent = "";

                Define.ins().splitfile_startday_2 = cFileIOStreamRead.readData("startday_2").split("#");
                Define.ins().splitfile_book_name_2 = cFileIOStreamRead.readData("book_name_2").split("#");
                Define.ins().splitfile_book_aut_2 = cFileIOStreamRead.readData("book_aut_2").split("#");
                Define.ins().splitfile_today_2 = cFileIOStreamRead.readData("today_2").split("#");
                Define.ins().splitfile_img_url_2 = cFileIOStreamRead.readData("image_url_2").split("#");
                Define.ins().splitfile_book_total = cFileIOStreamRead.readData("book_total").split("#");
                Define.ins().splitfile_book_reading = cFileIOStreamRead.readData("book_reading").split("#");
                Define.ins().splitfile_persent = cFileIOStreamRead.readData("percentage").split("#");

                Define.ins().splitfile_startday_2[pos] = Define.ins().splitfile_startday_2[pos].replace(Define.ins().splitfile_startday_2[pos], "");
                Define.ins().splitfile_book_name_2[pos] = Define.ins().splitfile_book_name_2[pos].replace(Define.ins().splitfile_book_name_2[pos], "");
                Define.ins().splitfile_book_aut_2[pos] = Define.ins().splitfile_book_aut_2[pos].replace(Define.ins().splitfile_book_aut_2[pos], "");
                Define.ins().splitfile_today_2[pos] = Define.ins().splitfile_today_2[pos].replace(Define.ins().splitfile_today_2[pos], "");
                Define.ins().splitfile_img_url_2[pos] = Define.ins().splitfile_img_url_2[pos].replace(Define.ins().splitfile_img_url_2[pos], "");
                Define.ins().splitfile_book_total[pos] = Define.ins().splitfile_book_total[pos].replace(Define.ins().splitfile_book_total[pos], "");
                Define.ins().splitfile_book_reading[pos] = Define.ins().splitfile_book_reading[pos].replace(Define.ins().splitfile_book_reading[pos], "");
                Define.ins().splitfile_persent[pos] = Define.ins().splitfile_persent[pos].replace(Define.ins().splitfile_persent[pos], "");

                for (int i = 0; i < Define.ins().splitfile_book_name_2.length; i++) {
                    if (Define.ins().splitfile_startday_2[i] != "" |
                            Define.ins().splitfile_book_name_2[i] != "" |
                            Define.ins().splitfile_book_aut_2[i] != "" |
                            Define.ins().splitfile_today_2[i] != "" |
                            Define.ins().splitfile_img_url_2[i] != ""|
                            Define.ins().splitfile_book_total[i] != ""|
                            Define.ins().splitfile_book_reading[i] != ""|
                            Define.ins().splitfile_persent[i] != "") {
                        Define.ins().temp_startday_2 += Define.ins().splitfile_startday_2[i] + "#";
                        Define.ins().temp_book_name_2 += Define.ins().splitfile_book_name_2[i] + "#";
                        Define.ins().temp_book_aut_2 += Define.ins().splitfile_book_aut_2[i] + "#";
                        Define.ins().temp_today_2 += Define.ins().splitfile_today_2[i] + "#";
                        Define.ins().temp_image_url_2 += Define.ins().splitfile_img_url_2[i] + "#";
                        Define.ins().temp_book_total += Define.ins().splitfile_book_total[i] + "#";
                        Define.ins().temp_book_reading += Define.ins().splitfile_book_reading[i] + "#";
                        Define.ins().percent += Define.ins().splitfile_persent[i] + "#";
                    }
                }
                    
                cFileIOStreamWrite.writeData("startday_2", Define.ins().temp_startday_2);
                cFileIOStreamWrite.writeData("book_name_2", Define.ins().temp_book_name_2);
                cFileIOStreamWrite.writeData("book_aut_2", Define.ins().temp_book_aut_2);
                cFileIOStreamWrite.writeData("image_url_2", Define.ins().temp_image_url_2);
                cFileIOStreamWrite.writeData("book_total", Define.ins().temp_book_total);
                cFileIOStreamWrite.writeData("book_reading", Define.ins().temp_book_reading);
                cFileIOStreamWrite.writeData("today_2", Define.ins().temp_today_2);
                cFileIOStreamWrite.writeData("percentage", Define.ins().percent);
                Define.ins().splitfile_startday_2 = cFileIOStreamRead.readData("startday_2").split("#");
                Define.ins().splitfile_img_url_2 = cFileIOStreamRead.readData("image_url_2").split("#");
                Define.ins().splitfile_book_name_2 = cFileIOStreamRead.readData("book_name_2").split("#");
                Define.ins().splitfile_book_aut_2 = cFileIOStreamRead.readData("book_aut_2").split("#");
                Define.ins().splitfile_today_2 = cFileIOStreamRead.readData("today_2").split("#");
                Define.ins().splitfile_book_total = cFileIOStreamRead.readData("book_total").split("#");
                Define.ins().splitfile_book_reading = cFileIOStreamRead.readData("book_reading").split("#");
                Define.ins().splitfile_persent = cFileIOStreamRead.readData("percentage").split("#");
                if (cFileIOStreamRead.readData("book_reading").length() == 0 |
                        cFileIOStreamRead.readData("book_total").length() == 0 |
                        cFileIOStreamRead.readData("image_url_2").length() == 0 |
                        cFileIOStreamRead.readData("book_name_2").length() == 0 |
                        cFileIOStreamRead.readData("book_aut_2").length() == 0 |
                        cFileIOStreamRead.readData("startday_2").length() == 0 |
                        cFileIOStreamRead.readData("percentage").length() == 0) {
                    adapter2.addItem(null, "저장된 책이 없습니다.", "", "", 0, "0%", "");
                }else {
                    for (int i = 0; i < Define.ins().splitfile_book_name_2.length; i++) {
                        adapter2.addItem(
                                Define.ins().splitfile_img_url_2[i],
                                Define.ins().splitfile_book_name_2[i],
                                Define.ins().splitfile_book_aut_2[i],
                                Define.ins().splitfile_startday_2[i],
                                Integer.valueOf(Define.ins().splitfile_persent[i]),
                                Integer.valueOf(Define.ins().splitfile_persent[i]) + "%",
                                Define.ins().splitfile_book_reading[i] + "/" + Define.ins().splitfile_book_total[i]);
                    }
                }
                adapter2.notifyDataSetChanged();
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
