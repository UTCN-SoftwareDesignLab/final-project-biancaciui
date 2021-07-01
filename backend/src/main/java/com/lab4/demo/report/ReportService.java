package com.lab4.demo.report;

import java.io.File;
import java.io.IOException;

public interface ReportService {

    File export();

    ReportType getType();

}
