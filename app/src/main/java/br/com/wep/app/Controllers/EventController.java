package br.com.wep.app.Controllers;

import br.com.wep.app.model.Entities.Event;
import br.com.wep.app.model.Repos.EventRepo;
import br.com.wep.app.model.Repos.UserRepo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URL;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/event")
public class EventController {

    @Autowired
    private EventRepo repo;
    private UserRepo userRepo;

    @GetMapping
    public List<Event> getEvents(){
        List<Event> events = (List<Event>) repo.findAll();
        return events;
    }

    //Registrar evento
    @PostMapping
    public Event registerEvent(Event event){
        try {
            return repo.save(event);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    //Deletar evento
    @DeleteMapping(path = "/{event_id}")
    public boolean deleteEvent(@PathVariable(name = "event_id") int eventID,
                               @RequestHeader String Authentication){
        try {
            repo.deleteById(eventID);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //get event ID
    @GetMapping(path = "/{eventID}")
    public Event getEventById(@PathVariable(name = "eventID") int eventID){
        try {
            Event event = repo.findById(eventID).get();
//            Event eventFound = event.get();

            return event;

        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

}
