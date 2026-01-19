import com.example.mobiletest.services.LoginService
import com.example.mobiletest.utils.ApiUrls
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


object RetrofitInstance {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiUrls.BASE)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val loginService: LoginService = retrofit.create(LoginService::class.java)
}
