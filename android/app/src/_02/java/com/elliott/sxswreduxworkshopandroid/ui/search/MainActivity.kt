package com.elliott.sxswreduxworkshopandroid.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.elliott.sxswreduxworkshopandroid.R
import kotlinx.android.synthetic._02.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        val imageRecyclerView = search_item_list
        val searchEditText = search_editText
        val imageAdapter = ImageAdapter(object : ImageAdapter.ItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
            }
        })
        imageRecyclerView.adapter = imageAdapter
        imageRecyclerView.layoutManager = LinearLayoutManager(this)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                viewModel.onSearch(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        viewModel.uiModelLiveData.observe(this, Observer {
            //Update UI
            search_subtitle.text = it?.searchSubTitle
        })
    }
}
