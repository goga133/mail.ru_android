package com.goga133.hw1.presentationlayer.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goga133.hw1.R
import com.goga133.hw1.objects.Card

/**
 * @author <a href="mailto:asromanyuk@edu.hse.ru"> Andrey Romanyuk</a>
 */
class CardHolder(view: View, private val listener: IListener) : RecyclerView.ViewHolder(view) {

    interface IListener {
        fun onCardClicked(position: Int)
    }

    private val numberTextView: TextView = view.findViewById(R.id.number)

    init {
        view.setOnClickListener {
            listener.onCardClicked(adapterPosition)
        }
    }

    fun bind(item: Card) {
        numberTextView.apply {
            text = item.number.toString()
            setTextColor(item.getColor())
        }
    }
}
