package studentsports.domain.model;

import java.io.Serializable;

public class Member implements Serializable {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Date paymentDate;
    private String studentNumber;
    private boolean ispaid;
    private Date paidUntil;


    public Member(String firstName, String lastName, String userName, String password, String studentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.studentNumber = studentNumber;
        this.paymentDate = null;
        this.ispaid = false;
        this.paidUntil = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
        this.ispaid = true;
    }

    public boolean isIspaid() {
        return ispaid;
    }

    public void setIspaid(boolean ispaid) {
        this.ispaid = ispaid;
    }

    public void setPaidUntil(Date paidUntil) {
        this.paidUntil = paidUntil;
    }

    public Date getPaidUntil() {
        return paidUntil;
    }

    @Override
    public String toString() {
        String s = "";
        s += "Name: " + firstName + ". Last name: " + lastName + ". Username: " + userName + ". Password: " + password;
        if (studentNumber != null) {
            s += ". Student number: " + studentNumber;


        }
        if (paymentDate != null) {
            s += ". Payment date: " + paymentDate;

        }
        return s;
      
        }
    }



