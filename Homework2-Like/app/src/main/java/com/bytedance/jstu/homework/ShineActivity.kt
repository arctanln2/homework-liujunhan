package com.bytedance.jstu.homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sackcentury.shinebuttonlib.ShineButton

class ShineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shine)

        val btSmile = findViewById<ShineButton> (R.id.po_image1);
        btSmile.init(this)

        val btHeart = findViewById<ShineButton> (R.id.po_image2);
        btHeart.init(this)

        val btStar = findViewById<ShineButton> (R.id.po_image3);
        btStar.init(this)
    }

}