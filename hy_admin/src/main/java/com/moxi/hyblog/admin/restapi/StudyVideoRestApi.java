package com.moxi.hyblog.admin.restapi;


import com.moxi.hyblog.admin.annotion.AuthorityVerify.AuthorityVerify;
import com.moxi.hyblog.admin.annotion.AvoidRepeatableCommit.AvoidRepeatableCommit;
import com.moxi.hyblog.admin.annotion.OperationLogger.OperationLogger;
import com.moxi.hyblog.admin.global.SysConf;
import com.moxi.hyblog.utils.ResultUtil;
import com.moxi.hyblog.xo.service.ResourceSortService;
import com.moxi.hyblog.xo.service.StudyVideoService;
import com.moxi.hyblog.xo.utils.WebUtil;
import com.moxi.hyblog.xo.vo.StudyVideoVO;
import com.moxi.hyblog.base.exception.ThrowableUtils;
import com.moxi.hyblog.base.validator.group.Delete;
import com.moxi.hyblog.base.validator.group.GetList;
import com.moxi.hyblog.base.validator.group.Insert;
import com.moxi.hyblog.base.validator.group.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 视频表 RestApi
 * </p>
 *
 * @author xzx19950624@qq.com
 * @since 2020年1月10日22:44:35
 */
@RestController
@RequestMapping("/studyVideo")
@Api(value = "学习视频相关接口", tags = {"学习视频相关接口"})
@Slf4j
public class StudyVideoRestApi {

    @Autowired
    WebUtil webUtil;

    @Autowired
    StudyVideoService studyVideoService;
    @Autowired
    ResourceSortService resourceSortService;

    @AuthorityVerify
    @ApiOperation(value = "获取学习视频列表", notes = "获取学习视频列表", response = String.class)
    @PostMapping(value = "/getList")
    public String getList(@Validated({GetList.class}) @RequestBody StudyVideoVO studyVideoVO, BindingResult result) {

        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        return ResultUtil.result(SysConf.SUCCESS, studyVideoService.getPageList(studyVideoVO));
    }

    @AvoidRepeatableCommit
    @AuthorityVerify
    @OperationLogger(value = "增加学习视频")
    @ApiOperation(value = "增加学习视频", notes = "增加学习视频", response = String.class)
    @PostMapping("/add")
    public String add(@Validated({Insert.class}) @RequestBody StudyVideoVO studyVideoVO, BindingResult result) {

        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        return studyVideoService.addStudyVideo(studyVideoVO);
    }

    @AuthorityVerify
    @OperationLogger(value = "编辑学习视频")
    @ApiOperation(value = "编辑学习视频", notes = "编辑学习视频", response = String.class)
    @PostMapping("/edit")
    public String edit(@Validated({Update.class}) @RequestBody StudyVideoVO studyVideoVO, BindingResult result) {

        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        return studyVideoService.editStudyVideo(studyVideoVO);
    }

    @AuthorityVerify
    @OperationLogger(value = "删除学习视频")
    @ApiOperation(value = "删除学习视频", notes = "删除学习视频", response = String.class)
    @PostMapping("/deleteBatch")
    public String deleteBatch(@Validated({Delete.class}) @RequestBody List<StudyVideoVO> studyVideoVOList, BindingResult result) {

        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        return studyVideoService.deleteBatchStudyVideo(studyVideoVOList);
    }

}

