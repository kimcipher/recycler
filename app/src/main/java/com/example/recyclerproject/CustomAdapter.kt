package com.example.recyclerproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class RecyclerAdapter(var context: Context):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    var productList : List<ItemViewModel> = listOf() // empty list

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
    //Note below code returns above class and pass the view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item, parent, false)
        return ViewHolder(view)
    }
    //so far item view is same as single item
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val productName = holder.itemView.findViewById(R.id.productName) as TextView
        val productDesc = holder.itemView.findViewById(R.id.productDescription) as TextView
        val productLearnMore = holder.itemView.findViewById(R.id.productLearnMore) as TextView
        val image = holder.itemView.findViewById(R.id.productImage) as ImageView

        //bind
        val item = productList[position]
        productName.text=item.productName
        productLearnMore.text = item.productDescription
        productDesc.text = item.productLearnMore

        //
        Glide.with(context).load(item.productImage)
            .apply(RequestOptions().centerCrop())
            .into(image)



        // holder.itemView.setOnClickListener {
        //
        //
        //
        // }
    }

    override fun getItemCount(): Int { //count the number items coming from the API
        return productList.size
    }
    //we will call this function on Loopj response
    fun setProductListItems(productList: List<ItemViewModel>){
        this.productList = productList
        notifyDataSetChanged()
    }

}

/* 3 items -> used in recycler views
    onCreateViewHolder(): sets the vies to display the items
    onBindViewHolder(): bind the list items to our views e.g ImageView
    getItemCount() -> returns number of items present in a list
 */