package org.wayne.bookmanagermaster.utils;

import org.wayne.bookmanagermaster.model.Ticket;

import java.time.LocalDateTime;

public class TicketUtils {

    public static Ticket next(int uid){

        Ticket ticket = new Ticket();

        // 设置随机字符串
        ticket.setTicketTicket(UuidUtils.next());
        ticket.setTicketUserId(uid);

        //设置过期时间
        LocalDateTime expiredTime = LocalDateTime.now();
        expiredTime = expiredTime.plusMonths(3);
        ticket.setTicketExpiredAt(expiredTime);

        return ticket;
    }
}
