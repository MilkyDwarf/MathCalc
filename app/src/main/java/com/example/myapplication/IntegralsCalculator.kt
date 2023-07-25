package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
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
import java.io.IOException
import java.lang.NumberFormatException
import kotlin.properties.Delegates

//methods

class IntegralsCalculator : ComponentActivity() {

    lateinit var sButton: Button
    lateinit var function: EditText
    lateinit var sText: JLatexMathView
    lateinit var testText: TextView
    lateinit var intVis: JLatexMathView
    lateinit var diff: JLatexMathView
    lateinit var spinner: Spinner
    lateinit var lowLimit: EditText
    lateinit var highLimit: EditText
    lateinit var funcToVis: String
    lateinit var integral: PyObject
    lateinit var a: String
    lateinit var b: String
    lateinit var clearBut: Button
    var bool by Delegates.notNull<Boolean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_integrals)

        //Find views by id............................
        sButton = findViewById(R.id.solveButton)
        function = findViewById(R.id.editText)
        sText = findViewById(R.id.answer)
        intVis = findViewById(R.id.intVis)
        diff = findViewById(R.id.diff)
        spinner = findViewById(R.id.spinner)
        lowLimit = findViewById(R.id.aLimit)
        highLimit = findViewById(R.id.bLimit)
        clearBut = findViewById(R.id.clearButton)
        //..............................................
        function.setText("1")
        sButton.setBackgroundColor(getColor(R.color.button))
        clearBut.setBackgroundColor(getColor(R.color.button))
        //python accessing
        val py: Python = Python.getInstance()
        val pyObj: PyObject = py.getModule("script")

        //...............
        bool = true
        //Get Selected variable

        //.................
        CoroutineScope(Dispatchers.Main).launch {
                while (bool) {
                    try {
                        a = lowLimit.text.toString()
                        b = highLimit.text.toString()
                        funcToVis = function.text.toString().toLowerCase()
                        if (funcToVis.isEmpty()) {
                            funcToVis = "f(x)"
                        }
                        if (lowLimit.text.isEmpty() and highLimit.text.isEmpty()) {
                            integral =
                                pyObj.callAttr("intVis", funcToVis, spinner.selectedItem.toString())
                            setLatex(sText, integral.toString())
                        }
                        if (!(lowLimit.text.isEmpty()) and highLimit.text.isEmpty()) {
                            integral =
                                pyObj.callAttr(
                                    "intVis",
                                    funcToVis,
                                    spinner.selectedItem.toString(),
                                    a,
                                    "b"
                                )
                            setLatex(sText, integral.toString())
                        }
                        if (lowLimit.text.isEmpty() and !(highLimit.text.isEmpty())) {
                            integral =
                                pyObj.callAttr(
                                    "intVis",
                                    funcToVis,
                                    spinner.selectedItem.toString(),
                                    "a",
                                    b
                                )
                            setLatex(sText, integral.toString())
                        }
                        if (!(lowLimit.text.isEmpty()) and !(highLimit.text.isEmpty())) {
                            integral =
                                pyObj.callAttr(
                                    "intVis",
                                    funcToVis,
                                    spinner.selectedItem.toString(),
                                    a,
                                    b
                                )
                            setLatex(sText, integral.toString())
                        }
                    }
                    catch (e: Exception) {

                    }

                    delay(100)
                }
            }


        setLatexDesign(intVis, "\\int")
        CoroutineScope(Dispatchers.Main).launch {
            for (i in 1..999999) {
                val selected: String = spinner.selectedItem.toString()
                differential(diff, "d" + selected)
                delay(100)
            }
        }


        //set on click listener to find integral..................
        sButton.setOnClickListener {
            bool = false

            try {
                a = lowLimit.text.toString()
                b = highLimit.text.toString()
                if (a.isEmpty() and b.isEmpty()) {
                    integral = pyObj.callAttr(
                        "indefIntegrate",
                        function.text.toString().toLowerCase(),
                        spinner.selectedItem.toString()
                    )
                    setLatex(sText, integral.toString())

                }
                else if (!(a.isEmpty()) and !(b.isEmpty())) {
                    integral = pyObj.callAttr(
                        "defIntegrate",
                        function.text.toString().toLowerCase(),
                        spinner.selectedItem.toString(), a, b
                    )
                    setLatex(sText, integral.toString())
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Некорректный ввод данных", Toast.LENGTH_LONG).show()
            }

        }
        clearBut.setOnClickListener {
            bool = true
            function.text.clear()
            CoroutineScope(Dispatchers.Main).launch {
                while (bool) {
                    try {
                        a = lowLimit.text.toString()
                        b = highLimit.text.toString()
                        funcToVis = function.text.toString().toLowerCase()
                        if (funcToVis.isEmpty()) {
                            funcToVis = "f(x)"
                        }
                        if (lowLimit.text.isEmpty() and highLimit.text.isEmpty()) {
                            integral =
                                pyObj.callAttr("intVis", funcToVis, spinner.selectedItem.toString())
                            setLatex(sText, integral.toString())
                        }
                        if (!(lowLimit.text.isEmpty()) and highLimit.text.isEmpty()) {
                            integral = pyObj.callAttr(
                                "intVis",
                                funcToVis,
                                spinner.selectedItem.toString(),
                                a,
                                "b"
                            )
                            setLatex(sText, integral.toString())
                        }
                        if (lowLimit.text.isEmpty() and !(highLimit.text.isEmpty())) {
                            integral = pyObj.callAttr(
                                "intVis",
                                funcToVis,
                                spinner.selectedItem.toString(),
                                "a",
                                b
                            )
                            setLatex(sText, integral.toString())
                        }
                        if (!(lowLimit.text.isEmpty()) and !(highLimit.text.isEmpty())) {
                            integral = pyObj.callAttr(
                                "intVis",
                                funcToVis,
                                spinner.selectedItem.toString(),
                                a,
                                b
                            )
                            setLatex(sText, integral.toString())
                        }
                    }
                    catch (e:Exception) {
                        Toast.makeText(this@IntegralsCalculator, "ОШИБКА НАМ ВСЕМ КОНЕЦ", Toast.LENGTH_LONG).show()
                    }

                    delay(100)
                }
            }

            //........................................................
        }
    }

    fun undefinedIntegrate(func: String, variable: String) {

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
    fun differential(mathView: JLatexMathView, formula: String) {
        val drawable: JLatexMathDrawable = JLatexMathDrawable.builder(formula)
            .textSize(100f)
            .background(getColor(R.color.white))
            .align(JLatexMathDrawable.ALIGN_CENTER)
            .align(JLatexMathDrawable.ALIGN_CENTER)
            .build()

        mathView.setLatexDrawable(drawable)
    }
}



