package com.example.library
import android.annotation.SuppressLint
import android.content.ClipData
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.library.RoomDataBase.UserBook
import com.example.library.RoomDataBase.UsersDatabase
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_show_detail_book.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_show_detail_book.*

class ShowDetailBookFragment : androidx.fragment.app.Fragment() {

    var buttonActive: Boolean = false

    companion object {


        fun newInstance(book: Book): ShowDetailBookFragment{

            val showDetailBook = ShowDetailBookFragment()
            val args = Bundle()
            args.putParcelable("book", book)

            showDetailBook.arguments = args

            return showDetailBook
            }

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_detail_book, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val libro = arguments!!.getParcelable("book") ?: Book.getEmptyLibro()

        updateText(libro)

        val userBookDao = UsersDatabase.getInstance(context!!).userBookDao()
        val userBook: UserBook = UserBook(tv_nameBook.text.toString(), CurrentUser.id)
        getBookbyId().execute()
        bt_star.setOnClickListener {
            doAsync {
                if (buttonActive) {
                    userBookDao.insertFavoriteBook(userBook)
                } else {
                    userBookDao.removeFavorite(tv_nameBook.text.toString(), CurrentUser.id)
                }

            }
        }
    }
    @SuppressLint("StaticFieldLeak")
    internal inner class getBookbyId : AsyncTask<Void, Void, Int>() {
        override fun doInBackground(vararg params: Void): Int? {

            val agentDao = UsersDatabase.getInstance(context!!).userBookDao()

            return agentDao.isExistsFavoriteBookById(tv_nameBook.text.toString(), CurrentUser.id)
        }

        override fun onPostExecute(isExistBookById: Int){
            super.onPostExecute(isExistBookById)

            if (isExistBookById > 0) {
                if (bt_star.isActivated) {
                    buttonActive = true
                }

            }

        }



    }

    fun updateText(libro: Book) {
        Glide.with(context!!).load(libro.cover).into(iv_portrait_detail)
        tv_nameBook.text = libro.title
        tv_description.text = libro.description
    }


    }


