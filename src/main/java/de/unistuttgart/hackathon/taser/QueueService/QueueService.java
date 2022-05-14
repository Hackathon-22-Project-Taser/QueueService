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

    /**
     * Stores a given vote in the specified queue
     * @param identifier of the queue, to store the vote
     * @param vote to be stored in the queue
     */
    public void storeVote(final String identifier, final Vote vote) {
        final Queue<Map<LocalDateTime, Vote>> queue = this.findQueue(identifier);
        Map<LocalDateTime, Vote> map = new HashMap<>();
        map.put(LocalDateTime.now(), vote);
        queue.add(map);
    }

    /**
     * Returns the queue of the given id
     * @param identifier of the queue
     * @return queue with the specified queue
     */
    private Queue<Map<LocalDateTime, Vote>> findQueue(final String identifier) {
        return this.availableQueues.get(identifier);
    }

    /**
     * Clears a queue with the given identifier
     * @param identifier of the queue to be cleared
     */
    public void flushQueue(final String identifier) {
        final Queue<Map<LocalDateTime, Vote>> queue = this.findQueue(identifier);
        queue.clear();
    }
}
