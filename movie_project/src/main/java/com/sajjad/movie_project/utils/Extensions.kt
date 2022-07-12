package com.sajjad.movie_project.utils

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load

fun View.showVisibility(mustShow: Boolean) {
    if (mustShow) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun RecyclerView.initRecyclerView(
    layoutManager: RecyclerView.LayoutManager,
    adapter: RecyclerView.Adapter<*>
) {
    this.layoutManager = layoutManager
    this.adapter = adapter
    //this.setHasFixedSize(true)
}

fun ImageView.loadImage(url: String) {
    this.load(url) {
        crossfade(true)
        crossfade(8000)
    }
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}