package pl.sda.events.controller;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.events.model.CommentEntity;
import pl.sda.events.model.EventEntity;
import pl.sda.events.model.UserEntity;
import pl.sda.events.service.EventService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/search")
public class SearchController {
    private EventService eventService;

    //todo search by location and organiser

    @Autowired
    public SearchController(EventService eventService) {
        this.eventService = eventService;
    }

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ModelAndView search(ModelAndView modelAndView) {
//        EventEntity eventEntity = new EventEntity();
//        modelAndView.addObject("eventEntity", eventEntity);
//        modelAndView.setViewName("search");
//        return modelAndView;
//    }

//    @RequestMapping(value = "byLocation", method = RequestMethod.POST)
//    public ModelAndView searchByLoc(ModelAndView modelAndView, EventEntity eventEntity) {
//        modelAndView.addObject("eventEntity", eventEntity);
//        modelAndView.setViewName("found");
//        return modelAndView;
//    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showSearchLocation(ModelAndView modelAndView,
                                           @RequestParam(value = "search", required = false) String q) {
//        List<EventEntity> byLocation = eventService.findByLocation(q.toUpperCase());
//        modelAndView.addObject("searchedEvents", byLocation);
//        EventEntity eventEntity = new EventEntity();
//        modelAndView.addObject("eventEntity", eventEntity);
//        modelAndView.setViewName("found");
        modelAndView.setViewName("search");
        return modelAndView;
    }


//    @RequestMapping(value = "/byOrganiser", method = RequestMethod.POST)
//    public ModelAndView searchByOrg(ModelAndView modelAndView) {
//        modelAndView.addObject("event");
//        modelAndView.setViewName("search");
//        return modelAndView;
//    }
}
