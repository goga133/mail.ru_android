package com.goga133.hw1.presentationlayer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.goga133.hw1.R
import com.goga133.hw1.objects.Card
import com.goga133.hw1.presentationlayer.listeners.ICardListener
import kotlinx.android.synthetic.main.fragment_details.view.*

/**
 * @author <a href="mailto:asromanyuk@edu.hse.ru"> Andrey Romanyuk</a>
 */
open class CardDetailsFragment : Fragment() {
    private var listener: ICardListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = requireActivity() as? ICardListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        private const val CARD_NAME = "card"

        fun newInstance(card: Card): CardDetailsFragment {
            return CardDetailsFragment().apply {
                arguments = Bundle().apply { putSerializable(CARD_NAME, card) }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_details, container, false)

        // Слушатель для кнопки "Back":
        root.button_back.setOnClickListener(BackClickHandler())

        // Получение переданного экземпляра Card:
        val card: Card = this.arguments?.getSerializable(CARD_NAME) as Card

        root.textView_number.apply {
            text = card.number.toString()
            setTextColor(card.getColor())
        }

        return root
    }

    inner class BackClickHandler : View.OnClickListener {
        override fun onClick(view: View?) {
            listener?.onCardBack();
        }
    }
}
