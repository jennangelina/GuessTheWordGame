package com.example.android9_latihan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlayScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayScreen : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get player name
        var playerName: String = "error_name"
        if(arguments != null) {
            playerName = arguments?.getString("playerName").toString()
        }
        //testing
        var _textView1 = view.findViewById<TextView>(R.id.textView1)
        _textView1.text = playerName

        // game
        val words = arrayOf("pisang", "apel", "semangka", "jeruk", "durian")
        var tempIdx = mutableListOf<Int>(0,1,2,3,4)
        var playerScore = 15
        val _tvClue = view.findViewById<TextView>(R.id.tvClue)
        val _inputGuess = view.findViewById<EditText>(R.id.inputGuess)
        val _currentScore = view.findViewById<TextView>(R.id.currentScore)
        // start random
        var randomNum = (0..tempIdx.size-1).random()
        print("Hasil random: $randomNum")
        for (i in tempIdx) {
            print("$i ")
        }
        _tvClue.text = "Clue: " + words[tempIdx[randomNum]].length + " huruf"
        _currentScore.text = "Score: " + playerScore


        // back button
        val _backButton = view.findViewById<Button>(R.id.backButton)
        _backButton.setOnClickListener {
            val mfHome = HomeScreen()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mfHome, HomeScreen::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        // surrender button
        val _surrenderButton = view.findViewById<Button>(R.id.surrenderButton)
        _surrenderButton.setOnClickListener {
            val mfResult = ResultScreen()
            val mBundle = Bundle()
            mBundle.putInt("playerScore", playerScore)
            mBundle.putString("playerName", playerName)
            mfResult.arguments = mBundle

            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mfResult, ResultScreen::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        // submit button
        val _submitButton = view.findViewById<Button>(R.id.submitButton)
        _submitButton.setOnClickListener {
            if(_inputGuess.text != null) {
                if(_inputGuess.text.toString().lowercase() == words[tempIdx[randomNum]]) {
                    playerScore += 10
                } else {
                    playerScore -= 5
                }

                if(tempIdx.size == 1 || playerScore < 0) {
                    tempIdx.removeAt(randomNum)
                    for (i in tempIdx) {
                        print(i)
                    }

                    if(playerScore < 0){
                        playerScore = 0
                    }

                    val mfResult = ResultScreen()
                    val mBundle = Bundle()
                    mBundle.putInt("playerScore", playerScore)
                    mBundle.putString("playerName", playerName)
                    mfResult.arguments = mBundle

                    val mFragmentManager = parentFragmentManager
                    mFragmentManager.beginTransaction().apply {
                        replace(R.id.frameContainer, mfResult, ResultScreen::class.java.simpleName)
                        addToBackStack(null)
                        commit()
                    }
                } else {
                    tempIdx.removeAt(randomNum)
                    randomNum = (0..tempIdx.size-1).random()
                    print("Hasil random: $randomNum")
                    _tvClue.text = "Clue: " + words[tempIdx[randomNum]].length + " huruf"
                    _currentScore.text = "Score: " + playerScore
                    _inputGuess.setText("")
                }
            }

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlayScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlayScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}