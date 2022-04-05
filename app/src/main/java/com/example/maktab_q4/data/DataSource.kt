package ir.mohsenafshar.apps.mkbarchitecture.data

import androidx.lifecycle.LiveData
import com.example.maktab_q4.model.localmodel.User


interface DataSource {
    fun getUserList(): List<User>
    fun saveUserList(users: List<User>)

}