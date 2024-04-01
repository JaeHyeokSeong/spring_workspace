package hello.core;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("nameA");
        System.out.println("name = " + helloLombok.getName());
        System.out.println("helloLombok.toString() = " + helloLombok.toString());
    }
}
