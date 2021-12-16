package kr.example.booklication.API.APIwork;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import kr.example.booklication.R;

//url을 이미지로 바꿔주는 클래스
public class DownloadFilesTask {

    AppCompatActivity aDownloadFilesTask;
    ImageView BookImage;

    public DownloadFilesTask(AppCompatActivity appCompatActivity, String url){
        aDownloadFilesTask = appCompatActivity;

        View v = aDownloadFilesTask.getLayoutInflater().inflate(R.layout.book_info_activity,null);

        BookImage = v.findViewById(R.id.idIV_BookImage);

        new setDownloadFilesTask().execute(url);
    }

    private class setDownloadFilesTask extends AsyncTask<String,Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null;
            try {
                // 이미지 url 넣는곳
                String img_url = strings[0]; //url of the image
                URL url = new URL(img_url);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // doInBackground 에서 받아온 total 값 사용 장소
//            BookImage.setImageBitmap(result);
        }

    }

}
