package com.goga133.hw1.presentationlayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.goga133.hw1.R
import com.goga133.hw1.objects.Card
import com.goga133.hw1.presentationlayer.listeners.ICardListener
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author <a href="mailto:asromanyuk@edu.hse.ru"> Andrey Romanyuk</a>
 */
class MainActivity : AppCompatActivity(), ICardListener {

    private val containerTag by lazy { resources.getString(R.string.FRAGMENT_TAG) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null) {
            // Транзакция происходит только при старте приложения
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, CardsFragment(), containerTag)
                .commit()
        }
    }


    /**
     * Обработчик клика на карточку.
     */
    override fun onCardClicked(card: Card) {
        val instance = CardDetailsFragment.newInstance(card)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, instance, containerTag)
            .addToBackStack(containerTag)
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