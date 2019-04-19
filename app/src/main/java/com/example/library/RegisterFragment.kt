package com.example.library
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.library.RoomDataBase.User
import com.example.library.RoomDataBase.UserTask
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.*

private const val ARG_EMAIL = "email"
private const val ARG_PASSWORD = "password"


class RegisterFragment : Fragment() {


    private lateinit var email: String
    private lateinit var password: String

    var emailCorrecto: Boolean = false
    var contrasCorrect: Boolean = false
    var usernameCorrect: Boolean = false


    private lateinit var listener: OnRegisterFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_EMAIL) ?: ""
            password = it.getString(ARG_PASSWORD) ?: ""
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        et_email_registerfragment.setText(email)
        et_password_registerfragment.setText(password)



        bt_register_registerfragment.setOnClickListener {

            if (emailCorrecto && contrasCorrect) {
                createNewAccount(et_username_registerfragment.text.toString(),
                    et_email_registerfragment.text.toString(),
                    et_password_registerfragment.text.toString()
                )}
        }

        tv_goback_registerfragment.setOnClickListener {
            listener.goBack()
        }


        et_username_registerfragment.addTextChangedListener(object : TextChangedListener<EditText>(et_username_registerfragment) {
            override fun onTextChanged(target: EditText, s: Editable) {
                val res = resources
                val nombresErr = res.getStringArray(R.array.NameErrors)


                if (!et_username_registerfragment.text.isNullOrEmpty()) {

                    if (isAlpha(et_username_registerfragment)) {
                        et_username_registerfragment.error = null
                    } else {
                        et_username_registerfragment.error = nombresErr[1]
                    }
                } else {
                    et_username_registerfragment.error = nombresErr[0]
                }
            }
        })

        et_email_registerfragment.addTextChangedListener(object : TextChangedListener<EditText>(et_email_registerfragment) {
            override fun onTextChanged(target: EditText, s: Editable) {
                val res = resources
                val nombresErr = res.getStringArray(R.array.EmailErrors)


                if (!et_email_registerfragment.text.isNullOrEmpty()) {

                    if (isEmailValid(et_email_registerfragment)) {
                        emailCorrecto = true
                        et_email_registerfragment.error = null
                    } else {
                        et_email_registerfragment.error = nombresErr[1]
                    }
                } else {
                    et_email_registerfragment.error = nombresErr[0]
                }
            }
        })



        et_password_registerfragment.addTextChangedListener(object : TextChangedListener<EditText>(et_password_registerfragment) {
            override fun onTextChanged(target: EditText, s: Editable) {
                val res = resources
                val nombresErr = res.getStringArray(R.array.PasswordError)


                if (!et_password_registerfragment.text.isNullOrEmpty()) {
                    if (isPasswordValid(et_password_registerfragment)) {
                        et_password_registerfragment.error = null
                        contrasCorrect = true
                    } else {
                        et_password_registerfragment.error = nombresErr[1]
                    }
                } else {
                    et_password_registerfragment.error = nombresErr[0]
                }
            }
        })


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRegisterFragment) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnRegisterFragment")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = activity as OnRegisterFragment
    }

    interface OnRegisterFragment {
        fun onRegisterButtonPressed(name: String, email: String,Password: String)

        fun goBack()
    }

    companion object {
        @JvmStatic
        fun newInstance(email: String, password: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_EMAIL, email)
                    putString(ARG_PASSWORD, password)
                }
            }
    }


    fun isAlpha(nombre: EditText): Boolean {
        val name = nombre.text.toString()
        return name.matches("[a-zA-Z ]+".toRegex())
    }


    fun isEmailValid(nombre: EditText): Boolean {
        val name = nombre.text.toString()
        return name.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}\$".toRegex())
    }

    fun isPasswordValid(nombre: EditText): Boolean {
        val name = nombre.text.toString()
        return name.matches("^(?=.*?[a-zA-Z])(?=.*?[0-9]).{8,}\$".toRegex())


    }

    private fun createNewAccount(username: String, email: String , password: String) {
        val id: String = UUID.randomUUID().toString()
        var user = User(
            id,
            et_email_registerfragment.text.toString(),
            et_username_registerfragment.text.toString(),
            et_password_registerfragment.text.toString()
        )
        UserTask(this, activity!!.applicationContext, user).execute()
        listener.onRegisterButtonPressed("",email,password)
    }

    fun redirectLogin(user: User) {
        // Redirigir al register to login
        listener.onRegisterButtonPressed(user.userName, user.email, user.password)
    }


}
