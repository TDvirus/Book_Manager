package org.wayne.bookmanagermaster.service;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wayne.bookmanagermaster.dao.TicketDao;
import org.wayne.bookmanagermaster.model.Ticket;

@Service
public class TicketService {

    @Autowired
    TicketDao ticketDao;

    public void addTicket(Ticket ticket) {
        ticketDao.addTicket(ticket);
    }

    public Ticket getTicket(String ticket) {
        return ticketDao.selectByTicket(ticket);
    }

    public Ticket getTicket(int uid) {
        return ticketDao.selectByUserId(uid);
    }

    public void deleteTicket(String t) {
        ticketDao.deleteTicket(t);
    }

    public void deleteTicket(int tid) {
        ticketDao.deleteTicketById(tid);
    }
}
