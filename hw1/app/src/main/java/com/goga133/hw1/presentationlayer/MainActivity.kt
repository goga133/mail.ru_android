package com.goga133.hw1.presentationlayer

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.goga133.hw1.R
import com.goga133.hw1.objects.Card
import com.goga133.hw1.presentationlayer.listeners.ICardListener

/**
 * @author <a href="mailto:asromanyuk@edu.hse.ru"> Andrey Romanyuk</a>
 */
class MainActivity : AppCompatActivity(), ICardListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null) {
            // Транзакция происходит только при старте приложения
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, CardsFragment(), "FRAGMENT_CONTAINER")
                .commit()
        } else {
            // TODO: Восстановить данные
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        // TODO: Прописать сохранение.
        super.onSaveInstanceState(outState, outPersistentState)
    }


    /**
     * Обработчик клика на карточку.
     */
    override fun onCardClicked(card: Card) {
        val instance = CardDetailsFragment.newInstance(card)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, instance, CardsFragment.CONTAINER_TAG)
            .addToBackStack(CardsFragment.CONTAINER_TAG)
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