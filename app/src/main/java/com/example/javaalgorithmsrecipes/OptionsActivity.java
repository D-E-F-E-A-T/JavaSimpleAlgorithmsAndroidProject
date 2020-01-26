package com.example.javaalgorithmsrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionsActivity extends AppCompatActivity {

    public static final String TERMS = "https://bytao7mao.github.io/java_cookbook_webpage/legal/terms_and_conditions.html";
    public static final String LICENCES = "https://bytao7mao.github.io/java_cookbook_webpage/legal/licences.html";
    public static final String PRIVACY_POLICY = "https://bytao7mao.github.io/java_cookbook_webpage/legal/privacy_policy.html";
    public static final String WEB_PAGE = "https://bytao7mao.github.io/java_cookbook_webpage/";

    @BindView(R.id.tv_privacy_policy) TextView tv_privacy_policy;
    @BindView(R.id.tv_terms_and_conditions) TextView tv_terms_and_conditions;
    @BindView(R.id.tv_third_party_licences_link) TextView tv_licences;
    @BindView(R.id.tv_logo) TextView tv_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        ButterKnife.bind(OptionsActivity.this);

//        TextView tv_privacy_policy = findViewById(R.id.tv_privacy_policy);
        tv_privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent INTENT = new Intent(Intent.ACTION_VIEW);
                INTENT.setData(Uri.parse(PRIVACY_POLICY));
                startActivity(INTENT);
            }
        });
//        TextView tv_terms_and_conditions = findViewById(R.id.tv_terms_and_conditions);
        tv_terms_and_conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent INTENT = new Intent(Intent.ACTION_VIEW);
                INTENT.setData(Uri.parse(TERMS));
                startActivity(INTENT);
            }
        });
//        TextView tv_licences = findViewById(R.id.tv_third_party_licences_link);
        tv_licences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goto third party web view
//                final Intent INTENT = new Intent(Intent.ACTION_VIEW);
//                INTENT.setData(Uri.parse(LICENCES));
//                startActivity(INTENT);

                //goto third party activity
                final Intent INTENT_THIRD_PARTY = new Intent(OptionsActivity.this, ThirdParty.class);
                startActivity(INTENT_THIRD_PARTY);

            }
        });
//        TextView tv_logo = findViewById(R.id.tv_logo);
        tv_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent INTENT = new Intent(Intent.ACTION_VIEW);
                INTENT.setData(Uri.parse(WEB_PAGE));
                startActivity(INTENT);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
