package com.luca.academyProj.listener;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.luca.academyProj.businesscomponent.model.FailedLoginAttemptEvent;

@Component
public class FailedLoginAttemptListener {

    @Autowired
    private CacheManager cacheManager;

    @EventListener
    public void handleFailedLoginAttempt(FailedLoginAttemptEvent event) {
        Cache cache = cacheManager.getCache("failedLoginAttempts");
        if (cache != null) {
            String username = event.getUsername();
            Integer failedAttempts = cache.get(username, Integer.class);

            if (failedAttempts == null) {
                failedAttempts = 1;
            } else {
                failedAttempts++;
            }

            if (failedAttempts >= 5) {
                cache.put(username + "_blocked", true);
                cache.put(username + "_unlockTime", System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1));
            } else {
                // Resetta i tentativi falliti solo se è passato abbastanza tempo dal blocco
                Long unlockTime = cache.get(username + "_unlockTime", Long.class);
                if (unlockTime != null && System.currentTimeMillis() >= unlockTime) {
                    cache.evict(username);
                    cache.evict(username + "_blocked");
                    cache.evict(username + "_unlockTime");
                }
            }
            cache.put(username, failedAttempts);
        }
    }
}