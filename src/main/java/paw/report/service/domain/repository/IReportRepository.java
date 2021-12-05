package paw.report.service.domain.repository;

import paw.report.service.domain.model.Report;

import java.util.List;

public interface IReportRepository {

    public Report create(Report report);
    public List<Report> getAll();
}
