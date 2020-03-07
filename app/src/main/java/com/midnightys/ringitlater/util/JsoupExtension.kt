package com.midnightys.ringitlater.util

import org.jsoup.nodes.Document

/**
 * Created by Kort on 2020/3/7.
 */

val Document.title get() = getOgProperty(OgProperty.Title)
val Document.description get() = getOgProperty(OgProperty.Description)
val Document.image get() = getOgProperty(OgProperty.Image)
val Document.icon get() = head().select("link[href~=.*\\.ico]").first().attr("herf")
val Document.siteName get() = getOgProperty(OgProperty.SiteName)
val Document.url get() = getOgProperty(OgProperty.Url)

fun Document.getOgProperty(property: OgProperty) = selectFirst(property.cssQuery).attr("content")

enum class OgProperty(private val text: String) {
    Title("title"),
    Description("description"),
    Image("image"),
    SiteName("site_name"),
    Url("url")
    ;

    val cssQuery get() = "meta[property=og:${text}]"
}