import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Patient[] patients = new Patient[10];
        patients[0] = new Patient(1, "Smith", "John", "William", "555-1234", 1001, "Flu");
        patients[1] = new Patient(2, "Doe", "Jane", "Elizabeth", "555-5678", 1002, "Cold");
        patients[2] = new Patient(3, "Johnson", "Emily", "Rose", "555-8765", 1003, "Headache");
        patients[3] = new Patient(4, "Williams", "Michael", "James", "555-4321", 1004, "Fever");
        patients[4] = new Patient(5, "Jones", "Olivia", "Grace", "555-6789", 1005, "Cough");
        patients[5] = new Patient(6, "Brown", "Liam", "Noah", "555-3456", 1006, "Cold");
        patients[6] = new Patient(7, "Davis", "Ava", "Mia", "555-7890", 1007, "Stomachache");
        patients[7] = new Patient(8, "Miller", "Sophia", "Isabella", "555-2345", 1008, "Back Pain");
        patients[8] = new Patient(9, "Wilson", "Ethan", "Alexander", "555-6780", 1009, "Cold");
        patients[9] = new Patient(10, "Moore", "Charlotte", "Amelia", "555-9012", 1010, "Nausea");

        ArrayList<Patient> patientsWithCold  = getPatientsByDiagnosis(patients, "Cold");
        printPatients(patientsWithCold);
    }

    /**
     * This function search patients with the same diagnosis ad in parameters
     * @param patients array of Patient objects
     * @param diagnosis name of diagnosis
     * @return dynamic array list of patients with the same diagnosis
     */

    public static ArrayList<Patient> getPatientsByDiagnosis(Patient[] patients, String diagnosis){
        if(patients.length == 0){
            return null;
        }

        ArrayList<Patient> specificPatients = new ArrayList<Patient>();
        for(Patient patient: patients){
            if(diagnosis.equals(patient.getDiagnosis())){
                specificPatients.add(patient);
            }
        }

        return specificPatients;
    }

    /**
     * prints patients
     * @param patients dynamic array list of patients
     */

    public static void printPatients(ArrayList<Patient> patients){
        for(Patient patient: patients){
            System.out.println(patient.toString());
        }
    }
}














