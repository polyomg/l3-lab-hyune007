package com.poly.lab6.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Accounts", schema = "webshop")
public class Account implements Serializable {
    @Id
    private String username;

    private String password;
    private String fullname;
    private String email;
    private String photo;
    private boolean activated;
    private boolean admin;

    @OneToMany(mappedBy = "account")
    private List<Order> orders;
}