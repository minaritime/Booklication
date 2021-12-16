package kr.example.booklication.Search_ListView;

public class SearchListViewItem {
    String HomeiconImage_url ;
    String HometitleStr ;
    String HomedescStr ;
    String Homepublisher ;

    public void setUrl(String Url) {
        HomeiconImage_url = Url ;
    }
    public void setTitle(String title) {
        HometitleStr = title ;
    }
    public void setWriter(String desc) {
        HomedescStr = desc ;
    }
    public void setpublisher(String pub){
        Homepublisher = pub;
    }

    public String getUrl() { return this.HomeiconImage_url ; }
    public String getTitle() { return this.HometitleStr ; }
    public String getWriter() { return this.HomedescStr ; }
    public String getpublisher(){ return this.Homepublisher ;}
}