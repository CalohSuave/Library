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

    var libro: ArrayList<Book> = ArrayList()
    private lateinit var listener: OnListBookCellPressed


    //BOTONES FRAGMENTS
    interface OnListBookCellPressed {
        fun onButton(books: Book)
        fun goLogin()
    }

    //PARA ACTIVAR EL TOOLBAR
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    //PARA ACTIVAR EL TOOLBAR
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_list_books, container, false)

    }


    //EJECUTAMOS LA FUNCION EN SEGUNDO PLANO
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        queryBooks().execute()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnListBookCellPressed
    }


    //DECLARAR LAS OPCIONES DEL TOOBAL
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //AQUI DECLARAMOS LAS DIFERENTES ACCIONES QUE LLEVAN ACABO CADA ICONO
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
    internal inner class queryBooks : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg params: String?): String {

            var asnwerApi: String = ""
            val url = URL("https://www.googleapis.com/books/v1/volumes?q=Harry&maxResults=10&printType=books")
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

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            try {
                showList()

            } catch (e: IllegalStateException){
                e.message
            }
        }


    }

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

    private fun showList(){

        val adapter = CustomAdapter(context!!, libro)
        lista.adapter = adapter


        lista.setOnItemClickListener { _: AdapterView<*>, _: View, position: Int, _: Long ->
            listener.onButton(libro[position])
        }

    }

}