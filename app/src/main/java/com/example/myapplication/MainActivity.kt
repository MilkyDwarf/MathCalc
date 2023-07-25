package com.example.myapplication

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.chaquo.python.Python
import ru.noties.jlatexmath.JLatexMathDrawable
import ru.noties.jlatexmath.JLatexMathView



class MainActivity : ComponentActivity() {


    lateinit var form1: String
    lateinit var form2: String
    lateinit var form3: String
    lateinit var form4: String
    lateinit var form5: String

    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var button5: Button

    lateinit var vis1: JLatexMathView
    lateinit var vis2: JLatexMathView
    lateinit var vis3: JLatexMathView
    lateinit var vis4: JLatexMathView
    lateinit var vis5: JLatexMathView


    lateinit var gh: ImageView
    lateinit var vk: ImageView
    lateinit var tg: ImageView



    lateinit var nameTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val vis: JLatexMathView = findViewById(R.id.j_latex_math_view)

        //findViewById...................<
        button1 = findViewById(R.id.integralButton)
        nameTextView = findViewById(R.id.nameTextView)
        button2 = findViewById(R.id.diffButton)
        button3 = findViewById(R.id.doubleIntegralButton)
        button4 = findViewById(R.id.determinatorButton)
        button5 = findViewById(R.id.slauButton)
        vis1 = findViewById(R.id.integralsForm)
        vis2 = findViewById(R.id.diffForm)
        vis3 = findViewById(R.id.doubleIntegralsForm)
        vis4 = findViewById(R.id.determinatorForm)
        vis5 = findViewById(R.id.slauForm)
        gh = findViewById(R.id.gitHub)
        vk = findViewById(R.id.VK)
        tg = findViewById(R.id.TG)


        //.................................>



        gh.setOnClickListener {
            goToUrl("https://github.com/MilkyDwarf")
        }
        vk.setOnClickListener {
            goToUrl("https://vk.com/kvassdwarf")
        }
        tg.setOnClickListener {
            goToUrl("https://t.me/kvassdwarf")
        }




        //try to chahge Button color
        buttonColorCngh(button1)
        buttonColorCngh(button2)
        buttonColorCngh(button3)
        buttonColorCngh(button4)
        buttonColorCngh(button5)
        //.......................

        //Button changing activities.........

        button1.setOnClickListener {
            val myIntent = Intent(this@MainActivity, IntegralsCalculator::class.java)
            startActivity(myIntent)
        }
        button2.setOnClickListener {
            val myIntent = Intent(this@MainActivity, DerivativeCalculator::class.java)
            startActivity(myIntent)
        }
        button4.setOnClickListener {
            val myIntent = Intent(this@MainActivity, DeterminatorCalculator::class.java)
            startActivity(myIntent)
        }
        button5.setOnClickListener {
            val myIntent = Intent(this@MainActivity, SystemsCalculator::class.java)
            startActivity(myIntent)
        }
        button3.setOnClickListener {
            val myIntent = Intent(this@MainActivity, DoubleIntegralsCalculator::class.java)
            startActivity(myIntent)
        }

        //..........................


        //Python things.............................




        //Change font
        val type = Typeface.createFromAsset(assets, "fonts/Bahnschrift.ttf")
        nameTextView.typeface = type

        //..................




        //test Integrals..............



        //.............



        //formulas initialize
        form1 = "\\int_a^b{f(x)dx}"
        form2 = "\\frac{df}{dx} , \\frac{df}{dy}"
        form3 = "\\iint_S {f(x;y) dxdy}"
        form4 = "\\begin{vmatrix}\n" +
                "     a_{11} & \\hspace{1cm} \\ldots & \\hspace{1cm} a_{1 \\times n}\\\\ \n" +
                "\\\\\n" +
                "     \\ldots & \\hspace{1cm}\\ldots &\\hspace{1cm} \\ldots\\\\\n" +
                "  \\\\   a_{m1} &\\hspace{1cm} \\ldots &\\hspace{1cm} a_{m \\times n} \n" +
                "\\end{vmatrix}\n"
        form5 = "\\begin{cases} a_{11}x_1 + a_{12}x_2 +  \\ldots + a_{1n}x_n = b_1\\\\a_{21}x_1 + a_{22}x_2 + " +
                " \\ldots + a_{2n}x_n = b_2 \\\\ a_{m1}x_1 + a_{m2}x_2+\\ldots + a_{mn}x_n = b_m  \\end{cases}"
        //................................

        //..........
        setLatex(vis1, form1)
        setLatex(vis2, form2)
        setLatex(vis3, form3)    //mathView fill........
        setLatex(vis4, form4)
        setLatex(vis5, form5)
        //.................................


        //Easter Egg........................
        nameTextView.setOnClickListener {
            nameTextView.setText("Тут ничего нет")
        }
        //.......................................

        //vis.setLatexDrawable(drawable)
    }
    fun formVisText(view: TextView) {
        view.setText("А. Р. Т. Ё. М")
    }
    fun buttonColorCngh(but: Button) {
        but.setBackgroundColor(getColor(R.color.button))
    }
    fun setLatex(mathView: JLatexMathView, formula: String) {
        val drawable: JLatexMathDrawable = JLatexMathDrawable.builder(formula)
            .textSize(100f)
            .background(getColor(R.color.button))
            .align(JLatexMathDrawable.ALIGN_CENTER)
            .align(JLatexMathDrawable.ALIGN_CENTER)
            .build()
        formVisText(nameTextView)
        mathView.setLatexDrawable(drawable)
    }
    fun goToUrl(s: String) {
        val uri: Uri = Uri.parse(s)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}
