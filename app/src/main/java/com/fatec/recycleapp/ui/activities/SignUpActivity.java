package com.fatec.recycleapp.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.fatec.recycleapp.R;
import com.fatec.recycleapp.adapter.SignUpAdapter;

public class SignUpActivity extends AppCompatActivity {

    private ViewPager2 pager;
    private SignUpAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        pager = findViewById(R.id.signPager);
        adapter = new SignUpAdapter(this);
        pager.setAdapter(adapter);
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }
        });
    }

    public ViewPager2 getPager() {
        return pager;
    }
}