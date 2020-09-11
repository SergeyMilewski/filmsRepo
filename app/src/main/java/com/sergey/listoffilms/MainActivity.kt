package com.sergey.listoffilms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sergey.listoffilms.databinding.MainActivityBinding
import com.sergey.listoffilms.fragments.main.MainFragment
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}