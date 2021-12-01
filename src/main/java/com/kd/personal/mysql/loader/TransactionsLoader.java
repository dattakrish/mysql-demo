package com.kd.personal.mysql.loader;

import com.kd.personal.mysql.entity.Transaction;
import com.kd.personal.mysql.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
//@Service
public class TransactionsLoader implements CommandLineRunner {

  @Autowired
  private TransactionRepository transactionRepository;

  @Override
  public void run(String... args) throws Exception {

    System.out.println("Loading entries...");

    String[] firstnames = { "Tom", "Max", "Anna", "Hanna", "Mike", "Duke", "Fred",
       "Tim", "Paul", "Luke", "Tobias", "Timi", "Michelle", "Thomas", "Andrew" };
    String[] lastnames = { "Smith", "Taylor", "Williams", "Hammer", "Lewis", "Jones",
        "Evans", "Harris", "Mayer", "Schmid" };

    LocalDateTime initDate = LocalDateTime.of(1990, 12, 12, 12, 12);

//    for (int i = 0; i < 10000; i++) {
      Transaction p = new Transaction();
      p.setName(firstnames[ThreadLocalRandom.current().nextInt(0, firstnames.length)]);
//      p.setOpeningDate(new Date());
      p.setClosingDate("2021-01-01");//yyyy-MM-dd
      transactionRepository.save(p);
//    }

    System.out.println("...Finished loading 10.000 entities");
  }

}