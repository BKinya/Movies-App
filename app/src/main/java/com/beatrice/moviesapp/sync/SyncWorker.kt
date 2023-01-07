package com.beatrice.moviesapp.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.beatrice.moviesapp.core.domain.repository.MovieRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val moviesRepository: MovieRepository
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val syncSuccess = awaitAll(
                async { moviesRepository.sync() }
            ).all { it }
            if (syncSuccess) Result.success()
            else Result.retry()
        }
    }

}

val workerConstraints = Constraints.Builder().apply {
    setRequiredNetworkType(NetworkType.CONNECTED)
    setRequiresBatteryNotLow(true)
    setRequiresStorageNotLow(true)
}.build()

/**
 * Schedule worker to periodically fetch movies and sync the local storage
 */
val periodicSyncTask = PeriodicWorkRequestBuilder<SyncWorker>(1, TimeUnit.DAYS)
    .setConstraints(workerConstraints)
    .build()

val oneTimeTask = OneTimeWorkRequestBuilder<SyncWorker>().build()