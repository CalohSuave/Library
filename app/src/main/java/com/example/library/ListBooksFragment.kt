package com.example.library


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView



class ListBooksFragment : androidx.fragment.app.Fragment() {

    var libro: ArrayList<Book> = ArrayList()

    interface OnButtonPressedListener{
        fun onButton(books : Book)
    }

    private lateinit var listener: OnButtonPressedListener


    lateinit var adaptador: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val vista = inflater.inflate(R.layout.fragment_list_books,container,false)

        libro.add(Book("Hunger Games ",R.drawable.hg,"Libro que necesita Dragomir",0))
        libro.add(Book("Sex for Dummies",R.drawable.booksex,"Libro que necesita Dragomir",0))
        libro.add(Book("The Benefits of being an octopus",R.drawable.oct,"Libro que necesita Dragomir",0))
        libro.add(Book("One Last Good Day",R.drawable.olgd,"Libro que necesita Dragomir",0))
        libro.add(Book("Security a novel",R.drawable.security,"Libro que necesita Dragomir",0))
        libro.add(Book("IT",R.drawable.it,"Libro que necesita Dragomir",0))
        libro.add(Book("Harry Potter",R.drawable.hp,"Libro que necesita Dragomir",0))


        var lista = vista.findViewById(R.id.lista) as ListView

        adaptador = CustomAdapter(this.context!!,libro)


        lista.adapter = adaptador

      lista.setOnItemClickListener { parent, view, position, id ->
            listener.onButton(libro[position])
        }

        return vista
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as OnButtonPressedListener
    }

}
