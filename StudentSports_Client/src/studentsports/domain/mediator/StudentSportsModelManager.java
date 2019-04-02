package studentsports.domain.mediator;

import studentsports.domain.model.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class StudentSportsModelManager implements StudentSportsModel {
    private StudentSportsModel studentSportsModel;

    public StudentSportsModelManager(String ip, String port, String bindedName) throws RemoteException, NotBoundException, MalformedURLException {

        String host = "rmi://"+ip+":"+port+"/"+bindedName;
        studentSportsModel = new StudentSportsClient(host);
    }


    @Override
    public synchronized void addMemberToList(Member member) throws RemoteException, SQLException {
        studentSportsModel.addMemberToList(member);
    }



    @Override
    public synchronized void deleteMemberFromList(Member member) throws SQLException, RemoteException {
        studentSportsModel.deleteMemberFromList(member);
    }


    public synchronized MemberList getMemberList() throws SQLException, RemoteException {

        return studentSportsModel.getMemberList();
    }



    @Override
    public synchronized void deleteTrainerFromList(Trainer trainer) throws SQLException, RemoteException {
        studentSportsModel.deleteTrainerFromList(trainer);
    }

    @Override
    public synchronized void addTrainerToList(Trainer trainer) throws SQLException, RemoteException {
        studentSportsModel.addTrainerToList(trainer);
    }

    @Override
    public synchronized void addSportClass(SportClass sportClass) throws SQLException, RemoteException {
        studentSportsModel.addSportClass(sportClass);
    }

    @Override
    public synchronized void deleteSportClass(SportClass sportClass) throws SQLException, RemoteException {
        studentSportsModel.deleteSportClass(sportClass);
    }


    @Override
    public synchronized void deleteTrainerFromSportClass(SportClass sportClass, Trainer trainer) throws SQLException, RemoteException {
        studentSportsModel.deleteTrainerFromSportClass(sportClass,trainer);
    }

    @Override
    public synchronized void deleteParticipantFromSportClass(SportClass sportClass, Member member) throws RemoteException, SQLException {
        studentSportsModel.deleteParticipantFromSportClass(sportClass,member);
    }

    @Override
    public synchronized void addParticipantToSportClass(SportClass sportClass, Member member) throws RemoteException, SQLException {
        studentSportsModel.addParticipantToSportClass(sportClass,member);

    }

    @Override
    public synchronized void addTrainerToSportClass(SportClass sportClass, Trainer trainer) throws SQLException, RemoteException,IndexOutOfBoundsException {
        studentSportsModel.addTrainerToSportClass(sportClass,trainer);
    }

    @Override
    public synchronized TrainerList getTrainerList() throws SQLException, RemoteException {
        return studentSportsModel.getTrainerList();
    }

    @Override
    public synchronized SportClassList getSportClassList() throws SQLException, RemoteException {
        return studentSportsModel.getSportClassList();
    }

    @Override
    public synchronized Member getMemberByUserNameAndPassword(String userName, String password) throws SQLException, RemoteException {
        return studentSportsModel.getMemberByUserNameAndPassword(userName,password);
    }

    @Override
    public synchronized Trainer getTrainerByUserNameAndPassword(String userName, String password) throws SQLException, RemoteException {
        return studentSportsModel.getTrainerByUserNameAndPassword(userName,password);
    }

}
