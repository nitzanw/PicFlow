package com.nitzanwerber.picflow.dataModel.dto


data class FlickrPhotosSearchResponse(
    var photos: FlickerPhotos, var stat: String)

data class FlickerPhotos(
    var page: Int = 0,
    var pages: String = "",
    var perpage: Int = 0,
    var total: String = "",
    var photo: MutableList<FlickrPrePhoto> = mutableListOf())
