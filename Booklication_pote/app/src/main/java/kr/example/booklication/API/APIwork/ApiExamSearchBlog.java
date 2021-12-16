package kr.example.booklication.API.APIwork;

import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kr.example.booklication.Define;

//keyword를 이용하여 api를 불러오는 클래스
public class ApiExamSearchBlog {

    AppCompatActivity aApiExamSearchBlog;
    private static String clientId = "MYwLRZwU__80vMKSTa2z"; //애플리케이션 클라이언트 아이디값"
    private static String clientSecret = "9TgH3eoztu"; //애플리케이션 클라이언트 시크릿값"

    public ApiExamSearchBlog(AppCompatActivity appCompatActivity, String search) {
        aApiExamSearchBlog = appCompatActivity;
        new LoadApi().execute(search);
    }

    private class LoadApi extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... search) {

            String text = null;
            try {
                text = URLEncoder.encode(search[0], "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("검색어 인코딩 실패", e);
            }

            String apiURL = "https://openapi.naver.com/v1/search/book?query=" + text;

            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("X-Naver-Client-Id", clientId);
            requestHeaders.put("X-Naver-Client-Secret", clientSecret);
            String responseBody = get(apiURL,requestHeaders);

            parseData(responseBody);
            System.out.println("최종 : " + responseBody);

            return null;
        }

        public String get(String apiUrl, Map<String, String> requestHeaders){
            HttpURLConnection con = connect(apiUrl);
            try {
                con.setRequestMethod("GET");
                for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                    con.setRequestProperty(header.getKey(), header.getValue());
                }


                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                    return readBody(con.getInputStream());
                } else { // 에러 발생
                    return readBody(con.getErrorStream());
                }
            } catch (IOException e) {
                throw new RuntimeException("API 요청과 응답 실패", e);
            } finally {
                con.disconnect();
            }
        }

        public HttpURLConnection connect(String apiUrl){
            try {
                URL url = new URL(apiUrl);
                return (HttpURLConnection)url.openConnection();
            } catch (MalformedURLException e) {
                throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
            } catch (IOException e) {
                throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
            }
        }

        public String readBody(InputStream body){
            InputStreamReader streamReader = new InputStreamReader(body);


            try (BufferedReader lineReader = new BufferedReader(streamReader)) {
                StringBuilder responseBody = new StringBuilder();


                String line;
                while ((line = lineReader.readLine()) != null) {
                    responseBody.append(line);
                }


                return responseBody.toString();
            } catch (IOException e) {
                throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
            }
        }

        public void parseData(String responseBody) {
            String title; //제목
            String link;  //링크
            String author;  //저자
            String image;  //이미지
            String publisher;  //출판사
            String description;  //설명
            String isbn;  //뭔 번호
            String pubdate;  //출간일

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(responseBody.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("items");


                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    ArrayList<String> temp = new ArrayList<>();

                    title = item.getString("title");
                    title = title.replace("<b>","");
                    title = title.replace("</b>","");
                    link = item.getString("link");
                    link = link.replace("<b>","");
                    link = link.replace("</b>","");
                    author=item.getString("author");
                    author = author.replace("<b>","");
                    author = author.replace("</b>","");
                    image=item.getString("image");
                    image = image.replace("<b>","");
                    image = image.replace("</b>","");
                    publisher=item.getString("publisher");
                    publisher = publisher.replace("<b>","");
                    publisher = publisher.replace("</b>","");
                    description=item.getString("description");
                    description = description.replace("<b>","");
                    description = description.replace("</b>","");
                    description = description.replace("&quot;", "\"");
                    isbn=item.getString("isbn");
                    isbn = isbn.replace("<b>","");
                    isbn = isbn.replace("</b>","");
                    pubdate=item.getString("pubdate");
                    pubdate = pubdate.replace("<b>","");
                    pubdate = pubdate.replace("</b>","");

                    temp.add(title);
                    temp.add(link);
                    temp.add(author);
                    temp.add(image);
                    temp.add(publisher);
                    temp.add(description);
                    temp.add(isbn);
                    temp.add(pubdate);

                    System.out.println("api temp : " + temp);

                    Define.ins().apiArray.add(temp);

                    System.out.println("apiArray : " + Define.ins().apiArray);

                    System.out.println("Define.ins().apiArray.size : " + Define.ins().apiArray.size());

                }
                for(int j = 0; j < Define.ins().apiArray.size();j++) {
                    System.out.println("ApiArrayitem " + j + " : " + Define.ins().apiArray.get(j));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}