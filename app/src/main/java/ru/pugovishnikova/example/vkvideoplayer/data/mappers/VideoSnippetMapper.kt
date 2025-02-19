package ru.pugovishnikova.example.vkvideoplayer.data.mappers

import ru.pugovishnikova.example.vkvideoplayer.data.model.api.ContentDetails
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.ContentDetailsDto
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.ContentRating
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.ContentRatingDto
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.LocalizedDTO
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.Snippet
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.SnippetDto
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.Thumbnail
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.ThumbnailDTO
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.Localized
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.PageInfo
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.PageInfoDTO
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.RegionRestriction
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.RegionRestrictionDto
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.VideoResponse
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.VideoResponseDto
//import ru.pugovishnikova.example.vkvideoplayer.data.model.snippet.ThumbnailsDTO
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.YouTubeVideo
import ru.pugovishnikova.example.vkvideoplayer.data.model.api.YouTubeVideoDto

fun VideoResponseDto.toVideoResponse() = VideoResponse(
    kind = kind,
    etag = etag,
    items = items.map { it.toYouTubeItem() },
    prevPageToken = prevPageToken,
    nextPageToken = nextPageToken,
    pageInfo = pageInfo?.toPageInfo()
)

fun YouTubeVideoDto.toYouTubeItem() = YouTubeVideo(
    kind = kind,
    etag = etag,
    id = id,
    snippet = snippet?.toSnippet(),
    contentDetails = contentDetails?.toContentDetails()
)

fun SnippetDto.toSnippet() = Snippet(
    publishedAt = publishedAt,
    channelId = channelId,
    title = title,
    description = description,
    thumbnails = thumbnails?.mapValues { it.value.toThumbnail() },
    channelTitle = channelTitle,
    tags = tags,
    categoryId = categoryId,
    liveBroadcastContent = liveBroadcastContent,
    defaultLanguage = defaultLanguage,
    localized = localized?.toLocolized(),
    defaultAudioLanguage = defaultAudioLanguage
)

fun ContentDetailsDto.toContentDetails(): ContentDetails = ContentDetails(
    duration = duration,
    dimension = dimension,
    definition = definition,
    caption = caption,
    licensedContent = licensedContent,
    contentRating = contentRating?.toContentRating(),
    projection = projection,
    regionRestriction = regionRestriction?.toRegionRestriction(),
    hasCustomThumbnail = hasCustomThumbnail
)


fun ContentRatingDto.toContentRating() = ContentRating(
    acbRating = acbRating,
    agcomRating = agcomRating,
    anatelRating = anatelRating,
    bbfcRating = bbfcRating,
    bfvcRating = bfvcRating,
    bmukkRating = bmukkRating,
    catvRating = catvRating,
    catvfrRating = catvfrRating,
    cbfcRating = cbfcRating,
    cccRating = cccRating,
    cceRating = cceRating,
    chfilmRating = chfilmRating,
    chvrsRating = chvrsRating,
    cicfRating = cicfRating,
    cnaRating = cnaRating,
    cncRating = cncRating,
    csaRating = csaRating,
    cscfRating = cscfRating,
    czfilmRating = czfilmRating,
    djctqRating = djctqRating,
    djctqRatingReasons = djctqRatingReasons,
    ecbmctRating = ecbmctRating,
    eefilmRating = eefilmRating,
    egfilmRating = egfilmRating,
    eirinRating = eirinRating,
    fcbmRating = fcbmRating,
    fcoRating = fcoRating,
    fmocRating = fmocRating,
    fpbRating = fpbRating,
    fpbRatingReasons = fpbRatingReasons,
    fskRating = fskRating,
    grfilmRating = grfilmRating,
    icaaRating = icaaRating,
    ifcoRating = ifcoRating,
    ilfilmRating = ilfilmRating,
    incaaRating = incaaRating,
    kfcbRating = kfcbRating,
    kijkwijzerRating = kijkwijzerRating,
    kmrbRating = kmrbRating,
    lsfRating = lsfRating,
    mccaaRating = mccaaRating,
    mccypRating = mccypRating,
    mcstRating = mcstRating,
    mdaRating = mdaRating,
    medietilsynetRating = medietilsynetRating,
    mekuRating = mekuRating,
    mibacRating = mibacRating,
    mocRating = mocRating,
    moctwRating = moctwRating,
    mpaaRating = mpaaRating,
    mpaatRating = mpaatRating,
    mtrcbRating = mtrcbRating,
    nbcRating = nbcRating,
    nbcplRating = nbcplRating,
    nfrcRating = nfrcRating,
    nfvcbRating = nfvcbRating,
    nkclvRating = nkclvRating,
    oflcRating = oflcRating,
    pefilmRating = pefilmRating,
    rcnofRating = rcnofRating,
    resorteviolenciaRating = resorteviolenciaRating,
    rtcRating = rtcRating,
    rteRating = rteRating,
    russiaRating = russiaRating,
    skfilmRating = skfilmRating,
    smaisRating = smaisRating,
    smsaRating = smsaRating,
    tvpgRating = tvpgRating,
    ytRating = ytRating
)

fun RegionRestrictionDto.toRegionRestriction(): RegionRestriction = RegionRestriction(
    allowed = allowed,
    blocked = blocked
)


//
//fun ThumbnailsDTO.toThumbnails() = Thumbnails(
//    default = default.toThumbnail(),
//    medium = medium.toThumbnail(),
//    high = high.toThumbnail(),
//    standard = standard?.toThumbnail(),
//    maxres = maxres?.toThumbnail()
//)

fun ThumbnailDTO.toThumbnail() = Thumbnail(
    url = url,
    width = width,
    height = height
)

fun LocalizedDTO.toLocolized() = Localized(
    title = title,
    description = description
)

fun PageInfoDTO.toPageInfo() = PageInfo(
    totalResults = totalResults,
    resultsPerPage = resultsPerPage
)

