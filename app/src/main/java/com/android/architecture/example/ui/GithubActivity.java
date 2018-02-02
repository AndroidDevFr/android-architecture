package com.android.architecture.example.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.architecture.example.R;

public class GithubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, GithubFragment.newInstance())
                .commitAllowingStateLoss();
    }
}
