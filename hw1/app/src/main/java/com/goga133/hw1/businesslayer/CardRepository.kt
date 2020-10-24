package com.goga133.hw1.businesslayer

import com.goga133.hw1.objects.Card

/**
 * Класс-репозиторий для объектов Card с реализацией Singleton.
 * @see Card
 * @author <a href="mailto:asromanyuk@edu.hse.ru"> Andrey Romanyuk</a>
 */
open class CardRepository private constructor() {
    companion object {
        const val DEFAULT_COUNT: Int = 100;

        // Синглтон:
        val instance by lazy { CardRepository() }
    }

    private val droidList by lazy { initializeData() }

    public fun list() = droidList

    public fun item(index: Int) = droidList[index]

    private fun initializeData(): ArrayList<Card> {
        val cardList = ArrayList<Card>(DEFAULT_COUNT)

        for (i in 1..DEFAULT_COUNT)
            cardList.add(Card(i))

        return cardList;
    }
}