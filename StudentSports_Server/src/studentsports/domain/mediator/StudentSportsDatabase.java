
package studentsports.domain.mediator;

import studentsports.domain.model.*;
import studentsports.domain.model.Date;

import java.sql.*;

public class StudentSportsDatabase implements StudentSportsPersistence {

    private Connection connection;

    public StudentSportsDatabase(String user, String password, String ip, String database, String port) throws SQLException
    {
        String url = "jdbc:postgresql://"+ip+":"+port+"/"+database;
        connection = DriverManager.getConnection(url,user,password);

    }

    public MemberList loadMembers() throws SQLException {


        String sql = "SELECT * FROM studentsports.members;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        MemberList memberList = new MemberList();
        while (resultSet.next())
        {
            Member member = convertRowToMember(resultSet);
            if (convertRowToDate(resultSet,"payment_date")!=null)
            {
                member.setPaymentDate(convertRowToDate(resultSet,"payment_date"));
                member.setPaidUntil(convertRowToDate(resultSet,"paid_until"));
            }
            memberList.add(member);
        }
        return memberList;
    }

    public Member convertRowToMember(ResultSet resultSet) throws SQLException {
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String studentNumber = resultSet.getString("studentno");
        String userName = resultSet.getString("user_name");
        String password = resultSet.getString("password");
        if (studentNumber == "null")
        {
            studentNumber = null;
        }
        Date payedUntill = convertRowToDate(resultSet,"paid_until");
        Date paymentdate = convertRowToDate(resultSet,"payment_date");
        Member member = new Member(firstName,lastName,userName,password,studentNumber);

        if (paymentdate == null)
        {

        }
        else {
            member.setPaymentDate(paymentdate);
            member.setPaidUntil(payedUntill);
        }

        return member;
    }

    @Override
    public void saveMember(Member member) throws SQLException {

        PreparedStatement preparedStatement = null;
        java.sql.Date date1 = null;
        java.sql.Date date2 = null;


            preparedStatement = connection.prepareStatement("insert into studentsports.members"
                    + " (first_name, last_name,user_name,password,payment_date,studentno,paid_until)"
                    + " values (?, ?, ?, ?, ?, ?,?);");
            if (member.getPaymentDate()==null)
            {
                date1 = null;
            }
            else if (member.getPaidUntil()==null)
            {
                date2 =null;
            }
            else
            {
                date1 =  new java.sql.Date(member.getPaymentDate().getYear()-1900,member.getPaymentDate().getMonth()-1,member.getPaymentDate().getDay());
                date2 =  new java.sql.Date(member.getPaidUntil().getYear()-1900,member.getPaidUntil().getMonth()-1,member.getPaidUntil().getDay());


            }
            if (member.getStudentNumber()!=null)
            {
                int integer = Integer.parseInt(member.getStudentNumber());
                preparedStatement.setInt(6,integer);
            }
            else
            {
                preparedStatement.setNull(6, Types.INTEGER);
            }



            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getLastName());
            preparedStatement.setString(3,member.getUserName());
            preparedStatement.setString(4,member.getPassword());
            preparedStatement.setDate(5,date1);
            preparedStatement.setDate(7,date2);




