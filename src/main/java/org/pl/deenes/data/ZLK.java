package org.pl.deenes.data;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "zlk")
@EqualsAndHashCode
@DynamicUpdate
public class ZLK {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Integer zlkRegionNumber;
    private String actualWOS;
    private String actualWOSlink;
}
