package com.goga133.hw1.objects

import android.graphics.Color
import java.io.Serializable

/**
* @author <a href="mailto:asromanyuk@edu.hse.ru"> Andrey Romanyuk</a>
*/
data class Card(val number: Int) : Serializable {

    private val color: Int = getColorByNumber();

    fun getColor(): Int {
        return color;
    }

    private fun getColorByNumber(): Int {
        if (number % 2 == 0)
            return Color.RED
        return Color.BLUE;

        // РАНДОМНЫЕ ЦВЕТА:
        // val rnd = ThreadLocalRandom.current();
        // return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))*/
    }
}