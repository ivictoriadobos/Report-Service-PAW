package paw.report.service.infrastructure.entity;

import paw.report.service.domain.model.Report;
import paw.report.service.domain.model.ReportReason;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReportEntityMapper {

    public static List<Report> toDomainModel(List<ReportEntity> reportsEntity)
    {
        List<Report> domainReports = new ArrayList<>();
        reportsEntity.forEach(reportEntity -> domainReports.add(toDomainModel(reportEntity)));

        return domainReports;
    }

    public static Report toDomainModel(ReportEntity reportEntity)
    {
        return new Report(reportEntity.id.toString(), reportEntity.getListingId(), ReportReason.valueOf(reportEntity.getReason()), reportEntity.getTimestamp(), reportEntity.getDescription());
    }

    public static ReportEntity fromDomainModel(Report report)
    {
        ReportEntity result = new ReportEntity();
        result.setListingId(report.getListingId());
        result.setReason(report.getReason().name());
        result.setTimestamp(report.getTimestamp());
        result.setDescription(report.getDescription());
        return result;
    }

    public static List<ReportEntity> fromDomainModel(List<Report> reports)
    {
            List<ReportEntity> enityReports = new ArrayList<>();
            reports.forEach(report -> enityReports.add(fromDomainModel(report)));

            return enityReports;
    }
}
