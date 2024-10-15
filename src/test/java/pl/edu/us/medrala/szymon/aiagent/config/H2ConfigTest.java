package pl.edu.us.medrala.szymon.aiagent.config;

import org.h2.tools.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class H2ConfigTest {

    private Server h2Server;

    @BeforeEach
    public void setUp() throws SQLException {
        H2Config h2Config = new H2Config();
        h2Server = h2Config.h2Server();
    }

    @AfterEach
    public void tearDown() {
        h2Server.stop();
    }

    @Test
    public void testH2ServerNotNull() {
        assertNotNull(h2Server);
    }

    @Test
    public void testH2ServerIsStopped() {
        h2Server.stop();
        assertFalse(h2Server.isRunning(false));
    }

    @Test
    public void testH2ServerIsRunningAfterStop() throws SQLException {
        h2Server.stop();
        assertFalse(h2Server.isRunning(false));
        h2Server.start();
        assertTrue(h2Server.isRunning(false));
    }

    @Test
    public void testH2ServerPort() {
        assertEquals(9092, h2Server.getPort());
    }

    @Test
    public void testH2ServerState() {
        assertEquals("Not started", h2Server.getStatus());
    }

    @Test
    public void testH2ServerShutdown() {
        assertFalse(h2Server.isRunning(true));
    }

}
