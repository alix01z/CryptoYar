package com.alix01z.cryptoyar.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.alix01z.cryptoyar.MainActivity
import com.alix01z.cryptoyar.R
import com.alix01z.cryptoyar.databinding.FragmentMarketBinding

class MarketFragment : Fragment() {
    private lateinit var binding: FragmentMarketBinding
    private lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_market , container , false)
        binding.editextSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
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

        val appBarConfiguration = AppBarConfiguration.Builder(R.id.marketFragment)
//            .setOpenableLayout(mainActivity.drawerLayout)
            .build()
        val toolbar : Toolbar = view.findViewById(R.id.toolbar)

        NavigationUI.setupWithNavController(toolbar , navController , appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.marketFragment) {
                toolbar.title = "بازار"
            }
        }

    }
}