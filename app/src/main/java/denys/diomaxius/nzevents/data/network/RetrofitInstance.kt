package denys.diomaxius.nzevents.data.network

import denys.diomaxius.nzevents.data.Pass
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.eventfinda.co.nz/v2/"
    private const val USERNAME = Pass.USERNAME
    private const val PASSWORD = Pass.PASSWORD

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", Credentials.basic(USERNAME, PASSWORD))
                .build()
            chain.proceed(request)
        }
        .build()

    val api: EventFindApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(EventFindApi::class.java)
    }
}