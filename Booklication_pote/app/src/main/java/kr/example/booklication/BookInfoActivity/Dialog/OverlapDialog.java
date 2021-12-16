package kr.example.booklication.BookInfoActivity.Dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import kr.example.booklication.BookInfoActivity.Bottomsheetdialog.CustomBottomSheetDialog;
import kr.example.booklication.Define;
import kr.example.booklication.R;

public class OverlapDialog {
    Button Save;
    AppCompatActivity aOverlapDialog;
    CustomBottomSheetDialog bottomSheetDialog;

    public OverlapDialog(AppCompatActivity appCompatActivity){
        aOverlapDialog = appCompatActivity;
    }

    // 다이얼로그
    public void OnClickHandler() {
        Save = aOverlapDialog.findViewById(R.id.savebutton);
        bottomSheetDialog = new CustomBottomSheetDialog(aOverlapDialog);

        // 저장 버튼
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickHandler();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(aOverlapDialog);
        builder.setTitle("책 중복 저장").setMessage("저장하려는 책이 이미 존재해요! 다시 저장할까요?");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                // 바텀 시트 다이얼로그를 띄워줌
                bottomSheetDialog.callFunction();
                Define.ins().overlap = 1;
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                // 다이얼로그 종료
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
