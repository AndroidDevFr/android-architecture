package com.android.architecture.example.lib.rx.operators

import com.android.architecture.example.SampleRobolectricTestCase
import com.android.architecture.example.network.exceptions.ApiException
import com.google.gson.Gson
import io.reactivex.subjects.PublishSubject
import io.reactivex.subscribers.TestSubscriber
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.Response


class ApiErrorOperatorTest : SampleRobolectricTestCase() {

    @Test
    fun testErrorResponse() {
        val gson = Gson()

        val response: PublishSubject<Response<Int>> = PublishSubject.create()
        val result = response.lift(Operators.apiError(gson))

        val resultTest = TestSubscriber<Int>()
        result.subscribe(resultTest::onNext)

        response.onNext(Response.error<Int>(400, ResponseBody.create(MediaType.parse("application/json"),
                        "{\"status\": \"KO\",\"error\": \"bad request\"}")))

        resultTest.assertNoValues()
    }

}