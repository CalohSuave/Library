package com.example.library

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.detail_item.view.*

class AdapterCustom(val context: Context , val items:ArrayList<Libro>): BaseAdapter(){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val vista: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.detail_item, parent)

        val holder: ViewHolder = vista.tag as? ViewHolder ?: ViewHolder(vista)
        vista.tag = holder
        /*
        if (vista==null){
            vista= Layoutflater.from(context).inflate(R.layout.detail_item,null)

            holder = ViewHolder(vista)
            vista.tag = holder
        }else{
            holder = vista.tag as? ViewHolder

        }
        */

        val item = getItem(position)

        holder.titulo.text= item.title
        holder.cover.setImageResource(item.cover)


        holder.favourite.setOnClickListener {

            item.favourite =1
        }
        return vista

    }

    override fun getItem(position: Int): Libro {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.count()
    }


    private class ViewHolder(vista: View){
        val titulo: TextView = vista.tv_title_detail
        val cover: ImageView = vista.iv_portrait_detail
        val favourite: TextView = vista.cb_detail
    }




}