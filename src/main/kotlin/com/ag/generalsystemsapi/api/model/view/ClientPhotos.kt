package com.ag.generalsystemsapi.api.model.view

class ClientPhotos (
    var imageType: String="",
    var imageName:String="",
    var imageBase64: String="",
    var s3ImageUrl:String?="",
    var s3FullName:String?=""
)