package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 通用Controller
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @Autowired
    AliOssUtil aliOssUtil;

    /**
     * 图片上传至OSS
     * @param file
     * @return
     */
    @ApiOperation("图片上传至OSS")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        log.info("图片上传至OSS...");

        String originalFilename = file.getOriginalFilename();
        String endName = originalFilename.substring(originalFilename.indexOf("."));
        String objName = UUID.randomUUID() + endName;

        try {
            String uploadName = aliOssUtil.upload(file.getBytes(), objName);
            return Result.success(uploadName);
        } catch (IOException e) {
            log.info("上传图片失败：", e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }

}
