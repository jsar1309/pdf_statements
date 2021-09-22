package com.example.pdf.controller;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Client implements Serializable {

    private String id;
    private String contractId;
    private String documentId;
    private ClientDate date;
}
