package kr.example.booklication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Setmain cSetmain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cSetmain = new Setmain(this);
        cSetmain.Setting();
    }

    // 종료 애니메이션 제거 (생략가능)
    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(0, 0);
    }

}