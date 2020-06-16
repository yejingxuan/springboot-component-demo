package com.yjx.pg.model.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "profile")
public class Profile {

    @Id
    @Column(name = "profile_id")
    private String profileId;

    @Column(name = "version")
    private Integer version;

}
