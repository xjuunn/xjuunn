# Spinner

```markdown
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    <TextView
        android:id="@+id/tv_title"
        android:gravity="center"
        android:text="中国移动"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
    <Spinner
        android:id="@+id/myspinner"
        app:layout_constraintTop_toBottomOf="@id/tv_title"
        android:spinnerMode="dialog"
        android:prompt="@string/app_name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```

```java
package com.example.myspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    TextView mytv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        spinner = findViewById(R.id.myspinner);
        mytv = findViewById(R.id.tv_title);
        List<String> list = Arrays.asList(new String[]{"中国移动","中国电信","中国联通"});
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                TextView textView = findViewById(android.R.id.text1);
                mytv.setText(textView.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
```
```
