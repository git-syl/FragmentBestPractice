package com.exmaple.fragmentbestpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by S on 2016/3/21/021.
 * 新闻列表适配器类
 */
public class NewsAdapter extends ArrayAdapter<News> {

    private int resourceId;
    public NewsAdapter(Context context, int textViewResourceId, List<News> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view;
        if(convertView==null){
            //从news_item.xml加载
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        }else{
            view = convertView;
        }

        TextView newTitleText = (TextView) view.findViewById(R.id.news_title);
        newTitleText.setText(news.getTitle());
        return  view;
    }
}
