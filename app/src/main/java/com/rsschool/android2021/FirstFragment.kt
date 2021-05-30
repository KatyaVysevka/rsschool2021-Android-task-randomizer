package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minEdit: EditText? = null
    private var maxEdit: EditText? = null
    private var firstFragmentListener: FirstFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        firstFragmentListener = context as FirstFragmentListener
    }

    interface FirstFragmentListener {
        fun firstFragmentListener(min: Int, max: Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        // TODO: val min = ...
        // TODO: val max = ...
        minEdit = view.findViewById(R.id.min_value)
        maxEdit = view.findViewById(R.id.max_value)

        generateButton?.setOnClickListener {
            // TODO: send min and max to the SecondFragment
            val min = minEdit?.text.toString()
            val max = maxEdit?.text.toString()
            when (true) {
                (min == "" || max == "") -> Toast.makeText(
                    context, "Пожалуйста, введите данные", Toast.LENGTH_SHORT
                ).show()
                (max.toInt() == 0) -> Toast.makeText(
                    context, "Максимальное значение не может быть равно 0", Toast.LENGTH_SHORT
                ).show()
                (min.toInt() > max.toInt()) -> Toast.makeText(
                    context, "Минимальное значение не может быть меньше максимального",
                    Toast.LENGTH_SHORT
                ).show()
                else -> {
                    firstFragmentListener?.firstFragmentListener(min.toInt(), max.toInt())
                }
            }


        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        generateButton = null
        previousResult = null
        minEdit = null
        maxEdit = null
    }
}

