package com.niranisugar.android.Models;

public class ResellSugarAvailable {

    String TenderId, MillShortName, MillFullName, Zone, Grade, Season, Packing, Rate, GST, NetRate, image, Authorized;
    String ClientID;
    String QtyRemain,TenderDetailID,TenderNumber;
    String PaymentDate,LiftingDate;
    String AuthorizedName,AuthorizedContact,AuthorizedDistrict;

    public ResellSugarAvailable(String ClientID, String image, String tenderId, String millShortName, String millFullName, String zone, String grade,
                                String season, String packing, String rate, String GST, String netRate, String Authorized,
                                String QtyRemain, String TenderDetailID, String TenderNumber, String PaymentDate, String LiftingDate,
                                String AuthorizedName, String AuthorizedContact, String AuthorisedDistrict) {
        TenderId = tenderId;
        MillShortName = millShortName;
        MillFullName = millFullName;
        Zone = zone;
        Grade = grade;
        Season = season;
        Packing = packing;
        Rate = rate;
        this.GST = GST;
        NetRate = netRate;
        this.image = image;
        this.Authorized = Authorized;
        this.ClientID = ClientID;
        this.QtyRemain = QtyRemain;
        this.TenderDetailID = TenderDetailID;
        this.TenderNumber = TenderNumber;
        this.PaymentDate = PaymentDate;
        this.LiftingDate = LiftingDate;
        this.AuthorizedName = AuthorizedName;
        this.AuthorizedContact = AuthorizedContact;
        this.AuthorizedDistrict = AuthorisedDistrict;
    }

    public String getAuthorizedDistrict() {
        return AuthorizedDistrict;
    }

    public void setAuthorizedDistrict(String authorizedDistrict) {
        AuthorizedDistrict = authorizedDistrict;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }

    public String getLiftingDate() {
        return LiftingDate;
    }

    public String getAuthorizedName() {
        return AuthorizedName;
    }

    public void setAuthorizedName(String authorizedName) {
        AuthorizedName = authorizedName;
    }

    public String getAuthorizedContact() {
        return AuthorizedContact;
    }

    public void setAuthorizedContact(String authorizedContact) {
        AuthorizedContact = authorizedContact;
    }

    public void setLiftingDate(String liftingDate) {
        LiftingDate = liftingDate;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTenderId() {
        return TenderId;
    }

    public void setTenderId(String tenderId) {
        TenderId = tenderId;
    }

    public String getMillShortName() {
        return MillShortName;
    }

    public void setMillShortName(String millShortName) {
        MillShortName = millShortName;
    }

    public String getMillFullName() {
        return MillFullName;
    }

    public void setMillFullName(String millFullName) {
        MillFullName = millFullName;
    }

    public String getZone() {
        return Zone;
    }

    public void setZone(String zone) {
        Zone = zone;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getSeason() {
        return Season;
    }

    public void setSeason(String season) {
        Season = season;
    }

    public String getPacking() {
        return Packing;
    }

    public void setPacking(String packing) {
        Packing = packing;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }

    public String getNetRate() {
        return NetRate;
    }

    public void setNetRate(String netRate) {
        NetRate = netRate;
    }

    public String getAuthorized() {
        return Authorized;
    }

    public void setAuthorized(String authorized) {
        Authorized = authorized;
    }

    public String getQtyRemain() {
        return QtyRemain;
    }

    public void setQtyRemain(String qtyRemain) {
        QtyRemain = qtyRemain;
    }

    public String getTenderDetailID() {
        return TenderDetailID;
    }

    public void setTenderDetailID(String tenderDetailID) {
        TenderDetailID = tenderDetailID;
    }

    public String getTenderNumber() {
        return TenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        TenderNumber = tenderNumber;
    }
}
