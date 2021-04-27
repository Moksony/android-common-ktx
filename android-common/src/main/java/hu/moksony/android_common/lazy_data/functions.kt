package hu.moksony.android_common.lazy_data

fun <T> dataSource(call: suspend () -> T): LazyData.DataSource<T> {
    return object : LazyData.DataSource<T>() {
        override suspend fun load(): T {
            return call.invoke()
        }
    }
}

fun <T> lazyData(call: suspend () -> T): LazyData<T> {
    val source = dataSource(call)
    return LazyData(source)
}