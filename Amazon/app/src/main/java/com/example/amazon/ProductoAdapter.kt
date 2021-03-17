package com.example.amazon

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView


class ProductoAdapter internal constructor(private val mContext: Context) : BaseAdapter() {

    // References to our images
    private val mThumbIds = arrayOf(R.drawable.logo1, R.drawable.logo2, R.drawable.logo3,
            R.drawable.logo4, R.drawable.logo5, R.drawable.logo6,
            R.drawable.logo7, R.drawable.logo8, R.drawable.logo9)

    override fun getCount(): Int {
        return mThumbIds.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    // Create a new ImageView for each item referenced by the Adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = ImageView(mContext)
            imageView.layoutParams = ViewGroup.LayoutParams(300, 300)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setBackgroundColor(Color.parseColor("#F8F8F8"))
        } else {
            imageView = (convertView as ImageView?)!!
        }

        imageView.setImageResource(mThumbIds[position])
        return imageView
    }

}