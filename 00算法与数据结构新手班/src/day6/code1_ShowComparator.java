package day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author:fish
 * @date: 2023/2/9-11:19
 * @content:
 * 数组排序   Arrays.sort(数组名,new IdComparator());
 * 可变数组排序  可变数组名.sort(new AgeComparator());
 */
public class code1_ShowComparator {
    public static class Student{
        public String name;
        public int id;
        public int age;

        public Student(String name,int id,int age){
            this.name=name;
            this.age=age;
            this.id=id;
        }
    }

    //自定义比较器 按id升序
    public static class IdComparator implements Comparator<Student>{
        //返回-1 第一个值在前面
        //返回1 第二个值在前面
        //返回0 无所谓
        @Override
        public int compare(Student o1, Student o2) {
            if (o1.id<o2.id) {
                return -1;
            }else if (o1.id>o2.id){
                return 1;
            }else {
                return 0;
            }
        }
    }
    //自定义比较器 按age降序
    public static class AgeComparator implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            if (o1.age<o2.age){
                return 1;
            }else if (o1.age>o2.age){
                return -1;
            }else{ return 0;}
        }
    }
    //打印学生数组
    public static void printList(Student[] student){
        for (Student s:student){
            System.out.println("name："+s.name+" ,id："+s.id+" ,age："+s.age);
        }
    }

    public static void main(String[] args) {
        Student s1=new Student("haha",1,23);
        Student s2=new Student("lili",4,34);
        Student s3=new Student("yuyu",3,19);
        Student s4=new Student("jojo",5,46);
        Student s5=new Student("gogo",2,36);

        //数组
        Student[] students={s1,s2,s3,s4,s5};
        printList(students);
        System.out.println("按Id排序后");

        System.out.println("看这里————————————");
//        Arrays.sort(students,new IdComparator());
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.id-o2.id;
            }
        });
        printList(students);
        System.out.println("===========");

        //可变数组
        ArrayList<Student> arrayList=new ArrayList<>();
        arrayList.add(s1);
        arrayList.add(s2);
        arrayList.add(s3);
        arrayList.add(s4);
        arrayList.add(s5);
        for (Student s:arrayList){
            System.out.println("name："+s.name+" ,id："+s.id+" ,age："+s.age);
        }
        System.out.println("按Age排序后");
        arrayList.sort(new AgeComparator());
        for (Student s:arrayList){
            System.out.println("name："+s.name+" ,id："+s.id+" ,age："+s.age);
        }
        System.out.println("+++++++++++");

        //优先队列
        PriorityQueue<Student> heap=new PriorityQueue<>(new AgeComparator());
        heap.add(s1);
        heap.add(s2);
        heap.add(s3);
        heap.add(s4);
        heap.add(s5);

        while (!heap.isEmpty()){
            Student s=heap.poll();
            System.out.println("name："+s.name+" ,id："+s.id+" ,age："+s.age);
        }
        System.out.println("+++++++++++++++");

        //凡是跟有序有关的结构 都可自定义比较器 TreeSet，TreeMap等


    }
}
