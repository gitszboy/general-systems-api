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
    var agnComUploadsCode: Long? = null,

    @Column(name = "fle_filename", nullable = true)
    var agnComUploadFileName: String?,

    @Column(name = "fle_fileUri", nullable = true)
    var agnComUploadFileUri: String?,

    @Column(name = "fle_fileDownloadUri", nullable = true)
    var agnComUploadFileDownloadUri: String?,

    @Column(name = "fle_filesize", nullable = true)
    var agnComUploadFileSize: Long?,

    @Column(name = "fle_upload_date", nullable = true)
    var agnComUploadDate: Date? = null,
)