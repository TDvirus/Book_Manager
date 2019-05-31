package org.wayne.bookmanagermaster.intercepter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.wayne.bookmanagermaster.model.Ticket;
import org.wayne.bookmanagermaster.model.User;
import org.wayne.bookmanagermaster.service.TicketService;
import org.wayne.bookmanagermaster.service.UserService;
import org.wayne.bookmanagermaster.utils.ConcurrentUtils;
import org.wayne.bookmanagermaster.utils.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
public class HostInfoInterceptor implements HandlerInterceptor {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    /**
     * 为注入host信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String t = CookieUtils.getCookie("t", request);
        if (!StringUtils.isEmpty(t)) {
            Ticket ticket = ticketService.getTicket(t);
            if (ticket != null && ticket.getTicketExpiredAt().isAfter(LocalDateTime.now())) {
                User host = userService.getUser(ticket.getTicketUserId());
                ConcurrentUtils.setHost(host);
            }
        }
        return true;
    }
}
