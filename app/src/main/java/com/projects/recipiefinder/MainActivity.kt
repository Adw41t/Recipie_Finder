package com.projects.recipiefinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun searchRecipie(view: View) {
        val intent = Intent(this,RecipieList::class.java)
        val item = recipieItem_edit_text.text.toString()
        if(item!=null&& item.isNotBlank())
            intent.putExtra(getString(R.string.recipieItem),item)
        val ingredients = recipieIngredients_edit_text.text.toString()
        if(ingredients!=null&& ingredients.isNotBlank())
            intent.putExtra(getString(R.string.recipieIngredients),ingredients)

        startActivity(intent)
    }


}
