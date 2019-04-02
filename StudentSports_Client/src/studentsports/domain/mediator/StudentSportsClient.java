package studentsports.domain.mediator;

import studentsports.domain.model.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class StudentSportsClient implements StudentSportsModel {
    private RemoteStudentSports remoteStudentSports;
    public StudentSportsClient(String host) throws RemoteException, NotBoundException, MalformedURLException {

            remoteStudentSports = (RemoteStudentSports) Naming.lookup(host);

    }

    public void addMemberToList(Member member) throws RemoteException, SQLException {
        remoteStudentSports.addMemberToList(member);
    }


    @Override
    public void deleteMemberFromList(Member member) throws SQLException, RemoteException {
        remoteStudentSports.deleteMemberFromList(member);
    }

    @Override
    public MemberList getMemberList() throws SQLException, RemoteException {
        return remoteStudentSports.getMemberList();
    }


    @Override
    public void deleteTrainerFromList(Trainer trainer) throws SQLException, RemoteException {
        remoteStudentSports.deleteTrainerFromList(trainer);
    }

    @Override
    public void addTrainerToList(Trainer trainer) throws SQLException, RemoteException {
        remoteStudentSports.addTrainerToList(trainer);
    }

    @Override
    public void addSportClass(SportClass sportClass) throws SQLException, RemoteException {
        remoteStudentSports.addSportClass(sportClass);
    }

    @Override
    public void deleteSportClass(SportClass sportClass) throws SQLException, RemoteException {
        remoteStudentSports.deleteSportClass(sportClass);
    }

    @Override
    public void deleteTrainerFromSportClass(SportClass sportClass, Trainer trainer) throws SQLException, RemoteException {
        remoteStudentSports.deleteTrainerFromSportClass(sportClass,trainer);
    }

    @Override
    public void deleteParticipantFromSportClass(SportClass sportClass, Member member) throws RemoteException, SQLException {
        remoteStudentSports.deleteParticipantFromSportClass(sportClass,member);
    }

    @Override
    public void addParticipantToSportClass(SportClass sportClass, Member member) throws RemoteException, SQLException {
        remoteStudentSports.addParticipantToSportClass(sportClass,member);
    }

    @Override
    public void addTrainerToSportClass(SportClass sportClass, Trainer trainer) throws SQLException, RemoteException {
        remoteStudentSports.addTrainerToSportClass(sportClass,trainer);
    }

    @Override
    public TrainerList getTrainerList() throws SQLException, RemoteException {
        return remoteStudentSports.getTrainerList();
    }

    @Override
    public SportClassList getSportClassList() throws SQLException, RemoteException {
        return remoteStudentSports.getSportClassList();
    }

    @Override
    public Member getMemberByUserNameAndPassword(String userName, String password) throws SQLException, RemoteException {
        return remoteStudentSports.getMemberByUserNameAndPassword(userName,password);
    }

    @Override
    public Trainer getTrainerByUserNameAndPassword(String userName, String password) throws SQLException, RemoteException {
        return remoteStudentSports.getTrainerByUserNameAndPassword(userName,password);
    }
}
