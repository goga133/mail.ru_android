package com.goga133.hw1.presentationlayer

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goga133.hw1.R
import com.goga133.hw1.businesslayer.CardRepository
import com.goga133.hw1.objects.Card
import com.goga133.hw1.presentationlayer.adapter.CardHolder
import com.goga133.hw1.presentationlayer.adapter.CardsAdapter
import com.goga133.hw1.presentationlayer.listeners.ICardListener
import kotlinx.android.synthetic.main.fragment_numbers.view.*


/**
 * @author <a href="mailto:asromanyuk@edu.hse.ru"> Andrey Romanyuk</a>
 */
open class CardsFragment : Fragment() {
    companion object {
        const val CARDS_ARRAY = "CARDS_ARRAY"
        const val LANDSCAPE_CARDS_COUNT = 4;
        const val DEFAULT_CARDS_COUNT = 3;
    }

    private var listener: ICardListener? = null
    private var cards: ArrayList<Card>? = null

    /**
     * Инициализируем переменную listener при создании фрагмента.
     * @see ICardListener
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Инициализация поля listener
        listener = requireActivity() as? ICardListener
    }

    /**
     * Очищаем ссылку на listener и cards при уничтожении экземпляра фрагмента.
     */
    override fun onDetach() {
        super.onDetach()
        listener = null
        cards = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Сохраняем массив карточек:
        outState.putParcelableArrayList(CARDS_ARRAY, cards)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // Сохраняем в cards список
        cards = savedInstanceState?.getParcelableArrayList<Card>(CARDS_ARRAY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_numbers, container, false)

        // Инициализириуем массив карточек:
        if(cards == null)
            cards = CardRepository.instance.list()


        val adapter = cards?.let { CardsAdapter(it, CardClickHandler()) }

        val recyclerView = root.recyclerView_numbers.apply {
            this.setHasFixedSize(false)

            // Создаём взависимости от ориентации GridLayoutManager
            this.layoutManager = GridLayoutManager(
                context, when (resources.configuration.orientation) {
                    // Если ориентация LANDSPACE, то 4 столбца, иначе - 3
                    Configuration.ORIENTATION_LANDSCAPE -> LANDSCAPE_CARDS_COUNT
                    else -> DEFAULT_CARDS_COUNT
                }

            )
            this.adapter = adapter
        }

        root.button_add.setOnClickListener(adapter?.let { AddClickHandler(it, recyclerView) })

        return root
    }

    /**
     * Handler-class для описания функционала кнопки Add при клике.
     */
    inner class AddClickHandler(
        private val adapter: CardsAdapter,
        private val recyclerView: RecyclerView
    ) : View.OnClickListener {

        override fun onClick(view: View?) {
            adapter.addItem(Card(adapter.itemCount + 1))
            // Скроллим к последнему элементу
            recyclerView.scrollToPosition(adapter.itemCount - 1)
        }
    }

    /**
     * Handler-class для описания функционала клика по карточке.
     */
    inner class CardClickHandler() : CardHolder.IListener {
        override fun onCardClicked(position: Int) {
            val card = CardRepository.instance.item(position)

            listener?.onCardClicked(card)
        }
    }

}