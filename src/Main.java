import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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
                scanner.next(); // Clear the invalid input
            }
        }

        Patient[] patients = new Patient[numPatients];

        for (int i = 0; i < patients.length; i++) {
            System.out.println("Enter details for patient " + (i + 1) + ":");
            int id = readInt(scanner, "ID: ");
            scanner.nextLine(); // Consume the newline
            String lastName = readString(scanner, "Last Name: ");
            String firstName = readString(scanner, "First Name: ");
            String middleName = readString(scanner, "Middle Name: ");
            String phoneNumber = readString(scanner, "Phone Number: ");
            int medCardNum = readInt(scanner, "Medical Card Number: ");
            String diagnosis = readString(scanner, "Diagnosis: ");

            patients[i] = new Patient(id, lastName, firstName, middleName, phoneNumber, medCardNum, diagnosis);
        }

        System.out.println("\nEnter the first digit of phone number to filter: ");
        byte firstNumber = readByte(scanner, "First Digit: ");
        ArrayList<Patient> patientsByPhoneNum = getPatientsByFirstPhoneNumDigit(patients, firstNumber);
        printPatients(patientsByPhoneNum);

        scanner.close();
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private static byte readByte(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextByte();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a byte.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private static String readString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static ArrayList<Patient> getPatientsByDiagnosis(Patient[] patients, String diagnosis){
        if(patients.length == 0){
            return null;
        }

        ArrayList<Patient> specificPatients = new ArrayList<>();
        for(Patient patient: patients){
            if(diagnosis.equals(patient.getDiagnosis())){
                specificPatients.add(patient);
            }
        }

        return specificPatients;
    }

    public static ArrayList<Patient> getPatientsByMedCard(Patient[] patients, int start, int end){
        if(patients.length == 0 || start > end){
            return null;
        }

        ArrayList<Patient> patientsByMedCard = new ArrayList<>();
        for(Patient patient: patients){
            int medCardNum = patient.getMedicalCardNum();
            if(medCardNum >= start && medCardNum <= end){
                patientsByMedCard.add(patient);
            }
        }

        return patientsByMedCard;
    }

    public static ArrayList<Patient> getPatientsByFirstPhoneNumDigit(Patient[] patients, byte firstNumber){
        if(patients.length == 0 || firstNumber < 0 || firstNumber > 9){
            return null;
        }

        ArrayList<Patient> patientsByPhoneNum = new ArrayList<>();
        int number = 0;

        for(Patient patient: patients){
            String phoneNumber = patient.getPhoneNumber();
            if((phoneNumber.charAt(0) - '0') == firstNumber){
                patientsByPhoneNum.add(patient);
                number++;
            }
        }

        System.out.println("Number of patients with the same first number in phone number: " + number);
        return patientsByPhoneNum;
    }

    public static void printPatients(ArrayList<Patient> patients){
        if(patients == null || patients.isEmpty()){
            System.out.println("List is empty.");
            return;
        }
        int number = 1;
        for(Patient patient: patients){
            System.out.println(number + ".");
            System.out.println(patient.toString());
            number++;
        }
    }
}
