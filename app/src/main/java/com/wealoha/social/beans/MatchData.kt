package com.wealoha.social.beans

import com.wealoha.social.beans.imagemap.HasImageMap
import java.io.Serializable

/**
 * Match调用结果，下一批待匹配的用户或者错误
 *
 * @author javamonk
 * @see
 * @since
 * @date 2014-10-29 下午4:44:17
 */
class MatchData(
    var list: List<User>? = null,

    /** 还需要多少秒才能刷下一批  */
    var quotaResetSeconds: Int = 0,

    /** 每次刷一批的时间段长度  */
    var quotaDurationSeconds: Int = 0,

    /** 可用的重置配额次数  */
    var quotaReset: Int = 0,
    override var imageMap: Map<String, Image>? = null,
    var recommendSourceMap: Map<String, String>? = null
) : ResultData(), Serializable, HasImageMap {

    companion object {
        private const val serialVersionUID = -7473243341042126058L

        @kotlin.jvm.JvmField
        val TAG = MatchData::class.java.simpleName
    }
}