package com.edmond.cam.service;

import com.edmond.cam.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private static Map<String, User> cache = new ConcurrentHashMap<>();

    public User getUser(String username) {
        return cache.get(username);
    }

    @PostConstruct
    private void init() {
        User edmond = new User(0, "eywang", "$2a$10$wT6DAVsIa0Tuz7wtaMQYbu.ssriu6Juqnf90/27FFNcfq/oIKer7u");
        User yinjia = new User(1, "yinjia", "$2a$10$tB1wIxJDjzSiAs/xo8Kqv.H1wjVlgIIid/Cq.ZAVD2.Oc3rSEpGKa");
        User parent = new User(2, "parent", "$2a$10$AhkVbkMh1T8MgpBjJ1W1/OEiFs.fyNYZKO06LN2Zy1Ym.7ZHX3PJK");
        User tstUsr = new User(3, "aaa", "$2a$10$xAvSWjsrrLnUDK48fpIMReTpR/YZThSmk1XFQfKZOrhnAr2z70Zvq");
        cache.put(edmond.getUsername(), edmond);
        cache.put(yinjia.getUsername(), yinjia);
        cache.put(parent.getUsername(), parent);
        cache.put(tstUsr.getUsername(), tstUsr);
    }
}
