package com.lab4.demo.report;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.File;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(API_PATH)
@RequiredArgsConstructor
public class ReportController {

    private final ReportServiceFactory reportServiceFactory;

    @GetMapping(EXPORT_REPORT)
    public File export(@PathVariable ReportType type) {
        System.out.println("Export "+type);
        return reportServiceFactory.getReportService(type).export();
    }

}
