package com.example.myapplication

import android.graphics.Typeface
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.noties.jlatexmath.JLatexMathDrawable
import ru.noties.jlatexmath.JLatexMathView
import kotlin.properties.Delegates

class DerivativeCalculator : ComponentActivity() {

    lateinit var logoText: TextView
    lateinit var derDesign: JLatexMathView
    lateinit var nEditText: EditText
    lateinit var derrivative: PyObject
    lateinit var varEditText: EditText
    lateinit var py: Python
    lateinit var pyObj: PyObject
    lateinit var solveButton: Button
    lateinit var variable: String
    lateinit var clearButton: Button
    lateinit var func: String
    lateinit var standartN: String
    lateinit var n: String
    lateinit var standartVar: String
    lateinit var solveMathView: JLatexMathView
    lateinit var function: EditText
    var detectCycle by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_derivative)
        //...............................................................
        derDesign = findViewById(R.id.DerrDedign)
        nEditText = findViewById(R.id.nEditText)
        varEditText = findViewById(R.id.varEditText)
        solveButton = findViewById(R.id.sButton)
        clearButton = findViewById(R.id.clearButton)
        solveMathView = findViewById(R.id.solveAndVis)
        logoText = findViewById(R.id.titleText)
        function = findViewById(R.id.function)
        standartN = "1"
        standartVar = "x"
        py = Python.getInstance()
        pyObj = py.getModule("script")
        //...............................................................
        detectCycle = 0
        cycle()
        solveButton.setBackgroundColor(getColor(R.color.button))
        clearButton.setBackgroundColor(getColor(R.color.button))

        val type = Typeface.createFromAsset(assets, "fonts/Bahnschrift.ttf")
        logoText.typeface = type
        solveButton.typeface = type
        clearButton.typeface = type


        solveButton.setOnClickListener {
            detectCycle = 1
            cycle()
            val a: String = setVisSolveReally(n, variable, func)
            if (a == "false") {
                Toast.makeText(this, "Некорректные данные", Toast.LENGTH_LONG).show()
            } else {
                setLatex(solveMathView, a)
            }
            CoroutineScope(Dispatchers.Main).launch {
                while (true) {
                    if (func.toLowerCase() != function.text.toString().toLowerCase()) {
                        detectCycle = 0
                        cycle()
                        break
                    }
                    delay(100)
                }


            }

        }

        clearButton.setOnClickListener {
            detectCycle = 0
            nEditText.setText("")
            varEditText.setText("")
            function.setText("")
            cycle()
        }


    }








        fun setVis(n: String, variable: String): String {

            var a: String = (pyObj.callAttr("setVis", n, variable)).toString()
            return a
        }

        fun setVisSolve(n: String, variable: String, function: String): String {
            var a: String = (pyObj.callAttr("setVisSolve", n, variable, function)).toString()
            return a
        }
        fun setVisSolveReally(n: String, variable: String, function: String): String {
            var a: String = (pyObj.callAttr("setVisSolveReally", n, variable, function)).toString()
            return a
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
                .align(JLatexMathDrawable.ALIGN_CENTER).textSize(210f)
                .build()

            mathView.setLatexDrawable(drawable)
        }

        fun cycle() {
            try {
                val bool: Boolean = true
                CoroutineScope(Dispatchers.Main).launch {
                    while (bool) {
                        if (detectCycle == 1) {
                            break
                            return@launch
                        } else {
                            n = nEditText.text.toString()
                            variable = varEditText.text.toString()
                            func = function.text.toString().toLowerCase()
                            if (n.isEmpty()) {
                                n = standartN

                            }
                            if (variable.isEmpty()) {
                                variable = standartVar
                            }
                            val N = n.toLowerCase()
                            val Variable = variable.toLowerCase()
                            setLatexDesign(derDesign, setVis(N, Variable))
                            setLatex(solveMathView, setVisSolve(N, Variable, func))


                        }
                        delay(100)

                    }
                }
            }
            catch (E:Exception) {

            }
        }
    }