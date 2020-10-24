package com.goga133.hw1.presentationlayer.listeners;

import com.goga133.hw1.objects.Card;

/**
 * @author <a href="mailto:asromanyuk@edu.hse.ru"> Andrey Romanyuk</a>
 */
public interface ICardListener {
    void onCardClicked(Card card);

    void onCardBack();
}
