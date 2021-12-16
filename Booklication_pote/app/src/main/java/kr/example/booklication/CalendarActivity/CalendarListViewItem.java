package kr.example.booklication.CalendarActivity;

public class CalendarListViewItem {
    private String sImage;
    private String sTitle;
    private String sWritter;

    public void setImage(String iamge) {
        this.sImage = iamge;
    }
    public void setTitle(String title) {
        this.sTitle = title;
    }
    public void setWritter(String writter) {
        this.sWritter = writter;
    }

    public String getImage() {
        return sImage;
    }
    public String getTitle() {
        return sTitle;
    }
    public String getWritter() {
        return sWritter;
    }
}