package com.projects.recipiefinder

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_recipie_list.*
import org.json.JSONException
import org.json.JSONObject

class RecipieList : AppCompatActivity() {

    var volleyRequest: RequestQueue?=null
    var recipieListAdapter:RecipieListAdapter?=null
    var layoutManager: RecyclerView.LayoutManager?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipie_list)
        val intent = intent
        val item = intent.getStringExtra(getString(R.string.recipieItem))
        val ingredients = intent.getStringExtra(getString(R.string.recipieIngredients))
        var url = BuildConfig.RECIPE_API_URL
        if(ingredients== null ||ingredients.isBlank()){
            if(item!=null && item.isNotBlank()){
                url = url + "q=$item"
            }
        }
        if(ingredients!= null && ingredients.isNotBlank()){
            val splitStr = ingredients.trim { it <= ' ' }.split("\\s+".toRegex()).toTypedArray()
            url = url +"i="
            for (ing in splitStr){
                url = url + ing +","
            }
            if(item!=null && item.isNotBlank()){
                url = url + "&q=$item"
            }
        }
        volleyRequest= Volley.newRequestQueue(this)

        getRecipie(url)
    }
    fun getRecipie(url: String){
        val recipieList=ArrayList<Recipie>()
        val recipieRequest= JsonObjectRequest(
            Request.Method.GET, url,
            { response: JSONObject ->
                try {
                    val resultArray = response.getJSONArray("results")

                    for (i in 0 until resultArray.length()) {
                        val recipieObject = resultArray.getJSONObject(i)
                        val title = recipieObject.getString("title")
                        val link = recipieObject.getString("href")
                        val image = recipieObject.getString("thumbnail")
                        val ingredients = "Ingredients:- " + recipieObject.getString("ingredients")

                        val recipie = Recipie(title, link, ingredients, image)
                        recipieList?.add(recipie)
                    }
                    if(recipieList!=null && recipieList.size>0) {
                        recipieListAdapter = RecipieListAdapter(recipieList, this)
                        layoutManager = LinearLayoutManager(this)
                        recyler.layoutManager = layoutManager
                        recyler.adapter = recipieListAdapter
                    }
                    else{
                        showToastAndFinish()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    showToastAndFinish()
                }
            },
            { error: VolleyError? ->

                showToastAndFinish()
            })

        volleyRequest!!.add(recipieRequest)
    }

    fun showToastAndFinish(){

        Toast.makeText(this,"No Recipe found. Please try again with different item/ingredient.",Toast.LENGTH_SHORT).show()
        finish()
    }
}

