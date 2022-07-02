package by.kesso.pixabaytest.repository.login.entiry

import android.provider.ContactsContract
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {
    @Query("""SELECT * FROM user WHERE email = :email""")
    fun getUser(email: String): Single<List<User>>

    @Insert
    fun insert(user: User): Single<Long>
}