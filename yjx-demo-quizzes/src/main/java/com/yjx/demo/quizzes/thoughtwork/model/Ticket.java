package com.yjx.demo.quizzes.thoughtwork.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Ticket implements Serializable {

    private static final long serialVersionUID = 8595186791004855407L;

    private String ticketNo;

    private String msg;
}
