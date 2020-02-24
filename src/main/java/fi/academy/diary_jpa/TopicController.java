package fi.academy.diary_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:63342")
public class TopicController {

    @Autowired
    TopicRepository topRep;

    @GetMapping("")
    public List<Topic> allTopics() {
        List<Topic> topics = new ArrayList<>();
        Iterable<Topic> result = topRep.findAll();
        result.iterator().forEachRemaining(topics::add);
        return topics;
    }

    @PostMapping("")
    public void newTopic(@RequestBody Topic topic) {
        topic.setComplete(false);
        Date today = (Date) Calendar.getInstance().getTime();
        topic.setCreationdate(today);
        topRep.save(topic);
    }

}
