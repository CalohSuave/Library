package com.example.library
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_show_detail_book.*


class ShowDetailBookFragment : androidx.fragment.app.Fragment() {

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_detail_book, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val libro = arguments!!.getParcelable("book") ?: Book.getEmptyLibro()

        updateText(libro)

    }

    fun updateText(libro: Book) {
        iv_portrait_detail.setImageResource(libro.cover)
        tv_nameBook.text = libro.title
        tv_description.text=libro.description

    }


    }


