package com.example.mobiletest.data.repository

import android.content.Context
import com.example.mobiletest.states.LoginState
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
                response.body()?.let {
                    LoginState.Success(it.access)
                } ?: LoginState.Error("Resposta inválida do servidor")
            } else {
                when (response.code()) {
                    400, 401 -> LoginState.Error("Usuário ou senha incorretos", response.code())
                    else -> LoginState.Error(
                        "Erro no servidor (${response.code()})",
                        response.code()
                    )
                }
            }

        } catch (e: Exception) {   // 👈 aqui estava o crash
            LoginState.Error("Erro de comunicação com o servidor: ${e.localizedMessage}")
        }
    }}
