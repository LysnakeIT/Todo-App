package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Cette classe permet de gerer la redirection vers le lien d'une tache
 */
public class taskWebActivity extends AppCompatActivity {

    private String url;
    private WebView vw;

    @Override
    /**
     * Ce onCreate ouvre une page web dont l'url est l'url de la tache que l'on a selectionnee
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_web);

        //on recupere le intent contenant l'url
        Bundle extras = getIntent().getExtras();
        url = extras.getString("url");

        vw = (WebView) findViewById(R.id.webView);
        vw.loadUrl(url);

        vw.setWebViewClient(new WebClient());
    }

    private class WebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if ((keyCode == KeyEvent.KEYCODE_BACK) && vw.canGoBack()){
            vw.goBack();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}