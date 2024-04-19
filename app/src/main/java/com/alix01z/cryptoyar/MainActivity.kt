package com.alix01z.cryptoyar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.alix01z.cryptoyar.databinding.ActivityMainBinding
import com.alix01z.cryptoyar.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AppViewModel
    lateinit var drawerLayout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        viewModel = ViewModelProvider(this)[AppViewModel::class.java]


        callMarketSummary()

        viewModel.fetchedMarketData.observe(this) { response ->
            if (response != null) {
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.d("Retrofit", "Request Successful: ${data!!.data.cryptoCurrencyList.get(0).quotes.get(0).price}")
                } else {
                    Log.d("Retrofit", "Request Failed: ${response.code()}")
                }
            }
            else{
                Log.d("Retrofit", "Reponse is Null")
            }
        }

        setupNavigationComponent()

    }

    private fun setupNavigationComponent(){
        val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        val navController = navHostFragment.navController

        //Set DrawerLayout onClicks & Sync with navController
        val appBarConfiguration = AppBarConfiguration.Builder(R.id.homeFragment , R.id.marketFragment , R.id.watchlistFragment)
            .setOpenableLayout(binding.drawerLayout)
            .build()
        NavigationUI.setupWithNavController(binding.navView , navController)

        val popupMenu = android.widget.PopupMenu(this , null)
        popupMenu.inflate(R.menu.meun_bottom_bar)
        val menu = popupMenu.menu

        binding.bottomBar.setupWithNavController(menu , navController)

    }
    private fun callMarketSummary(){
        CoroutineScope(Dispatchers.IO).launch {
            val pageSrc :Document = Jsoup.connect("https://coinmarketcap.com/").get()
            val scrapeMarketSum: Elements = pageSrc.getElementsByClass("sc-f70bb44c-0 iQEJet cmc-link")
            Log.e("JSOUP",  "callMarketSummary: " + scrapeMarketSum.text())
        }
    }

}