            preparedStatement.executeUpdate();

    }

    @Override
    public void removeMember(Member member) throws SQLException {
        PreparedStatement preparedStatement =connection.prepareStatement("DELETE from studentsports.members WHERE user_name = ? and password = ?");
        preparedStatement.setString(1,member.getUserName());
        preparedStatement.setString(2,member.getPassword());

        preparedStatement.executeUpdate();
    }

    @Override
    public void clearMembers() throws SQLException {
        PreparedStatement preparedStatement =connection.prepareStatement(
                "DELETE FROM studentsports.members;");
        preparedStatement.executeUpdate();
    }

    @Override
    public TrainerList loadTrainers() throws SQLException {
        String sql = "SELECT * FROM studentsports.trainers;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        TrainerList trainerList = new TrainerList();
        while (resultSet.next())
        {

            Trainer trainer = convertRowToTrainer(resultSet);
            trainerList.addTrainer(trainer);
        }
        return trainerList;
    }

    public Trainer convertRowToTrainer(ResultSet resultSet) throws SQLException {
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String userName = resultSet.getString("user_name");
        String password = resultSet.getString("password");
        Trainer trainer = new Trainer(firstName,lastName,userName,password);


        return trainer;
    }

    @Override
    public void saveTrainer(Trainer trainer) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("insert into studentsports.trainers"
                    + " (first_name, last_name,user_name,password)"
                    + " values (?, ?, ?, ?);");



        preparedStatement.setString(1, trainer.getFirstName());
        preparedStatement.setString(2, trainer.getLastName());
        preparedStatement.setString(3,trainer.getUserName());
        preparedStatement.setString(4,trainer.getPassword());



        preparedStatement.executeUpdate();


    }

    @Override
    public void removeTrainer(Trainer trainer) throws SQLException {
        PreparedStatement preparedStatement =connection.prepareStatement("DELETE from studentsports.trainers WHERE user_name = ? and password = ?");
        preparedStatement.setString(1,trainer.getUserName());
        preparedStatement.setString(2,trainer.getPassword());

        preparedStatement.executeUpdate();

    }

    @Override
    public void clearTrainers() throws SQLException {
        PreparedStatement preparedStatement =connection.prepareStatement(
                "DELETE FROM studentsports.trainers;");
        preparedStatement.executeUpdate();
    }

    @Override
    public SportClassList loadSporsClasses() throws SQLException {

        String sql = "SELECT * FROM studentsports.sportclass;";

        PreparedStatement myStm = connection.prepareStatement(sql);

        ResultSet rs = myStm.executeQuery();
        SportClassList sportClassList = new SportClassList();
        while (rs.next())
        {
            SportClass sportClass = convertRowToSportClass(rs);
            sportClassList.addSportClass(sportClass);
            sportClass.setParticipants(loadParticipants(sportClass));
            sportClass.setTrainers(loadTrainersForClass(sportClass));

        }
        return sportClassList;
    }

    public SportClass convertRowToSportClass(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        int capacity = resultSet.getInt("capacity");
        Date date = convertRowToDate(resultSet,"date");
        SportClass sportClass = new SportClass(name,capacity,date);
        return sportClass;
    }

    @Override
    public void saveSportsClass(SportClass sportClass) throws SQLException {
        java.sql.Date date1;

        PreparedStatement preparedStatement = connection.prepareStatement("insert into studentsports.sportclass"
                    + " (name, capacity,date)"
                    + " values (?, ?, ?);");


                date1 =  new java.sql.Date(sportClass.getDate().getYear()-1900,sportClass.getDate().getMonth()-1,sportClass.getDate().getDay());




        preparedStatement.setString(1, sportClass.getName());
        preparedStatement.setInt(2, sportClass.getCapacity());
        preparedStatement.setDate(3,date1);



        preparedStatement.executeUpdate();

    }

    @Override
    public void removeSportsClass(SportClass sportClass) throws SQLException {
        PreparedStatement preparedStatement =connection.prepareStatement("DELETE from studentsports.sportclass WHERE name = ? and date = ?");
        java.sql.Date date1;
        date1 =  new java.sql.Date(sportClass.getDate().getYear()-1900,sportClass.getDate().getMonth()-1,sportClass.getDate().getDay());
        preparedStatement.setString(1,sportClass.getName());
        preparedStatement.setDate(2,date1);


        preparedStatement.executeUpdate();
    }

    @Override
    public void clearSportClass() throws SQLException {
        PreparedStatement preparedStatement =connection.prepareStatement(
                "DELETE FROM studentsports.sportclass;");
        preparedStatement.executeUpdate();
    }

    public Date convertRowToDate(ResultSet rs,String colum) throws SQLException
    {
        String date = rs.getString(colum);
        Date  newdate;
        if (date==null)
        {
            newdate = null;
        }
        else {
            StringBuilder stringYear = new StringBuilder(date);
            stringYear.replace(4,10,"");
            int intYear = Integer.parseInt(stringYear.toString());
            StringBuilder stringDay = new StringBuilder(date);
            stringDay.replace(0,8,"");
            int intDay = Integer.parseInt(stringDay.toString());
            StringBuilder stringMonth = new StringBuilder(date);
            stringMonth.replace(0,5,"");
            stringMonth.replace(2,6,"");
            int intMonth = Integer.parseInt(stringMonth.toString());
            newdate = new Date(intDay,intMonth,intYear);
        }
        return newdate;
    }
    public void addParticipant(SportClass sportClass,Member member) throws SQLException {
        java.sql.Date date1;
        date1 =  new java.sql.Date(sportClass.getDate().getYear()-1900,sportClass.getDate().getMonth()-1,sportClass.getDate().getDay());
        PreparedStatement preparedStatement =connection.prepareStatement(
                "INSERT INTO studentsports.attends(idmembers,first_name,idclass,classname,user_name,date)\n" +
                        "SELECT t1.memberid, t1.first_name, t2.classid, t2.name, t1.user_name, t2.date FROM studentsports.members t1 , studentsports.sportclass t2\n" +
                        "where first_name=? and name=? and date=? and user_name=?;");
        preparedStatement.setString(1,member.getFirstName());
        preparedStatement.setString(2,sportClass.getName());
        preparedStatement.setDate(3,date1);
        preparedStatement.setString(4,member.getUserName());

        preparedStatement.executeUpdate();
    }

    public MemberList loadParticipants(SportClass sportClass) throws SQLException {

        java.sql.Date date1;
        date1 =  new java.sql.Date(sportClass.getDate().getYear()-1900,sportClass.getDate().getMonth()-1,sportClass.getDate().getDay());
        PreparedStatement preparedStatement = connection.prepareStatement("select m.*\n" +
                "from studentsports.members m\n" +
                "where m.memberid in (select a.idmembers from studentsports.attends a where a.classname = ? and a.date = ?);");
        preparedStatement.setString(1,sportClass.getName());
        preparedStatement.setDate(2,date1);

        ResultSet resultSet = preparedStatement.executeQuery();
        MemberList memberList = new MemberList();
        while (resultSet.next())
        {
            Member member = convertRowToMember(resultSet);
            if (!(convertRowToDate(resultSet,"payment_date")==null))
            {
                member.setPaymentDate(convertRowToDate(resultSet,"payment_date"));
            }
            memberList.add(member);
        }
        return memberList;
    }
    public TrainerList loadTrainersForClass(SportClass sportClass) throws SQLException {

        java.sql.Date date1;
        date1 =  new java.sql.Date(sportClass.getDate().getYear()-1900,sportClass.getDate().getMonth()-1,sportClass.getDate().getDay());
        PreparedStatement preparedStatement = connection.prepareStatement("select t.*\n" +
                "from studentsports.trainers t\n" +
                "where t.trainerid in (select a.trainerid from studentsports.trains a where a.sportsclassname = ? and a.date = ?);");
        preparedStatement.setString(1,sportClass.getName());
        preparedStatement.setDate(2,date1);

        ResultSet resultSet = preparedStatement.executeQuery();
        TrainerList trainerList = new TrainerList();
        while (resultSet.next())
        {
            Trainer trainer = convertRowToTrainer(resultSet);
            trainerList.addTrainer(trainer);
        }
        return trainerList;
    }
    public void addTrainerToClass(SportClass sportClass,Trainer trainer) throws SQLException {
        PreparedStatement preparedStatement =connection.prepareStatement("INSERT INTO studentsports.trains(trainerid,trainersname,sportclassid,sportsclassname,date,user_name)\n" +
                "SELECT t1.trainerid, t1.first_name, t2.classid, t2.name, t2.date, t1.user_name FROM studentsports.trainers t1 ,studentsports.sportclass t2 where user_name=? and password=? and name=? and date = ?;\n" +
                "\n");
        java.sql.Date date1;
        date1 =  new java.sql.Date(sportClass.getDate().getYear()-1900,sportClass.getDate().getMonth()-1,sportClass.getDate().getDay());
        preparedStatement.setString(1,trainer.getUserName());
        preparedStatement.setString(2,trainer.getPassword());
        preparedStatement.setString(3,sportClass.getName());
        preparedStatement.setDate(4,date1);
        preparedStatement.executeUpdate();

    }
    public void removeTrainerFromClass(Trainer trainer,SportClass sportClass) throws SQLException{
        java.sql.Date date1;
        date1 =  new java.sql.Date(sportClass.getDate().getYear()-1900,sportClass.getDate().getMonth()-1,sportClass.getDate().getDay());
        PreparedStatement preparedStatement = connection.prepareStatement("delete from studentsports.trains where trainersname = ? AND sportsclassname = ? and date = ? and user_name = ?;\n" +
                "\n");
        preparedStatement.setString(1,trainer.getFirstName());
        preparedStatement.setString(2,sportClass.getName());
        preparedStatement.setDate(3,date1);
        preparedStatement.setString(4,trainer.getUserName());
        preparedStatement.executeUpdate();

    }
    public void removeMemberFromClass(Member member,SportClass sportClass) throws SQLException{
        java.sql.Date date1;
        date1 =  new java.sql.Date(sportClass.getDate().getYear()-1900,sportClass.getDate().getMonth()-1,sportClass.getDate().getDay());
        PreparedStatement preparedStatement = connection.prepareStatement("delete from studentsports.attends where first_name = ? AND classname = ? and user_name = ?  and date =?;\n" +
                "\n");
        preparedStatement.setString(1,member.getFirstName());
        preparedStatement.setString(2,sportClass.getName());
        preparedStatement.setString(3,member.getUserName());
        preparedStatement.setDate(4,date1);
        preparedStatement.executeUpdate();

    }

}
