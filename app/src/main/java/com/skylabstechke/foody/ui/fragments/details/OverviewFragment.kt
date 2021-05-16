package com.skylabstechke.foody.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.skylabstechke.foody.R
import com.skylabstechke.foody.models.Result
import kotlinx.android.synthetic.main.fragment_overview.view.*


class OverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_overview, container, false)
        val args = arguments
        val myResult: Result? = args?.getParcelable("recipeBundle")

        view.image.load(myResult?.image)
        view.overview_title.text = myResult?.title
        view.time.text = myResult?.readyInMinutes.toString()
        view.likes.text = myResult?.aggregateLikes.toString()
        view.description_text.text = myResult?.summary

        return view
    }


}