package mobile.android.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mobile.android.shoppinglist.data.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase: RoomDatabase() {
    abstract fun getShoppingDao(): ShoppingDao

    companion object {
        // Volatile mean to makse sure that only one thread at a time is writing to that instance,
        // Because otherwise it could be there 2 thread and both want to initialize instance variable in the same time
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        // usually combination with invoke, basically means this function executed whenever we create instance our shopping database class
        // so whenever we write something like ShoppingDatabase() this invoke function will be executed
        // Synchronize -> to makesure that no other threats will access that instance at the same time
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ShoppingDatabase::class.java, "ShoppingDB.db").build()
    }
}