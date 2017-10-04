package me.mziulu.clockin.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.mziulu.clockin.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setupPager(pager)
        tabs.setupWithViewPager(pager)
    }

    private fun setupPager(pager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MainFragment(), "Main")
        adapter.addFragment(LogFragment(), "Log")
        pager.adapter = adapter
    }
}

private class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments = mutableListOf<Pair<Fragment, String>>()

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(Pair(fragment, title))
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position].first
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragments[position].second
    }

    override fun getCount(): Int {
        return fragments.size
    }
}
