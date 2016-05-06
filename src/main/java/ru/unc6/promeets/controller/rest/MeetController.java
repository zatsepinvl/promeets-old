package ru.unc6.promeets.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.unc6.promeets.model.entity.*;
import ru.unc6.promeets.model.service.entity.BoardService;
import ru.unc6.promeets.model.service.entity.CardService;
import ru.unc6.promeets.model.service.entity.MeetService;

import java.util.List;

import org.apache.log4j.Logger;
import ru.unc6.promeets.model.service.entity.MeetInfoService;
import ru.unc6.promeets.model.service.entity.UserMeetService;

@RestController
@RequestMapping("/api/meets")
public class MeetController extends BaseRestController<Meet, Long> {

    private static final Logger log = Logger.getLogger(MeetController.class);

    private MeetService meetService;

    @Autowired
    private CardService cardService;

    @Autowired
    private UserMeetService userMeetService;

    @Autowired
    private BoardService boardService;
    
    @Autowired
    private MeetInfoService meetInfoService;

    @Autowired
    public MeetController(MeetService service) {
        super(service);
        this.meetService = service;
    }


    @RequestMapping(value = "/{id}/users", method = RequestMethod.GET)
    public List getUsers(@PathVariable("id") long id) {
        checkIsNotFoundById(id);
        return userMeetService.getUserMeetsByMeetId(id);
    }

    @RequestMapping(value = "/{id}/notes", method = RequestMethod.GET)
    public List getMeetNotes(@PathVariable("id") long id) {
        checkIsNotFoundById(id);
        return meetService.getMeetNotes(id);
    }

    @RequestMapping(value = "/{id}/tasks", method = RequestMethod.GET)
    public List getMeetTargets(@PathVariable("id") long id) {
        checkIsNotFoundById(id);
        return meetService.getMeetAims(id);
    }

    @RequestMapping(value = "/{id}/boards", method = RequestMethod.GET)
    public Board getBoard(@PathVariable("id") long meetId, @RequestParam("page") int page) {
        checkIsNotFoundById(meetId);
        return boardService.getBoardByMeetId(meetId, page);
    }

    @RequestMapping(value = "/{id}/cards", method = RequestMethod.GET)
    public List<Card> getCardsByMeetIdAndPage(@PathVariable("id") long meetId, @RequestParam("page") int page) {
        checkIsNotFoundById(meetId);
        return cardService.getCardsByMeetIdAndPage(meetId, page);
    }
    
    @RequestMapping(value = "/{id}/info", method = RequestMethod.GET)
    public List<MeetInfo> getInfoByMeetId(@PathVariable("id") long meetId) 
    {
        checkIsNotFoundById(meetId);
        return meetInfoService.getByMeetId(meetId);
    }
    
    @RequestMapping(value = "/{id}/info/online", method = RequestMethod.GET)
    public List<MeetInfo> getOnlineByMeetId(@PathVariable("id") long meetId) 
    {
        checkIsNotFoundById(meetId);
        return meetInfoService.getOnlineByMeetId(meetId);
    }

}