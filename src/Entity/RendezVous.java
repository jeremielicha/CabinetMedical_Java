package Entity;

public class RendezVous {
    private String heureRdv;
    private String nomPatient;
    private String nomPathologique;

    public RendezVous(String heureRdv, String nomPatient, String nomPathologique) {
        this.heureRdv = heureRdv;
        this.nomPatient = nomPatient;
        this.nomPathologique = nomPathologique;
    }

    public String getHeureRdv() {
        return heureRdv;
    }

    public void setHeureRdv(String heureRdv) {
        this.heureRdv = heureRdv;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    public String getNomPathologique() {
        return nomPathologique;
    }

    public void setNomPathologique(String nomPathologique) {
        this.nomPathologique = nomPathologique;
    }
}