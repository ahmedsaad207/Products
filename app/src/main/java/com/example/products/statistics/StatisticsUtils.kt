package com.example.products.statistics

import android.util.Log

internal fun getActiveAndCompletedStats(tasks: List<Task?>?): StatsResult {

    val totalTasks = tasks?.size ?: 0
   // Log.i("TAG", "getActiveAndCompletedStats: $totalTasks")
    val numberOfActiveTasks = tasks?.count { it?.isActive ?: false } ?: 1
    var activePercent = 0;
    var completePercent=0
    if (tasks != null) {
    activePercent =  if (totalTasks == 0) 0 else  100 * numberOfActiveTasks/ totalTasks
    completePercent = if (totalTasks == 0) 0 else 100 * (totalTasks - numberOfActiveTasks) / totalTasks
    }

    return StatsResult(
        activeTasksPercent = activePercent.toFloat(),
        completedTasksPercent = completePercent.toFloat()
    )
}

data class StatsResult(val activeTasksPercent: Float, val completedTasksPercent: Float)