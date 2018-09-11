package com.example.customerclient.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class CustomerEvent {
    private Customer customer;
    private Date creationDate;
    private String event;
}
