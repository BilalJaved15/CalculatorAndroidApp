package com.badjack.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isOperatorPresent = false
    var isOperatorNegative = false
    var op1 = 0F
    var op2 = 0F
    var selectedOp = ""
    var isEvaluated = false
    var result = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun numberBtnHandler(view: View) {
        if (!isEvaluated) {
            if (txt_expr.text.endsWith("+ ") || txt_expr.text.endsWith("- ") || txt_expr.text.endsWith(
                    "* "
                ) || txt_expr.text.endsWith("/ ")
            ) {
                field_expr.text = "0"
            }
            var selectedButton = view as Button
            var inp: String = when (selectedButton.id) {
                R.id.button_0 -> "0"
                R.id.button_1 -> "1"
                R.id.button_2 -> "2"
                R.id.button_3 -> "3"
                R.id.button_4 -> "4"
                R.id.button_5 -> "5"
                R.id.button_6 -> "6"
                R.id.button_7 -> "7"
                R.id.button_8 -> "8"
                R.id.button_9 -> "9"
                else -> "."
            }
            if (field_expr.text == "0" && inp == ".") {
                field_expr.text = "${field_expr.text}$inp"
                txt_expr.text = "${txt_expr.text}$inp"
            } else if (field_expr.text == "0" && inp != ".") {
                field_expr.text = inp
                if (!isOperatorPresent) {
                    txt_expr.text = inp
                } else {
                    txt_expr.text = "${txt_expr.text}$inp"
                }
            } else if (field_expr.text != "0" && (("." !in field_expr.text && inp == ".") || inp != ".")) {
                field_expr.text = "${field_expr.text}$inp"
                txt_expr.text = "${txt_expr.text}$inp"
            }
        }
    }

    fun deleteBtnHandler(view: View) {
        if (field_expr.text.length == 1 && field_expr.text != "0") {
            field_expr.text = "0"
            txt_expr.text = "${txt_expr.text.substring(0, txt_expr.text.length - 1)}"
        } else {
            field_expr.text = field_expr.text.substring(0, field_expr.text.length - 1)
            txt_expr.text = "${txt_expr.text.substring(0, txt_expr.text.length - 1)}"
            if (field_expr.text == "") {
                field_expr.text = "0"
            }
            if (txt_expr.text == "") {
                txt_expr.text = "0"
            }
        }
        if (field_expr.text == "0" && isOperatorNegative) {
            negateBtnHandler(button_neg)
        }
        if (field_expr.text == "-") {
            field_expr.text = "0"
        }
    }

    private fun getOperatorFromField(): Float {
        val strOp1 = when (field_expr.text[field_expr.text.length - 1]) {
            '.' -> "${field_expr.text}0"
            else ->
                "${field_expr.text}"
        }
        return (strOp1).toFloat()
    }

    fun operatorBtnHandler(view: View) {
        var selectedButton = view as Button
        when (selectedButton.id) {
            R.id.button_add -> {
                selectedOp = "+"
                if (!isOperatorPresent) {
                    isOperatorPresent = true
                    op1 = getOperatorFromField()
                    field_expr.text = "0"
                    if (!isEvaluated) {
                        txt_expr.text = "${txt_expr.text} + "
                    } else {
                        var strResult = (result).toString()
                        if (strResult.endsWith(".0")) {
                            strResult = strResult.replace(".0", "")
                        }
                        txt_expr.text = "$strResult $selectedOp "
                    }
                    isEvaluated = false
                    isOperatorNegative = false
                } else {
                    op2 = getOperatorFromField()
                    field_expr.text = (op1 + op2).toString()
                    result = op1 + op2
                    isEvaluated = true
                    isOperatorPresent = true
                    op1 = result
                }
            }
            R.id.button_subtract -> {
                selectedOp = "-"
                if (!isOperatorPresent) {
                    isOperatorPresent = true
                    op1 = getOperatorFromField()
                    field_expr.text = "0"
                    if (!isEvaluated) {
                        txt_expr.text = "${txt_expr.text} - "
                    } else {
                        var strResult = (result).toString()
                        if (strResult.endsWith(".0")) {
                            strResult = strResult.replace(".0", "")
                        }
                        txt_expr.text = "$strResult $selectedOp "
                    }
                    isEvaluated = false
                    isOperatorNegative = false
                } else {
                    op2 = getOperatorFromField()
                    field_expr.text = (op1 - op2).toString()
                    result = op1 - op2
                    isEvaluated = true
                    isOperatorPresent = true
                    op1 = result
                }
            }
            R.id.button_multiply -> {
                selectedOp = "*"
                if (!isOperatorPresent) {
                    isOperatorPresent = true
                    op1 = getOperatorFromField()
                    field_expr.text = "0"
                    if (!isEvaluated) {
                        txt_expr.text = "${txt_expr.text} * "
                    } else {
                        var strResult = (result).toString()
                        if (strResult.endsWith(".0")) {
                            strResult = strResult.replace(".0", "")
                        }
                        txt_expr.text = "$strResult $selectedOp "
                    }
                    isEvaluated = false
                    isOperatorNegative = false
                } else {
                    op2 = getOperatorFromField()
                    field_expr.text = (op1 * op2).toString()
                    result = op1 * op2
                    isEvaluated = true
                    isOperatorPresent = true
                    op1 = result
                }
            }
            R.id.button_divide -> {
                selectedOp = "/"
                if (!isOperatorPresent) {
                    isOperatorPresent = true
                    op1 = getOperatorFromField()
                    field_expr.text = "0"
                    if (!isEvaluated) {
                        txt_expr.text = "${txt_expr.text} / "
                    } else {
                        var strResult = (result).toString()
                        if (strResult.endsWith(".0")) {
                            strResult = strResult.replace(".0", "")
                        }
                        txt_expr.text = "$strResult $selectedOp "
                    }
                    isEvaluated = false
                    isOperatorNegative = false
                } else {
                    op2 = getOperatorFromField()
                    if (op2 != 0F) {
                        field_expr.text = (op1 / op2).toString()
                        result = op1 / op2
                    } else {
                        field_expr.text = "DivByZero Error"
                    }
                    isEvaluated = true
                    isOperatorPresent = true
                    op1 = result
                }
            }
        }
        if (field_expr.text.toString().endsWith(".0")) {
            field_expr.text = field_expr.text.toString().replace(".0", "")
        }
        if (isEvaluated) {
            isEvaluated = false
            var strResult = (result).toString()
            if (strResult.endsWith(".0")) {
                strResult = strResult.replace(".0", "")
            }
            txt_expr.text = "$strResult $selectedOp "

        }
    }

    fun negateBtnHandler(view: View) {
        if (field_expr.text != "0" && field_expr.text != "0.") {
            if (!isOperatorNegative) {
                Log.d("Negate", "Making op negative: ${field_expr.text} ---> -${field_expr.text}")
                if (!isOperatorPresent || (isOperatorPresent && selectedOp != "-")) {
                    field_expr.text = "-${field_expr.text}"
                }
                if (!isOperatorPresent) {
                    txt_expr.text = "-${txt_expr.text}"
                } else {
                    if (selectedOp == "+") {
                        txt_expr.text = (txt_expr.text).toString().replace("+", "-")
                        selectedOp = "-"
                    } else if (selectedOp != "-") {
                        txt_expr.text = "${
                            txt_expr.text.substring(
                                0,
                                txt_expr.text.toString().lastIndexOf(" ") + 1
                            )
                        }-${
                            txt_expr.text.substring(
                                txt_expr.text.toString().lastIndexOf(" ") + 1,
                                txt_expr.text.toString().lastIndex + 1
                            )
                        }"
                    }
                }
                isOperatorNegative = true
                if ((isOperatorPresent && selectedOp == "-")) {
                    isOperatorNegative = false
                }
            } else {
                Log.d(
                    "Negate",
                    "Making op non-negative: ${field_expr.text} ---> ${
                        field_expr.text.substring(
                            1,
                            field_expr.text.length
                        )
                    }"
                )
                field_expr.text = field_expr.text.substring(1, field_expr.text.length)
                if (!isOperatorPresent) {
                    txt_expr.text = txt_expr.text.substring(1, txt_expr.text.length)
                } else {
                    if (selectedOp == "-") {
                        txt_expr.text = (txt_expr.text).toString().replace("-", "+")
                        selectedOp = "+"
                    } else if (selectedOp == "+") {
                        txt_expr.text = (txt_expr.text).toString().replace("+", "-")
                        selectedOp = "-"
                    } else {
                        txt_expr.text = "${
                            txt_expr.text.substring(
                                0,
                                txt_expr.text.toString().lastIndexOf(" ") + 1
                            )
                        }${
                            txt_expr.text.substring(
                                txt_expr.text.toString().lastIndexOf(" ") + 2,
                                txt_expr.text.toString().lastIndex + 1
                            )
                        }"
                    }
                }
                isOperatorNegative = false
            }
        }
    }

    fun clearBtnHandler(view: View) {
        isOperatorPresent = false
        isOperatorNegative = false
        isEvaluated = false
        selectedOp = ""
        result = 0F
        op1 = 0F
        op2 = 0F
        txt_expr.text = "0"
        field_expr.text = "0"
    }

    fun equalBtnHandler(view: View) {
        if (isOperatorPresent) {
            when (selectedOp) {
                "+" -> {
                    op2 = getOperatorFromField()
                    field_expr.text = (op1 + op2).toString()
                    result = op1 + op2
                    isOperatorPresent = false
                }
                "-" -> {
                    op2 = getOperatorFromField()
                    field_expr.text = (op1 - op2).toString()
                    result = op1 - op2
                    isOperatorPresent = false
                }
                "*" -> {
                    op2 = getOperatorFromField()
                    field_expr.text = (op1 * op2).toString()
                    result = op1 * op2
                    isOperatorPresent = false
                }
                "/" -> {
                    op2 = getOperatorFromField()
                    if (op2 != 0F) {
                        field_expr.text = (op1 / op2).toString()
                        result = op1 / op2
                    } else {
                        field_expr.text = "DivByZero Error"
                    }
                    isOperatorPresent = false
                }
            }
            isEvaluated = true
        }
        if (field_expr.text.toString().endsWith(".0")) {
            field_expr.text = field_expr.text.toString().replace(".0", "")
        }
    }
}