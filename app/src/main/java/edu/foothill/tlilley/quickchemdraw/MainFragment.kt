package edu.foothill.tlilley.quickchemdraw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * MainFragment.kt
 * Final Project / CS 64A
 * Foothill College / R.Scovil
 * Trent Lilley / trent.vanlilley@gmail.com
 */

class MainFragment : Fragment() {
    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        // go to list when Go To List button on main page is pressed
        btnGoToList.setOnClickListener{
            navController!!.navigate(R.id.action_mainFragment_to_listFragment)
        }
        // go to a new canvas when Start Draw button on main page is pressed
        btnStartDraw.setOnClickListener {
            navController!!.navigate(R.id.action_mainFragment_to_drawFragment)
        }

    }
}