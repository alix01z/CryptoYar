package com.alix01z.cryptoyar.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.alix01z.cryptoyar.MainActivity
import com.alix01z.cryptoyar.R
import com.alix01z.cryptoyar.databinding.FragmentMarketBinding
import com.alix01z.cryptoyar.databinding.FragmentWatchlistBinding

class WatchlistFragment : Fragment() {
    private lateinit var binding: FragmentWatchlistBinding
    private lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_watchlist , container , false)


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

        val appBarConfiguration = AppBarConfiguration.Builder(R.id.watchlistFragment)
            .setOpenableLayout(mainActivity.drawerLayout)
            .build()
        val toolbar : Toolbar = view.findViewById(R.id.toolbar)

        NavigationUI.setupWithNavController(toolbar , navController , appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.watchlistFragment) {
                toolbar.setNavigationIcon(R.drawable.baseline_bookmark_24)
                toolbar.title = "واچ لیست"
            }
        }
    }
}