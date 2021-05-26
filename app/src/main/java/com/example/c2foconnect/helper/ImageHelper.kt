package com.example.c2foconnect.helper

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.example.c2foconnect.R
import com.example.c2foconnect.video.model.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_video.*

class ImageHelper {
    
    companion object{
        public fun setRoundImage(context:Context,imageView:ImageView, url: String, radius:Int) {
            Picasso.with(context).load(url)
                .resize(radius,radius)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        val imageBitmap =
                            (imageView.drawable as BitmapDrawable).bitmap
                        val imageDrawable =
                            RoundedBitmapDrawableFactory.create(context.resources, imageBitmap)
                        imageDrawable.isCircular = true
                        imageDrawable.cornerRadius = Math.max(
                            imageBitmap.width,
                            imageBitmap.height
                        ) / 2.0f
                        imageView.setImageDrawable(imageDrawable)
                    }

                    override fun onError() {
                        imageView.setImageResource(R.drawable.ic_user)
                    }
                })
        }
    }
}