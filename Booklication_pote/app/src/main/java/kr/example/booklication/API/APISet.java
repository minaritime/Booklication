package kr.example.booklication.API;

import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AppCompatActivity;

import kr.example.booklication.API.APIwork.ApiExamSearchBlog;
import kr.example.booklication.API.APIwork.DownloadFilesTask;

public class APISet {

    AppCompatActivity aAPISet;
    ApiExamSearchBlog api;

    public APISet(AppCompatActivity appCompatActivity){
        aAPISet = appCompatActivity;
    }

    public void APISearch(String keyword){
        api = new ApiExamSearchBlog(aAPISet,keyword);
    }


}