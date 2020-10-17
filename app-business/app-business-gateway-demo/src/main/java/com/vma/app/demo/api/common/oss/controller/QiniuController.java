package com.vma.app.demo.api.common.oss.controller;

import com.vma.app.demo.api.common.oss.domain.resp.CkeditorResp;
import com.vma.assist.define.Assist;
import com.vma.assist.wraps.FileWrap;
import com.vma.assist.wraps.UrlWrap;
import com.vma.authorization.annotion.SkipVmaAuthorization;
import com.vma.resource.upload.qiniu.QiniuClient;
import com.vma.resource.upload.qiniu.domain.bo.QiniuTokenBO;
import com.vma.resource.upload.qiniu.domain.entity.VmaPutRet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

/**
 * 七牛控制器
 */
@RestController
@RequestMapping("/demo/common/v1.0/qiniu_oss")
@Api(value = "QiniuController", tags = {"七牛控制器"})
@Slf4j
public class QiniuController {

    @Autowired
    private QiniuClient qiniuClient;

    @Value("${vma.resource.upload.templateUpload:}")
    private String templateUpload = "";

    /**
     * 七牛token
     *
     * @return
     */
    @ApiOperation(value = "获取七牛token", notes = "获取七牛token")
    @GetMapping("/token")
    @SkipVmaAuthorization
    public QiniuTokenBO getQiNiuToken() {
        return qiniuClient.getToken();
    }


    /**
     * ckeditor4.3图片上传
     *
     * @param multipartRequest
     * @param response
     * @param callback
     * @param backUrl
     * @throws IOException
     */
    @ApiOperation(value = "CKEditor4.3图片上传")
    @PostMapping(value = "editor")
    @SkipVmaAuthorization
    public void uploadForCKEditor(MultipartHttpServletRequest multipartRequest,
                                  HttpServletResponse response,
                                  @RequestParam("CKEditorFuncNum") String callback,
                                  @RequestParam(value = "backUrl", required = false) String backUrl) throws IOException {
        String imgRealPath = this.upload(multipartRequest.getFile(multipartRequest.getFileNames().next()));
        PrintWriter out = response.getWriter();
        if (StringUtils.isBlank(backUrl)) {
            response.setContentType("text/html; charset=utf-8");
            out.println("<script TYPE=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imgRealPath + "','')");
            out.println("</script>");
        } else {
            response.sendRedirect(UrlWrap.compat(backUrl, "imgUrl=" + imgRealPath + "&callback=" + callback));
        }
    }


    /**
     * ckeditor4.4图片上传
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "CKEditor4.4图片上传")
    @PostMapping(value = "editor4.4")
    @SkipVmaAuthorization
    public CkeditorResp uploadForCKEditor4(@RequestParam("upload") MultipartFile multipartFile) throws IOException {
        String url = this.upload(multipartFile);
        CkeditorResp ckeditorBO = new CkeditorResp();
        ckeditorBO.setUploaded(1);
        ckeditorBO.setFileName(url);
        ckeditorBO.setUrl(url);
        return ckeditorBO;
    }

    /**
     * 文件上传
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "upload")
    @ApiImplicitParams({@ApiImplicitParam(name = "multipartFile", required = true, dataType = "MultipartFile", allowMultiple = true)})
    @PostMapping("/upload")
    @SkipVmaAuthorization
    public String upload(@RequestParam(value = "multipartFile") MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty() || StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            Assist.threw("请先选择文件！");
        }

        Assist.threw(StringUtils.isBlank(templateUpload), "请设置服务器临时文件上传目录");
        File dest = FileWrap.newFile(templateUpload + UUID.randomUUID() + multipartFile.getOriginalFilename());
        multipartFile.transferTo(dest);
        VmaPutRet putRet = qiniuClient.resolveResponseToVmaPutRet(qiniuClient.upload(dest.getAbsolutePath()));
        FileWrap.deleteFile(dest.getAbsolutePath());
        return qiniuClient.getQiniuConfig().getDomainUrl() + "/" + putRet.getKey();
    }
}
