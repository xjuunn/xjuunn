package com.example.myapplication.ui;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.viewbinding.ViewBinding;

import com.example.myapplication.beans.NewsListBean;
import com.example.myapplication.beans.NewsTypeList;
import com.example.myapplication.databinding.ListItemBinding;
import com.example.myapplication.databinding.ModelLayoutBinding;
import com.example.myapplication.utils.IModelgetItemData;
import com.example.myapplication.utils.IOkhttpgetBean;
import com.example.myapplication.utils.IRecyclergetItem;
import com.example.myapplication.utils.ListModel;
import com.example.myapplication.utils.OkHttpUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class TestActivity extends ModelActivity<ModelLayoutBinding>{
    @Override
    protected void initView() {
        super.initView();
        setBanner("/prod-api/api/metro/rotation/list");
        OkHttpUtil.doGet("/prod-api/press/category/list", NewsTypeList.class,bean -> {
            setTab(initModel(bean.getData(),item -> new ListModel(
                    item.getId(),item.getName()
            )));
        });
    }

    @Override
    protected void newsClick(ListItemBinding item, int pos, ListModel data) {
        Toast.makeText(context, data.getTitle(), Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void tabClick(TabLayout.Tab tab) {
        OkHttpUtil.doGet("/prod-api/press/press/list?pageNum=1&pageSize=10&type="+tab.getTag(), NewsListBean.class, bean -> {
            initNewsList(initModel(bean.getRows(), item -> new ListModel(
                    item.getCover(), item.getTitle(), item.getContent(), item.getLikeNum() + "", item.getReadNum() + ""
            )));
        });
    }


}
