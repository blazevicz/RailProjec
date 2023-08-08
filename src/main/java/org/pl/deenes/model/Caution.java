package org.pl.deenes.model;


import lombok.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Caution {


    private final Integer cautionId;
    private final Integer lineNumber;
    private final Double startFrom;
    private final Double endOn;
    private final String reason;
    private final String trackNumber;
    private final String leftTrack;
    private final String rightTrack;
    private final String comments;

}
