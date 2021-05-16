package com.skylabstechke.foody.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
        when {
            myResult?.vegetarian == true -> {
                view.veg_image.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
                view.vegeterian.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
            }
            myResult?.vegan == true -> {
                view.vegan_image.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
                view.vegan.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            }
            myResult?.glutenFree == true -> {
                view.gluten_image.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
                view.gluten_text.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
            }
            myResult?.dairyFree == true -> {
                view.dairy_image.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
                view.dairy_text.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
            }
            myResult?.veryHealthy == true -> {
                view.health_image.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
                view.health_text.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
            }

        }


        return view
    }


}