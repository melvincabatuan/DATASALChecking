/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.narag.test;

import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author cobalt
 */
@RunWith(Suite.class)
@SuiteClasses({MyArrayListTest.class, MyLinkedListTest.class, MyStackTest.class, MyQueueTest.class, MyBstTest.class})
public class PracticalTestSuiteNarag {
    
  private static final Logger LOG = Logger.getLogger(PracticalTestSuiteNarag.class.getName());

  @BeforeClass
  public static void setupBeforeSuiteRuns() {
    LOG.info("START: Testing Narag's CP12 Practical...");
  }

  @AfterClass
  public static void tearDownAfterSuiteRuns() {
    LOG.info("END of testing...");
}
    
}
