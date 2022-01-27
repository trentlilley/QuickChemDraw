package edu.foothill.tlilley.quickchemdraw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_existing_draw.*
import kotlinx.android.synthetic.main.fragment_existing_draw.view.*

/**
 * ExistingDrawFragment.kt
 * Final Project / CS 64A
 * Foothill College / R.Scovil
 * Trent Lilley / trent.vanlilley@gmail.com
 */

// handles fragment_existing_draw layout where users can view and edit their previous drawings
class ExistingDrawFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var documentViewModel: DocumentViewModel
    private val args by navArgs<ExistingDrawFragmentArgs>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_existing_draw, container, false)

        view.etDocTitleUpdate.setText(args.currentDocument.title)
        documentViewModel = ViewModelProvider(this).get(DocumentViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        // update document when button is pressed
        fabDoneUpdate.setOnClickListener {
            updateDocument()
        }
    }

    // update any changes made to the document
    private fun updateDocument() {
        val title = etDocTitleUpdate.text.toString()

        if (title.isNotEmpty()) {
            val updatedDocument = Document(args.currentDocument.id, title)
            documentViewModel.updateDocument(updatedDocument)
            navController.navigate(R.id.action_existingDrawFragment_to_listFragment)
            Toast.makeText(requireContext(), "Updated successfully", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(requireContext(), "Title field is blank", Toast.LENGTH_LONG).show()
        }
    }
}