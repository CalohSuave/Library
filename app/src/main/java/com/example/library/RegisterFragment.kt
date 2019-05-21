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

    /** User's email */
    private lateinit var email: String

    /** User's password */
    private lateinit var password: String

    /** Is the email correct */
    var emailCorrecto: Boolean = false

    /** Is the password correct */
    var contrasCorrect: Boolean = false

    /** Is the username correct */
    var usernameCorrect: Boolean = false

    /** Register Fragment Listener */
    private lateinit var listener: OnRegisterFragment


    /**
     * If there are values in the variables it save them otherwise it sets them to ""
     * @param savedInstanceState
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_EMAIL) ?: ""
            password = it.getString(ARG_PASSWORD) ?: ""
        }

    }


    /** When the view is created it inflates it
     * @param inflater the layout inflater
     * @param container the view group
     * @param savedInstanceState
     * @return View the inflated view*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    /**
     * When the activity is created it gets the values of {@link #email} and {@link #password}
     * it also sets the button and finally it checks the text entered in the three
     * different inputs
     * @param savedInstanceState
     * */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        checkErrors()

        et_username_registerfragment.setText("")
        et_email_registerfragment.setText(email)
        et_password_registerfragment.setText(password)



        bt_register_registerfragment.setOnClickListener {

            if (emailCorrecto && contrasCorrect) {
                createNewAccount(
                    et_username_registerfragment.text.toString(),
                    et_email_registerfragment.text.toString(),
                    et_password_registerfragment.text.toString()
                )
            }
        }

        tv_goback_registerfragment.setOnClickListener {
            listener.goBack()
        }


    }

    /** @throws RuntimeException*/
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

        /** Action it does when the button is pressed
         * @param name user name
         * @param email user email
         * @param Password user password*/
        fun onRegisterButtonPressed(name: String, email: String, Password: String)

        /** Goes back */
        fun goBack()
    }

    companion object {
        @JvmStatic
                /** It creates a new Instance of the Register Fragment and it puts the email and password
                 * @param email user email
                 * @param password user password*/
        fun newInstance(email: String, password: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_EMAIL, email)
                    putString(ARG_PASSWORD, password)
                }
            }
    }


    /**
     * Checks if it has only letters
     * @param nombre username
     * @return Boolean true if it has only letters and false if it hasn't
     * */
    fun isAlpha(nombre: EditText): Boolean {
        val name = nombre.text.toString()
        return name.matches("[a-zA-Z ]+".toRegex())
    }


    /**
     * Checks is the email is valid
     * @param nombre email string
     * @return Boolean true if is valid and false if it's not
     * */
    fun isEmailValid(nombre: EditText): Boolean {
        val name = nombre.text.toString()
        return name.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}\$".toRegex())
    }

    /**
     * Checks if the Password is valid
     * @param nombre password string
     * @return Boolean true if is valid and false if it's not
     * */
    fun isPasswordValid(nombre: EditText): Boolean {
        val name = nombre.text.toString()
        return name.matches("^(?=.*?[a-zA-Z])(?=.*?[0-9]).{8,}\$".toRegex())


    }

    /**
     * Creates a new account
     * @param username user username
     * @param email user email
     * @param password user password
     * */
    private fun createNewAccount(username: String, email: String, password: String) {
        val id: String = UUID.randomUUID().toString()
        var user = User(
            id,
            et_email_registerfragment.text.toString(),
            et_username_registerfragment.text.toString(),
            et_password_registerfragment.text.toString()
        )
        UserTask(this, activity!!.applicationContext, user).execute()
        listener.onRegisterButtonPressed("", email, password)
    }

    /**
     * Redirects the user to the login, with his email and password
     * @param user The User object with all the user information
     * */
    fun redirectLogin(user: User) {
        // Redirigir al register to login
        listener.onRegisterButtonPressed(user.userName, user.email, user.password)
    }


    fun checkErrors() {
        et_username_registerfragment.addTextChangedListener(object :
            TextChangedListener<EditText>(et_username_registerfragment) {

            /** When the text is changed it checks the format is correct with its regex {@link #isAlpha()}
             * @param target the edit text where is checking
             * @param s the editable*/
            override fun onTextChanged(target: EditText, s: Editable) {
                val res = resources
                val nombresErr = res.getStringArray(R.array.NameErrors)


                if (!et_username_registerfragment.text.isNullOrEmpty()) {

                    if (isAlpha(et_username_registerfragment)) {
                        usernameCorrect = true
                        et_username_registerfragment.error = null
                    } else {
                        et_username_registerfragment.error = nombresErr[1]
                    }
                } else {
                    et_username_registerfragment.error = nombresErr[0]
                }
            }
        })

        et_email_registerfragment.addTextChangedListener(object :
            TextChangedListener<EditText>(et_email_registerfragment) {

            /** When the text its changed it checks the {@link #email} format is correct using
             * {@link #isEmailValid()}
             * @param target the edit text where is checking
             * @param s the editable*/
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



        et_password_registerfragment.addTextChangedListener(object :
            TextChangedListener<EditText>(et_password_registerfragment) {

            /** When the text its changed it checks the {@link #password} format is correct using
             * {@link #isPasswordValid()}
             * @param target the edit text where is checking
             * @param s the editable*/
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


}
