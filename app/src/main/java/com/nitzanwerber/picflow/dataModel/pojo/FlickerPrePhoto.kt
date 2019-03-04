package com.nitzanwerber.picflow.dataModel.pojo

data class FlickerPrePhoto(
    val id: String? = null,
    val owner: String? = "",
    val secret: String? = "",
    val server: String? = "",
    val farm: String? = "",
    val title: String? = "",
    val ispublic: Int = 0,
    val isfriend: Int = 0,
    val isfamily: Int = 0,
    val url_o: String? = ""
)
