package com.elliott.sxswreduxworkshopandroid.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.elliott.sxswreduxworkshopandroid.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic._05.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val transitionName = intent?.extras?.getString("transition_name")
        header_image.transitionName = transitionName
        val viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.uiModelLiveData.observe(this, Observer {
            val url = it?.item?.links?.get(0)?.href
            Picasso.get().load(url).into(header_image)
            title_text.text = it?.item?.data?.get(0)?.title
            description.text = it?.item?.data?.get(0)?.description
        })
    }
}
