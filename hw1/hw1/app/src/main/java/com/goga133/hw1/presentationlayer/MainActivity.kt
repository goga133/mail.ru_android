package com.goga133.hw1.presentationlayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.goga133.hw1.R
import com.goga133.hw1.objects.Card
import com.goga133.hw1.presentationlayer.listeners.ICardListener

/**
 * @author <a href="mailto:asromanyuk@edu.hse.ru"> Andrey Romanyuk</a>
 */
class MainActivity : AppCompatActivity(), ICardListener {

    companion object{
         const val CONTAINER_TAG = "FRAGMENT_CONTAINER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null) {
            // Транзакция происходит только при старте приложения
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, CardsFragment(), CONTAINER_TAG)
                .commit()
        }
    }


    /**
     * Обработчик клика на карточку.
     */
    override fun onCardClicked(card: Card) {
        val instance = CardDetailsFragment.newInstance(card)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, instance, CONTAINER_TAG)
            .addToBackStack(CONTAINER_TAG)
            .setCustomAnimations(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
            .commit()
    }

    /**
     * Обработчик клика на кнопку "Back"
     */
    override fun onCardBack() {
        supportFragmentManager.popBackStack()
    }
}