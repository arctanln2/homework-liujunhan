package com.bytedance.jstu.homework

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.bytedance.jstu.homework.R
import com.bytedance.jstu.homework.api.Youdao
import com.bytedance.jstu.homework.api.YoudaoFanyi
import com.bytedance.jstu.homework.api.YoudaoRes
import com.google.gson.GsonBuilder
import okhttp3.*
import com.google.android.material.textfield.TextInputEditText
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import com.google.gson.JsonParser
import okhttp3.EventListener
import java.io.IOException
import java.net.URLEncoder
import kotlin.collections.HashMap
import java.util.*
import com.bytedance.jstu.homework.interceptor.TimeConsumeInterceptor


class SecondActivity : AppCompatActivity() {
    private val client = OkHttpClient.Builder().build()
    private val gson = GsonBuilder().create()
    private var button: Button? = null
    private var translation: TextView? = null
    private var input: EditText? = null

    private var result: String? = null

    private val okhttpListener = object : EventListener() {
        @SuppressLint("SetTextI18n")
        override fun dnsStart(call: Call, domainName: String) {
            super.dnsStart(call, domainName)
            updateShowTextView("\nDns Search: $domainName")
        }

        @SuppressLint("SetTextI18n")
        override fun responseBodyStart(call: Call) {
            super.responseBodyStart(call)
            updateShowTextView("\nResponse Start")
        }
    }

    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(TimeConsumeInterceptor())
        .eventListener(okhttpListener)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        translation = findViewById(R.id.translation)
        input = findViewById(R.id.edit)
        button = findViewById(R.id.translate)



        button?.setOnClickListener {
//            Translate()
            button?.isClickable = false
            Log.println(Log.INFO, "DispStr", input?.text.toString())
            val rootUrl: String="https://dict.youdao.com/jsonapi"

            // http Launching
            val url = rootUrl.toHttpUrl().newBuilder()
                .addQueryParameter("q", input?.text.toString())
                .build()

            val request: Request = Request.Builder().url(url)
                .header("User-Agent", "Sjtu-Android-OKHttp").build()

            Log.println(Log.INFO, "DispStr",request.toString())
            httpClient.newCall(request).enqueue(callback)


            button?.isClickable = true
        }
    }

    val callback = object :Callback {

        override fun onFailure(call: Call, e: IOException) {
            runOnUiThread {

                translation?.text = "HttpFailure"
                Log.println(Log.WARN, "httperror", e.toString())
            }
        }
        override fun onResponse(call: Call, response: Response) {
            var strResult : String
            if (response.isSuccessful) {
                val bodyString = response.body?.string()
                Log.println(Log.INFO, "dispStr", bodyString.toString())
                val jsonObject = JsonParser.parseString(bodyString).asJsonObject

                val webtransObject = jsonObject.get("web_trans").asJsonObject
                val webtranslation = webtransObject.get("web-translation").asJsonArray.get(0).asJsonObject
                val transObeject=webtranslation.get("trans").asJsonArray
                strResult=""
                for(item in transObeject){
                    strResult = strResult + item.asJsonObject.get("value").toString()
                }
                Log.println(Log.INFO, "dispStr", strResult)
                runOnUiThread {
                    if(translation!=null) {
                        translation?.text=strResult

                    }
                }

            } else {
                Log.println(Log.INFO, "dispStr", "failure")
            }




//
//
//
//
//
//            if (dicts.any{ it.asString == "fanyi" }) {
//                val translationObject  = jsonObject.get("fanyi").asJsonObject
//                strResult = translationObject.get("tran").asString
//            } else {
//                strResult = "None"
//            }
//
//            val tmpItem = TranslationCacheItem(
//                call.request().url,strResult,guessLanguage
//            )
//            applyTCI(tmpItem)

        }
    }

    class TranslationCacheItem (url: HttpUrl, trans: String?, guessedLang: String) {
        val url = url
        val trans = trans
        val guessLang = guessedLang
        //val dicts = dicts
    }
    fun applyTCI(tci: TranslationCacheItem) {
        val hintLang : String
        if (tci.guessLang != "zh" && tci.guessLang != "eng") {
            Log.println(Log.INFO,"dispStr", "The language seems not supported. Plz input zh or en instead of ${tci.guessLang}")
            hintLang = "Is it in language ${tci.guessLang}? But not supported"
        } else {
            hintLang = "Seems fine!"
        }
        val strResult : String
        if (tci.trans != null) {
            strResult = tci.trans
        } else {
            strResult = "None"
        }

        runOnUiThread {
            if(translation!=null) {
                translation?.text=strResult

            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun updateShowTextView(text: String, append: Boolean = true) {
        if (Looper.getMainLooper() !== Looper.myLooper()) {
            // 子线程，提交到主线程中去更新 UI.
            runOnUiThread {
                updateShowTextView(text, append)
            }
        } else {
            translation?.text = if (append) translation?.text.toString() + text else text
        }
    }
}