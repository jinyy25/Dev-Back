package kr.vtw.kms.member.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class tb_log_vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logSeq;

    @Column(nullable = false)
    private String vendorSeq;

    @Column(nullable = false)
    private String vendorId;

    @Column(nullable = false)
    private String userSeq;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private int userIp;

    @Column(nullable = false)
    private String created;

    @Column(nullable = false)
    private String logType;

    @Column(nullable = false)
    private String logSearch;




}
