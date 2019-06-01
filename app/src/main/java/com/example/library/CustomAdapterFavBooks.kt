package com.example.library

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.library.RoomDataBase.UsersDatabase
import kotlinx.android.synthetic.main.detail_fav_item.view.*
import org.jetbrains.anko.doAsync

class CustomAdapterFavBooks(private val context: Context, private val items:ArrayList<String>): BaseAdapter(){

    /**
     * Getter of the View
     * @param position the position
     * @param convertView the view
     * @param parent the view group
     * @return View vista
     * */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        // SI PONES PARENT NO APARECE
        /** The inflated view */
        val vista: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.detail_fav_item, null)

        /** Vista tag*/
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

        /** Item in a certain position of the list*/
        val item = getItem(position)

        holder.titulo.text= item


        holder?.delet?.setOnClickListener {

            items?.removeAt(position)
            notifyDataSetChanged()

            doAsync {
                val userBookDao = UsersDatabase.getInstance(context!!).userBookDao()
                userBookDao.removeFavorite(item, CurrentUser.id)



            }
        }

        return vista




    }

    /**
     * Item getter
     * @param position the position of the item to return
     * @return Book item in a specified position
     * */
    override fun getItem(position: Int): String {
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

    /**
     * Creates the list of books
     * @param vista where the list will be created
     * */
    private class ViewHolder(vista: View){
        /** the title it will be shown in each item of the list*/

        val titulo: TextView = vista.name_book_fav

        val delet : TextView = vista.delete_book

        /** the cover (image) that will be shown in each item of the list*/
        /*val favourite: TextView = vista.cb_detail*/

    }




}