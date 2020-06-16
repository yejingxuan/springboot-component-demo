package com.yjx.pg.controller;


import com.yjx.pg.base.PageDto;
import com.yjx.pg.model.pojo.Profile;
import com.yjx.pg.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping(value = "/get")
    public PageDto<Profile> getProfile(Integer pageNo, Integer PageSize) {
        PageDto<Profile> page = new PageDto<>();
        page.setPageNo(pageNo);
        page.setPageSize(PageSize);

        PageDto<Profile> res = profileService.getProfilePage(page);

        return res;
    }

}
