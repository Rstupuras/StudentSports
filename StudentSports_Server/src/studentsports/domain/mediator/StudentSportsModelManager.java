package studentsports.domain.mediator;

import studentsports.domain.model.*;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class StudentSportsModelManager implements StudentSportsModel {
    private MemberList memberList;
    private StudentSportsPersistence studentSportsPersistence;
    private TrainerList trainerList;
    private SportClassList sportClassList;

    public StudentSportsModelManager(String user, String password, String ip, String database, String port, String bind, String registryPort) throws SQLException, RemoteException {
            studentSportsPersistence = new StudentSportsDatabase(user, password, ip, database, port);
            memberList = new MemberList();
            memberList = studentSportsPersistence.loadMembers();
            trainerList = new TrainerList();
            trainerList = studentSportsPersistence.loadTrainers();
            sportClassList = studentSportsPersistence.loadSporsClasses();
            StudentSportsServer vipassanaServer = new StudentSportsServer(this,bind,registryPort);

    }


    @Override
    public synchronized void addMemberToList(Member member) throws SQLException {
        memberList.add(member);
        studentSportsPersistence.saveMember(member);
    }

    @Override
    public synchronized void deleteMemberFromList(Member member) throws SQLException {
        memberList.delete(member);
        studentSportsPersistence.removeMember(member);
    }


    public synchronized MemberList getMemberList() throws SQLException {

        return memberList = studentSportsPersistence.loadMembers();
    }



    @Override
    public synchronized void deleteTrainerFromList(Trainer trainer) throws SQLException {
        studentSportsPersistence.removeTrainer(trainer);
        trainerList.delete(trainer);
    }

    @Override
    public synchronized void addTrainerToList(Trainer trainer) throws SQLException {
        studentSportsPersistence.saveTrainer(trainer);
        trainerList.addTrainer(trainer);
    }

    @Override
    public synchronized void addSportClass(SportClass sportClass) throws SQLException {
        sportClassList.addSportClass(sportClass);
        studentSportsPersistence.saveSportsClass(sportClass);
    }

    @Override
    public synchronized void deleteSportClass(SportClass sportClass) throws SQLException {
        studentSportsPersistence.removeSportsClass(sportClass);
        sportClassList.deleteSportClass(sportClass);
    }


    @Override
    public synchronized void deleteTrainerFromSportClass(SportClass sportClass, Trainer trainer) throws SQLException {
        studentSportsPersistence.removeTrainerFromClass(trainer, sportClass);
        sportClass.deleteTrainerFromSportClass(trainer);
    }

    @Override
    public synchronized void deleteParticipantFromSportClass(SportClass sportClass, Member member) throws SQLException {
        studentSportsPersistence.removeMemberFromClass(member,sportClass);
        sportClass.deleteParticipant(member);
    }

    @Override
    public synchronized void addParticipantToSportClass(SportClass sportClass, Member member) throws IndexOutOfBoundsException, SQLException {
        if (sportClass.getAllParticipants().size()<sportClass.getCapacity())
        {
            studentSportsPersistence.addParticipant(sportClass, member);
            sportClass.addParticipant(member);
        }
        else {
            throw new IndexOutOfBoundsException();
        }

    }

    @Override
    public synchronized void addTrainerToSportClass(SportClass sportClass, Trainer trainer) throws SQLException {
        studentSportsPersistence.addTrainerToClass(sportClass, trainer);
        sportClass.addTrainerToSportClass(trainer);
    }

    @Override
    public synchronized TrainerList getTrainerList() throws SQLException {
        return trainerList = studentSportsPersistence.loadTrainers();
    }

    @Override
    public synchronized SportClassList getSportClassList() throws SQLException {
        return sportClassList = studentSportsPersistence.loadSporsClasses();
    }

    @Override
    public synchronized Member getMemberByUserNameAndPassword(String userName, String password) {
        return memberList.getMemberByUserNameAndPassword(userName, password);
    }

    @Override
    public synchronized Trainer getTrainerByUserNameAndPassword(String userName, String password) throws SQLException {
        return trainerList.getTrainerByUserNameAndPassword(userName, password);
    }

}
