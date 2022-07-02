package by.kesso.pixabaytest.repository.login.entiry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.kesso.pixabaytest.domain.entity.LoginResult

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
)
