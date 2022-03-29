package com.example.todosobreyo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.example.todosobreyo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Edman Quinteros")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName

        /*findViewById<Button>(R.id.done_button).setOnClickListener {
            addNickname(it)
        }*/

        binding.doneButton.setOnClickListener {
            addNickname(it)
        }

        binding.nicknameText.setOnClickListener {
            updateNickname(it)
        }
    }

    private fun addNickname(view: View) {
        //val editText = findViewById<TextView>(R.id.nickname_text)

        /*nicknameTextView.text = editText.text
        editText.visibility = View.GONE
        view.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE*/

        //Kotlinize la función usando apply{} y no usar en cada una binding.nicknameText.tex....
        binding.apply {
            //El nicknameText requiere un String, y nicknameEdit.text es un Editable
            //nicknameText.text = binding.nickNameEdit.text.toString()
            myName?.nickname = nickNameEdit.text.toString()
            invalidateAll()  //para q interfaz de usuario se actualice con el valor en el objeto de enlace actualizado.
            nickNameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }


        // Hide the keyboard.(Ocultar teclado)

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateNickname(view: View){
        /*val editText = findViewById<EditText>(R.id.nickName_edit)
        val doneButton = findViewById<Button>(R.id.done_button)*/

        binding.nicknameText.visibility = View.GONE
        binding.doneButton.visibility = View.VISIBLE
        binding.nickNameEdit.visibility = View.VISIBLE

        // Set the focus to the edit text.
        binding.nicknameText.requestFocus()

        // Show the keyboard.(mostrar teclado)
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.nickNameEdit, 0)
    }
}