package com.example.to_do

import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.adapter.ItemViewAdapter
import com.example.to_do.databinding.DialogBoxBinding
import com.example.to_do.databinding.FragmentListTaskBinding
import com.example.to_do.model.task


class ListTask : Fragment() {

    private var binding: FragmentListTaskBinding? = null
    private var recyclerView: RecyclerView? = null
    private lateinit var binding1: DialogBoxBinding
    private val sharedViewModel: taskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentListTaskBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding?.listItem
        val adapter = ItemViewAdapter(
            this@ListTask.requireContext(),
            sharedViewModel.listTask

        )
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager =
            LinearLayoutManager(
                this@ListTask.requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        binding?.add?.setOnClickListener {
            showCreateTaskDialog(LayoutInflater.from(activity), adapter)

        }


    }

    fun showCreateTaskDialog(inflater: LayoutInflater, adapter: ItemViewAdapter) {
        val builder = AlertDialog.Builder(this@ListTask.requireContext())
        binding1 = DialogBoxBinding.inflate(layoutInflater)
        val dialogLayout = binding1.root



        with(builder) {
            setTitle("Enter The Task")
            setPositiveButton("add") { dialog, which ->
                lateinit var task: task
                if (binding1?.Input?.text.toString() != "") {
                    val option_checked =
                        binding1?.SetOptions?.checkedRadioButtonId //option_checked contains the ID of the radioButton pressed
                    if (option_checked == R.id.op1) {
                        task = task(
                            binding1.Input.text.toString(),
                            binding1?.op1?.text.toString()
                        )
                        adapter.addTask(task)
                    } else if (option_checked == R.id.op2) {
                        task = task(
                            binding1.Input.text.toString(),
                            binding1?.op2?.text.toString()
                        )
                        adapter.addTask(task)
                    } else  {
                        task = task(binding1.Input.text.toString(), binding1?.op3?.text.toString())
                        adapter.addTask(task)
                    }
                } else {
                    Toast.makeText(activity, "No Task Entered!", Toast.LENGTH_SHORT).show()

                }
            }
            setNegativeButton("Cancel") { dialog, which ->
                Log.d(
                    "Main",
                    "Negative Button Clicked"
                )
            }
            setView(dialogLayout)
            show()


        }
    }


}

