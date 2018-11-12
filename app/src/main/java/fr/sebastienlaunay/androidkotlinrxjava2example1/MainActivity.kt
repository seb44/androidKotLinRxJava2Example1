package fr.sebastienlaunay.androidkotlinrxjava2example1

//https://code.tutsplus.com/fr/tutorials/kotlin-reactive-programming-with-rxjava-and-rxkotlin--cms-31577
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            // Méthode 1
            startRxStream()

            // Méthode 2
            startRxStream2()

            // Méthode 3
            startRxStream3()

            // Méthode 4
            startRxStream4()

        }
    }

    private fun startRxStream() {

        // Creation d'un observable
        val myObservable = getObservable()

        // Création d'un observer
        var myObserver = getObserver()

        myObservable.subscribe(myObserver)

    }

    private fun getObservable(): Observable<String> {
        return Observable.just("1", "2", "3", "4", "5")
    }

    private fun getObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(s: String) {
                Log.d(TAG, "onNext : $s")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "On Error : ${e.message}")
            }

            override fun onComplete() {
                Log.d(TAG, "OnCompletable startRxStream1")
            }
        }
    }

    private fun startRxStream2() {
        Observable.just("14", "15", "16", "17")
            .doOnNext {
                Log.d(TAG, "onNext : $it")
            }
            .doOnComplete {
                Log.d(TAG, "OnCompletable startRxStream2")
            }
            .doOnError {
                Log.d(TAG, "On Error : ${it.message}")
            }
            .subscribe()
    }


    private fun startRxStream3() {
        Completable.create {
            Log.d(TAG, "Appel de Completable")
            return@create
        }
        .doOnComplete {
            Log.d(TAG, "OnCompletable startRxStream3")
        }
        .doOnError {
                Log.d(TAG, "On Error : ${it.message}")
        }
        .subscribe()
    }

    private fun startRxStream4() {
        Observable.just("21", "22", "23", "24")
//            .doOnNext {
//                Log.d(TAG, "onNext : $it")
//            }
            .ioToMainThread()   // Utilisation d'une extension pour factorisation
            .subscribe()
    }
}
