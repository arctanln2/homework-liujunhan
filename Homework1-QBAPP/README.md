# Homework1

题库应用

两个页面

一个是用cycle的题目概览

一个页面是题库的详细信息页

步骤

1. 布局
2. 按钮跳转
3. 加上数据
   1. 在.kt文件中引入数据
   2. 在adapter中将数据和ViewHolder一一对应，
   3. 点击按钮 之后的数据如何和其一一对应，按钮对应的事件和传入的数据要如何设计？

## 用到的功能模块

activity

cycleview中的每一项用item_layout进行设置

```kotlin
 android.widget.TextView.setAllCaps(boolean AllCaps) // TextView中字母大小写设置
```

在ViewHolder里添加点击事件跳转到新的页面

1. 自定义listener变量 并传入这个项的position值

类似[Adding Buttons in RecyclerView Row Items](https://www.youtube.com/watch?v=FA5cGLLiSWs)

![image-20220302100943076](README/image-20220302100943076.png)



recycleView Click

ListView本身就有onItemClick的方法

https://www.cnblogs.com/chenxibobo/p/6136626.html

https://www.cxyzjd.com/article/nuannuanloveai/83786032

https://blog.csdn.net/BADAO_LIUMANG_QIZHI/article/details/103919470

[Android ListView Tutorial with Kotlin

[How to add onClick to RecyclerView list items in Kotlin](https://www.raywenderlich.com/155-android-listview-tutorial-with-kotlin#toc-anchor-009)