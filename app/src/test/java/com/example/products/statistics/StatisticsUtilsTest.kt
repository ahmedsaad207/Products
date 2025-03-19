package com.example.products.statistics

import androidx.compose.ui.util.trace
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert
import org.junit.Test

class StatisticsUtilsTest {

    @Test
    fun getActiveAndCompletedStats_NoCompletedTwoCompleted3ActiveEmptyListNull_return100Active40Completed60Active() {
        //Given
        val tasks = listOf(Task(isCompleted = false))

        // When
        val result = getActiveAndCompletedStats(tasks)

        // Then
        MatcherAssert.assertThat(result.completedTasksPercent, `is` (0f))
    }

    @Test
    fun getActiveAndCompletedStats_TwoCompleted3Active_return40PercentCompleted60PercentActive() {
        //Given
        val tasks = listOf(
            Task(isCompleted = false),
            Task(isCompleted = false),
            Task(isCompleted = false),
            Task(isCompleted = true),
            Task(isCompleted = true)
        )

        // When
        val result = getActiveAndCompletedStats(tasks)

        // Then
        MatcherAssert.assertThat(result.completedTasksPercent, `is` (40f))
        MatcherAssert.assertThat(result.activeTasksPercent, `is` (60f))
    }

    @Test
    fun getActiveAndCompletedStats_EmptyList_returnZeroPercentCompletedZeroPercentActive() {
        //Given
        val tasks = emptyList<Task>()

        // When
        val result = getActiveAndCompletedStats(tasks)

        // Then
        MatcherAssert.assertThat(result.completedTasksPercent, `is` (0f))
        MatcherAssert.assertThat(result.activeTasksPercent, `is` (0f))
    }

    @Test
    fun getActiveAndCompletedStats_Null_returnZeroPercentCompletedZeroPercentActive() {
        //Given
      //  val tasks = listOf(null)

        // When
        val result = getActiveAndCompletedStats(null)

        // Then
        MatcherAssert.assertThat(result.completedTasksPercent, `is` (0f))
        MatcherAssert.assertThat(result.activeTasksPercent, `is` (0f))
    }


}