package com.example.amazon2

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AccountAdapter internal constructor(private val mContext: Context, private val array: Map<String,Array<String>>) : BaseAdapter() {

    // References to our images
//    private val mThumbIds = arrayOf(R.drawable.logo1, R.drawable.logo2, R.drawable.logo3, R.drawable.logo4)

    override fun getCount(): Int {
        return array.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    // Create a new ImageView for each item referenced by the Adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val keys: Array<String> = array.keys.toTypedArray()
        val textView: TextView
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = TextView(mContext)
            textView.setTextSize(18F)
            textView.setTypeface(null,Typeface.BOLD)

        } else {
            textView = (convertView as TextView?)!!
        }

        textView.setText(keys[position])
        return textView
    }

}