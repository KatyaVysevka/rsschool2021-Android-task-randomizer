package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    private var backButton: Button? = null
    private var result: TextView? = null
    private var secondFragmentListener: SecondFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        secondFragmentListener = context as SecondFragmentListener
    }

    interface SecondFragmentListener {
        fun secondFragmentListener(result: Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        result?.text = generate(min, max).toString()

        backButton?.setOnClickListener {
            // TODO: implement back
            toFirstFragment()
        }
    }

    private fun toFirstFragment() {
        val previousNumber = result?.text.toString().toInt()
        secondFragmentListener?.secondFragmentListener(previousNumber)
    }

    private fun generate(min: Int, max: Int): Int {
        // TODO: generate random number
        return ((max - min) * Math.random() + min).toInt()
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            // TODO: implement adding arguments
            args.putInt(MIN_VALUE_KEY, min)
            args.putInt(MAX_VALUE_KEY, max)
            fragment.arguments = args

            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}