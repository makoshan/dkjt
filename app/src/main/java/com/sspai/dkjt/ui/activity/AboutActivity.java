package com.sspai.dkjt.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sspai.dkjt.R;

/**
 * FIXME
 *
 * @author Makoshan
 * @date 2015-01-16 10:34
 */
public class AboutActivity extends ActionBarActivity implements View.OnClickListener{
    @InjectView(R.id.action_bar_back)
    ImageView btnBack;
    @InjectView(R.id.author)
    LinearLayout btnAuthor;
    @InjectView(R.id.designer)
    LinearLayout btnDesign;
    @InjectView(R.id.new_dev)
    LinearLayout btnNewDev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.inject(this);
        btnBack.setOnClickListener(this);
        btnAuthor.setOnClickListener(this);
        btnDesign.setOnClickListener(this);
        btnNewDev.setOnClickListener(this);

    }

    void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_back:
                finish();
                break;
            case R.id.author:
                openUrl("https://twitter.com/f2prateek");
                break;
            case R.id.designer:
                openUrl("http://weibo.com/amyths");
                break;
            case R.id.new_dev:
                openUrl("http://weibo.com/1606834872");
                break;
            default:
                break;
        }

    }
}
