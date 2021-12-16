package kr.example.booklication.RecordActivity.Dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatActivity;

import kr.example.booklication.Define;
import kr.example.booklication.FileIOStream.FileIOStreamRead;
import kr.example.booklication.FileIOStream.FileIOStreamWrite;
import kr.example.booklication.RecordActivity.Fragment3ListView.Fragment3ListViewAdapter;

public class DeleteDialog3 {

    AppCompatActivity aDeleteDialog;
    FileIOStreamWrite cFileIOStreamWrite;
    FileIOStreamRead cFileIOStreamRead;
    Fragment3ListViewAdapter adapter3;

    public DeleteDialog3(AppCompatActivity appCompatActivity) {
        aDeleteDialog = appCompatActivity;
        cFileIOStreamWrite = new FileIOStreamWrite(aDeleteDialog);
        cFileIOStreamRead = new FileIOStreamRead(aDeleteDialog);
    }

    //다이얼로그
    public void OnClickHandler(int pos, Fragment3ListViewAdapter adapter) {
        adapter3 = adapter;

        AlertDialog.Builder builder = new AlertDialog.Builder(aDeleteDialog);
        builder.setTitle("책 삭제").setMessage("저장한 책을 삭제할까요?");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                adapter3.lstlistViewItem.clear();
                Define.ins().temp_image_url_3 = "";
                Define.ins().temp_book_name_3 = "";
                Define.ins().temp_book_aut_3 = "";
                Define.ins().temp_today_3 = "";
                Define.ins().temp_book_exp = "";

                Define.ins().splitfile_img_url_3 = cFileIOStreamRead.readData("image_url_3").split("#");
                Define.ins().splitfile_book_name_3 = cFileIOStreamRead.readData("book_name_3").split("#");
                Define.ins().splitfile_book_aut_3 = cFileIOStreamRead.readData("book_aut_3").split("#");
                Define.ins().splitfile_today_3 = cFileIOStreamRead.readData("today_3").split("#");
                Define.ins().splitfile_book_exp = cFileIOStreamRead.readData("book_exp").split("#");

                Define.ins().splitfile_img_url_3[pos] = Define.ins().splitfile_img_url_3[pos].replace(Define.ins().splitfile_img_url_3[pos], "");
                Define.ins().splitfile_book_name_3[pos] = Define.ins().splitfile_book_name_3[pos].replace(Define.ins().splitfile_book_name_3[pos], "");
                Define.ins().splitfile_book_aut_3[pos] = Define.ins().splitfile_book_aut_3[pos].replace(Define.ins().splitfile_book_aut_3[pos], "");
                Define.ins().splitfile_today_3[pos] = Define.ins().splitfile_today_3[pos].replace(Define.ins().splitfile_today_3[pos], "");
                Define.ins().splitfile_book_exp[pos] = Define.ins().splitfile_book_exp[pos].replace(Define.ins().splitfile_book_exp[pos], "");

                for (int i = 0; i < Define.ins().splitfile_book_name_3.length; i++) {
                    if (Define.ins().splitfile_img_url_3[i] != "" |
                            Define.ins().splitfile_book_name_3[i] != "" |
                            Define.ins().splitfile_book_aut_3[i] != "" |
                            Define.ins().splitfile_today_3[i] != "" |
                            Define.ins().splitfile_book_exp[i] != "") {
                        Define.ins().temp_image_url_3 += Define.ins().splitfile_img_url_3[i] + "#";
                        Define.ins().temp_book_name_3 += Define.ins().splitfile_book_name_3[i] + "#";
                        Define.ins().temp_book_aut_3 += Define.ins().splitfile_book_aut_3[i] + "#";
                        Define.ins().temp_today_3 += Define.ins().splitfile_today_3[i] + "#";
                        Define.ins().temp_book_exp += Define.ins().splitfile_book_exp[i] + "#";
                    }
                }

                cFileIOStreamWrite.writeData("image_url_3", Define.ins().temp_image_url_3);
                cFileIOStreamWrite.writeData("book_name_3", Define.ins().temp_book_name_3);
                cFileIOStreamWrite.writeData("book_exp", Define.ins().temp_book_exp);
                cFileIOStreamWrite.writeData("today_3", Define.ins().temp_today_3);
                cFileIOStreamWrite.writeData("book_aut_3", Define.ins().temp_book_aut_3);
                Define.ins().splitfile_img_url_3 = cFileIOStreamRead.readData("image_url_3").split("#");
                Define.ins().splitfile_book_name_3 = cFileIOStreamRead.readData("book_name_3").split("#");
                Define.ins().splitfile_book_aut_3 = cFileIOStreamRead.readData("book_aut_3").split("#");
                Define.ins().splitfile_today_3 = cFileIOStreamRead.readData("today_3").split("#");
                Define.ins().splitfile_book_exp = cFileIOStreamRead.readData("book_exp").split("#");
                if (cFileIOStreamRead.readData("image_url_3").length() == 0 |
                        cFileIOStreamRead.readData("book_name_3").length() == 0 |
                        cFileIOStreamRead.readData("book_exp").length() == 0) {
                    adapter3.addItem(null, "저장된 책이 없습니다.", "");
                } else {
                    for (int i = 0; i < Define.ins().splitfile_book_name_3.length; i++) {
                        System.out.println("i result : " + i);
                        adapter3.addItem(
                                Define.ins().splitfile_img_url_3[i],
                                Define.ins().splitfile_book_name_3[i],
                                Define.ins().splitfile_book_exp[i]);
                    }
                }
                adapter3.notifyDataSetChanged();
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
