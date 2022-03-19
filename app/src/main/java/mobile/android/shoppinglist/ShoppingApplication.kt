package mobile.android.shoppinglist

import android.app.Application
import mobile.android.shoppinglist.data.db.ShoppingDatabase
import mobile.android.shoppinglist.data.repositories.ShoppingRepository
import mobile.android.shoppinglist.ui.shoppinglist.ShoppingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ShoppingApplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ShoppingApplication))
        bind() from singleton { ShoppingDatabase(instance())}
        bind() from singleton { ShoppingRepository(instance()) }
        // use provider because we want to use that instance activity in shopping activity
        bind() from provider {
            ShoppingViewModelFactory(instance())
        }
    }
}