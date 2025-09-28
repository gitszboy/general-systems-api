package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "file_upload_details")
class FileDetailsModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fle_code")
    var fileUploadsCode: Long? = null,

    @Column(name = "fle_filename", nullable = true)
    var fileUploadFileName: String? = null,

    @Column(name = "fle_fileUri", nullable = true)
    var fileUploadFileUri: String? = null,

    @Column(name = "fle_fileDownloadUri", nullable = true)
    var fileUploadFileDownloadUri: String? = null,

    @Column(name = "fle_filesize", nullable = true)
    var fileUploadFileSize: Long? = null,

    @Column(name = "fle_upload_date", nullable = true)
    var fileUploadDate: Date? = null,
)