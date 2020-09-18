package com.wiserock.heruri.navigation.course

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.wiserock.heruri.MainActivity
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.FragmentLectureBinding
import com.wiserock.heruri.view.adapter.CourseViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_lecture.*
import kotlinx.android.synthetic.main.fragment_lecture.view.*

class LectureFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val binding: FragmentLectureBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_lecture, container, false)
        val view = binding.root
        MainActivity.dialog.visibility = View.VISIBLE
        val viewPager = view.fragment_lecture_viewPager
        viewPager.offscreenPageLimit = 2
        val fragmentManager = childFragmentManager
        val adapter = CourseViewPagerAdapter(fragmentManager, 2)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(onPageChangeListener(viewPager))
        view.fragment_lecture_text1.setOnClickListener(setOnClickTextListener1(viewPager))
        view.fragment_lecture_text2.setOnClickListener(setOnClickTextListener2(viewPager))
        return binding.root
    }

    private fun onPageChangeListener(viewPager: ViewPager): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        fragment_lecture_text1.setTextColor(Color.parseColor("#000000"))
                        fragment_lecture_text2.setTextColor(Color.parseColor("#ececec"))
                        val dp = resources.displayMetrics.density
                        fragment_lecture_linearLayout.animate().translationX(0f * dp).start()
                        viewPager.currentItem = 0
                    }
                    1 -> {
                        fragment_lecture_text1.setTextColor(Color.parseColor("#ececec"))
                        fragment_lecture_text2.setTextColor(Color.parseColor("#000000"))
                        val dp = resources.displayMetrics.density
                        fragment_lecture_linearLayout.animate().translationX(70f * dp).start()
                        viewPager.currentItem = 1
                    }
                    else -> {
                        fragment_lecture_text1.setTextColor(Color.parseColor("#000000"))
                        fragment_lecture_text2.setTextColor(Color.parseColor("#ececec"))
                        val dp = resources.displayMetrics.density
                        fragment_lecture_linearLayout.animate().translationX(0f * dp).start()
                        viewPager.currentItem = 0
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        }
    }

    private fun setOnClickTextListener1(viewPager: ViewPager): View.OnClickListener? {
        return View.OnClickListener {
            fragment_lecture_text1.setTextColor(Color.parseColor("#000000"))
            fragment_lecture_text2.setTextColor(Color.parseColor("#ececec"))
            val dp = resources.displayMetrics.density
            fragment_lecture_linearLayout.animate().translationX(0f * dp).start()
            viewPager.currentItem = 0
        }
    }

    private fun setOnClickTextListener2(viewPager: ViewPager): View.OnClickListener? {
        return View.OnClickListener {
            fragment_lecture_text1.setTextColor(Color.parseColor("#ececec"))
            fragment_lecture_text2.setTextColor(Color.parseColor("#000000"))
            val dp = resources.displayMetrics.density
            fragment_lecture_linearLayout.animate().translationX(70f * dp).start()
            viewPager.currentItem = 1
        }
    }
}