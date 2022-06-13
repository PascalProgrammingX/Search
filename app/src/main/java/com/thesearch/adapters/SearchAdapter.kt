package com.thesearch.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.thesearch.R
import com.thesearch.response.Drink

class SearchAdapter(
    private var context: Activity,
    var Click: (Drink) -> Unit):
    RecyclerView.Adapter<SearchViewHolder>() {

    private var searchResponses:List<Drink>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.itemview_search, parent, false)
        return SearchViewHolder(layout)

    }


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val searchResponse = searchResponses!![position]
        holder.strCategory.text = searchResponse.strCategory
        holder.strGlass.text = searchResponse.strGlass
        holder.strInstructions.text = searchResponse.strInstructions
        holder.strIngredient1.text = searchResponse.strIngredient1
        holder.strIngredient2.text = searchResponse.strIngredient2
        holder.strIngredient3.text = searchResponse.strIngredient3
        Glide.with(context).load(searchResponse.strDrinkThumb).into(holder.strDrinkThumb)
        if (searchResponse.strAlcoholic == "Alcoholic"){
            holder.strAlcoholic.text =  "YES"
            holder.strAlcoholic.setTextColor(context.resources.getColor(R.color.red))
        }else{
            holder.strAlcoholic.text =  "NO"
            holder.strAlcoholic.setTextColor(context.resources.getColor(R.color.green))
        }

        holder.itemView.setOnClickListener {
            //Listener Interface for item click event
            if (holder.expand.isVisible)
                holder.expand.visibility = View.GONE
            else
                holder.expand.visibility = View.VISIBLE
        }


    }



    fun setSearchResult(newList:List<Drink>){
        searchResponses = newList
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return if (searchResponses != null) searchResponses!!.size else 0
    }



}



class SearchViewHolder(layout: View): RecyclerView.ViewHolder(layout){

    val strDrinkThumb: ShapeableImageView = layout.findViewById(R.id.strDrinkThumb)
    val strCategory: MaterialTextView = layout.findViewById(R.id.strCategory)
    val strGlass: AppCompatTextView = layout.findViewById(R.id.strGlass)
    val strInstructions: AppCompatTextView = layout.findViewById(R.id.strInstructions)
    val strAlcoholic: AppCompatTextView = layout.findViewById(R.id.strAlcoholic)
    val strIngredient1: AppCompatTextView = layout.findViewById(R.id.strIngredient1)
    val strIngredient2: AppCompatTextView = layout.findViewById(R.id.strIngredient2)
    val strIngredient3: AppCompatTextView = layout.findViewById(R.id.strIngredient3)
    val expand: LinearLayoutCompat = layout.findViewById(R.id.expand)


}


