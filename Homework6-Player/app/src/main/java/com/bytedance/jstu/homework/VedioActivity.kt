package com.bytedance.jstu.homework

import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.MediaController
import android.widget.VideoView

class VedioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vedio)
        findView()
    }

    private fun findView(){
        val videoView: MyVedio = findViewById(R.id.videoView)
        findViewById<View>(R.id.buttonPlay).setOnClickListener { videoView.start() }
        findViewById<View>(R.id.buttonPause).setOnClickListener { videoView.pause() }
        findViewById<View>(R.id.buttonReplay).setOnClickListener { videoView.resume() }
        videoView.setVideoPath(getVideoPath(R.raw.big_buck_bunny))
        videoView.setMediaController(MediaController(this))
    }

    private fun getVideoPath(resId: Int): String {
        return "android.resource://" + this.packageName + "/" + resId
    }

    @Override
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setContentView(R.layout.activity_vedio)
        findView()
        val ori = newConfig.orientation
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            supportActionBar?.hide()
        } else {
            supportActionBar?.show()

        }
    }
}