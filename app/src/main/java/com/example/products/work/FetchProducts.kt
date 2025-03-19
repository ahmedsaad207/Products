//package com.example.products.work
//
//import android.content.Context
//import android.util.Log
//import androidx.work.CoroutineWorker
//import androidx.work.WorkerParameters
//import androidx.work.workDataOf
//import com.example.products.data.remote.ProductApi
//import com.example.products.utils.Constants
//import com.google.gson.Gson
//
//class FetchProducts(context: Context, workerParameters: WorkerParameters) :
//    CoroutineWorker(context, workerParameters) {
//    override suspend fun doWork(): Result {
////        Log.i("TAG", "doWork: ")
//        val service = ProductApi.service
//        val response = service.getProductResponse()
//        val productResponse = response.body()
//        if (response.isSuccessful && productResponse != null) {
//            val products = productResponse.products
//            val gson = Gson()
//            val productJson = gson.toJson(products)
////            Log.i("TAG", "doWork: response.isSuccessful, size: ${products?.size}")
//            return Result.success(workDataOf(Constants.LIST_PRODUCTS to productJson))
//        } else {
//            return Result.failure()
//        }
//
//    }
//}