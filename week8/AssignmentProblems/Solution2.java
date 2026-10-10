import java.util.*;

class Customer {
    private String name;

    Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

abstract class Seat {
    private String seatId;

    Seat(String seatId) {
        this.seatId = seatId;
    }

    public String getSeatId() {
        return seatId;
    }

    public abstract double getPrice();
}

class RegularSeat extends Seat {
    RegularSeat(String id) {
        super(id);
    }

    public double getPrice() {
        return 150;
    }
}

class PremiumSeat extends Seat {
    PremiumSeat(String id) {
        super(id);
    }

    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat extends Seat {
    ReclinerSeat(String id) {
        super(id);
    }

    public double getPrice() {
        return 400;
    }
}

class Show {
    private String name;
    private Set<String> bookedSeats = new HashSet<>();

    Show(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean areAvailable(List<Seat> seats) {
        Set<String> requested = new HashSet<>();

        for (Seat seat : seats) {
            if (bookedSeats.contains(seat.getSeatId())
                    || !requested.add(seat.getSeatId())) {
                return false;
            }
        }

        return true;
    }

    public void reserve(List<Seat> seats) {
        for (Seat seat : seats) {
            bookedSeats.add(seat.getSeatId());
        }
    }

    public void release(List<Seat> seats) {
        for (Seat seat : seats) {
            bookedSeats.remove(seat.getSeatId());
        }
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private boolean cancelled = false;
    private boolean showStarted = false;

    Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
    }

    public double getTotal() {
        double total = 0;

        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        return total;
    }

    public void cancel() {
        if (cancelled) {
            System.out.println("Booking is already cancelled.");
            return;
        }

        if (showStarted) {
            System.out.println("Cannot cancel after the show starts.");
            return;
        }

        show.release(seats);
        cancelled = true;

        System.out.println(customer.getName() + "'s booking cancelled.");

        System.out.print("Seats ");
        for (int i = 0; i < seats.size(); i++) {
            System.out.print(seats.get(i).getSeatId());

            if (i < seats.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println(" released.");
    }

    public void startShow() {
        showStarted = true;
    }
}

public class Solution2 {
    static Booking book(Customer customer, Show show, Seat... seats) {
        List<Seat> seatList = Arrays.asList(seats);

        if (seatList.isEmpty() || seatList.size() > 6) {
            System.out.println("Booking must contain 1 to 6 seats.");
            return null;
        }

        if (!show.areAvailable(seatList)) {
            System.out.println("One or more seats are already booked for this show.");
            return null;
        }

        show.reserve(seatList);

        Booking booking = new Booking(customer, show, seatList);

        System.out.print("Booking confirmed for "
                + customer.getName() + ": ");

        for (int i = 0; i < seatList.size(); i++) {
            System.out.print(seatList.get(i).getSeatId());

            if (i < seatList.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.printf(". Total: ₹%.2f.%n", booking.getTotal());

        return booking;
    }

    public static void main(String[] args) {
        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Show show = new Show("7 PM");

        Booking b1 = book(
                asha, show,
                new RegularSeat("A1"),
                new RegularSeat("A2"),
                new PremiumSeat("F5")
        );

        book(ravi, show, new RegularSeat("A2"));

        Booking b2 = book(ravi, show, new ReclinerSeat("R1"));

        if (b1 != null) {
            b1.cancel();
        }

        book(neha, show, new RegularSeat("A2"));
    }
}