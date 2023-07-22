package uz.pdp.backend.entity;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id"})
@EqualsAndHashCode
public class BaseEntity<IDTYPE> {
    {
        createdAt=LocalDateTime.now();
        updatedAt=LocalDateTime.now();
    }
    IDTYPE id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    IDTYPE createdBy;
}
