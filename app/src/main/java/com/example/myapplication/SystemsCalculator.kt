package com.example.myapplication

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.rememberTopAppBarState
import androidx.core.view.updateLayoutParams
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import ru.noties.jlatexmath.JLatexMathDrawable
import ru.noties.jlatexmath.JLatexMathView
import kotlin.properties.Delegates

class SystemsCalculator: ComponentActivity() {
    lateinit var eq1: EditText
    lateinit var eq2: EditText
    lateinit var eq3: EditText
    lateinit var eq4: EditText
    lateinit var eq5: EditText
    lateinit var eq6: EditText
    lateinit var eq7: EditText
    lateinit var eq8: EditText
    lateinit var eq9: EditText
    lateinit var eq10: EditText
    lateinit var line: LinearLayout
    lateinit var pyObj: PyObject
    lateinit var py:Python
    lateinit var systemSign: JLatexMathView
    lateinit var solveView: JLatexMathView
    lateinit var plusButton: Button
    lateinit var minusButton: Button
    lateinit var clearButton: Button
    lateinit var solveButton: Button
    var n by Delegates.notNull<Int>()
    lateinit var complexBox: CheckBox
    var wdth by Delegates.notNull<Int>()
    var hthh by Delegates.notNull<Int>()


    override fun onStart() {
        super.onStart()
        setContentView(R.layout.calculator_systems)

        //...............................................................
        eq1 = findViewById(R.id.eq1)
        eq2 = findViewById(R.id.eq2)
        eq3 = findViewById(R.id.eq3)
        eq4 = findViewById(R.id.eq4)
        eq5 = findViewById(R.id.eq5)
        eq6 = findViewById(R.id.eq6)
        eq7 = findViewById(R.id.eq7)
        eq8 = findViewById(R.id.eq8)
        eq9 = findViewById(R.id.eq9)
        eq10 = findViewById(R.id.eq10)
        line = findViewById(R.id.Linear1)
        wdth = 84

        py = Python.getInstance()
        pyObj = py.getModule("script")
        systemSign = findViewById(R.id.systemSign)
        solveView = findViewById(R.id.solveView)
        plusButton = findViewById(R.id.plusBut)
        minusButton = findViewById(R.id.minusBut)
        clearButton = findViewById(R.id.clear)
        solveButton = findViewById(R.id.solve)
        complexBox = findViewById(R.id.complexBox)
        n = 10
        //...........................................................

        changeNumEqs()
        setLatexDesign(systemSign, "\\begin{cases}  &\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\&\\\\   \\end{cases}   ")


        minusButton.setOnClickListener {
            if (n>2) {
                wdth = systemSign.width
                hthh = systemSign.height

                n-=1
                changeNumEqs()
            }
            else if (n == 2) {
                n -= 1
                setLatexDesign(systemSign, "")
                changeNumEqs()
            }
            else {
                Toast.makeText(this, "Не бывает блин систем уравнений из нуля уравнений", Toast.LENGTH_LONG).show()
            }
        }
        plusButton.setOnClickListener {
            if (n<10) {
                wdth = systemSign.width
                hthh = systemSign.height
                n+=1
                changeNumEqs()

            }
            else {
                Toast.makeText(this, "Слишком много уравнений, остановитесь!!!!", Toast.LENGTH_LONG).show()
            }
        }


        solveButton.setOnClickListener {
            val bool: Boolean = complexBox.isChecked


            if ((!complexBox.isChecked)) {
                try {
                    solverReal()
                }
                catch (E:Exception) {
                    Toast.makeText(this, "Ошибка, возможно вы ввели что-то некорректно, либо не хватает мощности для вычисления", Toast.LENGTH_LONG).show()
                } }
                else {
                    try {
                        solverAll()
                    }
                    catch (E:Exception) {
                        Toast.makeText(this, "Ошибка, возможно вы ввели что-то некорректно, либо не хватает мощности для вычисления", Toast.LENGTH_LONG).show()
                    }
                }
            }











    }
    fun solverReal() {
        when (n) {
            1 -> {setLatex(solveView, pyObj.callAttr("solverReal", n, eq1.text.toString()).toString()) }
            2-> {
                setLatex(solveView, pyObj.callAttr("solverReal", n, eq1.text.toString(), eq2.text.toString()).toString())
            }
            3->setLatex(solveView, pyObj.callAttr("solverReal", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString()).toString())
            4 -> setLatex(solveView, pyObj.callAttr("solverReal", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString(), eq4.text.toString()).toString())
            5 -> setLatex(solveView, pyObj.callAttr("solverReal", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString(), eq4.text.toString(), eq5.text.toString()).toString())
            6 -> setLatex(solveView, pyObj.callAttr("solverReal", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString(), eq4.text.toString(), eq5.text.toString(), eq6.text.toString()).toString())
            7 -> setLatex(solveView, pyObj.callAttr("solverReal", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString(), eq4.text.toString(), eq5.text.toString(),  eq6.text.toString(),  eq7.text.toString()).toString())
            8 -> setLatex(solveView, pyObj.callAttr("solverReal", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString(), eq4.text.toString(), eq5.text.toString(), eq6.text.toString(),  eq7.text.toString(), eq8.text.toString()).toString())
            9 -> setLatex(solveView, pyObj.callAttr("solverReal", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString(), eq4.text.toString(), eq5.text.toString(), eq6.text.toString(),  eq7.text.toString(), eq8.text.toString(), eq9.text.toString() ).toString())
            10 -> setLatex(solveView, pyObj.callAttr("solverReal", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString(), eq4.text.toString(), eq5.text.toString(),  eq6.text.toString(),  eq7.text.toString(), eq8.text.toString(), eq9.text.toString(), eq10.text.toString()).toString())
        }
        }
    fun solverAll() {
        when (n) {
            1 -> {
                setLatex(solveView, pyObj.callAttr("solverAll", n, eq1.text.toString()).toString()) }
            2-> {
                setLatex(solveView, pyObj.callAttr("solverAll", n, eq1.text.toString(), eq2.text.toString()).toString())
            }
            3->setLatex(solveView, pyObj.callAttr("solverAll", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString()).toString())
            4 -> setLatex(solveView, pyObj.callAttr("solverAll", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString(), eq4.text.toString()).toString())
            5 -> setLatex(solveView, pyObj.callAttr("solverAll", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString(), eq4.text.toString(), eq5.text.toString()).toString())
            6 -> setLatex(solveView, pyObj.callAttr("solverAll", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString(), eq4.text.toString(), eq5.text.toString(), eq6.text.toString()).toString())
            7 -> setLatex(solveView, pyObj.callAttr("solverAll", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString(), eq4.text.toString(), eq5.text.toString(),  eq6.text.toString(),  eq7.text.toString()).toString())
            8 -> setLatex(solveView, pyObj.callAttr("solverAll", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString(), eq4.text.toString(), eq5.text.toString(), eq6.text.toString(),  eq7.text.toString(), eq8.text.toString()).toString())
            9 -> setLatex(solveView, pyObj.callAttr("solverAll", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString(), eq4.text.toString(), eq5.text.toString(), eq6.text.toString(),  eq7.text.toString(), eq8.text.toString(), eq9.text.toString() ).toString())
            10 -> setLatex(solveView, pyObj.callAttr("solverAll", n, eq1.text.toString(), eq2.text.toString(), eq3.text.toString(), eq4.text.toString(), eq5.text.toString(),  eq6.text.toString(),  eq7.text.toString(), eq8.text.toString(), eq9.text.toString(), eq10.text.toString()).toString())
        }
    }


