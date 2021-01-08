package tr.com.minicrm.productgroup.data.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IdGenerator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
  protected static HazelcastInstance hazelcastInstance;
  protected static IdGenerator idGenerator;

  @BeforeAll
  static void setup() throws Exception {
    prepareHazelcastInstance();
    prepareIdGenerator();
  }

  private static void prepareIdGenerator() {
    idGenerator = hazelcastInstance.getIdGenerator("newId");
  }

  @AfterAll
  static void tearDown() throws Exception {
    Hazelcast.shutdownAll();
  }

  private static void prepareHazelcastInstance() {
    hazelcastInstance = Hazelcast.newHazelcastInstance();
  }

}
