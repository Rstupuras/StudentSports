package studentsports.domain.mediator;

import studentsports.domain.model.*;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface StudentSportsModel {
    void addMemberToList(Member member) throws SQLException;
    void deleteMemberFromList(Member member) throws SQLException;
    MemberList getMemberList() throws SQLException;
    void deleteTrainerFromList(Trainer trainer) throws SQLException;
    void addTrainerToList(Trainer trainer) throws SQLException;
    void addSportClass(SportClass sportClass) throws SQLException;
    void deleteSportClass(SportClass sportClass) throws SQLException;
    void deleteTrainerFromSportClass(SportClass sportClass, Trainer trainer) throws SQLException;
    void deleteParticipantFromSportClass(SportClass sportClass, Member member) throws SQLException;
    void addParticipantToSportClass(SportClass sportClass, Member member) throws Exception;
    void addTrainerToSportClass(SportClass sportClass, Trainer trainer) throws SQLException;
    TrainerList getTrainerList() throws SQLException;
    SportClassList getSportClassList() throws SQLException;
    Member getMemberByUserNameAndPassword(String userName,String password) throws SQLException;
    Trainer getTrainerByUserNameAndPassword(String userName,String password) throws SQLException;


}