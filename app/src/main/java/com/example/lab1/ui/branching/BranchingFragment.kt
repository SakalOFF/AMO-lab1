package com.example.lab1.ui.branching

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
import kotlinx.android.synthetic.main.fragment_branching.*
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class BranchingFragment : Fragment() {

    private lateinit var branchingViewModel: BranchingViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        branchingViewModel =
                ViewModelProvider(this).get(BranchingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_branching, container, false)

        val mathView: MathView = root.findViewById(R.id.branching_formula_view)
        val mathml = "\$\$y = \\begin{cases}\n" +
                     "   \\sin{x} + \\sqrt{\\cos{x}} &\\text{if } x>0 \\\\\n" +
                     "   \\sqrt{\\frac{a+x}{b-x}} &\\text{if } x\\leqslant0\n" +
                     "\\end{cases}\$\$"
        mathView.text = mathml
        val calculateButton: Button = root.findViewById(R.id.branching_calculate_button)
        calculateButton.setOnClickListener(this::branchingCalculate)
        val pictureButton: Button = root.findViewById(R.id.branching_scheme_button)
        pictureButton.setOnClickListener(this::onPictureButtonClick)

        return root
    }

    private fun branchingCalculate(view: View){
        val a  = branching_a_value.text.toString()
        val b  = branching_b_value.text.toString()
        val x  = branching_x_value.text.toString()
        if(a == "" || b == "" || x == ""){
            branchingResultView.text = ""
            val myToast = Toast.makeText(activity, "Необхідно задати всі змінні", Toast.LENGTH_SHORT)
            myToast.show()
        }
        else if(x.toDouble() < 0 && x == b){
            branchingResultView.text = ""
            val myToast = Toast.makeText(activity, "Помилка, ділення на нуль", Toast.LENGTH_SHORT)
            myToast.show()
        }
        else if(cos(x.toDouble()) < 0 || ((a.toDouble() + b.toDouble()) / (b.toDouble() - x.toDouble())) < 0){
            branchingResultView.text = ""
            val myToast = Toast.makeText(activity, "Неможливо взяти корінь із від'ємного числа", Toast.LENGTH_SHORT)
            myToast.show()
        }
        else{
            val y: Double = if (x.toDouble() > 0){
                sin(x.toDouble()) + sqrt(cos(x.toDouble()))
            } else
                sqrt((a.toDouble() + x.toDouble()) / (b.toDouble() - x.toDouble()))
            branchingResultView.text = getString(R.string.y_string, y)
        }
    }

    private fun onPictureButtonClick(view: View){
        val myAct: OnFragmentInteractionListener = activity as OnFragmentInteractionListener
        myAct.onFragmentInteraction(R.id.nav_branching)
    }

}