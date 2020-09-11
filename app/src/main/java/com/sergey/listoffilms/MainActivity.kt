package com.sergey.listoffilms

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.sergey.listoffilms.databinding.MainActivityBinding
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        setupActionBar()
        findNavController(R.id.nav_host_fragment).apply {
            addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.navigation_details -> setupActionBar(true)
                    else -> setupActionBar()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupActionBar(shouldShowHomeButton: Boolean = false) {
        supportActionBar?.setDisplayHomeAsUpEnabled(shouldShowHomeButton)
        supportActionBar?.setHomeButtonEnabled(shouldShowHomeButton)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}