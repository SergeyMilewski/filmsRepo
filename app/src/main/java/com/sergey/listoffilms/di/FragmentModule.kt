package com.sergey.listoffilms.di

import com.sergey.listoffilms.fragments.details.FilmDetailsFragment
import com.sergey.listoffilms.fragments.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailsFragment(): FilmDetailsFragment

}