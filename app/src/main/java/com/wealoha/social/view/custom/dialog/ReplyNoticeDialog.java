package com.wealoha.social.view.custom.dialog;import javax.inject.Inject;import android.app.Dialog;import android.content.Context;import android.graphics.Bitmap;import android.graphics.Color;import android.graphics.drawable.BitmapDrawable;import android.graphics.drawable.ColorDrawable;import android.graphics.drawable.Drawable;import android.os.Bundle;import android.text.TextUtils;import android.view.View;import android.view.Window;import android.widget.Button;import android.widget.EditText;import android.widget.ImageView;import android.widget.LinearLayout;import android.widget.RelativeLayout;import com.google.gson.reflect.TypeToken;import com.lidroid.xutils.HttpUtils;import com.lidroid.xutils.exception.HttpException;import com.lidroid.xutils.http.RequestParams;import com.lidroid.xutils.http.ResponseInfo;import com.lidroid.xutils.http.callback.RequestCallBack;import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;import com.wealoha.social.BaseFragAct;import com.wealoha.social.R;import com.wealoha.social.beans.ApiResponse;import com.wealoha.social.beans.ResultData;import com.wealoha.social.beans.Notify;import com.wealoha.social.commons.GlobalConstants;import com.wealoha.social.commons.JsonController;import com.wealoha.social.inject.Injector;import com.wealoha.social.utils.ContextUtil;public class ReplyNoticeDialog extends Dialog implements android.view.View.OnClickListener {	private static final ColorDrawable TRANSPARENT_DRAWABLE = new ColorDrawable(Color.TRANSPARENT);	RelativeLayout popup_report_root_rl;	private Bitmap bitmap;	ImageView replay_aloha_iv;	Button btn_send;	EditText et_sendmessage;	Notify notify;	@Inject	ContextUtil contextUtil;	@Inject	Context mContext;	public ReplyNoticeDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {		super(context, cancelable, cancelListener);	}	public ReplyNoticeDialog(Context context, int theme) {		super(context, theme);	}	public ReplyNoticeDialog(Context context, BaseFragAct activity, Bitmap drawable, Notify notify) {		super(context, android.R.style.Animation_Activity);		this.bitmap = drawable;		this.notify = notify;	}	@Override	protected void onCreate(Bundle savedInstanceState) {		super.onCreate(savedInstanceState);		setContentView(R.layout.dialog_replay_aloha_view);		Injector.inject(this);		Window mWindow = getWindow();		mWindow.setBackgroundDrawable(TRANSPARENT_DRAWABLE);		popup_report_root_rl = (RelativeLayout) findViewById(R.id.popup_report_root_rl);		replay_aloha_iv = (ImageView) findViewById(R.id.replay_aloha_iv);		btn_send = (Button) findViewById(R.id.btn_send);		et_sendmessage = (EditText) findViewById(R.id.et_sendmessage);		btn_send.setOnClickListener(this);		popup_report_root_rl.setOnClickListener(this);		int width = bitmap.getWidth();		int height = bitmap.getHeight();		Drawable drawable = new BitmapDrawable(mContext.getResources(), bitmap);		replay_aloha_iv.setLayoutParams(new LinearLayout.LayoutParams(width, height));		replay_aloha_iv.setBackground(drawable);		bitmap = null;	}	@Override	public void onClick(View arg0) {		switch (arg0.getId()) {		case R.id.popup_report_root_rl:			this.dismiss();			break;		case R.id.btn_send:			sendReply();			break;		}	}	/**	 * @Description:	 * @see:	 * @since:	 * @description	 * @author: sunkist	 * @date:2014-11-20	 */	private void sendReply() {		String comment = et_sendmessage.getText().toString();		if (TextUtils.isEmpty(comment)) {			return;		}		HttpUtils httpUtils = new HttpUtils();		RequestParams requestParams = new RequestParams();		requestParams.addBodyParameter(GlobalConstants.AppConstact.POST_ID, this.notify.getPostId());		requestParams.addBodyParameter(GlobalConstants.AppConstact.COMMENT, "");		contextUtil.addGeneralHttpHeaders(requestParams);		httpUtils.send(HttpMethod.POST, GlobalConstants.ServerUrl.FEED_COMMENTS, requestParams, new RequestCallBack<String>() {			@Override			public void onFailure(HttpException arg0, String arg1) {				// ToastUtil.shortToastCenter(mContext, "	 Failure	");				ReplyNoticeDialog.this.dismiss();			}			@Override			public void onSuccess(ResponseInfo<String> arg0) {				ApiResponse<ResultData> apiResponse = JsonController.parseJson(arg0.result, new TypeToken<ApiResponse<ResultData>>() {				}.getType());				if (apiResponse != null && apiResponse.isOk()) {					// ToastUtil.shortToastCenter(mContext, "	 Send	");				} else {					// ToastUtil.shortToastCenter(mContext, "	 Failure	");				}				ReplyNoticeDialog.this.dismiss();			}		});	}}