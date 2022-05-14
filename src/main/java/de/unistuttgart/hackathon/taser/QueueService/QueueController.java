package de.unistuttgart.hackathon.taser.QueueService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @PostConstruct
    public void init(){
        queueService = new QueueService();
    }

    /**
     * Creates a new Queue
     * @requires no queue with given identifier exists
     */
    @PostMapping("/queue/create/{identifier}")
    public void createQueue(@PathVariable final String identifier) {
        logger.info("create Queue with identifier (queueNumber):" + identifier);
        if (queueService.isIdentifierUsed(identifier)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Queue with ID %s already exists!",
                    identifier));
        }
        queueService.createQueue(identifier);
    }

    /**
     * Stores a vote of a student
     * @requires queue with the given identifier to exist
     */
    @PostMapping("/queue/store/{identifier}")
    public void storeVote(@PathVariable final String identifier, @RequestBody final Boolean vote) {
        queueService.storeVote(identifier, vote);
    }

    /**
     * Empties the queue
     * @requires queue with the given identifier to exist
     */
    @PostMapping("/queue/flush/{identifier}")
    public void flushQueue(@PathVariable final String identifier){
        queueService.flushQueue(identifier);
    }

    /**
     * Empties the queue
     * @requires queue with the given identifier to exist
     */
    @GetMapping("/queue/getQueues")
    public Map<String, Queue<Map<LocalDateTime, Boolean>>> getQueues(){
        return queueService.getQueues();
    }
}
