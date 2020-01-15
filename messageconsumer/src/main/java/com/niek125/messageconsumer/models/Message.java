package com.niek125.messageconsumer.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "message")
public class Message {
    @Id
    private String messageid;
    private String senderid;
    private String content;
    private String sendtime;
    private String projectid;
}
