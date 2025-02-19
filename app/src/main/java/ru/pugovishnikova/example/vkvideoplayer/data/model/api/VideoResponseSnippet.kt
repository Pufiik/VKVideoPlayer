package ru.pugovishnikova.example.vkvideoplayer.data.model.api
import kotlinx.serialization.Serializable

@Serializable
data class VideoResponse(
    val kind: String,
    val etag: String,
    val items: List<YouTubeVideo>,
    val prevPageToken: String?,
    val nextPageToken: String?,
    val pageInfo: PageInfo?
)

@Serializable
data class YouTubeVideo(
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: Snippet?,
    val contentDetails: ContentDetails?,
)

@Serializable
data class Snippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String?,
    val thumbnails: Map<String, Thumbnail>?,
    val channelTitle: String?,
    val tags: List<String>?,
    val categoryId: String?,
    val liveBroadcastContent: String?,
    val defaultLanguage: String?,
    val localized: Localized?,
    val defaultAudioLanguage: String?
)

//@Serializable
//data class Thumbnails(
//    val default: Thumbnail,
//    val medium: Thumbnail,
//    val high: Thumbnail,
//    val standard: Thumbnail? = null,
//    val maxres: Thumbnail? = null
//)

@Serializable
data class Thumbnail(
    val url: String?,
    val width: Int?,
    val height: Int?
)

@Serializable
data class Localized(
    val title: String?,
    val description: String?
)

@Serializable
data class PageInfo(
    val totalResults: Int?,
    val resultsPerPage: Int?
)

@Serializable
data class ContentDetails(
    val duration: String?,
    val dimension: String?,
    val definition: String?,
    val caption: String?,
    val licensedContent: Boolean?,
    val contentRating: ContentRating?,
    val projection: String?,
    val regionRestriction: RegionRestriction?,
    val hasCustomThumbnail: Boolean?
)

@Serializable
data class ContentRating(
    val acbRating: String? = null,
    val agcomRating: String? = null,
    val anatelRating: String? = null,
    val bbfcRating: String? = null,
    val bfvcRating: String? = null,
    val bmukkRating: String? = null,
    val catvRating: String? = null,
    val catvfrRating: String? = null,
    val cbfcRating: String? = null,
    val cccRating: String? = null,
    val cceRating: String? = null,
    val chfilmRating: String? = null,
    val chvrsRating: String? = null,
    val cicfRating: String? = null,
    val cnaRating: String? = null,
    val cncRating: String? = null,
    val csaRating: String? = null,
    val cscfRating: String? = null,
    val czfilmRating: String? = null,
    val djctqRating: String? = null,
    val djctqRatingReasons: List<String>? = null,
    val ecbmctRating: String? = null,
    val eefilmRating: String? = null,
    val egfilmRating: String? = null,
    val eirinRating: String? = null,
    val fcbmRating: String? = null,
    val fcoRating: String? = null,
    val fmocRating: String? = null,
    val fpbRating: String? = null,
    val fpbRatingReasons: List<String>? = null,
    val fskRating: String? = null,
    val grfilmRating: String? = null,
    val icaaRating: String? = null,
    val ifcoRating: String? = null,
    val ilfilmRating: String? = null,
    val incaaRating: String? = null,
    val kfcbRating: String? = null,
    val kijkwijzerRating: String? = null,
    val kmrbRating: String? = null,
    val lsfRating: String? = null,
    val mccaaRating: String? = null,
    val mccypRating: String? = null,
    val mcstRating: String? = null,
    val mdaRating: String? = null,
    val medietilsynetRating: String? = null,
    val mekuRating: String? = null,
    val mibacRating: String? = null,
    val mocRating: String? = null,
    val moctwRating: String? = null,
    val mpaaRating: String? = null,
    val mpaatRating: String? = null,
    val mtrcbRating: String? = null,
    val nbcRating: String? = null,
    val nbcplRating: String? = null,
    val nfrcRating: String? = null,
    val nfvcbRating: String? = null,
    val nkclvRating: String? = null,
    val oflcRating: String? = null,
    val pefilmRating: String? = null,
    val rcnofRating: String? = null,
    val resorteviolenciaRating: String? = null,
    val rtcRating: String? = null,
    val rteRating: String? = null,
    val russiaRating: String? = null,
    val skfilmRating: String? = null,
    val smaisRating: String? = null,
    val smsaRating: String? = null,
    val tvpgRating: String? = null,
    val ytRating: String? = null
)

@Serializable
data class RegionRestriction(
    val allowed: List<String>? = null,
    val blocked: List<String>? = null
)

