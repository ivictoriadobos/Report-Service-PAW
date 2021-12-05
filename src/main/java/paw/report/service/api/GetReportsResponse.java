package paw.report.service.api;

import paw.report.service.api.data.ReportData;
import paw.report.service.domain.model.Report;

import java.util.List;

public class GetReportsResponse {
    private List<ReportData> reports;

    public GetReportsResponse(List<ReportData> reports) {
        this.reports = reports;
    }
}
