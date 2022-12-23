import java.util.Scanner;

class Isbn10 {
    public static final int OK = 0;
    public static final int FALSCHES_ZEICHEN = 1;
    public static final int FALSCHE_PRUEFSUMME = 2;
    public static final int FALSCHE_LAENGE = 3;

    static int validityIsbn10(String isbn) {

        int[] res = new int[10];
        int tmp = 0;
        char[] ca = new char[10];

        // purge -
        for(char c : isbn.toCharArray()) {
            if(c != '-') {
                if(tmp > 9) {
                    return FALSCHE_LAENGE;
                }
                ca[tmp] = c;
                tmp++;
            }
        }

        // len check
        if(ca[9] == '\0') {
            return FALSCHE_LAENGE;
        }
        isbn = new String(ca);

        // parse to int
        for(int i = 0; i < 9; i++) {
            try {
                res[i] = (Integer.parseInt(Character.toString(isbn.charAt(i)))) * (i+1);
            }
            catch(NumberFormatException e) {
                return FALSCHES_ZEICHEN;
            }         
        }
        res[9] = (isbn.charAt(9) == 'X') ? 10 : 0;
        if(res[9] == 0) {
            try {
                res[9] = Integer.parseInt(Character.toString(isbn.charAt(9)));
            }
            catch(NumberFormatException e) {
                return FALSCHES_ZEICHEN;
            }
        }
        
        // checksum
        tmp = 0;
        for(int i = 0; i < 9; i++) {
            tmp += res[i];
        }
        return ((tmp % 11) == res[9]) ? OK : FALSCHE_PRUEFSUMME;
    }

    public static void main(String args[]) {
        System.out.print("ISBN eingeben: ");
        Scanner scanner = new Scanner(System.in);
        String isbn = scanner.nextLine();
        scanner.close();

        switch (validityIsbn10(isbn)) {
            case OK:
                System.out.println("OK");
                break;
            case FALSCHES_ZEICHEN:
                System.out.println("Fehler: Falsches Zeichen");
                break;
            case FALSCHE_PRUEFSUMME:
                System.out.println("Fehler: Pruefziffer falsch");
                break;
            case FALSCHE_LAENGE:
                System.out.println("Fehler: Falsche Laenge");
                break;
            default:
                System.out.println("Fehler: intern");
                break;
        }
    }
}