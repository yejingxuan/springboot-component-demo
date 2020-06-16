package com.yjx.pg.service;

import com.yjx.pg.base.PageDto;
import com.yjx.pg.model.pojo.Profile;

public interface ProfileService {

    PageDto<Profile> getProfilePage(PageDto<Profile> page);
}
