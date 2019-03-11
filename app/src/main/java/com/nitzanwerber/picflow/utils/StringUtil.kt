package com.nitzanwerber.picflow.utils

import com.nitzanwerber.picflow.dataModel.dto.FlickerPrePhoto

internal  fun createUrl(flickerPrePhoto: FlickerPrePhoto): String? {
    var farmId = flickerPrePhoto.farm
    var serverId = flickerPrePhoto.server
    var ID = flickerPrePhoto.id
    var secret = flickerPrePhoto.secret
    return "//http://farm$farmId.staticflickr.com/$serverId/$ID" + "_$secret.jpg"
}