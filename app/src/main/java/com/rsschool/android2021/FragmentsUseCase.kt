package com.rsschool.android2021

interface FragmentsUseCase {
    fun openFirstFragmentInterface(previousNumber: Int)

    fun openSecondFragmentInterface(min: Int, max: Int)

    fun showToast(message: String)

    fun onBackPressedListener(previousNumber: Int)
}