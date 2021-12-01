package com.kd.personal.mysql.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDto implements Serializable {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String openingDateTo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String openingDateFrom;
}
