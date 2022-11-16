package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnPlus.setOnClickListener{setTextFields("+")}
        binding.btnMinus.setOnClickListener{setTextFields("-")}
        binding.btnMulti.setOnClickListener{setTextFields("*")}
        binding.btnDiv.setOnClickListener{setTextFields("/")}

        binding.mathNumbers.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                // if the event is a key down event on the enter button
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    setTextFields1()
                    //hide cursor from edit text
                    binding.mathNumbers.isCursorVisible = false
                    return true
                }
                return false
            }
        })


        binding.btnBack.setOnClickListener {
            val str = binding.mathOperation.text.toString()
            if(str.isNotEmpty()){
                binding.mathOperation.text = str.substring(0,str.length-1)
            }
        }


        binding.btnEquals.setOnClickListener{
            try {
                val ex = ExpressionBuilder(binding.mathOperation.text.toString()).build()
                val result = ex.evaluate()
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("result", result)
                startActivity(intent)
            }catch (e:Exception) {
                Log.d("Error","message: ${e.message}")
            }
        }








    }




    fun setTextFields(str: String){

        if(binding.mathNumbers.text.isNotEmpty()) {
            binding.mathOperation.append(binding.mathNumbers.text)
            binding.mathNumbers.setText("")
        }

        binding.mathOperation.append(str)
    }


    fun setTextFields1(){

        if( binding.mathNumbers.text.isNotEmpty()) {
            binding.mathOperation.append(binding.mathNumbers.text)
            binding.mathNumbers.setText("")
        }
    }
}