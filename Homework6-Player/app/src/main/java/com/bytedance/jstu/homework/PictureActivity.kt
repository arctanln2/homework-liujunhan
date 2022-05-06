package com.bytedance.jstu.homework

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import androidx.viewpager2.widget.ViewPager2
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.request.transition.Transition
import com.bytedance.jstu.homework.R
import java.util.ArrayList

class PictureActivity : AppCompatActivity() {
    private val pages: MutableList<View> = ArrayList()
    lateinit var viewPager: ViewPager

    @SuppressLint("SdCardPath")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        viewPager = findViewById(R.id.media_view_pager)
//        addImage(url)
//        addImage("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202108%2F03%2F20210803111549_b54a3.thumb.1000_0.jpeg")
        addImage("https://pics2.baidu.com/feed/0824ab18972bd407b9665c55730b0d590eb3097b.jpeg")
        addImage("https://shuiyuan.sjtu.edu.cn/uploads/default/original/3X/5/2/52c6a5ed6816d1feb7b62ea427708de4dc346c3c.jpeg")
//        addImage("http://cn.bing.com/th?id=OHR.LargestCave_ZH-CN2069899703_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp")
        addImage2("https://i0.wp.com/www.printmag.com/wp-content/uploads/2021/02/4cbe8d_f1ed2800a49649848102c68fc5a66e53mv2.gif?fit=476%2C280&ssl=1")
        val adapter = PicAdapter()
        adapter.setDatas(pages)

        viewPager.adapter = adapter

    }
    private fun addImage(resId: Int) {
        val imageView =
            layoutInflater.inflate(R.layout.activity_base_image_item, null) as ImageView
        Glide.with(this)
            .load(resId)
            .error(R.drawable.error)
            .into(imageView)
        pages.add(imageView)
    }

    private fun addImage(path: String) {
        val imageView =
            layoutInflater.inflate(R.layout.activity_base_image_item, null) as ImageView
        Glide.with(this)
            .load(path)
            .apply(RequestOptions().circleCrop().diskCacheStrategy(DiskCacheStrategy.ALL))
            .error(R.drawable.error)
            .into(imageView)
        pages.add(imageView)
    }
    private fun addImage2(path: String) {
        val imageView =
            layoutInflater.inflate(R.layout.activity_base_image_item, null) as ImageView
        Glide.with(this)
            .asGif()
            .load(path)
            .apply(RequestOptions().circleCrop().diskCacheStrategy(DiskCacheStrategy.ALL))
            .error(R.drawable.error)
            .into(imageView)
        pages.add(imageView)
    }
}