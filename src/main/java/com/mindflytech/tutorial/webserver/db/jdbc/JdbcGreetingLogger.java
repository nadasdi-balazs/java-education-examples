package com.mindflytech.tutorial.webserver.db.jdbc;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class JdbcGreetingLogger {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void logGreeting(String greeting) {
        String sql = " insert into greetingschema.greeting_log (greeting) " +
                     " values ('" + greeting + "')";
        log.info("-- will log greeting, sql is: '" + sql + "'");

        int rows = jdbcTemplate.update(sql);
        if (rows > 0) {
            log.info("-- rows inserted: " + rows);
        }
    }
}
