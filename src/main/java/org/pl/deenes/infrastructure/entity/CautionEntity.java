package org.pl.deenes.infrastructure.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "caution")
public class CautionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "caution_id")
    private Integer cautionId;

    @Column(name = "line_number")
    private Integer lineNumber;

    @Column(name = "start_from")
    private Double startFrom;

    @Column(name = "end_on")
    private Double endOn;

    @Column(name = "reason")
    private String reason;

    @Column(name = "track_number")
    private String trackNumber;

    @Column(name = "left_track")
    private String leftTrack;

    @Column(name = "right_track")
    private String rightTrack;

    @Column(name = "comments")
    private String comments;
}
