package Main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Patient.Patient;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Patient> patients = new ArrayList<>();

        while (true) {
            printMenu();
            int choice = readInt(scanner, "Option: ");

            switch (choice) {
                case 1 -> addNewPatient(scanner, patients);
                case 2 -> showPatients(patients);
                case 3 -> filterByPhoneNumber(scanner, patients);
                case 4 -> filterByMedicalCardRange(scanner, patients);
                case 5 -> filterByDiagnosis(scanner, patients);
                case 6 -> {
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║              PATIENT MENU                ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1. Add a new patient                     ║");
        System.out.println("║ 2. Show list of patients                 ║");
        System.out.println("║ 3. Filter by first digit of phone number ║");
        System.out.println("║ 4. Filter by medical card number range   ║");
        System.out.println("║ 5. Filter by diagnosis                   ║");
        System.out.println("║ 6. Exit                                  ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.print("Choose an option. ");
    }

    private static void addNewPatient(Scanner scanner, ArrayList<Patient> patients) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║            ADDING NEW PATIENT            ║");
        System.out.println("╚══════════════════════════════════════════╝");
        int id = readInt(scanner, "ID: ");
        scanner.nextLine();
        String lastName = readString(scanner, "Last Name: ");
        String firstName = readString(scanner, "First Name: ");
        String middleName = readString(scanner, "Middle Name: ");
        String phoneNumber = readString(scanner, "Phone Number: ");
        int medCardNum = readInt(scanner, "Medical Card Number: ");
        scanner.nextLine();
        String diagnosis = readString(scanner, "Diagnosis: ");

        patients.add(new Patient(id, lastName, firstName, middleName, phoneNumber, medCardNum, diagnosis));
        System.out.println("Patient added successfully.");
    }

    private static void showPatients(ArrayList<Patient> patients) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║              LIST OF PATIENTS            ║");
        System.out.println("╚══════════════════════════════════════════╝");
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        int number = 1;
        for (Patient patient : patients) {
            System.out.println("Patient #" + number + ":");
            System.out.println(patient.toString());
            number++;
        }
    }

    private static void filterByPhoneNumber(Scanner scanner, ArrayList<Patient> patients) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║    FILTERING BY FIRST DIGIT OF PHONE     ║");
        System.out.println("╚══════════════════════════════════════════╝");
        int firstDigit = readInt(scanner, "Enter the first digit of the phone number: ");
        ArrayList<Patient> filteredPatients = getPatientsByFirstPhoneNumDigit(patients, firstDigit);
        printPatients(filteredPatients);
    }

    private static void filterByMedicalCardRange(Scanner scanner, ArrayList<Patient> patients) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   FILTERING BY MEDICAL CARD NUMBER RANGE ║");
        System.out.println("╚══════════════════════════════════════════╝");
        int startRange = readInt(scanner, "Enter the start of the medical card range: ");
        int endRange = readInt(scanner, "Enter the end of the medical card range: ");
        ArrayList<Patient> filteredPatients = getPatientsByMedCard(patients, startRange, endRange);
        printPatients(filteredPatients);
    }

    private static void filterByDiagnosis(Scanner scanner, ArrayList<Patient> patients) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║          FILTERING BY DIAGNOSIS          ║");
        System.out.println("╚══════════════════════════════════════════╝");
        scanner.nextLine();
        String diagnosis = readString(scanner, "Enter the diagnosis to filter by: ");
        ArrayList<Patient> filteredPatients = getPatientsByDiagnosis(patients, diagnosis);
        printPatients(filteredPatients);
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
    }

    private static String readString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static ArrayList<Patient> getPatientsByDiagnosis(ArrayList<Patient> patients, String diagnosis) {
        ArrayList<Patient> specificPatients = new ArrayList<>();
        for (Patient patient : patients) {
            if (diagnosis.equalsIgnoreCase(patient.getDiagnosis())) {
                specificPatients.add(patient);
            }
        }
        return specificPatients;
    }

    public static ArrayList<Patient> getPatientsByMedCard(ArrayList<Patient> patients, int start, int end) {
        ArrayList<Patient> patientsByMedCard = new ArrayList<>();
        for (Patient patient : patients) {
            int medCardNum = patient.getMedicalCardNum();
            if (medCardNum >= start && medCardNum <= end) {
                patientsByMedCard.add(patient);
            }
        }
        return patientsByMedCard;
    }

    public static ArrayList<Patient> getPatientsByFirstPhoneNumDigit(ArrayList<Patient> patients, int firstNumber) {
        ArrayList<Patient> patientsByPhoneNum = new ArrayList<>();
        for (Patient patient : patients) {
            String phoneNumber = patient.getPhoneNumber();
            if ((phoneNumber.charAt(0) - '0') == firstNumber) {
                patientsByPhoneNum.add(patient);
            }
        }
        return patientsByPhoneNum;
    }

    public static void printPatients(ArrayList<Patient> patients) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║          FILTERED LIST OF PATIENTS       ║");
        System.out.println("╚══════════════════════════════════════════╝");
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        int number = 1;
        for (Patient patient : patients) {
            System.out.println("Patient #" + number + ":");
            System.out.println(patient.toString());
            number++;
        }
    }
}
