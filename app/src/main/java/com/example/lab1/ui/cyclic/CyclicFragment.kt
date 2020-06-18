package com.example.lab1.ui.cyclic

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
import kotlinx.android.synthetic.main.fragment_cyclic.*
import java.lang.Exception

class MyException(str: String) : Exception(str)

class CyclicFragment : Fragment() {

    private lateinit var cyclicViewModel: CyclicViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        cyclicViewModel =
                ViewModelProvider(this).get(CyclicViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cyclic, container, false)

        val mathView: MathView = root.findViewById(R.id.cyclic_formula_view)
        val mathml = "<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" mathcolor=\"black\">" +
                "  <mi>f</mi>" +
                "  <mo>=</mo>" +
                "  <mfrac>" +
                "      <mn>1</mn>" +
                "      <msub>" +
                "          <mi>n</mi>" +
                "          <mn>1</mn>" +
                "      </msub>" +
                "  </mfrac>" +
                "  <mo>+</mo>" +
                "  <mfrac>" +
                "      <mn>1</mn>" +
                "      <msub>" +
                "          <mi>n</mi>" +
                "          <mn>2</mn>" +
                "      </msub>" +
                "  </mfrac>" +
                "  <mo>+</mo>" +
                "  <mo>...</mo>" +
                "  <mo>+</mo>" +
                "  <mfrac>" +
                "      <mn>1</mn>" +
                "      <msub>" +
                "          <mi>n</mi>" +
                "          <mi>m</mi>" +
                "      </msub>" +
                "  </mfrac>" +
                "</math>"
        mathView.text = mathml
        val calculateButton: Button = root.findViewById(R.id.cyclic_calculate_button)
        calculateButton.setOnClickListener(this::cyclicCalculate)
        val pictureButton: Button = root.findViewById(R.id.cyclic_scheme_button)
        pictureButton.setOnClickListener(this::onPictureButtonClick)

        return root
    }

    private fun cyclicCalculate(view: View){
        try{
            val nString = cyclic_n_value.text.toString().trim().split(" ")
            val n: Array<Double>
            n = Array(nString.size) { i -> (nString[i].toDouble())}
            var f = 0.0
            for(value: Double in n){
                if(value == 0.0){
                    val me = MyException("Помилка, ділення на нуль")
                    throw me
                }
                f += 1 / value
            }
            cyclicResultView.text = getString(R.string.f_string, f)
        }
        catch (ex: NumberFormatException){
            cyclicResultView.text = ""
            val myToast = Toast.makeText(activity, "Вводити слід лише числа через пробіл", Toast.LENGTH_SHORT)
            myToast.show()
        }
        catch (ex: MyException){
            cyclicResultView.text = ""
            val myToast = Toast.makeText(activity, ex.message, Toast.LENGTH_SHORT)
            myToast.show()
        }
    }

    private fun onPictureButtonClick(view: View){
        val myAct: OnFragmentInteractionListener = activity as OnFragmentInteractionListener
        myAct.onFragmentInteraction(R.id.nav_cyclic)
    }

}