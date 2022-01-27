package edu.foothill.tlilley.quickchemdraw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

/**
 * ListFragment.kt
 * Final Project / CS 64A
 * Foothill College / R.Scovil
 * Trent Lilley / trent.vanlilley@gmail.com
 */

// manages fragment_list layout which displays the user's documents
class ListFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var documentViewModel: DocumentViewModel

    // inflates the fragment_list layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // view model
        documentViewModel = ViewModelProvider(this).get(DocumentViewModel::class.java)
        documentViewModel.readAllData.observe(viewLifecycleOwner, Observer { doc ->
            adapter.setData(doc)
        })

        return view
    }

    // initializes the navigation controller
    // adds functionality to the add button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        fabAdd.setOnClickListener {
            navController!!.navigate(R.id.action_listFragment_to_drawFragment)
        }
    }
}