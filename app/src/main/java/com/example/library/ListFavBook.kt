package com.example.library

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.library.RoomDataBase.UserBook
import com.example.library.RoomDataBase.UsersDatabase
import kotlinx.android.synthetic.main.fragment_show_detail_book.*
import kotlinx.android.synthetic.main.fragment_list_fav_book.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class ListFavBook : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var listener: OnFragmentInteractionListener

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)

            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
<<<<<<< HEAD
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val userBookDao = UsersDatabase.getInstance(context!!).userBookDao()
        val userBook: UserBook = UserBook(tv_nameBook.text.toString(), CurrentUser.id)
        val userArray:ArrayList<String> = userBookDao.getAll(CurrentUser.id)

        for (i in 0..userArray.size-1){
            println(userArray[i])
        }

=======
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
>>>>>>> 8917842729921ac48dd7510ee9c386bfd3a64367
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_fav_book, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bt_fav_fragment.setOnClickListener {


            listener.onFragmentInteraction()
        }
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }





    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFavBook().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
