package studentsports.domain.model;

import java.io.Serializable;
import java.util.ArrayList;

public class MemberList implements Serializable {
    private ArrayList<Member> memberList;

    public MemberList() {
        memberList = new ArrayList<>();
    }

    public void add(Member member) {
        memberList.add(member);
    }

    public Member getMember(Member member) {
        for (int i=0;i<0;i++)
        {
            if (memberList.get(i).getFirstName()==member.getFirstName())
            {
                return member;
            }
        }
        return null;
    }

    public Member getMemberByStudentNumber(String studentNumber) {
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getStudentNumber().equals(studentNumber)) {
                return memberList.get(i);
            }
        }
        return null;
    }

    public Member getMemberByFirstName(String firstName) {
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getFirstName().equalsIgnoreCase(firstName)) {
                if (memberList.get(i).getFirstName().equalsIgnoreCase(firstName)) {
                    return memberList.get(i);
                }
            }
        }
            return null;
        }

        public void delete(Member member){

        memberList.remove(member);


        }

        public String toString () {
            String s = "";
            for (int i = 0; i < memberList.size(); i++) {
                if (i == memberList.size() - 1) {
                    s += memberList.get(i);
                } else {
                    s += memberList.get(i) + "\n";
                }
            }
            return s;
        }

        public int size () {
            return memberList.size();
        }

        public Member getMemberByNumber ( int number){
            return memberList.get(number);
        }
        public MemberList getAllUnpaidMembers()
        {
            MemberList memberLists = new MemberList();
            for (int i = 0;i<memberList.size();i++)
            {
                if (memberList.get(i).getPaymentDate()==null)
                {
                    memberLists.add(memberList.get(i));
                }
            }
            return memberLists;
        }
        public Member getMemberByUserNameAndPassword(String userName,String password)
        {
            Member member = null;
            for (int i=0;i<memberList.size();i++)
            {
                if (memberList.get(i).getUserName().equals(userName))
                {
                    if (memberList.get(i).getPassword().equals(password))
                    {
                        member= memberList.get(i);
                    }
                }
            }
            return member;
        }
    }
