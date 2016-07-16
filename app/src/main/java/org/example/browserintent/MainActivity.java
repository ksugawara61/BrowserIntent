package org.example.browserintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText urlText;
    private Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // すべてのユーザーインタフェース要素のハンドルを取得
        urlText = (EditText)findViewById(R.id.url_field);
        goButton = (Button)findViewById(R.id.go_button);
        // イベントハンドラをセット
        goButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openBrowser();
            }
        });
        urlText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    openBrowser();
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * テキストボックスで指定されたURLを使ってブラウザーを開く
     */
    private void openBrowser() {
        Uri uri = Uri.parse(urlText.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
