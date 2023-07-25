package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.ComponentActivity
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import ru.noties.jlatexmath.JLatexMathDrawable
import ru.noties.jlatexmath.JLatexMathView

class DoubleIntegralsCalculator: ComponentActivity() {
    lateinit var oneLowLimit: EditText
    lateinit var twoLowLimit: EditText
    lateinit var oneHighLimit: EditText
    lateinit var twoHighLimit: EditText
    lateinit var function: EditText
    lateinit var insideVariable: Spinner
    lateinit var outsideVariable:Spinner
    lateinit var answer: JLatexMathView
    lateinit var py: Python
    lateinit var pyObj: PyObject
    lateinit var diff: JLatexMathView
    lateinit var sButton: Button
    lateinit var clearButton: Button
    lateinit var intVis: JLatexMathView


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.DoubleIntegralsCalculator)

        //...................................................
        oneLowLimit = findViewById(R.id.a1Limit)
        twoLowLimit = findViewById(R.id.a2Limit)
        oneHighLimit = findViewById(R.id.b1Limit)
        twoHighLimit = findViewById(R.id.b2Limit)
        diff = findViewById(R.id.diff)
        function = findViewById(R.id.editText)
        insideVariable = findViewById(R.id.spinnerInside)
        outsideVariable = findViewById(R.id.spinnerOutside)
        sButton = findViewById(R.id.solveButton)
        clearButton = findViewById(R.id.clearButton)
        answer = findViewById(R.id.answer)
        intVis = findViewById(R.id.intVis)
        //...................................................
        setLatex(intVis, "\\iint")


    }



    fun setLatex(mathView: JLatexMathView, formula: String) {
        val drawable: JLatexMathDrawable = JLatexMathDrawable.builder(formula)
            .textSize(100f)
            .background(getColor(R.color.white))
            .align(JLatexMathDrawable.ALIGN_CENTER)
            .align(JLatexMathDrawable.ALIGN_CENTER)
            .build()

        mathView.setLatexDrawable(drawable)
    }
    fun setLatexDesign(mathView: JLatexMathView, formula: String) {
        val drawable: JLatexMathDrawable = JLatexMathDrawable.builder(formula)
            .textSize(100f)
            .background(getColor(R.color.white))
            .align(JLatexMathDrawable.ALIGN_CENTER)
            .align(JLatexMathDrawable.ALIGN_CENTER).textSize(1050f)
            .build()

        mathView.setLatexDrawable(drawable)
    }
}