package com.example.myapplication.utils;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity implements IgetBinding<T>{
    protected T binding;
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getBinding(getLayoutInflater(),null,false);
        setContentView(binding.getRoot());
        context = this;
        initView();
    }
    public <T1> List<ListModel> initModel(List<T1> bean, IModelgetItemData<T1, ListModel> t2IModelgetItemData) {
        return bean.stream().map(t2IModelgetItemData::getItemData).collect(Collectors.toList());
    }
    protected abstract void initView();






























}
