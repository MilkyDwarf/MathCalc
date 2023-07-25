package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.updateLayoutParams
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import ru.noties.jlatexmath.JLatexMathDrawable
import ru.noties.jlatexmath.JLatexMathView
import java.lang.Exception
import kotlin.properties.Delegates


class DeterminatorCalculator : ComponentActivity() {

    lateinit var a1: EditText
    lateinit var a2: EditText
    lateinit var a3: EditText
    lateinit var a4: EditText
    lateinit var a5: EditText
    lateinit var a6: EditText
    lateinit var a7: EditText
    lateinit var b1: EditText
    lateinit var b2: EditText
    lateinit var b3: EditText
    lateinit var b4: EditText
    lateinit var b5: EditText
    lateinit var b6: EditText
    lateinit var b7: EditText
    lateinit var c1: EditText
    lateinit var c2: EditText
    lateinit var c3: EditText
    lateinit var c4: EditText
    lateinit var c5: EditText
    lateinit var c6: EditText
    lateinit var c7: EditText
    lateinit var d1: EditText
    lateinit var d2: EditText
    lateinit var d3: EditText
    lateinit var d4: EditText
    lateinit var d5: EditText
    lateinit var d6: EditText
    lateinit var d7: EditText
    lateinit var e1: EditText
    lateinit var e2: EditText
    lateinit var e3: EditText
    lateinit var e4: EditText
    lateinit var e5: EditText
    lateinit var e6: EditText
    lateinit var e7: EditText
    lateinit var f1: EditText
    lateinit var f2: EditText
    lateinit var f3: EditText
    lateinit var f4: EditText
    lateinit var f5: EditText
    lateinit var f6: EditText
    lateinit var f7: EditText
    lateinit var g1: EditText
    lateinit var g2: EditText
    lateinit var g3: EditText
    lateinit var g4: EditText
    lateinit var g5: EditText
    lateinit var g6: EditText
    lateinit var g7: EditText
    lateinit var seventhColumn: LinearLayout
    lateinit var sixthColumn: LinearLayout
    lateinit var fivethColumn: LinearLayout
    lateinit var fourdColumn: LinearLayout
    lateinit var thirdColumn: LinearLayout
    lateinit var plus: Button
    lateinit var minus: Button
    lateinit var sButton: Button
    lateinit var answer: JLatexMathView
    var n by Delegates.notNull<Int>()
    lateinit var leftBracket: JLatexMathView
    lateinit var rightBracket: JLatexMathView
    lateinit var py: Python
    lateinit var pyObj: PyObject




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_determinator)
        n = 7
        changeNumColumnMinus()
        //.......................................................................
        a1 = findViewById(R.id.a1)
        a2 = findViewById(R.id.a2)
        a3 = findViewById(R.id.a3)
        a4 = findViewById(R.id.a4)
        a5 = findViewById(R.id.a5)
        a6 = findViewById(R.id.a6)
        a7 = findViewById(R.id.a7)
        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)
        b3 = findViewById(R.id.b3)
        b4 = findViewById(R.id.b4)
        b5 = findViewById(R.id.b5)
        b6 = findViewById(R.id.b6)
        b7 = findViewById(R.id.b7)
        c1 = findViewById(R.id.c1)
        c2 = findViewById(R.id.c2)
        c3 = findViewById(R.id.c3)
        c4 = findViewById(R.id.c4)
        c5 = findViewById(R.id.c5)
        c6 = findViewById(R.id.c6)
        c7 = findViewById(R.id.c7)
        d1 = findViewById(R.id.d1)
        d2 = findViewById(R.id.d2)
        d3 = findViewById(R.id.d3)
        d4 = findViewById(R.id.d4)
        d5 = findViewById(R.id.d5)
        d6 = findViewById(R.id.d6)
        d7 = findViewById(R.id.d7)
        e1 = findViewById(R.id.e1)
        e2 = findViewById(R.id.e2)
        e3 = findViewById(R.id.e3)
        e4 = findViewById(R.id.e4)
        e5 = findViewById(R.id.e5)
        e6 = findViewById(R.id.e6)
        e7 = findViewById(R.id.e7)
        f1 = findViewById(R.id.f1)
        f2 = findViewById(R.id.f2)
        f3 = findViewById(R.id.f3)
        f4 = findViewById(R.id.f4)
        f5 = findViewById(R.id.f5)
        f6 = findViewById(R.id.f6)
        f7 = findViewById(R.id.f7)
        g1 = findViewById(R.id.g1)
        g2 = findViewById(R.id.g2)
        g3 = findViewById(R.id.g3)
        g4 = findViewById(R.id.g4)
        g5 = findViewById(R.id.g5)
        g6 = findViewById(R.id.g6)
        g7 = findViewById(R.id.g7)
        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)
        sButton = findViewById(R.id.solbeButton)
        answer = findViewById(R.id.determinateView)
        seventhColumn = findViewById(R.id.seventhColumn)
        sixthColumn = findViewById(R.id.sixthColumn)
        fivethColumn = findViewById(R.id.fivethColumn)
        fourdColumn = findViewById(R.id.fourdColumn)
        thirdColumn = findViewById(R.id.thirdColumn)
        leftBracket = findViewById(R.id.leftBracket)
        rightBracket = findViewById(R.id.rightBracket)
        answer = findViewById(R.id.determinateView)
        py = Python.getInstance()
        pyObj = py.getModule("script")

        //.......................................................................

        setLatexDesign(leftBracket, "\\bigg|")
        setLatexDesign(rightBracket, "\\bigg|")


        minus.setOnClickListener {
            if (n>2) {
                n = n-1
                changeNumColumnMinus()
                val Width1: Int = leftBracket.width
                val Width2: Int = rightBracket.width
                leftBracket.updateLayoutParams {
                    width = Width1 - 33
                }
                rightBracket.updateLayoutParams {
                    width = Width1 - 33
                }
            }
            else {
                Toast.makeText(this, "Вы что глупый? Определитель матрицы 1 на 1 это само число алло", Toast.LENGTH_LONG).show()
            }
        }
        plus.setOnClickListener {
            if (n<7) {
                n = n+1
                changeNumColumnPlus()
                val Width1: Int = leftBracket.width
                val Width2: Int = rightBracket.width
                leftBracket.updateLayoutParams {
                    width = Width1 + 33
                }

                rightBracket.updateLayoutParams {
                    width = Width1 + 33
                }
            }
            else {
                Toast.makeText(this, "Я легко могу сделать возможность добавить ещё, но у вас на экране места не хватит", Toast.LENGTH_LONG).show()
            }
        }

        sButton.setOnClickListener {
//            try {
                when (n) {
                    7 -> {
                        setLatex(
                            answer, pyObj.callAttr(
                                "determinantVis7",
                                a1.text.toString(),
                                a2.text.toString(),
                                a3.text.toString(),
                                a4.text.toString(),
                                a5.text.toString(),
                                a6.text.toString(),
                                a7.text.toString(),
                                b1.text.toString(),
                                b2.text.toString(),
                                b3.text.toString(),
                                b4.text.toString(),
                                b5.text.toString(),
                                b6.text.toString(),
                                b7.text.toString(),
                                c1.text.toString(),
                                c2.text.toString(),
                                c3.text.toString(),
                                c4.text.toString(),
                                c5.text.toString(),
                                c6.text.toString(),
                                c7.text.toString(),
                                d1.text.toString(),
                                d2.text.toString(),
                                d3.text.toString(),
                                d4.text.toString(),
                                d5.text.toString(),
                                d6.text.toString(),
                                d7.text.toString(),
                                e1.text.toString(),
                                e2.text.toString(),
                                e3.text.toString(),
                                e4.text.toString(),
                                e5.text.toString(),
                                e6.text.toString(),
                                e7.text.toString(),
                                f1.text.toString(),
                                f2.text.toString(),
                                f3.text.toString(),
                                f4.text.toString(),
                                f5.text.toString(),
                                f6.text.toString(),
                                f7.text.toString(),
                                g1.text.toString(),
                                g2.text.toString(),
                                g3.text.toString(),
                                g4.text.toString(),
                                g5.text.toString(),
                                g6.text.toString(),
                                g7.text.toString()
                            ).toString()
                        )

                    }

                    6 -> setLatex(
                        answer,
                        pyObj.callAttr(
                            "determinantVis6",
                            a1.text.toString(),
                            a2.text.toString(),
                            a3.text.toString(),
                            a4.text.toString(),
                            a5.text.toString(),
                            a6.text.toString(),
                            b1.text.toString(),
                            b2.text.toString(),
                            b3.text.toString(),
                            b4.text.toString(),
                            b5.text.toString(),
                            b6.text.toString(),
                            c1.text.toString(),
                            c2.text.toString(),
                            c3.text.toString(),
                            c4.text.toString(),
                            c5.text.toString(),
                            c6.text.toString(),
                            d1.text.toString(),
                            d2.text.toString(),
                            d3.text.toString(),
                            d4.text.toString(),
                            d5.text.toString(),
                            d6.text.toString(),
                            e1.text.toString(),
                            e2.text.toString(),
                            e3.text.toString(),
                            e4.text.toString(),
                            e5.text.toString(),
                            e6.text.toString(),
                            f1.text.toString(),
                            f2.text.toString(),
                            f3.text.toString(),
                            f4.text.toString(),
                            f5.text.toString(),
                            f6.text.toString()
                        ).toString()
                    )

                    5 -> setLatex(
                        answer, pyObj.callAttr(
                            "determinantVis5",
                            a1.text.toString(),
                            a2.text.toString(),
                            a3.text.toString(),
                            a4.text.toString(),
                            a5.text.toString(),
                            b1.text.toString(),
                            b2.text.toString(),
                            b3.text.toString(),
                            b4.text.toString(),
                            b5.text.toString(),
                            c1.text.toString(),
                            c2.text.toString(),
                            c3.text.toString(),
                            c4.text.toString(),
                            c5.text.toString(),
                            d1.text.toString(),
                            d2.text.toString(),
                            d3.text.toString(),
                            d4.text.toString(),
                            d5.text.toString(),
                            e1.text.toString(),
                            e2.text.toString(),
                            e3.text.toString(),
                            e4.text.toString(),
                            e5.text.toString()
                        ).toString()
                    )

                    4 -> setLatex(
                        answer, pyObj.callAttr(
                            "determinantVis4",
                            a1.text.toString(),
                            a2.text.toString(),
                            a3.text.toString(),
                            a4.text.toString(),
                            b1.text.toString(),
                            b2.text.toString(),
                            b3.text.toString(),
                            b4.text.toString(),
                            c1.text.toString(),
                            c2.text.toString(),
                            c3.text.toString(),
                            c4.text.toString(),
                            d1.text.toString(),
                            d2.text.toString(),
                            d3.text.toString(),
                            d4.text.toString()
                        ).toString()
                    )

                    3 -> setLatex(
                        answer, pyObj.callAttr(
                            "determinantVis3",
                            a1.text.toString(),
                            a2.text.toString(),
                            a3.text.toString(),
                            b1.text.toString(),
                            b2.text.toString(),
                            b3.text.toString(),
                            c1.text.toString(),
                            c2.text.toString(),
                            c3.text.toString()
                        ).toString()
                    )

                    2 -> setLatex(
                        answer,
                        pyObj.callAttr(
                            "determinantVis2",
                            a1.text.toString(),
                            a2.text.toString(),
                            b1.text.toString(),
                            b2.text.toString()
                        ).toString()
                    )
                }

//            catch (E:Exception) {
//                Toast.makeText(this, "ssss", Toast.LENGTH_LONG).show()
//            }
        }


    }

    fun changeNumColumnMinus() {
        when (n) {
            7 -> {

            }

            6 -> sevenClear()
            5 -> {
                sevenClear()
                sixClear()
            }

            4 -> {
                sevenClear()
                sixClear()
                fiveClear()
            }

            3 -> {
                sevenClear()
                sixClear()
                fiveClear()
                fourClear()
            }

            2 -> {
                sevenClear()
                sixClear()
                fiveClear()
                threeClear()

            }
        }
    }

        fun changeNumColumnPlus() {
            when (n) {
                3 -> {threeUnClear()}
                4 -> {
                    threeUnClear()
                    fourUnClear()
                }
                5 -> {
                    threeUnClear()
                    fourUnClear()
                    fiveUnClear()
                }
                6 -> {
                    threeUnClear()
                    fourUnClear()
                    fiveUnClear()
                    sixUnClear()
                }
                7 -> {
                    threeUnClear()
                    fourUnClear()
                    fiveUnClear()
                    sixUnClear()
                    sevenUnClear()
                }
            }


    }


    fun sevenUnClear() {
        seventhColumn.setVisibility(View.VISIBLE)
        g1.setVisibility(View.VISIBLE)
        g2.setVisibility(View.VISIBLE)
        g3.setVisibility(View.VISIBLE)
        g4.setVisibility(View.VISIBLE)
        g5.setVisibility(View.VISIBLE)
        g6.setVisibility(View.VISIBLE)

    }


    fun sixUnClear() {
        sixthColumn.setVisibility(View.VISIBLE)
        f1.setVisibility(View.VISIBLE)
        f2.setVisibility(View.VISIBLE)
        f3.setVisibility(View.VISIBLE)
        f4.setVisibility(View.VISIBLE)
        f5.setVisibility(View.VISIBLE)

    }
    fun fiveClear() {
        fivethColumn.setVisibility(View.GONE)
        e1.setVisibility(View.GONE)
        e2.setVisibility(View.GONE)
        e3.setVisibility(View.GONE)
        e4.setVisibility(View.GONE)
    }
    fun fourClear() {
        fourdColumn.setVisibility(View.GONE)
        d1.setVisibility(View.GONE)
        d2.setVisibility(View.GONE)
        d3.setVisibility(View.GONE)
    }
    fun threeClear() {
        thirdColumn.setVisibility(View.GONE)
        c1.setVisibility(View.GONE)
        c2.setVisibility(View.GONE)
    }

    fun sevenClear() {
        seventhColumn.setVisibility(View.GONE)
        g1.setVisibility(View.GONE)
        g2.setVisibility(View.GONE)
        g3.setVisibility(View.GONE)
        g4.setVisibility(View.GONE)
        g5.setVisibility(View.GONE)
        g6.setVisibility(View.GONE)

    }


    fun sixClear() {
        sixthColumn.setVisibility(View.GONE)
        f1.setVisibility(View.GONE)
        f2.setVisibility(View.GONE)
        f3.setVisibility(View.GONE)
        f4.setVisibility(View.GONE)
        f5.setVisibility(View.GONE)

    }
    fun fiveUnClear() {
        fivethColumn.setVisibility(View.VISIBLE)
                e1.setVisibility(View.VISIBLE)
                e2.setVisibility(View.VISIBLE)
                e3.setVisibility(View.VISIBLE)
                e4.setVisibility(View.VISIBLE)
    }
    fun fourUnClear() {
        fourdColumn.setVisibility(View.VISIBLE)
                d1.setVisibility(View.VISIBLE)
                d2.setVisibility(View.VISIBLE)
                d3.setVisibility(View.VISIBLE)
    }
    fun threeUnClear() {
        thirdColumn.setVisibility(View.VISIBLE)
        c1.setVisibility(View.VISIBLE)
        c2.setVisibility(View.VISIBLE)
    }
    fun setLatexDesign(mathView: JLatexMathView, formula: String) {
        val drawable: JLatexMathDrawable = JLatexMathDrawable.builder(formula)
            .textSize(1000f)
            .background(getColor(R.color.white))
            .align(JLatexMathDrawable.ALIGN_CENTER)
            .align(JLatexMathDrawable.ALIGN_CENTER)
            .build()

        mathView.setLatexDrawable(drawable)
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
}