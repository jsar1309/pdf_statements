package com.example.pdf.controller;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ClientDate implements Serializable {

    private String year;
    private String month;

}
