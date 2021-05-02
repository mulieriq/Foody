package com.skylabstechke.foody.ui.fragments.recipes.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skylabstechke.foody.R
import com.skylabstechke.foody.utils.Constants.Companion.DEFAULT_DIET_TYPE
import com.skylabstechke.foody.utils.Constants.Companion.DEFAULT_MEAL_TYPE
import com.skylabstechke.foody.viewmodels.RecipesViewModel


class BottomSheet : BottomSheetDialogFragment() {

    private lateinit var rView: RecipesViewModel


    private var mealTypeChip = DEFAULT_MEAL_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rView = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recipes_bottom_sheet, container, false)
        return view;
    }


}