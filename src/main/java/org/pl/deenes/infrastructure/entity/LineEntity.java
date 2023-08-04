package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name = "line")
public class LineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id")
    private Integer lineId;
    @Column(name = "line_number", unique = true)
    private Integer lineNumber;
    @Column(name = "relation_from")
    private String relationFrom;
    @Column(name = "relation_to")
    private String relationTo;
    @OneToOne
    @JoinColumn(name = "line_details_id")
    private LineDetailsEntity lineEntry;

}
