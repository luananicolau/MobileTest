package com.example.mobiletest.data.repository

import android.content.Context
import com.example.mobiletest.LoginState
import com.example.mobiletest.data.model.LoginModel
import com.example.mobiletest.services.LoginService
import com.example.mobiletest.services.TokenResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import javax.inject.Inject


interface LoginRepository {

    suspend fun getToken(loginModel: LoginModel): LoginState<String>

}

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService,
    @ApplicationContext private val context: Context
) : LoginRepository {
    override suspend fun getToken(loginModel: LoginModel): LoginState<String> {
        return try {
            val response = loginService.getToken(loginModel)

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    LoginState.Success(body.access)
                } else {
                    LoginState.Error("vazio")
                }
            } else {
                LoginState.Error("erro API", response.code())
            }
        } catch (e: HttpException) {
            LoginState.Error(e.message ?: "Invalid Credentials", e.code())
        }
    }
}