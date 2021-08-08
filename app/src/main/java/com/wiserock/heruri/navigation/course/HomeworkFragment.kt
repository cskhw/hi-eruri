package com.wiserock.heruri.navigation.course

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.DefaultItemAnimator
import com.wiserock.heruri.MainActivity
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.FragmentHomeworkBinding
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.view.adapter.HomeworkAdapter
import kotlinx.android.synthetic.main.fragment_homework.view.*

class HomeworkFragment : Fragment() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHomeworkBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_homework, container, false)
        val view = binding.root
        val homeworkViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(LectureViewModel::class.java)
        val progressBar = view.fragment_homework_progressBar
        progressBar.visibility = View.VISIBLE
        val recyclerView = view.fragment_homework_recycler
        recyclerView.itemAnimator = DefaultItemAnimator()
        homeworkViewModel.homeworkList.value = MyApp.homeworkArrayList
        homeworkViewModel.homeworkList.observe(viewLifecycleOwner, Observer { it: Any ->
            progressBar.visibility = View.GONE
            MainActivity.dialog.visibility = View.GONE
            recyclerView.adapter?.notifyDataSetChanged()
            recyclerView.adapter = HomeworkAdapter(homeworkViewModel)
        })
        return view
    }
}