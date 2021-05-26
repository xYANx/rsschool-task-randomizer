package com.rsschool.android2021

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import kotlin.jvm.internal.Intrinsics
import kotlin.random.Random

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minValue: EditText? = null
    private var maxValue: EditText? = null
    private var fragmentsUseCase: FragmentsUseCase? = null
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        fragmentsUseCase = activity as MainActivity
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
        minValue = view.findViewById(R.id.min_value)
        maxValue = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {
            if(minValue?.text?.isNotEmpty() == true && maxValue?.text?.isNotEmpty() == true) {
                val min = minValue?.text.toString().toIntOrNull()
                val max = maxValue?.text.toString().toIntOrNull()
                if (min != null && max != null) {
                    if (min < 0 || max < 0 || min > max) {
                        fragmentsUseCase?.showToast("Min or Max value is incorrect")
                    } else {
                        fragmentsUseCase?.openSecondFragmentInterface(min, max)
                    }
                } else fragmentsUseCase?.showToast("Min or Max value is incorrect")
            } else fragmentsUseCase?.showToast("Empty value")
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
}