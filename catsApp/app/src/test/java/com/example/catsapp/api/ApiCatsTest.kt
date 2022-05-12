package com.example.catsapp.api


import com.espoo.android.MockResponseFileReader
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class ApiCatsTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var service: ApiService

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    @Test
    fun `read sample success json file`(){
        val reader = MockResponseFileReader("cats/success_response.json")
        assertNotNull(reader.content)
    }
    @Test
    fun `read sample failed json file`(){
        val reader = MockResponseFileReader("cats/failed_response.json")
        assertNotNull(reader.content)
    }

    @Test
    fun `fetch cats and check response Code 200 returned`(){
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("cats/success_response.json").content)
        mockWebServer.enqueue(response)
        // Act
        val  actualResponse = service.getFetchCats().execute()
        // Assert
        assertEquals(response.toString().contains("200"),actualResponse.code().toString().contains("200"))
        assertEquals(60,actualResponse.body()?.data?.size)
    }

    @Test
    fun `fetch cats with word that doesn't exist and check the response code 200 returned`() {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("cats/success_response_word_not_found.json").content)
        mockWebServer.enqueue(response)
        // Act
        val  actualResponse = service.getFetchCats().execute()
        // Assert
        assertEquals(0,actualResponse.body()?.data?.size)
    }

    @Test
    fun `fetch cats invalid Authorization  and check the 403 response code returned`() {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_FORBIDDEN)
            .setBody(MockResponseFileReader("cats/failed_response_403.json").content)
        mockWebServer.enqueue(response)
        // Act
        val  actualResponse = service.getFetchCats().execute()
        // Assert
        actualResponse.body()
        assertEquals(response.toString().contains("403"),actualResponse.code().toString().contains("403"))
    }

    @Test
    fun `fetch cats invalid endpoint  and check the 404 response code returned`() {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
            .setBody(MockResponseFileReader("cats/failed_response_404.json").content)
        mockWebServer.enqueue(response)
        // Act
        val  actualResponse = service.getFetchCats().execute()
        // Assert
        assertEquals(null,actualResponse.body())
    }



    @After
    fun stopService() {
        mockWebServer.shutdown()
    }


}