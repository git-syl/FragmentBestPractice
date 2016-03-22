package com.exmaple.fragmentbestpractice;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Window;


/**
 * Created by S on 2016/3/21/021.
 * 新闻内容活动类
 */
public class NewsContentActivity extends Activity {

    public static void actionStart(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //加载新闻主内容布局
        setContentView(R.layout.news_content);
        //获取传入的内容与标题
        String newsTitle = getIntent().getStringExtra("news_title");
        String newsContent = getIntent().getStringExtra("news_content");
        Log.e("Err","newsTitle："+newsTitle+"newsContent："+newsContent);
        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().
                findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle, newsContent);
        //刷新界面
    }
}