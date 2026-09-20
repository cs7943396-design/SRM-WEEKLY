import java.util.Arrays;

final class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(
            int index, String newId) {

        String[] newBookIds =
            Arrays.copyOf(bookIds, bookIds.length);

        newBookIds[index] = newId;

        return new LoanReceipt(memberId, newBookIds);
    }
}