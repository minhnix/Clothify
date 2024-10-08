package com.clothify.utils;

import com.clothify.service.StoreService;
import com.clothify.service.impl.StoreServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class DatabaseInitializer implements CommandLineRunner {

  private final JdbcTemplate jdbcTemplate;
  private final StoreServiceImpl storeServiceImpl;

  @Autowired
  public DatabaseInitializer(JdbcTemplate jdbcTemplate, StoreServiceImpl storeServiceImpl) {
    this.jdbcTemplate = jdbcTemplate;
    this.storeServiceImpl = storeServiceImpl;
  }

  @Transactional
  @Override
  public void run(String... args) throws Exception {
//    log.info("Initializing database");
//    storeServiceImpl.createStore();
  }
}
