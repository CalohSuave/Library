package com.example.library
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_list_books.*
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.URL

class ListBooksFragment : Fragment() {

    /** List with all the books*/
    var libro: ArrayList<Book> = ArrayList()

    /** Listener of the BookCell*/
    private lateinit var listener: OnListBookCellPressed



    companion object {


        fun newInstance(query: String): ListBooksFragment{

            val showDetailBook = ListBooksFragment()
            val args = Bundle()
            args.putString("query", query)
            showDetailBook.arguments = args
            return showDetailBook
        }

    }

    //BOTONES FRAGMENTS
    /** Fragment buttons*/
    interface OnListBookCellPressed {
        fun onButton(books: Book)
        fun goLogin()
    }

    //PARA ACTIVAR EL TOOLBAR
    /** Activates the toolbar
     * @param savedInstanceState
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }


    //PARA ACTIVAR EL TOOLBAR
    /** While the view is being create it activates the toolbar
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return View the view inflated
     * */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_list_books, container, false)

    }


    //EJECUTAMOS LA FUNCION EN SEGUNDO PLANO
    /** When the is created it executes the query of the books
     * @param view
     * @param savedInstanceState bundle
     * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var query = arguments!!.getString("query")

        if(query == "none" ){


        }else{
            queryBooks().execute()
        }

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnListBookCellPressed
    }


    //DECLARAR LAS OPCIONES DEL TOOBAL
    /**
     * Declares the options of the toolbar
     * @param menu the menu
     * @param inflater the menu inflater
     * */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //AQUI DECLARAMOS LAS DIFERENTES ACCIONES QUE LLEVAN ACABO CADA ICONO
    /** Defines each button action
     * @param item the menu item
     * @return Boolean when action done
     * */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when (item.itemId) {
            R.id.log_out -> {
                listener.goLogin()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })

    }




    //TASK PARA HACER LA LLAMADA A LA API
    /** All the tasks to call the API */
    internal inner class queryBooks : AsyncTask<String, String, String>() {
        /**
         * Calls the API on the background
         * @param params
         * @return asnwerApi String of the answer of the API
         * */
        override fun doInBackground(vararg params: String?): String {
            var query = arguments!!.getString("query")

            var asnwerApi: String = ""
            val url = URL("https://www.googleapis.com/books/v1/volumes?q=$query&maxResults=10&printType=books")
            val connection = url.openConnection() as HttpURLConnection
            connection.connect()
            val rd = BufferedReader(InputStreamReader(connection.inputStream) as Reader?)


            var s = rd.readLine()
            while (s != null) {
                asnwerApi += s
                s = rd.readLine()
            }
            parseBooks(asnwerApi)

            // Return the raw response.
            return asnwerApi
        }

        /** After executing the statement it prints the list
         * @param result the result obtained
         * @catch IllegalStateException
         * */
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            try {
                showList()

            } catch (e: IllegalStateException){
                e.message
            }
        }


    }

    /**
     * Parses all the books and gets all the information of each book
     * @param documentToParse the JSon document where all the books are
     * @catch RuntimeException
     * @catch JSONException
     * */
    private fun parseBooks(documentToParse: String) {
        val jsonObject = JSONObject(documentToParse)
        val itemsArray = jsonObject.getJSONArray("items")
        var i = 0

        while (i < itemsArray.length()) {

            try {
                val book = itemsArray.getJSONObject(i)
                val volumeInfo = book.getJSONObject("volumeInfo")
                val title = volumeInfo.getString("title")


                var description: String = "No Description"

                if (volumeInfo.has("description")) {
                    description = volumeInfo.getString("description")
                }

                val imageLinks = JSONObject(volumeInfo.optString("imageLinks"))
                val image = imageLinks.getString("thumbnail")

                libro.add(Book(title, image, description, 0))
                i ++

            } catch (e: RuntimeException) {
                e.stackTrace
            } catch (e: JSONException) {
                e.stackTrace
            }

        }
    }

    /** Shows the list of books*/
    private fun showList(){
        val adapter = CustomAdapter(context!!, libro)
        lista.adapter = adapter

        lista.setOnItemClickListener { _: AdapterView<*>, _: View, position: Int, _: Long ->
            listener.onButton(libro[position])
        }

    }

}