package com.mohammedragab.innovaalomovesmock.domainlayer.baseusecase


import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class UseCase<T,IN>() {

    private var parentJob: Job = Job()
    var backgroundContext: CoroutineContext = Dispatchers.IO
    var foregroundContext: CoroutineContext = Dispatchers.Main

    protected abstract suspend fun executeOnBackground(item: IN): T

    fun execute(item: IN, blockN:  suspend (T)->Unit) {
        //   val response = RequestN<T>().apply { blockN() }
        unsubscribe()
        parentJob = Job()
        CoroutineScope(foregroundContext + parentJob).launch {

            val result = withContext(backgroundContext) {
                executeOnBackground(item)
            }
            blockN(result)

        }
    }

    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

    class RequestN<T> {
        private var onComplete: ((T) -> Unit)? = null

        fun onComplete(block: (T) -> Unit) {
            onComplete = block
        }

        operator fun invoke(result: T) {
            onComplete?.let {
                it.invoke(result)
            }
        }

    }
}