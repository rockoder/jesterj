/*
 * Copyright 2016 Needham Software LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jesterj.ingest.scanners;

import static com.copyright.easiertest.EasierMocks.prepareMocks;
import static com.copyright.easiertest.EasierMocks.replay;
import static com.copyright.easiertest.EasierMocks.reset;
import static com.copyright.easiertest.EasierMocks.verify;
import static org.junit.Assert.assertEquals;

import org.jesterj.ingest.model.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.copyright.easiertest.Mock;
import com.copyright.easiertest.ObjectUnderTest;

/**
 * Tests the JDBC scanner.
 * 
 * @author dgoldenberg
 */
@Ignore("Pending resolution of Issue #27")
public class JdbcScannerImplTest {

  @ObjectUnderTest
  private JdbcScanner obj;

  @Mock
  private Document mockDocument;
  
  public JdbcScannerImplTest() {
    prepareMocks(this);
  }

  @Before
  public void setUp() {
    reset();
  }

  @After
  public void tearDown() {
    verify();
  }

  @Test
  public void testBuild() {
    replay();

    JdbcScanner.Builder builder = new JdbcScanner.Builder();
    
    builder
      .batchSize(100)
      .named("JDBC Scanner")
      .withAutoCommit(true)
      .withFetchSize(1000)
      .withJdbcDriver("com.myco.jdbc.Driver")
      .withJdbcPassword("password")
      .withJdbcUrl("jdbc:myco://localhost/employees")
      .withJdbcUser("user")
      .withQueryTimeout(3600)
      .withSqlStatement("select * from employees");
    
    JdbcScanner built = (JdbcScanner) builder.build();
    
    assertEquals("JDBC Scanner", built.getName());
    assertEquals(true, built.isAutoCommit());
    assertEquals(1000, built.getFetchSize());
    assertEquals("com.myco.jdbc.Driver", built.getJdbcDriver());
    assertEquals("password", built.getJdbcPassword());
    assertEquals("jdbc:myco://localhost/employees", built.getJdbcUrl());
    assertEquals("user", built.getJdbcUser());
    assertEquals(3600, built.getQueryTimeout());
    assertEquals("select * from employees", built.getSqlStatement());
  }

  @Test
  public void testScan() throws InterruptedException {
    // TODO
  }
}