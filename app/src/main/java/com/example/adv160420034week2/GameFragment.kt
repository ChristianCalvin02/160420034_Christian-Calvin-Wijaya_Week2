package com.example.adv160420034week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.get
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputLayout
import kotlin.random.Random

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName

            var playerTurn = view.findViewById<TextView>(R.id.txtTurn)
            playerTurn.text = "$playerName's turn"

            var number1 = Random.nextInt(0, 100) //perintah untuk melakukan random dari 0 hingga 100
            var number2 = Random.nextInt(0, 100)
            var totalScore = 0

            var txtNumber1 = view.findViewById<TextView>(R.id.txtNum1)
            var txtNumber2 = view.findViewById<TextView>(R.id.txtNum2)
            txtNumber1.text = "$number1"
            txtNumber2.text = "$number2"

            var total = number1 + number2
            val inputTotal = view.findViewById<EditText>(R.id.hasil)

            val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
            btnSubmit.setOnClickListener {
                if (Integer.parseInt(inputTotal.text.toString()) == total) {
                    inputTotal.text.clear()
                    number1 = Random.nextInt(0, 100)
                    number2 = Random.nextInt(0, 100)
                    txtNumber1.text = "$number1"
                    txtNumber2.text = "$number2"
                    total = number1 + number2
                    totalScore++
                } else{
                    val totalScores = totalScore.toString()
                    val action2 = GameFragmentDirections.actionResultFragment(totalScores)
                    Navigation.findNavController(it).navigate(action2)
                }
            }
        }
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener{
            val action = GameFragmentDirections.actionMainFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}