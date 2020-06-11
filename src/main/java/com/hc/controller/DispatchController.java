package com.hc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DispatchController {
    @GetMapping("/file")
    public String file() {
        return "file";
    }
    @GetMapping("/multifile1")
    public String multifile1() {
        return "multifile1";
    }

    @GetMapping("/multifile2")
    public String multifile2() {
        return "multifile2";
    }

    @GetMapping("/download")
    public String download() {
        return "download";
    }
}
