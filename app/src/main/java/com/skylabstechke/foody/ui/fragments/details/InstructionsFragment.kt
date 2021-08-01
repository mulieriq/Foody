package com.skylabstechke.foody.ui.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.skylabstechke.foody.R
import com.skylabstechke.foody.models.Result
import kotlinx.android.synthetic.main.fragment_instructions.view.*


class InstructionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val arg= arguments
        val myBundle:Result? = arg?.getParcelable<Result>("recipeBundle")
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_instructions, container, false)

        view.instructionsWebView.webViewClient = object : WebViewClient(){}
                view.instructionsWebView.loadUrl(myBundle?.sourceUrl!!)

        return  view
    }


}