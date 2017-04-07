package com.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class Controller {


    @GetMapping("/flight")
    public Flights getFlight() {

        Person person = new Person();
        person.setFirstName("Some name");
        person.setLastName("Some other name");
        PersonTicket personTicket = new PersonTicket();
        personTicket.setPassenger(person);
        personTicket.setPrice(200);
        Flights flight = new Flights();
        try {
            flight.setDeparts(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2017-04-21 08:34"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<PersonTicket> list = new ArrayList<>();
        list.add(personTicket);
        flight.setTickets(list);
        return flight;
    }


    @GetMapping("")
    public List<Flights> getFlightList() {

        Person person = new Person();
        Person person1 = new Person();

        person.setFirstName("Some name");
        person.setLastName("Some other name");
        PersonTicket personTicket = new PersonTicket();
        personTicket.setPassenger(person);
        personTicket.setPrice(200);
        Flights flight = new Flights();
        try {
            flight.setDeparts(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2017-04-21 08:34"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<PersonTicket> list = new ArrayList<>();
        list.add(personTicket);
        flight.setTickets(list);

        person1.setFirstName("Some other name");
        person1.setLastName(null);
        PersonTicket personTicket1 = new PersonTicket();
        personTicket1.setPassenger(person1);
        personTicket1.setPrice(400);
        Flights flight1 = new Flights();
        try {

            flight1.setDeparts(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2017-04-21 08:34"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<PersonTicket> list1 = new ArrayList<>();
        list1.add(personTicket1);
        flight1.setTickets(list1);

        return Arrays.asList(flight, flight1);
    }

    @PostMapping(value = "/tickets/total", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TicketAmount postFlight(@RequestBody TestTickets testTickets) {
        PersonTicket[] tickets = testTickets.getTickets();
        int sum = 0;
        for (PersonTicket tix : tickets) {
            int price = tix.getPrice();
            sum = sum + price;
        }
        TicketAmount amount = new TicketAmount();
        amount.setResult(sum);
        return amount;
    }

    public static class Flights {
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private Date departs;
        private List<PersonTicket> tickets;

        public List<PersonTicket> getTickets() {
            return tickets;
        }

        public void setTickets(List<PersonTicket> tickets) {
            this.tickets = tickets;
        }

        public Date getDeparts() {
            return departs;
        }

        public void setDeparts(Date departs) {
            this.departs = departs;
        }
    }

    public static class PersonTicket {
        private Person passenger;
        private int price;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public Person getPassenger() {
            return passenger;
        }

        public void setPassenger(Person passenger) {
            this.passenger = passenger;
        }
    }

    public static class Person {

        private String firstName;
        private String lastName;

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
    }

    //// FOr Sum
    public static class TestTickets {
        private PersonTicket[] tickets;

        public PersonTicket[] getTickets() {
            return tickets;
        }

        public void setTickets(PersonTicket[] tickets) {
            this.tickets = tickets;
        }
    }


    public static class TicketAmount {
        private int result;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }


    }

}