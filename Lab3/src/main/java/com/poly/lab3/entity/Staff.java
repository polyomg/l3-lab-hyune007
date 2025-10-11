package com.poly.lab3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Staff {
    private String id;
    private String fullname;
    @Default
    private String photo ="photo.jpg";
    @Default
    private Boolean gender=true;
    @Default
    private Date birthdate = new Date();
    @Default
    private Double salary =1234.5678;
    @Default
    private Integer level = 0;
}
