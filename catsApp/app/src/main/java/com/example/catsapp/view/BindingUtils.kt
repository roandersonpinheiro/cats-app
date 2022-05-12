package com.example.catsapp.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.catsapp.R
import com.example.catsapp.model.Image
import com.squareup.picasso.Picasso


class BindingUtils {
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, itemImage: Image?) {
            Picasso.get().load(itemImage?.thumbLink ?: itemImage?.link).fit()
                .placeholder(R.drawable.ic_cat)
                .error(R.drawable.ic_error)
                .into(view)
        }
    }
}