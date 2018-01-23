package com.example.vladan.firstkotlinapp.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by Vladan on 13.12.2017..
 */

        @BindingAdapter("image_url")
        fun loadImage(imageView: ImageView, url: String) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
