package com.bohra.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import kotlin.Result

class MainActivity : AppCompatActivity() {

    lateinit var result1:String
    lateinit var result2:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Number

        one.setOnClickListener { calculate("1", true) }
        two.setOnClickListener { calculate("2", true) }
        three.setOnClickListener { calculate("3", true) }
        four.setOnClickListener { calculate("4", true) }
        five.setOnClickListener { calculate("5", true) }
        six.setOnClickListener { calculate("6", true) }
        seven.setOnClickListener { calculate("7", true) }
        eight.setOnClickListener { calculate("8", true) }
        nine.setOnClickListener { calculate("9", true) }
        zero.setOnClickListener { calculate("0", true) }
        decimalSymbol.setOnClickListener { calculate("0", true) }

//        Operator
        addSymbol.setOnClickListener { calculate("+", false) }
        subSymbol.setOnClickListener { calculate("-", false) }
        multiplySymbol.setOnClickListener { calculate("*", false) }
        divideSymbol.setOnClickListener { calculate("/", false) }
        startBracket.setOnClickListener { calculate("(", false) }
        endBracket.setOnClickListener { calculate(")", false) }

        equal.setOnClickListener {

            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()

                if (result == longResult.toDouble()){
                    Result.text = longResult.toString()
                }else{
                    Result.text = result.toString()
                }
            } catch (e : Exception){
                Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()
            }


        }

//        on Click of clear we have to manually change our restult and expression

        clear.setOnClickListener {
            Result.text = ""
            tvExpression.text = ""
        }

//        On clicking of backspace we have to remove last character from the text view
        backspace.setOnClickListener {
            val number = tvExpression.text.toString()

            if (number.isNotEmpty()) {
                tvExpression.text = number.substring(0, number.length - 1)
            }

            Result.text = ""
        }

    }

    //    this is for append each digit on clicking
    fun calculate(string: String, canClear: Boolean) {

        if (Result.text.isNotEmpty()){
            tvExpression.text = ""
        }

//        if  boolean is true then we will add entered number in tvExpression and make result as empty
        if (canClear) {
            Result.text = ""
            tvExpression.append(string)


        } else {
            tvExpression.append(Result.text)
            tvExpression.append(string)
            Result.text = ""
        }
    }
}
