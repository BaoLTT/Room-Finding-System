package com.roomfindingsystem.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReportProcessedDto {
    private LocalDate solve;
}
