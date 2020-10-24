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
        const val CONTAINER_TAG = "FRAGMENT_CONTAINER";
    }

    private var listener: ICardListener? = null

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
     * Очищаем ссылку на listener при уничтожении экземпляра фрагмента.
     */
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_numbers, container, false)

        val adapter = CardsAdapter(CardRepository.instance.list(), CardClickHandler())

        val recyclerView = root.recyclerView_numbers.apply {
            this.setHasFixedSize(false)

            // Создаём взависимости от ориентации GridLayoutManager
            this.layoutManager = GridLayoutManager(
                context, when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> 4
                    Configuration.ORIENTATION_PORTRAIT -> 3
                    else -> 3
                }
            )
            this.adapter = adapter
        }

        root.button_add.setOnClickListener(AddClickHandler(adapter, recyclerView))

        return root;
    }

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

    inner class CardClickHandler() : CardHolder.IListener {
        override fun onCardClicked(position: Int) {
            val card = CardRepository.instance.item(position)

            listener?.onCardClicked(card)
        }
    }

}