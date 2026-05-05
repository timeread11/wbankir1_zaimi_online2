package ru.wbankirruru.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceError;


public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        
        webView.setWebViewClient(new WebViewClient() {
    private void showError(WebView view, String url) {
        String html = "<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><style>body{display:flex;flex-direction:column;justify-content:center;align-items:center;height:100vh;margin:0;font-family:sans-serif;background:#fff;color:#333;text-align:center;}h2{margin-top:0}p{color:#666;margin-bottom:20px}a{text-decoration:none;background:#2196F3;color:#fff;padding:12px 24px;border-radius:8px;font-weight:bold;font-size:16px}</style></head><body><h2>Нет интернета</h2><p>Проверьте подключение и попробуйте снова</p><a href=\"" + url + "\">Обновить</a></body></html>";
        view.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        if (request.isForMainFrame()) {
            showError(view, request.getUrl().toString());
        }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        showError(view, failingUrl);
    }
});

        webView.loadUrl("https://wbankir.ru");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
