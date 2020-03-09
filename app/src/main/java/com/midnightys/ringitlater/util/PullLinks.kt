package com.midnightys.ringitlater.util

import java.util.regex.Pattern

/**
 * Created by Kort on 2020/3/8.
 */
fun pullLinks(string: String): String {
    val regex =
        "\\(?\\b(http[s]://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]"
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(string)
    var urlString = ""
    while (matcher.find()) {
        urlString = matcher.group()
        if (urlString.startsWith("(") && urlString.endsWith(")")) {
            urlString = urlString.substring(1, urlString.length - 1)
        }
    }
    return urlString
}