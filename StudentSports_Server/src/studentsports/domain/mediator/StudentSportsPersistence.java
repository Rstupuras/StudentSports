package studentsports.domain.mediator;

import studentsports.domain.model.*;

import java.sql.SQLException;

public interface StudentSportsPersistence {
    MemberList loadMembers() throws SQLException;

    void saveMember(Member member) throws SQLException;

    void removeMember(Member member) throws SQLException;

    void clearMembers() throws SQLException;

    TrainerList loadTrainers() throws SQLException;

    void saveTrainer(Trainer trainer) throws SQLException;

    void removeTrainer(Trainer trainer) throws SQLException;

    void clearTrainers() throws SQLException;

    SportClassList loadSporsClasses() throws SQLException;

    void saveSportsClass(SportClass sportClass) throws SQLException;

    void removeSportsClass(SportClass sportClass) throws SQLException;

    void clearSportClass() throws SQLException;

    void addTrainerToClass(SportClass sportClass, Trainer trainer) throws SQLException;

    void removeTrainerFromClass(Trainer trainer, SportClass sportClass) throws SQLException;

    void addParticipant(SportClass sportClass, Member member) throws SQLException;
    void removeMemberFromClass(Member member,SportClass sportClass) throws SQLException;
}