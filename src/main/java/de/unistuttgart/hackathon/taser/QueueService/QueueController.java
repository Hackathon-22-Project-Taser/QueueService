package de.unistuttgart.hackathon.taser.QueueService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Queue;

/**
 * Controller for the QueueService REST-API
 */
@RestController
public class QueueController {

    private QueueService queueService;
    private final Logger logger = LoggerFactory.getLogger(QueueController.class);

    /**
     * Initialize the QueueController
     */
    @PostConstruct
    public void init() {
        this.queueService = new QueueService();
    }

    /**
     * Creates a new queue
     * @param identifier of the queue to create
     * @requires no queue with given identifier exists
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/queue/create/{identifier}")
    public void createQueue(@PathVariable final String identifier) {
        if (this.queueService.isIdentifierUsed(identifier)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Queue with ID %s already exists!",
                    identifier));
        }
        logger.info("create queue with identifier (queueNumber):" + identifier);
        this.queueService.createQueue(identifier);
    }

    /**
     * Deletes a specific queue
     * @param identifier delete the specified queue
     * @requires a queue with given identifier to exist
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/queue/delete/{identifier}")
    public void deleteQueue(@PathVariable final String identifier) {
        if (!(this.queueService.isIdentifierUsed(identifier))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Queue with ID %s doesn't exist!",
                    identifier));
        }
        logger.info("delete queue with identifier (queueNumber):" + identifier);
        this.queueService.deleteQueue(identifier);
    }

    /**
     * Stores a vote of a student
     * @param vote whether a student is lost
     * @param identifier of the room
     * @requires queue with the given identifier to exist
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/queue/store/{identifier}")
    public void storeVote(@PathVariable final String identifier, final Boolean vote) {
        if (!(this.queueService.isIdentifierUsed(identifier))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Queue with ID %s doesn't exist!",
                    identifier));
        }
        logger.info("store vote in queue with identifier (queueNumber):" + identifier);
        this.queueService.storeVote(identifier, vote);
    }

    /**
     * Empties the queue
     * @param identifier of the room to flush
     * @requires queue with the given identifier to exist
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/queue/flush/{identifier}")
    public void flushQueue(@PathVariable final String identifier) {
        if (!(this.queueService.isIdentifierUsed(identifier))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Queue with ID %s doesn't exist!",
                    identifier));
        }
        logger.info("empty queue with identifier (queueNumber):" + identifier);
        this.queueService.flushQueue(identifier);
    }

    /**
     * Empties all queues
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/queues/flush")
    public void flushQueues() {
        this.queueService.flushQueues();
    }

    /**
     * Returns all queues
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/queue/getQueues")
    public Map<String, Queue<Map<LocalDateTime, Boolean>>> getQueues() {
        return queueService.getQueues();
    }
}
