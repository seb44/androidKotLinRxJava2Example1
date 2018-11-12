package fr.sebastienlaunay.androidkotlinrxjava2example1

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.ioToMainThread(): Observable<T> =
    subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .doOnNext {
        Log.d("ThreadExtension", "onNext : $it")
    }
