package com.alix01z.cryptoyar.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.alix01z.cryptoyar.MainActivity
import com.alix01z.cryptoyar.R
import com.alix01z.cryptoyar.adapters.ViewPagerHomeAdapter
import com.alix01z.cryptoyar.databinding.FragmentHomeBinding
import com.alix01z.cryptoyar.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var mainActivity: MainActivity
    private val viewModel: AppViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_home , container , false)
        setupViewPager()
        Log.d("TAG", "HEELLLLLLLOOOO")



        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(view)
    }


    private fun setupToolbar(view: View){
        val navController = Navigation.findNavController(view)

        val appBarConfiguration = AppBarConfiguration.Builder(R.id.homeFragment)
            .setOpenableLayout(mainActivity.drawerLayout)
            .build()
        val toolbar : Toolbar = view.findViewById(R.id.toolbar)

        NavigationUI.setupWithNavController(toolbar , navController , appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment) {
                toolbar.setNavigationIcon(R.drawable.baseline_home_24)
                toolbar.title = "خانه"
            }
        }
    }
    private fun setupViewPager(){
        viewModel.getViewPagerData().observe(viewLifecycleOwner){
            binding.vpagerHome.adapter = ViewPagerHomeAdapter(it)
            binding.vpagerHome.offscreenPageLimit = 3
            binding.wormDotIndicHome.attachTo(binding.vpagerHome)
        }
    }
}