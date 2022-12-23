import java.util.Scanner;

class Isbn10 {
    public static final int OK = 0;
    public static final int FALSCHES_ZEICHEN = 1;
    public static final int FALSCHE_PRUEFSUMME = 2;
    public static final int FALSCHE_LAENGE = 3;

    static int validityIsbn10(String isbn) {

        int[] res = new int[10];
        int tmp = 0;

        if(isbn.length() != 10) {
            return FALSCHE_LAENGE;
        }
        for(int i = 0; i < 9; i++) {
            try {
                res[i] = Integer.parseInt(isbn.substring(i, i+1)) * (10-i);
            }
            catch(NumberFormatException e) {
                return FALSCHES_ZEICHEN;
            }         
        }
        tmp = (isbn.substring(9,9+1).equals("X")) ? 10 : 0;
        res[9] = (tmp == 10) ? tmp : 0;
        if(res[9] == 0) {
            try {
                res[9] = Integer.parseInt(isbn.substring(9, 9+1));
            }
            catch(NumberFormatException e) {
                return FALSCHES_ZEICHEN;
            }
        }
        tmp = 0;
        for(int val : res) {
            tmp += val;
        }
        return ((tmp % 11) == 0) ? OK : FALSCHE_PRUEFSUMME;
    }

    public static void main(String args[]) {
        System.out.print("ISBN eingeben: ");
        Scanner scanner = new Scanner(System.in);
        String isbn = scanner.nextLine();
        scanner.close();

        switch (validityIsbn10(isbn)) {
            case OK:
                System.out.println("ISBN is valid");
                break;
            case FALSCHES_ZEICHEN:
                System.out.println("ISBN contains a wrong character");
                break;
            case FALSCHE_PRUEFSUMME:
                System.out.println("ISBN checksum is wrong");
                break;
            case FALSCHE_LAENGE:
                System.out.println("ISBN has the wrong length");
                break;
            default:
                System.out.println("Something went wrong (entered default case)");
                break;
        }

    }
}