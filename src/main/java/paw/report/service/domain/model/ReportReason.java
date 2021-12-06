package paw.report.service.domain.model;

import java.util.Arrays;
import java.util.Optional;

public enum ReportReason {
    UNKNOWN("UNKNOWN"),
    VIOLENCE("VIOLENCE"),
    SEXUAL("SEXUAL"),
    FRAUD("FRAUD"),
    ILLEGAL("ILLEGAL"),
    OTHER("OTHER");

    private String reasonValueString;

    ReportReason(String reasonValueString) {
        this.reasonValueString = reasonValueString;
    }

    public String getReasonValueString() {
        return reasonValueString;
    }

    public static ReportReason fromString(String reasonString) {
        Optional<ReportReason> reason = Arrays
                .stream(ReportReason.values())
                .filter(value -> value.name().equalsIgnoreCase(reasonString))
                .findFirst();

        return reason.orElse(UNKNOWN);
    }
}


