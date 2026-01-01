package service;

import model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class FileStorageService {

    private static final String DATA_DIR = "data";
    private static final String BOOK_FILE = DATA_DIR + "/books.csv";
    private static final String LOAN_FILE = DATA_DIR + "/loans.csv";
    private static final String MEMBER_FILE = DATA_DIR + "/members.csv";

    public FileStorageService() {
        new File(DATA_DIR).mkdirs();
        createIfNotExists(BOOK_FILE, "isbn,title,available");
        createIfNotExists(LOAN_FILE, "loanId,memberId,isbn,loanDate,returnDate,returned");
        createIfNotExists(MEMBER_FILE, "id,username,password,role");
    }

    private void createIfNotExists(String path, String header) {
        File f = new File(path);
        if (!f.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
                pw.println(header);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // ================= BOOK =================
    public List<Book> loadBooks() {
        List<Book> list = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(BOOK_FILE))) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] d = sc.nextLine().split(",");
                list.add(new Book(d[0], d[1], Boolean.parseBoolean(d[2])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveBooks(List<Book> books) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BOOK_FILE))) {
            pw.println("isbn,title,available");
            for (Book b : books) {
                pw.println(b.getIsbn() + "," + b.getTitle() + "," + b.isAvailable());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= LOAN =================
    public List<Loan> loadLoans() {
        List<Loan> list = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(LOAN_FILE))) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] d = sc.nextLine().split(",");

                Loan l = new Loan(d[0], d[1], d[2]);
                l.setLoanDate(LocalDate.parse(d[3]));

                if (!d[4].isEmpty()) {
                    l.setReturnDate(LocalDate.parse(d[4]));
                }

                if (Boolean.parseBoolean(d[5])) {
                    l.markReturned();
                }

                list.add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveLoans(List<Loan> loans) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(LOAN_FILE))) {
            pw.println("loanId,memberId,isbn,loanDate,returnDate,returned");
            for (Loan l : loans) {
                pw.println(
                        l.getLoanId() + "," +
                                l.getMemberId() + "," +
                                l.getIsbn() + "," +
                                l.getLoanDate() + "," +
                                (l.getReturnDate() == null ? "" : l.getReturnDate()) + "," +
                                l.isReturned()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= USER =================
    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(MEMBER_FILE))) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] d = sc.nextLine().split(",");
                if (d[3].equals("ADMIN")) {
                    users.add(new Librarian(d[0], d[1], d[2]));
                } else {
                    users.add(new Member(d[0], d[1], d[2]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
