package com.example.proyectomoviles

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.proyectomoviles.utilities.*


class GridViewAdapter internal constructor(private val mContext: Context, private val mThumbIds: Array<Int>) : BaseAdapter() {

    // References to our images
//    private val mThumbIds = Utils.builds()

    override fun getCount(): Int {
        return mThumbIds.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(mContext)
            imageView.layoutParams = ViewGroup.LayoutParams(350, 500)
            imageView.setBackgroundResource(R.drawable.layout_redondeado)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            imageView = (convertView as ImageView?)!!
        }

        imageView.setImageResource(mThumbIds[position])

        return imageView
    }
}