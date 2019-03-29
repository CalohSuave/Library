package com.example.library


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import kotlinx.android.synthetic.main.fragment_list_books.*

class ListBooks : Fragment() {

    companion object {
        var libros = "libro"
    }
    var libro: ArrayList<Libro> = ArrayList()

    interface OnButtonPressedListener{
        fun onButton(books : Libro)
    }

    private lateinit var listener: OnButtonPressedListener




    lateinit var adaptador: AdapterCustom

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val vista = inflater.inflate(R.layout.fragment_list_books,container,false)
        libro.add(Libro("Sex for Dummies",R.drawable.booksex,"Libro que necesita Dragomir",0))
        libro.add(Libro("Sex for Dummies Vol 2",R.drawable.booksex,"Libro que necesita Dragomir",0))
        libro.add(Libro("Sex for Dummies",R.drawable.booksex,"Libro que necesita Dragomir",0))
        libro.add(Libro("Sex for Dummies Vol 2",R.drawable.booksex,"Libro que necesita Dragomir",0))
        libro.add(Libro("Sex for Dummies",R.drawable.booksex,"Libro que necesita Dragomir",0))
        libro.add(Libro("Sex for Dummies Vol 2",R.drawable.booksex,"Libro que necesita Dragomir",0))
        libro.add(Libro("Sex for Dummies",R.drawable.booksex,"Libro que necesita Dragomir",0))
        libro.add(Libro("Sex for Dummies Vol 2",R.drawable.booksex,"Libro que necesita Dragomir",0))


        var lista = vista.findViewById(R.id.lista) as ListView

        adaptador = AdapterCustom(requireContext(),libro)


        lista.adapter = adaptador

      lista.setOnItemClickListener { parent, view, position, id ->
            listener.onButton(libro[position])
        }

        return vista
    }


}
