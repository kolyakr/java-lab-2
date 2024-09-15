public class Patient {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;
    private int medicalCardNum;
    private String diagnosis;

    public Patient(){
        this.id = 0;
        this.surname = "Undefined";
        this.name = "Undefined";
        this.patronymic = "Undefined";
        this.phoneNumber = "Undefined";
        this.medicalCardNum = 0;
        this.diagnosis = "Undefined";
    }

    public Patient(int id, String surname, String name, String patronymic, String phoneNumber, int medicalCardNum, String diagnosis){
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.medicalCardNum = medicalCardNum;
        this.diagnosis = diagnosis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMedicalCardNum() {
        return medicalCardNum;
    }

    public void setMedicalCardNum(int medicalCardNum) {
        this.medicalCardNum = medicalCardNum;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString(){
        return String.format("""
                Patient {
                    id: %d
                    surname: %s
                    name: %s
                    patronymic: %s
                    phone number: %s
                    medical card number: %d
                    diagnosis: %s
                }
                """,
                this.id, this.surname, this.name, this.patronymic, this.phoneNumber, this.medicalCardNum, this.diagnosis);
    }
}