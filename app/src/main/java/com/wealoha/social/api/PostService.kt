package com.wealoha.social.api

import android.text.TextUtils
import com.wealoha.social.api.BaseService.ServiceResultCallback
import com.wealoha.social.beans.FeedGetData
import com.wealoha.social.beans.Post
import com.wealoha.social.beans.Post.Companion.fromPostDTOList
import com.wealoha.social.beans.Result
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response
import javax.inject.Inject

class PostService : AbsBaseService<Post?>() {
    @JvmField
	@Inject
    var feed2API: ServerApi? = null
    override fun getList(
        callback: ServiceResultCallback<Post?>,
        cursor: String?,
        vararg args: Any?
    ) {
        feed2API!!.getPosts(
            cursor!!,
            AbsBaseService.COUNT,
            object : Callback<Result<FeedGetData>> {
                override fun failure(arg0: RetrofitError) {
                    callback.failer()
                }

                override fun success(result: Result<FeedGetData>?, arg1: Response) {
                    if (result != null && result.isOk) {
                        list.addAll( //
                            fromPostDTOList(
                                result.data!!.list,  //
                                result.data!!.userMap,  //
                                result.data!!.imageMap,  //
                                result.data!!.videoMap,  //
                                result.data!!.commentCountMap,  //
                                result.data!!.likeCountMap
                            )
                        )
                        callback.success(list)
                        cursorId = result.data!!.nextCursorId
                        if (TextUtils.isEmpty(cursorId)) {
                            callback.nomore()
                        }
                    } else {
                        callback.failer()
                    }
                }
            })
    }
}