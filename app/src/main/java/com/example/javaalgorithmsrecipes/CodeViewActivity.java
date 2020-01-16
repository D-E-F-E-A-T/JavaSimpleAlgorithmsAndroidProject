package com.example.javaalgorithmsrecipes;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.protectsoft.webviewcode.Codeview;

import java.util.Objects;

import static com.example.javaalgorithmsrecipes.Constants.SharedPreferences.SAVINGS_FINAL;

public class CodeViewActivity extends AppCompatActivity  {
//    HighlightJsView highlightJsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_code);

        ActionBar actionBar = getSupportActionBar();
        //no need for this
//            Objects.requireNonNull(actionBar).setTitle(getResources().getString(R.string.dashboard));
        //set color of background
        Objects.requireNonNull(actionBar).
                setBackgroundDrawable(
                        new ColorDrawable(
                                ContextCompat.getColor(
                                        getApplicationContext(), R.color.white)));
        TextView textView = new TextView(getApplicationContext());
        textView.setText(getResources().getString(R.string.app_name));
//        textView.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.robotoblack));
//        textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        textView.setTextSize(28f);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        Objects.requireNonNull(actionBar).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(textView);
//            actionBar.setElevation(0.2f);
        actionBar.setElevation(0f);
//            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.actionbar_round));


        String desc = "";
        Bundle lastIntent = getIntent().getExtras();
        if (lastIntent != null) {
            desc = lastIntent.getString(SAVINGS_FINAL);
        }

        WebView webview = (WebView) findViewById(R.id.code_view);
        Codeview.with(getApplicationContext())
                .withCode(desc)
                .into(webview);
    }

}
