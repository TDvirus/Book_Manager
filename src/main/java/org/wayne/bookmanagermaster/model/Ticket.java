package org.wayne.bookmanagermaster.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Ticket implements Serializable {

    private int ticketId;
    private int ticketUserId;
    private String ticketTicket;
    // 过期时间
    private LocalDateTime ticketExpiredAt;
}
