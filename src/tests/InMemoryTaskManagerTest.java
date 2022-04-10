package tests;

import manager.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;

public class InMemoryTaskManagerTest extends TaskManagerTest {

    @BeforeEach
    void testsManager() {
        manager = new InMemoryTaskManager();
    }
}