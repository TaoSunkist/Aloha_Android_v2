package com.wealoha.social.api

import com.wealoha.social.beans.AuthData
import com.wealoha.social.beans.Result
import com.wealoha.social.beans.message.InboxSessionResult
import com.wealoha.social.impl.ServerUrlImpl
import io.reactivex.Single
import retrofit.http.GET
import retrofit.http.Query
import kotlin.random.Random

class RxMessageService {
    companion object {
        val shared = RxMessageService()
    }

//    fun sessions(
//        cursor: String,
//        count: Int
//    ): Single<Result<InboxSessionResult>> {
//        return Single.create<Result<InboxSessionResult>> {
//            Thread.sleep(500)
//            if (Random.nextInt(0, 50) > 10) {
//                /* success */
//                it.onSuccess(
//                    Result.success(InboxSessionResult.fake())
//                )
//            } else {
//                it.onError(Throwable("我就让你出错你能咋地"))
//            }
//        }
//    }

}