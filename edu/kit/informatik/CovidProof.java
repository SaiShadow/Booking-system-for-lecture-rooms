package edu.kit.informatik;

/**
 * This class represents all the covid-proof information a person needs.
 * 
 * @author uogok
 * @version 2
 *
 */
public class CovidProof {
    private final int testValidityTimePeriod = 180;
    private boolean vaccinated;
    private boolean recovered;
    private boolean tested;
    private Date vaccinationDate;
    private Date expiryDateTest;
    private Date expiryDateRecovery;

    /**
     * Sets the vaccination status of a person, this automatically also sets the
     * date of which the vaccination starts taking effect.
     * 
     * @param status    is the status of the vaccination, if the person is
     *                  vaccinated then the boolean value is true, otherwise false.
     * @param dateAdded is the date of which the vaccination starts taking effect.
     */
    public void setVaccinated(boolean status, Date dateAdded) {
        this.vaccinated = status;
        this.setVaccinationDate(dateAdded);
    }

    /**
     * Sets the recovery status of a person, this automatically also sets the date
     * of which the recovery starts taking effect.
     * 
     * @param status    is the status of the recovery-certificate, if the
     *                  certificate is valid, then the boolean value is true.
     * @param dateAdded is the date on which he received his certificate.
     */
    public void setRecovered(boolean status, Date dateAdded) {

        dateAdded.setDate(dateAdded.getDate() + testValidityTimePeriod);
        this.recovered = status;
        this.expiryDateRecovery = dateAdded;
    }

    /**
     * Sets the test status.
     * 
     * @param status    if the test result is negative and valid then the value is
     *                  true.
     * @param dateAdded date of the test.
     */
    public void setTested(boolean status, Date dateAdded) {
        this.tested = status;
        this.expiryDateTest = dateAdded;
    }

    /**
     * Gets the expiry date of the test.
     * 
     * @return expiry date of the test.
     */
    public Date getExpiryDateTest() {
        return this.expiryDateTest;
    }

    /**
     * Gets the date of the recovery.
     * 
     * @return expiry date of the recovery.
     */
    public Date getExpiryDateRecovered() {
        return this.expiryDateRecovery;
    }

    /**
     * Gets the status of the recovery-certificate.
     * 
     * @return status of recovery-certificate.
     */
    public boolean isRecovered() {
        return this.recovered;
    }

    /**
     * Gets the status of the test.
     * 
     * @return status of the test.
     */
    public boolean isTested() {
        return this.tested;
    }

    /**
     * Gets the value of the vaccination status.
     * 
     * @return status of the vaccination.
     */
    public boolean isVaccinated() {
        return this.vaccinated;
    }

    /**
     * Sets the status of the recovery.
     * 
     * @param recovered is true if the recovery is successful and the certificate is
     *                  still valid.
     */
    public void setRecovered(boolean recovered) {
        this.recovered = recovered;
    }

    /**
     * Sets the status of the test.
     * 
     * @param tested is true if the test comes out negative and the test is still
     *               valid.
     */
    public void setTested(boolean tested) {
        this.tested = tested;
    }

    /**
     * Gets date of which the vaccination starts taking effect.
     * 
     * @return date of which the vaccination starts taking effect.
     */
    public Date getVaccinationDate() {
        return vaccinationDate;
    }

    /**
     * Sets date of which the vaccination starts taking effect.
     * 
     * @param vaccinationDate date of which the vaccination starts taking effect.
     */
    public void setVaccinationDate(Date vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

}
