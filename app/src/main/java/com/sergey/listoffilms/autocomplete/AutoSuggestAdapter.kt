package com.sergey.listoffilms.autocomplete

import android.content.Context
import android.widget.ArrayAdapter
import com.sergey.listoffilms.api.models.Movie


class AutoSuggestAdapter(context: Context, resource: Int) :
    ArrayAdapter<CharSequence?>(context, resource) {
    private val searchData = mutableListOf<Movie>()

    fun setSearchData(rawList: List<Movie>) {
        searchData.clear()
        searchData.addAll(rawList)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return searchData.size
    }

    override fun getItem(position: Int): CharSequence? {
        return searchData[position].title
    }

    fun getObject(position: Int): Movie {
        return searchData[position]
    }
}