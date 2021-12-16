package kr.example.booklication.RecordActivity.Fragment2ListView;

import android.graphics.drawable.Drawable;

public class Fragment2ListViewItem {

    private String sImage;
    private String sTitle;
    private String sAuthor;
    private String sBeginDate;
    private int progressBar;
    private String sPercentage;
    private String sPage;

    public void setImage(String image3) {
        this.sImage = image3;
    }

    public String getImage() {
        return sImage;
    }

    public void setTitle(String title) {
        this.sTitle = title;
    }

    public String getTitle() {
        return sTitle;
    }

    public void setAuthor(String author) {
        this.sAuthor = author;
    }

    public String getAuthor() {
        return sAuthor;
    }

    public void setBeginDate(String begindate) {
        this.sBeginDate = begindate;
    }

    public String getBeginDate() {
        return sBeginDate;
    }

    public void setProgressBar(int progressBar){
        this.progressBar = progressBar;
    }

    public int getProgressBar(){
        return progressBar;
    }

    public void setPercentage(String percentage) {
        this.sPercentage = percentage;
    }

    public String getPercentage() {
        return sPercentage;
    }

    public void setPage(String page) {
        this.sPage = page;
    }

    public String getPage() {
        return sPage;
    }
}
