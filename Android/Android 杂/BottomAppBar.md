# BottomAppBar

~~~ xml
  <androidx.coordinatorlayout.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="100dp"
        android:layout_gravity="end">
        <com.google.android.material.bottomappbar.BottomAppBar
            android:layout_width="match_parent"
            android:layout_height="60dp"
            android:backgroundTint="@color/material_on_primary_disabled"
            android:layout_gravity="bottom"
            app:menu="@menu/menutest1"
            app:fabAnchorMode="cradle"
            app:fabCradleMargin="5dp"
            app:fabCradleRoundedCornerRadius="10dp"
            app:fabAlignmentMode="end"
            android:id="@+id/menu">
        </com.google.android.material.bottomappbar.BottomAppBar>
        <com.google.android.material.floatingactionbutton.FloatingActionButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:backgroundTint="#ee7959"
            android:src="@drawable/dizhi"
            app:layout_anchor="@id/menu"/>
    </androidx.coordinatorlayout.widget.CoordinatorLayout>
~~~

~~~ java
binding.menu.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.i(TAG, "onMenuItemClick: "+item.getTitle());
                return false;
            }
        });
~~~

