package Main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Patient.Patient;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numPatients = 0;
        while (true) {
            System.out.print("Enter the number of patients: ");
            try {
                numPatients = scanner.nextInt();
                if (numPatients <= 0) {
                    System.out.println("Number of patients must be positive.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }

        Patient[] patients = new Patient[numPatients];

        for (int i = 0; i < patients.length; i++) {
            System.out.println("Enter details for patient " + (i + 1) + ":");
            int id = readInt(scanner, "ID: ");
            scanner.nextLine();
            String lastName = readString(scanner, "Last Name: ");
            String firstName = readString(scanner, "First Name: ");
            String middleName = readString(scanner, "Middle Name: ");
            String phoneNumber = readString(scanner, "Phone Number: ");
            int medCardNum = readInt(scanner, "Medical Card Number: ");
            scanner.nextLine();
            String diagnosis = readString(scanner, "Diagnosis: ");

            patients[i] = new Patient(id, lastName, firstName, middleName, phoneNumber, medCardNum, diagnosis);
        }

        // Filter by phone number
        System.out.println("\nEnter the first digit of phone number to filter: ");
        int firstNumber = readInt(scanner, "First Digit: ");
        ArrayList<Patient> patientsByPhoneNum = getPatientsByFirstPhoneNumDigit(patients, firstNumber);
        printPatients(patientsByPhoneNum);

        // Filter by diagnosis
        scanner.nextLine();
        System.out.println("\nEnter diagnosis to filter patients: ");
        String diagnosisFilter = readString(scanner, "Diagnosis: ");
        ArrayList<Patient> patientsByDiagnosis = getPatientsByDiagnosis(patients, diagnosisFilter);
        printPatients(patientsByDiagnosis);

        // Filter by medical card
        System.out.println("\nEnter the start and end of medical card number range to filter: ");
        int startMedCard = readInt(scanner, "Start Range: ");
        int endMedCard = readInt(scanner, "End Range: ");
        ArrayList<Patient> patientsByMedCard = getPatientsByMedCard(patients, startMedCard, endMedCard);
        printPatients(patientsByMedCard);

        scanner.close();
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

    public static ArrayList<Patient> getPatientsByDiagnosis(Patient[] patients, String diagnosis) {
        if (patients.length == 0) {
            return null;
        }

        ArrayList<Patient> specificPatients = new ArrayList<>();
        for (Patient patient : patients) {
            if (diagnosis.equalsIgnoreCase(patient.getDiagnosis())) {
                specificPatients.add(patient);
            }
        }

        return specificPatients;
    }

    public static ArrayList<Patient> getPatientsByMedCard(Patient[] patients, int start, int end) {
        if (patients.length == 0 || start > end) {
            return null;
        }

        ArrayList<Patient> patientsByMedCard = new ArrayList<>();
        for (Patient patient : patients) {
            int medCardNum = patient.getMedicalCardNum();
            if (medCardNum >= start && medCardNum <= end) {
                patientsByMedCard.add(patient);
            }
        }

        return patientsByMedCard;
    }

    public static ArrayList<Patient> getPatientsByFirstPhoneNumDigit(Patient[] patients, int firstNumber) {
        if (patients.length == 0 || firstNumber < 0 || firstNumber > 9) {
            return null;
        }

        ArrayList<Patient> patientsByPhoneNum = new ArrayList<>();
        int number = 0;

        for (Patient patient : patients) {
            String phoneNumber = patient.getPhoneNumber();
            if ((phoneNumber.charAt(0) - '0') == firstNumber) {
                patientsByPhoneNum.add(patient);
                number++;
            }
        }

        System.out.println("Number of patients with the same first number in phone number: " + number);
        return patientsByPhoneNum;
    }

    public static void printPatients(ArrayList<Patient> patients) {
        if (patients == null || patients.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        int number = 1;
        for (Patient patient : patients) {
            System.out.println(number + ".");
            System.out.println(patient.toString());
            number++;
        }
    }
}
