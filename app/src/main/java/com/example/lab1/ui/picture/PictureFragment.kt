package com.example.lab1.ui.picture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lab1.MainActivity
import com.example.lab1.R
import kotlinx.android.synthetic.main.fragment_picture.*

class PictureFragment : Fragment() {

    private lateinit var pictureViewModel: PictureViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pictureViewModel =
            ViewModelProvider(this).get(PictureViewModel::class.java)

        return inflater.inflate(R.layout.fragment_picture, container, false)
    }

    override fun onResume() {
        super.onResume()
        val myAct = activity as MainActivity

        when(myAct.sourceId){
            R.id.nav_linear -> setPicture("linear_scheme.png")
            R.id.nav_branching -> setPicture("branching_scheme.png")
            R.id.nav_cyclic -> setPicture("cyclic_scheme.png")
        }
    }

    private fun setPicture(fileName: String){
        val myPictureView: WebView = pictureView.findViewById(R.id.pictureView)
        myPictureView.settings.setSupportZoom(true)
        myPictureView.settings.builtInZoomControls = true
        myPictureView.setPadding(0, 0, 0, 0)
        myPictureView.isScrollbarFadingEnabled = true
        myPictureView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        myPictureView.loadUrl("file:///android_res/drawable/$fileName")
    }
}