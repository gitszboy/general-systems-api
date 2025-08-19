package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.Date
import javax.persistence.*

@Setter
@Getter
@Entity
@Table(name = "portal_notifications")
class PortalNotificationsModel  (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pn_code")
    var notificationCode: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pn_user_id")
    var notificationUser: UsersModel? = null,

    @Column(name = "pn_title", nullable = true)
    var notificationTitle: String? = null,

    @Column(name = "pn_body", nullable = true)
    var notificationBody: String? = null,

    @Column(name = "pn_date", nullable = true)
    var notificationDate: Date? = null,

    @Column(name = "pn_status", nullable = true)
    var notificationStatus: String? = null,
)