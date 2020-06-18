package com.example.lab1.ui.linear

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lab1.OnFragmentInteractionListener
import com.example.lab1.R
import io.github.kexanie.library.MathView
import kotlinx.android.synthetic.main.fragment_linear.*
import kotlin.math.pow

class LinearFragment : Fragment() {

    private lateinit var linearViewModel: LinearViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        linearViewModel =
                ViewModelProvider(this).get(LinearViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_linear, container, false)
        val mathView: MathView = root.findViewById(R.id.linear_formula_view)

        val mathml = "<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" mathcolor=\"black\">" +
                "  <mi>Y</mi>" +
                "  <mo>=</mo>" +
                "  <msup>" +
                "      <mrow>" +
                "          <mo>(</mo>" +
                "          <mi>a</mi>" +
                "          <mo>+</mo>" +
                "          <mfrac>" +
                "              <mi>b</mi>" +
                "              <mi>c</mi>" +
                "          </mfrac>" +
                "          <mo>)</mo>" +
                "      </mrow>" +
                "      <mi>d</mi>" +
                "  </msup>" +
                "  <mo>+</mo>" +
                "  <msup>" +
                "      <mrow>" +
                "          <mo>(</mo>" +
                "          <mi>d</mi>" +
                "          <mo>+</mo>" +
                "          <mfrac>" +
                "              <mi>b</mi>" +
                "              <mi>c</mi>" +
                "          </mfrac>" +
                "          <mo>)</mo>" +
                "      </mrow>" +
                "      <mi>a</mi>" +
                "  </msup>" +
                "</math>"
        mathView.text = mathml
        val calculateButton: Button = root.findViewById(R.id.linear_calculate_button)
        calculateButton.setOnClickListener(this::linearCalculate)
        val pictureButton: Button = root.findViewById(R.id.linear_scheme_button)
        pictureButton.setOnClickListener(this::onPictureButtonClick)
        return root
    }

    private fun linearCalculate(view: View){
        val a  = a_value.text.toString()
        val b  = b_value.text.toString()
        val c  = c_value.text.toString()
        val d  = d_value.text.toString()
        if(a == "" || b == "" || c == "" || d == ""){
            linearResultView.text = ""
            val myToast = Toast.makeText(activity, "Необхідно задати всі змінні", Toast.LENGTH_SHORT)
            myToast.show()
        }
        else{
            val y = (a.toDouble() + b.toDouble() / c.toDouble()).pow(d.toDouble()) +
                    (d.toDouble() + b.toDouble() / c.toDouble()).pow(a.toDouble())
            linearResultView.text = getString(R.string.Y_string, y)
        }
    }

    private fun onPictureButtonClick(view: View){
        val myAct: OnFragmentInteractionListener = activity as OnFragmentInteractionListener
        myAct.onFragmentInteraction(R.id.nav_linear)
    }
}