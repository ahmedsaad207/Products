package com.example.products

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.work.WorkManager
import com.example.products.data.local.ProductDao
import com.example.products.data.local.ProductDatabase
import com.example.products.model.Product
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    lateinit var productsState: MutableState<List<Product>>
    lateinit var database: ProductDao
    lateinit var titleState: MutableState<String>

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackBarHostState = remember { SnackbarHostState() }
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()
            titleState = remember { mutableStateOf("") }
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = /*"Top App Bar Title"*/titleState.value) },
                        navigationIcon = {
                            IconButton(onClick = {}) {
                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = {}) {
                                Icon(Icons.Default.Search, contentDescription = "Search")
                            }
                        }
                    )
                },
                bottomBar = {
                    BottomAppBar {
                        BottomNavigationBar(navController)
                    }
                },
                snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        scope.launch {
                            snackBarHostState.showSnackbar("Add new Product")
                        }
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                },
                contentWindowInsets = WindowInsets.systemBars,
            ) { innerPadding ->
                Box(modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()) {
                    SetupNavHost(navController, snackBarHostState, titleState)
                }
            }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavController) {
        val selectedNavigationIndex = rememberSaveable() { mutableIntStateOf(0) }

        val navigationItems = listOf(
            NavigationItem(
                label = "All Products",
                icon = Icons.Default.Home,
                route = Screen.AllProduct
            ),
            NavigationItem(
                label = "Favorites",
                icon = Icons.Default.Favorite,
                route = Screen.FavProduct
            )
        )

        NavigationBar(containerColor = Color.White) {
            navigationItems.forEachIndexed { i, item ->
                NavigationBarItem(
                    selected = selectedNavigationIndex.intValue == i,
                    onClick = {
                        selectedNavigationIndex.intValue = i
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        Log.i("TAG", "BottomNavigationBar: onClick")
                    },
                    icon = { Icon(imageVector = item.icon, contentDescription = null) },
                    label = {
                        Text(
                            text = item.label,
                            color = if (i == selectedNavigationIndex.intValue) Color.Black else Color.Gray
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.surface,
                        indicatorColor = MaterialTheme.colorScheme.primary
                    )
                )

            }
        }
    }

    private fun fetchProducts() {
        val workManager = WorkManager.getInstance(this)
        database = ProductDatabase.getInstance(this).getProductDao()
        /*val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = OneTimeWorkRequestBuilder<FetchProducts>()
            .build()*/

        /*if (isInternetAvailable()) {
//            workManager.enqueue(request)
            Log.i("TAG", "checkWifiOnAndConnected: connected")

        } else {
//            lifecycleScope.launch(Dispatchers.IO) {
//
//                if (::productsState.isInitialized) {
//                    Log.i("TAG", "productsState.isInitialized")
//                    val products = database.getAllProducts()
//                    withContext(Dispatchers.Main) {
//                        productsState.value = products
//                    }
//                }
//            }
            Log.i("TAG", "checkWifiOnAndConnected: not connected")
        }*/

        /*if (checkWifiOnAndConnected()) {
            Log.i("TAG", "checkWifiOnAndConnected: connected")
            workManager.enqueueUniqueWork("products", ExistingWorkPolicy.KEEP, request)
        } else {
            lifecycleScope.launch(Dispatchers.IO) {
                productsState.value = database.getAllProducts()
            }
            Log.i("TAG", "checkWifiOnAndConnected: not connected")
        }*/

        /*val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetwork?.let { activeNetwork ->
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            val connectedToWifi = capabilities?.hasCapability(NetworkCapabilities.TRANSPORT_WIFI)
        } ?: kotlin.run { Log.i("TAG", "fetchProducts: activeNetwork are") }*/


        /* workManager.getWorkInfoByIdLiveData(request.id).observe(this) { workInfo ->
             when (workInfo?.state) {
                 WorkInfo.State.SUCCEEDED -> {
                     Log.i("TAG", "WorkInfo.State.SUCCEEDED: ")
                     let {
                         val productJson =
                             workInfo.outputData.getString(Constants.LIST_PRODUCTS) ?: ""
                         val gson = Gson()
                         val productListType = object : TypeToken<List<Product>>() {}.type
                         val products: List<Product> = gson.fromJson(productJson, productListType)
                         Log.i("TAG", "fetchProducts: products size= ${products.size}")
                         lifecycleScope.launch(Dispatchers.IO) {
                             database.insertAllProducts(products)
                         }
                         productsState.value = products
                     }
                 }

                 else -> {
                     Log.i("TAG", "WorkInfo.State.FAILED: ")
                 }
             }
         }*/
    }

    private fun checkInternetConnection(): Boolean {
        val connManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return mWifi!!.isConnected
    }

    private fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return result
    }

    private fun checkWifiOnAndConnected(): Boolean {
        val wifiMgr = getSystemService(WIFI_SERVICE) as WifiManager

        if (wifiMgr.isWifiEnabled) {

            val wifiInfo = wifiMgr.connectionInfo

            if (wifiInfo.networkId == -1) {
                return false
            }
            return true
        } else {
            return false
        }
    }

    /* @Composable
     fun ListOfCakes() {

         val products = productsState.value ?: listOf()

         LazyColumn(
             modifier = Modifier.fillMaxSize()
         ) {
             items(products.size) {
                 ProductRow(products[it])
             }
         }
     }*/

    /*@OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun ProductRow(product: Product) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .shadow(4.dp)
                .clip(RoundedCornerShape(8.dp)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GlideImage(
                model = product.thumbnail,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = product.title ?: "title is null",
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth()

                )
                Text(
                    text = product.brand ?: "brand is null",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth()
                )

                Text(
                    text = product.description ?: "description is null",
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth()
                )

                Text(
                    text = product.price ?: "price is null",
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth()
                )


            }
        }
    }*/

}