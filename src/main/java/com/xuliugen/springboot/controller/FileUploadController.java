package com.xuliugen.springboot.controller;

import com.xuliugen.springboot.common.ConstCommonString;
import com.xuliugen.springboot.common.LogUtil;
import org.slf4j.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

/**
 * Created by xuliugen on 2017/3/27.
 */
@Controller
public class FileUploadController {

    @RequestMapping(value = "/uploadByByte", method = RequestMethod.POST)
    public ModelAndView uploadByByte(@RequestPart("file") byte[] file) {
        MDC.put(ConstCommonString.TRACE_ID, LogUtil.getTraceId("FILEUPLOAD_CONTROLLER_UPLOADBYBYTE"));

        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }

    @RequestMapping(value = "/uploadByMultipart", method = RequestMethod.POST)
    public ModelAndView uploadByMultipart(@RequestPart("file") MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        MDC.put(ConstCommonString.TRACE_ID, LogUtil.getTraceId("FILEUPLOAD_CONTROLLER_UPLOADBYMULTIPART"));

        /**
         * 将上传的文件写入到文件系统中
         */
        multipartFile.transferTo(
                new File(request.getSession().getServletContext().getRealPath("/") + "/files/" + multipartFile.getOriginalFilename())
        );

        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }

    @RequestMapping(value = "/uploadByPart", method = RequestMethod.POST)
    public ModelAndView uploadByPart(@RequestPart("file") Part file, HttpServletRequest request) throws IOException {
        MDC.put(ConstCommonString.TRACE_ID, LogUtil.getTraceId("FILEUPLOAD_CONTROLLER_UPLOADBYPART"));

        /**
         * 将上传的文件写入到文件系统中,和MultipartFile.getOriginalFilename()的效果一样
         */
        file.write(request.getSession().getServletContext().getRealPath("/") + "/files/" + file.getSubmittedFileName());

        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }
}
