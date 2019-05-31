package org.wayne.bookmanagermaster.biz;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wayne.bookmanagermaster.model.Ticket;
import org.wayne.bookmanagermaster.model.User;
import org.wayne.bookmanagermaster.model.exceptions.LoginRegisterException;
import org.wayne.bookmanagermaster.service.TicketService;
import org.wayne.bookmanagermaster.service.UserService;
import org.wayne.bookmanagermaster.utils.ConcurrentUtils;
import org.wayne.bookmanagermaster.utils.MD5;
import org.wayne.bookmanagermaster.utils.TicketUtils;

import java.time.LocalDateTime;

@Service
public class LoginBiz {

    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;

    /**
     * 登录逻辑：先检查邮箱和密码，再更新t票
     *
     * @return 返回最新t票
     * @throws Exception 登录注册错误
     */

    public String login(String email, String password) throws Exception {

        User user = userService.getUser(email);


        // 判断用户是否存在
        if (user == null) {
            throw new LoginRegisterException("此用户不存在！");
        }
        if (!StringUtils.equals(MD5.next(password), user.getUserPassword())) {
            throw new LoginRegisterException("密码错误！");
        }

        // 检查ticket
        Ticket t = ticketService.getTicket(user.getUserId());

        // 如果没有t票，就生成一个
        if (t == null) {
            t = TicketUtils.next(user.getUserId());
            ticketService.addTicket(t);
            return t.getTicketTicket();
        }

        // 如果t票过期，那么将它删除
        if (t.getTicketExpiredAt().isBefore(LocalDateTime.now())) {
            ticketService.deleteTicket(t.getTicketId());
        }

        // 更新t票
        t = TicketUtils.next(user.getUserId());
        ticketService.addTicket(t);

        // 将当前用户id与t票id绑定
        ConcurrentUtils.setHost(user);

        return t.getTicketTicket();
    }

    /**
     * 当用户退出登录时，把相关用户的t票删除即可
     *
     * @param t
     */
    public void logout(String t) {
        ticketService.deleteTicket(t);
    }

    /**
     * 注册一个用户时，返回相应的t票
     *
     * @return 返回当前的用户t票
     */
    public String register(User user) throws Exception {

        if (userService.getUser(user.getUserEmail()) != null) {
            throw new LoginRegisterException("用户已经存在！");
        }

        String plain = user.getUserPassword();
        String md5 = MD5.next(plain);

        user.setUserPassword(md5);
        userService.addUser(user);

        // 添加用户t票
        Ticket t = TicketUtils.next(user.getUserId());
        ticketService.addTicket(t);

        // 绑定当前用户
        ConcurrentUtils.setHost(user);

        return t.getTicketTicket();
    }
}
