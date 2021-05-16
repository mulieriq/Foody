package com.skylabstechke.foody.ui.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skylabstechke.foody.R
import com.skylabstechke.foody.models.Result


class OverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_overview, container, false)
        var args = arguments
        val  myResult: Result? = args?.getParcelable("recipeBundle")



        return view
    }


}