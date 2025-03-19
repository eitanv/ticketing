package com.eitan.ticketing.controllers;

import com.eitan.ticketing.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    TicketService ticketService;

    /**
     * @param tickets input array of tikcets, for example: ["ID1:5:1001","ID6:5:1002","ID2:4:1007"]
     * @return String of top priorities IDs, for example: "ID1,ID6,ID2"
     */
    @PostMapping("/top")
    public ResponseEntity<String> getTopPriorities(@RequestBody String[] tickets) {
        String topPriorities = ticketService.getTopPriorities(tickets);
        return new ResponseEntity<>(topPriorities, HttpStatus.OK);
    }

}
