package com.elliott.sxswreduxworkshopandroid.ui.search

import android.app.ActivityOptions
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.elliott.sxswreduxworkshopandroid.R
import com.elliott.sxswreduxworkshopandroid.ui.detail.DetailActivity
import kotlinx.android.synthetic._05.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        val imageRecyclerView = search_item_list
        val searchEditText = search_editText
        val imageAdapter = ImageAdapter(object : ImageAdapter.ItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                viewModel.itemSelected(position)
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("transition_name", ViewCompat.getTransitionName(view))
                val options = ActivityOptions
                        .makeSceneTransitionAnimation(this@MainActivity, view, ViewCompat.getTransitionName(view))
                startActivity(intent, options.toBundle())
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
            imageRecyclerView.visibility = if (it?.isLoading == true) View.GONE else View.VISIBLE
            loading_icon.visibility = if (it?.isLoading == true) View.VISIBLE else View.GONE
            it?.images?.let {
                imageAdapter.items = it
                empty_message.visibility = View.GONE
            }
            if (it?.noItemsMessage?.isNotEmpty() == true) {
                empty_message.visibility = View.VISIBLE
                empty_message.text = it.noItemsMessage
            }
        })
        submit_btn.setOnClickListener {
            val inputMethodService = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodService.hideSoftInputFromWindow(searchEditText.windowToken, 0)
            viewModel.submitPressed()

        }
    }
}
