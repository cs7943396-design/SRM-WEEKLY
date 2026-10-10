import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

abstract class Room {
    protected String roomNumber;

    Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public abstract double calculatePrice(int days);
}

class StandardRoom extends Room {
    StandardRoom(String number) {
        super(number);
    }

    public double calculatePrice(int days) {
        return days * 100;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(String number) {
        super(number);
    }

    public double calculatePrice(int days) {
        return days * 200;
    }
}

class Suite extends Room {
    Suite(String number) {
        super(number);
    }

    public double calculatePrice(int days) {
        return days * 400;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Reservation {
    Customer customer;
    Room room;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate cancellationDeadline;
    boolean active = true;

    Reservation(Customer customer, Room room,
                LocalDate startDate, LocalDate endDate,
                LocalDate cancellationDeadline) {
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancellationDeadline = cancellationDeadline;
    }

    public boolean overlaps(LocalDate start, LocalDate end) {
        return active
                && startDate.isBefore(end)
                && start.isBefore(endDate);
    }

    public void cancel(LocalDate today) {
        if (!active) {
            System.out.println("Reservation already cancelled.");
        } else if (today.isAfter(cancellationDeadline)) {
            System.out.println("Cancellation deadline has passed.");
        } else {
            active = false;

            System.out.println("Reservation for "
                    + customer.name + ", "
                    + room.getClass().getSimpleName() + " "
                    + room.getRoomNumber()
                    + " cancelled successfully.");
        }
    }
}

class Hotel {
    private List<Reservation> reservations = new ArrayList<>();

    public boolean isAvailable(Room room,
                               LocalDate start, LocalDate end) {
        for (Reservation r : reservations) {
            if (r.room == room && r.overlaps(start, end)) {
                return false;
            }
        }

        return true;
    }

    public Reservation reserve(Customer customer, Room room,
                               LocalDate start, LocalDate end,
                               LocalDate cancellationDeadline) {
        if (!start.isBefore(end)) {
            System.out.println("Invalid reservation dates.");
            return null;
        }

        if (!isAvailable(room, start, end)) {
            System.out.println(room.getClass().getSimpleName()
                    + " " + room.getRoomNumber()
                    + " is not available from " + start
                    + " to " + end + ".");
            return null;
        }

        Reservation reservation = new Reservation(
                customer, room, start, end, cancellationDeadline);

        reservations.add(reservation);

        int days = (int) ChronoUnit.DAYS.between(start, end);

        System.out.println("Reservation confirmed for "
                + customer.name + ", "
                + room.getClass().getSimpleName() + " "
                + room.getRoomNumber() + " ("
                + start + " to " + end + ").");

        System.out.printf("Price: $%.2f.%n",
                room.calculatePrice(days));

        return reservation;
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        Room standard = new StandardRoom("101");
        Room deluxe = new DeluxeRoom("201");

        LocalDate start1 = LocalDate.of(2026, 1, 1);
        LocalDate end1 = LocalDate.of(2026, 1, 5);

        System.out.println("Standard Room 101 is available from "
                + start1 + " to " + end1 + ".");

        Reservation r1 = hotel.reserve(
                a, standard, start1, end1,
                LocalDate.of(2025, 12, 31));

        hotel.reserve(
                b, standard,
                LocalDate.of(2026, 1, 3),
                LocalDate.of(2026, 1, 7),
                LocalDate.of(2026, 1, 1));

        if (r1 != null) {
            r1.cancel(LocalDate.of(2025, 12, 30));
        }

        hotel.reserve(
                c, deluxe,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 12),
                LocalDate.of(2026, 2, 9));
    }
}