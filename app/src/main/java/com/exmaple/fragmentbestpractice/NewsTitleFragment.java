package com.exmaple.fragmentbestpractice;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by S on 2016/3/21/021.
 * 新闻标题Fragment类
 */
public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener {

     private ListView newsTitleListView;
     private List<News> newsList;
     private NewsAdapter adapter;
     private boolean isTwoPane;

    //当碎片和活动建立关联时调用
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList = getNews();//初始化新闻数据
        adapter = new NewsAdapter(activity,R.layout.news_item,newsList);
    }
    //为碎片创建视图（加载布局）时调用.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     //   View view =inflater.inflate(R.layout.news_content_frag,container,false);
        View view1=inflater.inflate(R.layout.new_title_frag,container,false);
        newsTitleListView = (ListView) view1.findViewById(R.id.news_title_list_view);
        Log.e("Err","newsTitleListView："+newsTitleListView+"，adapter："+adapter);
        newsTitleListView.setAdapter(adapter);

        newsTitleListView.setOnItemClickListener(this);
        return  view1;
    }
//确保与碎片相关活动一定已经创建完毕的时候调用
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane =true;
        }else{
            isTwoPane = false;
        }
    }
//16515
    private List<News> getNews() {
        List<News> newsList = new ArrayList<News>();
        News news1 = new News();
        news1.setTitle("Succeed in College as a Learning Disabled Student");
        news1.setContent("College freshmgen will soon learn to live with a roommate, adjust to a new social scene and survive less-than-stellar dining hall food. Students with learning disabilities will face these transitions while also grappling with a few more hurdles.");
        newsList.add(news1);

        News news2 = new News();
        news2.setTitle("Google Android exec poached by China 's XiaoMi");
        news2.setContent("China's Xiaomi has poached a key Google executive involved in the tech giant's Android phones, in a move seen as a coup for the rapidly growing Chinese smartphone maker.");
        newsList.add(news2);
        return newsList;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        News news =newsList.get(position);
        if (isTwoPane){
            NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().
                    findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refresh(news.getTitle(),news.getContent());
        }else {
            NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
        }
    }
}
