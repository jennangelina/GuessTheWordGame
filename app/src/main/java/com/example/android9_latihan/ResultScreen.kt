package com.example.android9_latihan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
 * Use the [ResultScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultScreen : Fragment() {
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
        return inflater.inflate(R.layout.fragment_result_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val _tvScore = view.findViewById<TextView>(R.id.tvScore)

        var playerName = "error_name"

        // masukkin data
        if(arguments != null) {
            val playerScore = arguments?.getInt("playerScore")
            _tvScore.text = playerScore.toString()
            playerName = arguments?.getString("playerName").toString()

            // masukkin data
            val cal: Calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("MMM d, yyyy")
            val date = sdf.format(cal.getTime())
            val hrs: Int = cal.get(Calendar.HOUR_OF_DAY)
            var min: Int = cal.get(Calendar.MINUTE)
            var _min = min.toString()
            if(min < 10) {
                _min = "0" + min.toString()
            }
            val time = (date + " " + hrs.toString() + ":" + _min)
            val data = scorehistory(playerName, playerScore as Int, time)

            databaseHistory.database.add(data)
        }

        val _playAgainButton = view.findViewById<Button>(R.id.playAgainButton)
        _playAgainButton.setOnClickListener {
            val mfPlay = PlayScreen()
            val mFragmentManager = parentFragmentManager
            val mBundle = Bundle()
            mBundle.putString("playerName", playerName)
            mfPlay.arguments = mBundle
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mfPlay, PlayScreen::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        val _exitButton = view.findViewById<Button>(R.id.exitButton)
        _exitButton.setOnClickListener {
            val mfHome = HomeScreen()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mfHome, HomeScreen::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        val _scoreHistoryButton = view.findViewById<Button>(R.id.scoreHistoryButton)
        _scoreHistoryButton.setOnClickListener {
//            val mfScoreHistoryScreen = ScoreHistoryScreen()
//            val mFragmentManager = parentFragmentManager
//            mFragmentManager.beginTransaction().apply {
//                replace(R.id.frameContainer, mfScoreHistoryScreen, ScoreHistoryScreen::class.java.simpleName)
//                addToBackStack(null)
//                commit()
//            }
            val eIntent = Intent(getActivity(), ScoreHistoryAct::class.java)
            activity?.startActivity(eIntent)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}