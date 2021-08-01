package com.skylabstechke.foody.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.skylabstechke.foody.R
import com.skylabstechke.foody.adapters.IngredientsRecyclerViewAdapter
import com.skylabstechke.foody.models.Result
import kotlinx.android.synthetic.main.fragment_ingredients.view.*


class IngredientsFragment : Fragment() {

    private val ingredientsRecyclerViewAdapter: IngredientsRecyclerViewAdapter by lazy { IngredientsRecyclerViewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ingredients, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable("recipeBundle")

        setUpRecyclerView(view)
        myBundle?.extendedIngredients.let {
            ingredientsRecyclerViewAdapter.setData(it!!)
        }
        return view
    }

    private fun setUpRecyclerView(view: View) {
        view.ingredientsRecyclerView.adapter = ingredientsRecyclerViewAdapter
        view.ingredientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}