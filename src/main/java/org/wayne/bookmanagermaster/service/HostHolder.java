package org.wayne.bookmanagermaster.service;

import org.springframework.stereotype.Service;
import org.wayne.bookmanagermaster.model.User;
import org.wayne.bookmanagermaster.utils.ConcurrentUtils;

@Service
public class HostHolder {

    public User getUser() {
        return ConcurrentUtils.getHost();
    }

    public void setUser(User user) {
        ConcurrentUtils.setHost(user);
    }

}
