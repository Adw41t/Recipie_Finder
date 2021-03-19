package com.projects.recipiefinder

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecipieListAdapter(val list:ArrayList<Recipie>, val context:Context): RecyclerView.Adapter<RecipieListAdapter.ViewHolder>() {

   inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var title=itemView.findViewById<TextView>(R.id.recipieTitle)
        var ingredients=itemView.findViewById<TextView>(R.id.recipieIngredients)
        var image=itemView.findViewById<ImageView>(R.id.imageView)
        fun bindView(recipie:Recipie){
            title.text=recipie.title
            ingredients.text=recipie.ingredients
            if(!TextUtils.isEmpty(recipie.image)){
                Picasso.Builder(context).build().load(recipie.image).placeholder(R.mipmap.ic_launcher)
                    .error(android.R.drawable.ic_menu_report_image)
                    .into(image)
            }
            else{
//                Picasso.Builder(context).build().load(R.mipmap.ic_launcher).into(image)
                image.visibility = GONE
            }

            if(recipie.link!=null && recipie.link!!.isNotBlank()){
                itemView.setOnClickListener {
                    val intent = Intent(context, WebviewActivity::class.java)
                    intent.putExtra(context.getString(R.string.recipie_url), recipie.link)
                    context.startActivity(intent)

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.list_row,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list.get(position))
    }
}