package com.example.library
import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.library.RoomDataBase.UsersDatabase
import kotlinx.android.synthetic.main.fragment_login.*

private const val ARG_EMAIL = "email"
private const val ARG_PASSWORD = "password"

class LoginFragment : Fragment() {
    /** The user Email*/
    private lateinit var email: String

    /** The user password*/
    private lateinit var password: String

    /** Fragment Listener */
    private lateinit var listener: OnLoginFragmentPressed


    /**When the view is created set the email and password to the values passed from another
     * fragment or if they are null they set them empty
     * @param savedInstanceState
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(ARG_EMAIL) ?: ""
            password = it.getString(ARG_PASSWORD) ?: ""
        }
    }

    /** When the activity is created it sets the email and password text. It also passes this
     * parameters to the register fragment {@link #goToRegisterFragment()} in case the user presses the register button
     * @param savedInstanceState
     *
     * */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        et_email_loginfragment.setText(email)
        et_password_loginfragment.setText(password)


        bt_register_loginfragment.setOnClickListener {
            listener.goToRegisterFragment(et_email_loginfragment.text.toString(),et_password_loginfragment.text.toString())
        }

    }

    /** When the view is created it sets the action of executing a query to the login button
     * @param view The login view
     * @param savedInstanceState*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_login_loginfragment.setOnClickListener {
            getUserDB().execute()
        }

    }

    /** It inflates the view
     * @param inflater layout inflater
     * @param container the view group
     * @param savedInstanceState
     * @return View with the view inflated*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    /** Sets the context value to the listener when the login is pressed
     * @param context
     * @throws RuntimeException*/
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLoginFragmentPressed) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnRegisterFragment") as Throwable
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = activity as OnLoginFragmentPressed
    }


    interface OnLoginFragmentPressed {
        //Click en el boton de login que hacer una query para saber si el usuario existe o no
        /** When the button is pressed it executes a query to know if the user exists
         * @param currentUserId the id of the user */
        fun isUserOnDataBase(currentUserId: Int)

        //Click en el boton de registro y enviamos el email y password
        /** Redirects to the Register Fragment
         * @param email the email we send to the fragment
         * @param password the password we send to the fragment*/
        fun goToRegisterFragment(email: String, password: String)
    }

    companion object {

        @JvmStatic
        /**Creates a new instance
         * @param email user email
         * @param password user password*/
        fun newInstance(email: String, password: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_EMAIL, email)
                    putString(ARG_PASSWORD, password)
                }
            }
    }

    //LOOOOOK DAT SANTI

    @SuppressLint("StaticFieldLeak")
    internal inner class getUserDB : AsyncTask<Void, Void, Int>() {

        /** In background checks if the user exists and return it if it does
         * @param params
         * @return {@value #-1} if the user does not exists or the user id if it exists */
        override fun doInBackground(vararg params: Void): Int? {

            val email :String = et_email_loginfragment.text.toString()
            val password:String = et_password_loginfragment.text.toString()
            val agentDao = UsersDatabase.getInstance(context!!).userDao()


            if (agentDao.isExistUser(email,password) == 0) {
                return -1
            } else {
                val agentDao2 = UsersDatabase.getInstance(context!!).userDao()
                return agentDao2.getUserByEmailAndPassword(et_email_loginfragment.text.toString(), et_password_loginfragment.text.toString())
            }

        }

        /**After executing it checks if the user is in the database
         * @param currentUserId the id of the user it is searching*/
        override fun onPostExecute(currentUserId: Int){
            super.onPostExecute(currentUserId)

            if (currentUserId != -1) {
                listener.isUserOnDataBase(currentUserId)
            } else {
                Toast.makeText(activity, "No existe usuario", Toast.LENGTH_LONG).show()
            }

        }

    }

}