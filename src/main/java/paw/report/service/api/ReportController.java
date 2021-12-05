package paw.report.service.api;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import paw.report.service.api.data.ReportData;
import paw.report.service.api.requests.ReportListingRequest;
import paw.report.service.domain.exception.InvalidReportException;
import paw.report.service.domain.model.Report;
import paw.report.service.domain.model.ReportReason;
import paw.report.service.domain.service.IReportService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReportController {

    @Autowired
    private IReportService reportService;


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Record created successfully"),
            @ApiResponse(responseCode = "400", description = "One or more fields are not valid"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{listingId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportData> reportListing(@PathVariable int listingId , @RequestBody ReportListingRequest request)
    {
        try {

            Report toBeCreated = new Report(null, listingId, ReportReason.valueOf(request.getReason()), null, request.getDescription() );

            ReportData response = new ReportData(reportService.reportListing(toBeCreated));

            return new ResponseEntity<ReportData>(response, HttpStatus.CREATED);
        }

        catch (InvalidReportException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid reason set");
        }

        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    @GetMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetReportsResponse> getAllReports()
    {
        try{

            List<ReportData> toBeReturned = reportService.getAllReports().stream().map(ReportData::new).collect(Collectors.toList());
            return new ResponseEntity<GetReportsResponse>(new GetReportsResponse(toBeReturned), HttpStatus.OK);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server erorr");
        }
    }

}
