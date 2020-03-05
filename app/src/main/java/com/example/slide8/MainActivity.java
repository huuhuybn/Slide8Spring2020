package com.example.slide8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);


        String html = "\n" +
                "<img src=\"https://hyundailevanluong.vn/wp-content/uploads/2019/02/hyundai-tucson-2019-may-dau.jpg\"" +
                " width=100% />";


        webView.loadData(html, "text/html", "utf-8");

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

    }

    String link = "https://vnexpress.net";

    public void loadData(View view) {
        final TextView tvData = findViewById(R.id.tvData);

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                processData();
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                tvData.setText(duLieu);
            }
        };

        asyncTask.execute();
    }

    public String duLieu;
    public void processData() {
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();

            Scanner scanner
                    = new Scanner(inputStream);

            String data = "";

            while (scanner.hasNext()) {
                data = data + scanner.nextLine();
            }
            scanner.close();

            duLieu = data;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
