class LibraryMember {

    private String membershipPin;
    String branchCode;          // default
    protected double finesOwed;
    public String displayName;

    LibraryMember(String membershipPin, String branchCode,
                  double finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

class AccessChecker {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        if (fieldModifier.equals("private")) {
            if (accessorContext.equals("SAME_CLASS"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {
            "private", "default", "protected", "public"
        };

        int[] allowed = new int[4];
        int[] denied = new int[4];

        for (int i = 0; i < attempts.length; i++) {

            int index = -1;

            if (attempts[i][0].equals("private"))
                index = 0;
            else if (attempts[i][0].equals("default"))
                index = 1;
            else if (attempts[i][0].equals("protected"))
                index = 2;
            else if (attempts[i][0].equals("public"))
                index = 3;

            String result =
                classifyAccess(attempts[i][0], attempts[i][1]);

            if (result.equals("ALLOWED"))
                allowed[index]++;
            else
                denied[index]++;
        }

        String result = "";

        for (int i = 0; i < 4; i++) {

            if (i > 0)
                result += " | ";

            result += modifiers[i] + ": "
                    + allowed[i] + " allowed / "
                    + denied[i] + " denied";
        }

        return result;
    }

    public static void main(String[] args) {

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
            classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
            classifyAccess("protected", "DIFFERENT_PACKAGE")
        );

        System.out.println(
            summarizeByModifier(attempts)
        );
    }
}