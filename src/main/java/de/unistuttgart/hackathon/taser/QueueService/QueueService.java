package de.unistuttgart.hackathon.taser.QueueService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class QueueService {

    private final Map<String, Queue<Map<LocalDateTime, Vote>>> availableQueues;

    public QueueService(){
        this.availableQueues = new HashMap<>();
    }

    /**
     * Creates a Queue paired to the given identifier
     * @param identifier of the queue to create
     * @requires !(isIdentifierUsed)
     */
    public void createQueue(final String identifier) {
        if (this.isIdentifierUsed(identifier)){
            throw new IllegalArgumentException("");
        }
        availableQueues.put(identifier, new LinkedList<>());
    }

    /**
     * Returns whether an identifier is already used
     * @param identifier to check for
     * @return whether the identifier is already used
     */
    public boolean isIdentifierUsed(final String identifier){
        return availableQueues.containsKey(identifier);
    }
}
