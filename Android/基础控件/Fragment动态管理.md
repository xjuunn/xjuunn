# Fragment 动态管理

~~~ java
FragmentManager fragmentManager = getSupportFragmentManager();
FragmentTransaction transation = fragmentManager.beginTransaction();
transation.replace(R.id.fragment,new BlankFragment1());
transation.addToBackStack(null);//取消退出栈操作
transation.commit();
~~~

