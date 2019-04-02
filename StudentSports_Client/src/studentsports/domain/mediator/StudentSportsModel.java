package studentsports.domain.mediator;

import studentsports.domain.model.*;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface StudentSportsModel {
    void addMemberToList(Member member) throws RemoteException, SQLException;
    void deleteMemberFromList(Member member) throws SQLException, RemoteException;
    MemberList getMemberList() throws SQLException, RemoteException;
    void deleteTrainerFromList(Trainer trainer) throws SQLException, RemoteException;
    void addTrainerToList(Trainer trainer) throws SQLException, RemoteException;
    void addSportClass(SportClass sportClass) throws SQLException, RemoteException;
    void deleteSportClass(SportClass sportClass) throws SQLException, RemoteException;
    void deleteTrainerFromSportClass(SportClass sportClass, Trainer trainer) throws SQLException, RemoteException;
    void deleteParticipantFromSportClass(SportClass sportClass, Member member) throws RemoteException, SQLException;
    void addParticipantToSportClass(SportClass sportClass, Member member) throws RemoteException, SQLException, IndexOutOfBoundsException;
    void addTrainerToSportClass(SportClass sportClass, Trainer trainer) throws SQLException, RemoteException;
    TrainerList getTrainerList() throws SQLException, RemoteException;
    SportClassList getSportClassList() throws SQLException, RemoteException;
    Member getMemberByUserNameAndPassword(String userName,String password) throws SQLException, RemoteException;
    Trainer getTrainerByUserNameAndPassword(String userName,String password) throws SQLException, RemoteException;

}