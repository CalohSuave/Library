package com.example.library
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
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
    lateinit var adaptador: CustomAdapter


    interface OnListBookCellPressed {
        fun onButton(books: Book)

        fun goLogin()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)


       return inflater.inflate(R.layout.fragment_list_books, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getGoogleBooks().execute()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnListBookCellPressed
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

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

    internal inner class getGoogleBooks : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg params: String?): String {

            var response = ""


            val url = URL("https://www.googleapis.com/books/v1/volumes?q=harry_potter&maxResults=10&printType=books")
            val connection = url.openConnection() as HttpURLConnection
            connection.connect()
            val rd = BufferedReader(InputStreamReader(connection.inputStream) as Reader?)


            var s = rd.readLine()
            while (s != null) {
                response += s
                s = rd.readLine()
            }


            parseJSONVolumes(response)

            // Return the raw response.
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            try {

                showBookList()

            } catch (e: IllegalStateException){
                e.message
            }
        }


    }



    private fun parseJSONVolumes(result: String) {

        val jsonObject = JSONObject(result)
        val itemsArray = jsonObject.getJSONArray("items")
        var i = 0

        while (i < itemsArray.length()) {

            try {

                // Get the current item information.
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

    private fun showBookList(){


        val adapter = CustomAdapter(context!!, libro)
        lista.adapter = adapter


    }

}