    fun changeNumEqs() {
            when (n) {
                10 -> {
                    eq1.setVisibility(View.VISIBLE)
                    eq2.setVisibility(View.VISIBLE)
                    eq3.setVisibility(View.VISIBLE)
                    eq4.setVisibility(View.VISIBLE)
                    eq5.setVisibility(View.VISIBLE)
                    eq6.setVisibility(View.VISIBLE)
                    eq7.setVisibility(View.VISIBLE)
                    eq8.setVisibility(View.VISIBLE)
                    eq9.setVisibility(View.VISIBLE)
                    eq10.setVisibility(View.VISIBLE)

                }
                9 -> {

                    eq1.setVisibility(View.VISIBLE)
                    eq2.setVisibility(View.VISIBLE)
                    eq3.setVisibility(View.VISIBLE)
                    eq4.setVisibility(View.VISIBLE)
                    eq5.setVisibility(View.VISIBLE)
                    eq6.setVisibility(View.VISIBLE)
                    eq7.setVisibility(View.VISIBLE)
                    eq8.setVisibility(View.VISIBLE)
                    eq9.setVisibility(View.VISIBLE)
                    eq10.setVisibility(View.GONE)
                    eq10.setText("")

                }
                8-> {

                    eq1.setVisibility(View.VISIBLE)
                    eq2.setVisibility(View.VISIBLE)
                    eq3.setVisibility(View.VISIBLE)
                    eq4.setVisibility(View.VISIBLE)
                    eq5.setVisibility(View.VISIBLE)
                    eq6.setVisibility(View.VISIBLE)
                    eq7.setVisibility(View.VISIBLE)
                    eq8.setVisibility(View.VISIBLE)
                    eq9.setVisibility(View.GONE)
                    eq10.setVisibility(View.GONE)
                    eq10.setText("")
                    eq9.setText("")
                }
                7-> {
                    eq1.setVisibility(View.VISIBLE)
                    eq2.setVisibility(View.VISIBLE)
                    eq3.setVisibility(View.VISIBLE)
                    eq4.setVisibility(View.VISIBLE)
                    eq5.setVisibility(View.VISIBLE)
                    eq6.setVisibility(View.VISIBLE)
                    eq7.setVisibility(View.VISIBLE)
                    eq8.setVisibility(View.GONE)
                    eq9.setVisibility(View.GONE)
                    eq10.setVisibility(View.GONE)
                    eq10.setText("")
                    eq9.setText("")
                    eq8.setText("")
                }
                6-> {
                    eq1.setVisibility(View.VISIBLE)
                    eq2.setVisibility(View.VISIBLE)
                    eq3.setVisibility(View.VISIBLE)
                    eq4.setVisibility(View.VISIBLE)
                    eq5.setVisibility(View.VISIBLE)
                    eq6.setVisibility(View.VISIBLE)
                    eq7.setVisibility(View.GONE)
                    eq8.setVisibility(View.GONE)
                    eq9.setVisibility(View.GONE)
                    eq10.setVisibility(View.GONE)
                    eq10.setText("")
                    eq9.setText("")
                    eq8.setText("")
                    eq7.setText("")

                }
                5-> {
                    eq1.setVisibility(View.VISIBLE)
                    eq2.setVisibility(View.VISIBLE)
                    eq3.setVisibility(View.VISIBLE)
                    eq4.setVisibility(View.VISIBLE)
                    eq5.setVisibility(View.VISIBLE)
                    eq6.setVisibility(View.GONE)
                    eq7.setVisibility(View.GONE)
                    eq8.setVisibility(View.GONE)
                    eq9.setVisibility(View.GONE)
                    eq10.setVisibility(View.GONE)
                    eq10.setText("")
                    eq9.setText("")
                    eq8.setText("")
                    eq7.setText("")
                    eq6.setText("")
                }
                4-> {
                    eq1.setVisibility(View.VISIBLE)
                    eq2.setVisibility(View.VISIBLE)
                    eq3.setVisibility(View.VISIBLE)
                    eq4.setVisibility(View.VISIBLE)
                    eq5.setVisibility(View.GONE)
                    eq6.setVisibility(View.GONE)
                    eq7.setVisibility(View.GONE)
                    eq8.setVisibility(View.GONE)
                    eq9.setVisibility(View.GONE)
                    eq10.setVisibility(View.GONE)
                    eq10.setText("")
                    eq9.setText("")
                    eq8.setText("")
                    eq7.setText("")
                    eq6.setText("")
                    eq5.setText("")
                }
                3-> {
                    eq1.setVisibility(View.VISIBLE)
                    eq2.setVisibility(View.VISIBLE)
                    eq3.setVisibility(View.VISIBLE)
                    eq4.setVisibility(View.GONE)
                    eq5.setVisibility(View.GONE)
                    eq6.setVisibility(View.GONE)
                    eq7.setVisibility(View.GONE)
                    eq8.setVisibility(View.GONE)
                    eq9.setVisibility(View.GONE)
                    eq10.setVisibility(View.GONE)
                    eq10.setText("")
                    eq9.setText("")
                    eq8.setText("")
                    eq7.setText("")
                    eq6.setText("")
                    eq5.setText("")
                    eq4.setText("")
                }
                2-> {
                    eq1.setVisibility(View.VISIBLE)
                    eq2.setVisibility(View.VISIBLE)
                    eq3.setVisibility(View.GONE)
                    eq4.setVisibility(View.GONE)
                    eq5.setVisibility(View.GONE)
                    eq6.setVisibility(View.GONE)
                    eq7.setVisibility(View.GONE)
                    eq8.setVisibility(View.GONE)
                    eq9.setVisibility(View.GONE)
                    eq10.setVisibility(View.GONE)
                    eq10.setText("")
                    eq9.setText("")
                    eq8.setText("")
                    eq7.setText("")
                    eq6.setText("")
                    eq5.setText("")
                    eq4.setText("")
                    eq3.setText("")
                }
                1-> {
                    eq1.setVisibility(View.VISIBLE)
                    eq2.setVisibility(View.GONE)
                    eq3.setVisibility(View.GONE)
                    eq4.setVisibility(View.GONE)
                    eq5.setVisibility(View.GONE)
                    eq6.setVisibility(View.GONE)
                    eq7.setVisibility(View.GONE)
                    eq8.setVisibility(View.GONE)
                    eq9.setVisibility(View.GONE)
                    eq10.setVisibility(View.GONE)
                    eq10.setText("")
                    eq9.setText("")
                    eq8.setText("")
                    eq7.setText("")
                    eq6.setText("")
                    eq5.setText("")
                    eq4.setText("")
                    eq3.setText("")
                    eq2.setText("")
                }
            }
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
            .align(JLatexMathDrawable.ALIGN_CENTER).textSize(2050f)
            .build()

        mathView.setLatexDrawable(drawable)
    }

}