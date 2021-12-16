package kr.example.booklication.Loading;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import androidx.annotation.NonNull;

import kr.example.booklication.R;

public class Loading_dialog extends Dialog {
    public Loading_dialog(@NonNull Context context) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loading);
    }
}
