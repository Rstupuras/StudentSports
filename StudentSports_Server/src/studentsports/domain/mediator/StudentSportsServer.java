package studentsports.domain.mediator;

import studentsports.domain.model.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class StudentSportsServer implements RemoteStudentSports,Serializable{
    private StudentSportsModel studentSportsModel;
    public StudentSportsServer(StudentSportsModel studentSportsModel, String bind, String port) throws RemoteException {
        this.studentSportsModel = studentSportsModel;
            int registryPort = Integer.parseInt(port);
            Registry registry = LocateRegistry.createRegistry(registryPort);
            UnicastRemoteObject.exportObject(this,0);
            registry.rebind(bind,this);
    }

    public void addMemberToList(Member member) throws SQLException {
        studentSportsModel.addMemberToList(member);
    }


    @Override
    public void deleteMemberFromList(Member member) throws SQLException {
        studentSportsModel.deleteMemberFromList(member);
    }

    @Override
    public MemberList getMemberList() throws SQLException {
        return studentSportsModel.getMemberList();
    }


    @Override
    public void deleteTrainerFromList(Trainer trainer) throws SQLException {
        studentSportsModel.deleteTrainerFromList(trainer);
    }

    @Override
    public void addTrainerToList(Trainer trainer) throws SQLException {
        studentSportsModel.addTrainerToList(trainer);
    }

    @Override
    public void addSportClass(SportClass sportClass) throws SQLException {
        studentSportsModel.addSportClass(sportClass);
    }

    @Override
    public void deleteSportClass(SportClass sportClass) throws SQLException {
        studentSportsModel.deleteSportClass(sportClass);
    }


    @Override
    public void deleteTrainerFromSportClass(SportClass sportClass, Trainer trainer) throws SQLException {
        studentSportsModel.deleteTrainerFromSportClass(sportClass,trainer);
    }

    @Override
    public void deleteParticipantFromSportClass(SportClass sportClass, Member member) throws SQLException {
        studentSportsModel.deleteParticipantFromSportClass(sportClass,member);
    }

    @Override
    public void addParticipantToSportClass(SportClass sportClass, Member member) throws Exception {
        studentSportsModel.addParticipantToSportClass(sportClass,member);
    }

    @Override
    public void addTrainerToSportClass(SportClass sportClass, Trainer trainer) throws SQLException {
        studentSportsModel.addTrainerToSportClass(sportClass,trainer);
    }

    @Override
    public TrainerList getTrainerList() throws SQLException {
        return studentSportsModel.getTrainerList();
    }

    @Override
    public SportClassList getSportClassList() throws SQLException {
        return studentSportsModel.getSportClassList();
    }

    @Override
    public Member getMemberByUserNameAndPassword(String userName, String password) throws SQLException {
        return studentSportsModel.getMemberByUserNameAndPassword(userName,password);
    }

    @Override
    public Trainer getTrainerByUserNameAndPassword(String userName, String password) throws SQLException, RemoteException {
        return studentSportsModel.getTrainerByUserNameAndPassword(userName,password);
    }
}
