package org.wayne.bookmanagermaster.intercepter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.wayne.bookmanagermaster.model.Ticket;
import org.wayne.bookmanagermaster.service.TicketService;
import org.wayne.bookmanagermaster.utils.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    TicketService ticketService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 没有t票，去登录
        String t = CookieUtils.getCookie("t", request);
        if (StringUtils.isEmpty(t)){
            response.sendRedirect("/users/login");
            return false;
        }
        // 无效t票，去登录
        Ticket ticket = ticketService.getTicket(t);
        if (ticket == null){
            response.sendRedirect("/users/login");
            return false;
        }
        // 过期t票，去登录
        if (ticket.getTicketExpiredAt().isBefore(LocalDateTime.now())){
            response.sendRedirect("/users/login");
            return false;
        }

        return true;
    }
}
