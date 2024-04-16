package com.alix01z.cryptoyar.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.alix01z.cryptoyar.MainActivity
import com.alix01z.cryptoyar.R
import com.alix01z.cryptoyar.adapters.TopCoinRvAdapter
import com.alix01z.cryptoyar.adapters.ViewPagerHomeAdapter
import com.alix01z.cryptoyar.databinding.FragmentHomeBinding
import com.alix01z.cryptoyar.models.DataItem
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

        readAllDataDB()


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
    private fun readAllDataDB(){
        viewModel.marketDataDB.observe(viewLifecycleOwner) {
            val coinList = ArrayList<DataItem>()
            it?.allMarketModel?.data?.cryptoCurrencyList?.forEach {
                coinList.add(it)
            }
        updateRecyclerView(coinList)
//            Log.d("DATABASE", "readAllDataDB: + ${it.allMarketModel.data.cryptoCurrencyList.get(0).quotes.get(0).price} ")
        }
    }
    private fun updateRecyclerView(coinList: ArrayList<DataItem>) {
        val adapter = TopCoinRvAdapter(coinList)
        binding.rvTopCoin.adapter = adapter
        binding.rvTopCoin.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        Toast.makeText(context , "Top coins Updated!", Toast.LENGTH_SHORT).show()
    }
}