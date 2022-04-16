package com.bytedance.jstu.homework

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.jstu.homework.databinding.ActivityGivelikeBinding
import com.sackcentury.shinebuttonlib.ShineButton

class givelike : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_givelike)

        val btLike = findViewById<ShineButton>(R.id.bt_like)
        btLike.init(this)

        val btSmile = findViewById<ShineButton> (R.id.bt_smile);
        btSmile.init(this)

        val btHeart = findViewById<ShineButton> (R.id.bt_heart);
        btHeart.init(this)

        val btStar = findViewById<ShineButton> (R.id.bt_star);
        btStar.init(this)
    }

}