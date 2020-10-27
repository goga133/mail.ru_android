package com.goga133.hw1.objects

import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
* @author <a href="mailto:asromanyuk@edu.hse.ru"> Andrey Romanyuk</a>
*/
data class Card(val number: Int) : Parcelable, Serializable {

    private val color: Int = getColorByNumber();

    constructor(parcel: Parcel) : this(parcel.readInt()) {
    }

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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(number)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Card> {
        override fun createFromParcel(parcel: Parcel): Card {
            return Card(parcel)
        }

        override fun newArray(size: Int): Array<Card?> {
            return arrayOfNulls(size)
        }
    }
}