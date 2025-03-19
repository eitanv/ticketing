package com.eitan.ticketing.model;

import lombok.Data;

@Data
public class Ticket {
    String ticketId;
    Integer priority;
    int timeStamp;

    public Ticket(String input) {
        String[] parts = input.split(":");
        this.ticketId = parts[0];
        this.priority = Integer.parseInt(parts[1]);
        this.timeStamp = Integer.parseInt(parts[2]);
    }

    boolean isHigherPriorityThan(Ticket other) {
        if (this.priority > other.priority) {
            return true;
        } else if (this.priority == other.priority) {
            return this.timeStamp < other.timeStamp;
        }
        return false;
    }
    public boolean isBefore(Ticket other) {
        return this.timeStamp < other.timeStamp;
    }
    public Ticket firstTicket(Ticket first, Ticket second) {
        if (first.isBefore(second)) {
            return first;
        } else {
            return second;
        }
    }

}
