package com.skylabstechke.foody.ui.fragments.recipes.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.skylabstechke.foody.R
import com.skylabstechke.foody.utils.Constants.Companion.DEFAULT_DIET_TYPE
import com.skylabstechke.foody.utils.Constants.Companion.DEFAULT_MEAL_TYPE
import com.skylabstechke.foody.viewmodels.RecipesViewModel
import kotlinx.android.synthetic.main.recipes_bottom_sheet.view.*
import java.util.*


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
        view.meal_type_chipGroupId.setOnCheckedChangeListener { group, checkedId ->

            val chip = group.findViewById<Chip>(checkedId)
            val selectedChip = chip.text.toString().toLowerCase(Locale.ROOT)
            mealTypeChip = selectedChip
            mealTypeChipId = checkedId

        }

        view.dietTypeChipGroupId.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedDiet = chip.text.toString().toLowerCase(Locale.ROOT)

            dietTypeChip = selectedDiet
            dietTypeChipId = checkedId

        }
        rView.readMealDietType.asLiveData().observe(viewLifecycleOwner, { value ->

            mealTypeChipId = value.selectedMealTypeId
            dietTypeChipId = value.selectedDietTypeId
            Log.d("CHIPS MEAL", mealTypeChipId.toString())
            Log.d("CHIPS DIET", dietTypeChipId.toString())
            updateChip(value.selectedMealTypeId, view.meal_type_chipGroupId)
            updateChip(value.selectedDietTypeId, view.dietTypeChipGroupId)
            Log.d("CHIPS SELECTED meal",value.selectedMealTypeId.toString())
            Log.d("CHIPS SELECTED diet",value.selectedDietTypeId.toString())


        })

        view.mealDietApplyButton.setOnClickListener {
            Log.d("CHIPS SAVE", dietTypeChipId.toString())
            Log.d("CHIPS SAVE", dietTypeChip.toString())
            Log.d("CHIPS SAVE", mealTypeChip.toString())
            Log.d("CHIPS SAVE", mealTypeChipId.toString())
            rView.saveMealAndDietType(mealTypeChip, mealTypeChipId, dietTypeChip, dietTypeChipId)
            val action = BottomSheetDirections.actionBottomSheetToRecipesFragment(true)
            findNavController().navigate(action)
        }
        return view;
    }

    private fun updateChip(chipId: Int, group: ChipGroup?) {
        Log.d("CHIPSID",chipId.toString())

        if (chipId != 0) {
            try {
                group?.findViewById<Chip>(chipId)?.isChecked = true
            }catch (e:Exception){

                Log.d("UPDATE",e.message.toString())
            }

        }


    }


}