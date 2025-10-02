package com.poly.lab4.entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Staff {
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String id;

    @NotBlank(message = "Họ tên không được để trống")
    private String fullname;

    @Default
    private String photo = "photo.jpg";

    @NotNull(message = "Giới tính không được để trống")
    private Boolean gender;

    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh không hợp lệ")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @NotNull(message = "Lương không được để trống")
    @Min(value = 1000, message = "Lương tối thiểu là 1000")
    private Double salary;

    @Default
    private Integer level = 0;
}
