package hu.moksony.android_common.lazy_data

import android.util.Log
import kotlinx.coroutines.*

class LazyData<T>(private val dataSource: DataSource<T>) {
    private var data: T? = null
    private var loadingJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.IO)

    suspend fun obtain(): T {
        if (data != null) {
            return data!!
        }
        var job: Job? = this.loadingJob

        Log.d(TAG, "obtain: $job")
        if (job != null && (job.isActive || !job.isCompleted)) {
            job.join()
        } else {
            this.loadingJob = scope.launch {
                val data = dataSource.load()
                if (isActive) {
                    this@LazyData.data = data
                }
            }
            job = this.loadingJob
            job!!.join()
        }

        if (this.data == null && job.isCancelled) {
            Log.d(TAG, "obtain: job is cancelled wait again...")
            this.obtain()
        }
        return this.data!!
    }

    fun invalidate() {
        this.loadingJob?.cancel()
        this.loadingJob = null
        this.data = null
    }

    abstract class DataSource<T>() {
        abstract suspend fun load(): T
    }

    companion object {
        private const val TAG = "LazyData"
    }
}