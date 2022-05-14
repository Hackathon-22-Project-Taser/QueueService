package de.unistuttgart.hackathon.taser.QueueService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueueServiceTest {

    private final QueueService queueService = new QueueService();

    @Test
    public void createQueue(){
        queueService.createQueue("38 0.008");
        assertTrue(queueService.isIdentifierUsed("38 0.008"));
    }
}
