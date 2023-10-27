package com.example.warrungu.components.cards

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.warrungu.components.dictionary.DictionaryItem
import com.example.warrungu.R

class CardAdapter(private val cardList: ArrayList<Card>): RecyclerView.Adapter<CardAdapter.CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_row_layout, parent, false)
        return CardViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        val currentItem = cardList[position]
        holder.english.text = currentItem.english
        holder.language.text = currentItem.language
        holder.id = currentItem.id

        holder.bind(holder.itemView)
    }
    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var id: String = "hi"
        val english : TextView = itemView.findViewById(R.id.english)
        val language : TextView = itemView.findViewById(R.id.language)

        fun bind(itemView: View){
            itemView.setOnClickListener {
                Log.d("CLICKY", id.toString())
                val intent = Intent(itemView.context, DictionaryItem::class.java).putExtra(
                    DictionaryItem.ID,id)

                itemView.context.startActivity(intent)
            }
        }
    }
}