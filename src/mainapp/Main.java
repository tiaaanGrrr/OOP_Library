package mainapp;

import model.*;
import service.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FileStorageService storage = new FileStorageService();
        LibraryService lib = new LibraryService();

        List<User> users = storage.loadUsers();
        AuthenticationService auth = new AuthenticationService(users);

        Scanner sc = new Scanner(System.in);

        // ================= LOGIN =================
        User user = null;
        while (user == null) {
            System.out.print("Username: ");
            String u = sc.nextLine();
            System.out.print("Password: ");
            String p = sc.nextLine();

            user = auth.login(u, p);
            if (user == null) {
                System.out.println("Login gagal, coba lagi.\n");
            }
        }

        System.out.println("Login sebagai " + user.getRole());

        if (user.getRole().equals("ADMIN")) {
            adminMenu(sc, lib);
        } else {
            memberMenu(sc, lib, user.getId());
        }

        sc.close();
        System.out.println("Program selesai.");
    }

    // ================= ADMIN MENU =================
    private static void adminMenu(Scanner sc, LibraryService lib) {
        int pilih;
        do {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. Lihat Buku");
            System.out.println("2. Tambah Buku");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();
            sc.nextLine();

            if (pilih == 1) {
                lib.getBooks().forEach(b ->
                        System.out.println(b.getIsbn() + " | " + b.getTitle() +
                                " | " + (b.isAvailable() ? "AVAILABLE" : "BORROWED"))
                );
            } else if (pilih == 2) {
                System.out.print("ISBN: ");
                String isbn = sc.nextLine();
                System.out.print("Judul: ");
                String title = sc.nextLine();
                lib.addBook(new Book(isbn, title, true));
                System.out.println("Buku ditambahkan.");
            }
        } while (pilih != 0);
    }

    // ================= MEMBER MENU =================
    private static void memberMenu(Scanner sc, LibraryService lib, String memberId) {
        int pilih;
        do {
            System.out.println("\n=== MEMBER MENU ===");
            System.out.println("1. Lihat Buku");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Kembalikan Buku");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();
            sc.nextLine();

            if (pilih == 1) {
                lib.getBooks().forEach(b ->
                        System.out.println(b.getIsbn() + " | " + b.getTitle() +
                                " | " + (b.isAvailable() ? "AVAILABLE" : "BORROWED"))
                );
            } else if (pilih == 2) {
                System.out.print("ISBN: ");
                String isbn = sc.nextLine();
                Loan l = lib.borrowBook(memberId, isbn);
                System.out.println(l == null ? "Gagal" : "Berhasil, LoanID " + l.getLoanId());
            } else if (pilih == 3) {
                System.out.print("Loan ID: ");
                String id = sc.nextLine();
                Fine fine = lib.returnBook(id);
                if (fine != null) {
                    System.out.println("Dikembalikan. Denda: Rp " + fine.getAmount());
                } else {
                    System.out.println("Gagal.");
                }
            }
        } while (pilih != 0);
    }
}
