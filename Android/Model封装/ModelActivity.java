package com.example.myapplication.ui;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.bumptech.glide.Glide;
import com.example.myapplication.beans.BannerBean;
import com.example.myapplication.databinding.ListItemBinding;
import com.example.myapplication.databinding.ModelLayoutBinding;
import com.example.myapplication.utils.BaseActivity;
import com.example.myapplication.utils.BaseRecyclerAdapter;
import com.example.myapplication.utils.IOkhttpgetBean;
import com.example.myapplication.utils.IRecyclergetItem;
import com.example.myapplication.utils.ListModel;
import com.example.myapplication.utils.OkHttpUtil;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import java.util.List;

public abstract class ModelActivity<T extends ViewBinding> extends BaseActivity<ModelLayoutBinding> {
    @Override
    protected void initView() {

    }

    protected void setToolbar(String title, boolean b) {
        binding.toolbar.titlet.setText(title);
        if (b) {
            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        } else binding.toolbar.back.setVisibility(View.GONE);
    }

    protected void setBanner(String url) {
        OkHttpUtil.doGet(url, BannerBean.class, new IOkhttpgetBean<BannerBean>() {
            @Override
            public void getBean(BannerBean bean) {
                binding.banner.setAdapter(new BannerImageAdapter<BannerBean.RowsBean>(bean.getRows()) {
                    @Override
                    public void onBindView(BannerImageHolder bannerImageHolder, BannerBean.RowsBean rowsBean, int i, int i1) {
                        Glide.with(context).load(OkHttpUtil.baseUrl + rowsBean.getAdvImg()).fitCenter().into(bannerImageHolder.imageView);
                    }
                }).start();
            }
        });
    }

    public BaseRecyclerAdapter initNewsList(List model) {
        BaseRecyclerAdapter<ListItemBinding, ListModel> adapter = new BaseRecyclerAdapter<ListItemBinding, ListModel>(model) {
        };
        adapter.setT1IRecyclergetItem(new IRecyclergetItem<ListItemBinding, ListModel>() {
            @Override
            public void getItem(ListItemBinding item, int pos, ListModel data) {
                Glide.with(context).load(OkHttpUtil.baseUrl + data.getImgurl()).fitCenter().into(item.img);
                item.title.setText(data.getTitle());
                item.content.setText(data.getContent());
                item.left.setText(data.getLeft());
                item.right.setText(data.getRight());
                item.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newsClick(item, pos, data);
                    }
                });
            }
        });
        binding.newsList.setAdapter(adapter);
        binding.newsList.setLayoutManager(new GridLayoutManager(context, 1));
        return adapter;
    }

    protected abstract void newsClick(ListItemBinding item, int pos, ListModel data);


    public void setTab(List<ListModel> model) {
        for (ListModel o : model) {
            binding.tab.addTab(binding.tab.newTab().setText(o.getTitle()).setTag(o.getId() + ""));
        }
        binding.tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabClick(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    protected abstract void tabClick(TabLayout.Tab tab);


}