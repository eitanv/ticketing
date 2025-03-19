package com.eitan.ticketing.services.implementation;

import com.eitan.ticketing.model.Ticket;
import com.eitan.ticketing.services.TicketService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTopPriorities(String[] input) {
        Integer maxPriority = 0;
        Map<Integer, List<Ticket>> priorityMap = new HashMap<>();
        for (String ticketString : input) {
            Ticket ticket = new Ticket(ticketString);
            if (ticket.getPriority() > maxPriority) {
                maxPriority = ticket.getPriority();
            }
            if (!priorityMap.containsKey(ticket.getPriority())) {
                priorityMap.put(ticket.getPriority(), new LinkedList<>());
            }
            List<Ticket> tickets = priorityMap.get(ticket.getPriority());
            int index = 0;
            while (index < tickets.size() && tickets.get(index).getTimeStamp() < ticket.getTimeStamp()) {
                index++;
            }
            tickets.add(index, ticket);        }
        StringBuilder result = new StringBuilder();
        int resultCounter = 0;
        for (int i = maxPriority; i >= 1; i--) {
            if (priorityMap.containsKey(i)) {
                List<Ticket> tickets = priorityMap.get(i);
                for (Ticket ticket : tickets) {
                    result.append(ticket.getTicketId());
                    resultCounter++;
                    if (resultCounter == 3) {
                        return result.toString();
                    }
                    result.append(",");
                }
            }
        }
        return "";
    }
}
