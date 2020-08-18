package com.wealoha.social.api.feed.service;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.wealoha.social.api.common.ApiErrorCode;
import com.wealoha.social.api.common.Direct;
import com.wealoha.social.api.feed.Feed2API;
import com.wealoha.social.api.feed.FeedGetData;
import com.wealoha.social.api.post.bean.Post;
import com.wealoha.social.beans.Result;
import com.wealoha.social.utils.XL;

public class TagedPostService extends Feed2Service {

	@Inject
	Feed2API feed2API;

	@Override
	public void getList(String cursor, int count, Direct direct, final String userid, final com.wealoha.social.api.common.BaseListApiService.ApiListCallback<Post> callback) {
		feed2API.getTagedList(cursor, count, new Callback<Result<FeedGetData>>() {

			@Override
			public void failure(RetrofitError error) {
				XL.i("Feed2Fragment", "service: faile--" + error.getMessage());
				callback.fail(null, error);
			}

			@Override
			public void success(Result<FeedGetData> result, Response arg1) {
				XL.i("Feed2Fragment", "service: success--");
				if (result == null || !result.isOk()) {
					callback.fail(ApiErrorCode.fromResult(result), null);
				} else {
					callback.success(transResult2List(result.data, userid), result.data.nextCursorId);
				}
			}
		});
	}

}
