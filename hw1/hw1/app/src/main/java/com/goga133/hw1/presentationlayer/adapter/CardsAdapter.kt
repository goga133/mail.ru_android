package com.goga133.hw1.presentationlayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goga133.hw1.R
import com.goga133.hw1.objects.Card

/**
 * @author <a href="mailto:asromanyuk@edu.hse.ru"> Andrey Romanyuk</a>
 */
class CardsAdapter(
    private val cardsArray: ArrayList<Card>,
    private val listener: CardHolder.IListener
) :
    RecyclerView.Adapter<CardHolder>() {

    fun addItem(card: Card) {
        cardsArray.add(card);
        this.notifyItemChanged(itemCount)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardHolder {
        return CardHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_card, parent, false), listener
        )
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(cardsArray[position])
    }

    override fun getItemCount() = cardsArray.size
}
