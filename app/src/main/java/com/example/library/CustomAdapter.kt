package com.example.library

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_item.view.*

class CustomAdapter(private val context: Context, private val items:ArrayList<Book>): BaseAdapter(){

    /**
     * Getter of the View
     * @param position the position
     * @param convertView the view
     * @param parent the view group
     * @return View vista
     * */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


        // SI PONES PARENT NO APARECE
        val vista: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.detail_item, null)

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
        Glide.with(context)
            .load(item.cover)
            .into(holder.cover!!)



        /*holder.favourite.setOnClickListener {

            item.favourite =1
        }*/
        return vista

    }

    /**
     * Item getter
     * @param position the position of the item to return
     * @return Book item in a specified position
     * */
    override fun getItem(position: Int): Book {
        return items[position]
    }

    /**
     * Item ID getter
     * @param position the position of the item's id you want to get
     * @return Long item's id
     * */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    /**
     * Gets the amount of items in the list
     * @return Int number of items
     * */
    override fun getCount(): Int {
        return items.count()
    }


    private class ViewHolder(vista: View){
        val titulo: TextView = vista.tv_title_detail
        val cover: ImageView = vista.iv_portrait_detail
        /*val favourite: TextView = vista.cb_detail*/
    }




}