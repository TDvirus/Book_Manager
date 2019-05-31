package org.wayne.bookmanagermaster.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.wayne.bookmanagermaster.model.Ticket;

@Repository
@Mapper
public interface TicketDao {

    @Insert("insert into ticket (ticket_user_id, ticket_ticket, ticket_expired_at) values (#{ticketUserId},#{ticketTicket},#{ticketExpiredAt})")
    void addTicket(Ticket ticket);

    @Select("select ticket_id,ticket_user_id,ticket_ticket,ticket_expired_at from ticket where ticket_ticket = #{ticket}")
    /*@Results(id = "ticketMap", value = {
            @Result(column = "ticket_id", property = "ticketId", id = true),
            @Result(column = "ticket_user_id", property = "ticketUserId"),
            @Result(column = "ticket_ticket", property = "ticketTicket"),
            @Result(column = "ticket_expired_at", property = "ticketExpiredAt")
    })*/
    Ticket selectByTicket(String ticket);

    //@ResultMap("ticketMap")
    @Select("select ticket_id,ticket_user_id,ticket_ticket,ticket_expired_at from ticket where ticket_user_id = #{uid}")
    Ticket selectByUserId(int uid);

    @Delete("delete from ticket where ticket_ticket = #{ticketTicket}")
    void deleteTicket(String t);

    @Delete("delete from ticket where ticket_id = #{tid}")
    void deleteTicketById(int tid);
}
