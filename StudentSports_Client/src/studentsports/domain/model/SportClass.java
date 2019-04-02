package studentsports.domain.model;

import java.io.Serializable;

public class SportClass implements Serializable {
    private MemberList participants;
    private String name;
    private TrainerList trainers;
    private int capacity;
    private Date date;

    public SportClass(String name, int capacity, Date date) {
        participants = new MemberList();
        trainers = new TrainerList();
        this.name = name;
        this.capacity = capacity;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void addParticipant(Member member) throws Exception {
        if (participants.size() < capacity) {
            participants.add(member);
        }
        else {
            throw new Exception();
        }
    }

    public void setParticipants(MemberList participants) {
        this.participants = participants;
    }

    public void setTrainers(TrainerList trainers) {
        this.trainers = trainers;
    }

    public void addTrainerToSportClass(Trainer trainer) {
        trainers.addTrainer(trainer);
    }

    public void deleteParticipant(Member member) {
        participants.delete(member);
    }

    public void deleteTrainerFromSportClass(Trainer trainer) {
        trainers.delete(trainer);
    }

    public MemberList getAllParticipants() {
        return participants;
    }

    public TrainerList getTrainers() {
        return trainers;
    }


    public String getParticipants() {
        String a = "";
        a += getAllParticipants().size() + "/";
        a += capacity;

        return a;
    }

    public boolean hasAParticipant(Member member) {
        return participants.getMemberByUserNameAndPassword(member.getUserName(),member.getPassword()) != null;
    }


}
