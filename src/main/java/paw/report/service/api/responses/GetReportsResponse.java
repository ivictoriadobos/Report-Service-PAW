package paw.report.service.api.responses;

import paw.report.service.api.data.ReportData;
import paw.report.service.domain.model.Report;

import java.util.List;
import java.util.Map;

public class GetReportsResponse {

    private List<ReportData> reports;

    public GetReportsResponse(List<ReportData> reports) {
        this.reports = reports;
    }

    public List<ReportData> getReports() {
        return reports;
    }
}
