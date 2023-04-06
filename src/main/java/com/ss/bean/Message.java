package com.ss.bean;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Entity
@Table(name = "message")
@Component
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String msg;
    private Date date;
    private String flag;
}
