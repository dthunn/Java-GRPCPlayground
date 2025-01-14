package protobasics;

import com.google.protobuf.Int32Value;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Timestamp;
import com.playground.models.common.Address;
import com.playground.models.common.BodyStyle;
import com.playground.models.common.Car;
import com.playground.models.protobasics04.*;
import com.playground.models.protobasics05.v1.Television;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;

public class Playground {
    private static final Logger log = LoggerFactory.getLogger(Playground.class);
    private static final Path PATH = Path.of("person.out");

    public static void main(String[] args) throws InvalidProtocolBufferException {

        var tv = Television.newBuilder()
                .setBrand("samsung")
                .setYear(2019)
                .build();

        V1Parser.parse(tv.toByteArray());
        V2Parser.parse(tv.toByteArray());
        V3Parser.parse(tv.toByteArray());

    }

//    public static void main(String[] args) {

//        var sample = Sample.newBuilder()
//                .setAge(Int32Value.of(12))
//                .setLoginTime(Timestamp.newBuilder().setSeconds(Instant.now().getEpochSecond()).build())
//                .build();
//
//        log.info("{}", Instant.ofEpochSecond(sample.getLoginTime().getSeconds()));
//
//
//    }

//    public static void main(String[] args) {
//
//        var address = Address.newBuilder().setCity("atlanta").build();
//        var car = Car.newBuilder().setBodyStyle(BodyStyle.COUPE).build();
//        var person = Person.newBuilder()
//                .setName("sam")
//                .setAge(12)
//                .setCar(car)
//                .setAddress(address)
//                .build();
//
//        log.info("{}", person);
//    }

//    public static void main(String[] args) {
//
//        var email = Email.newBuilder().setAddress("sam@gmail.com").setPassword("admin").build();
//        var phone = Phone.newBuilder().setNumber(123456789).setCode(123).build();
//
//        login(Credentials.newBuilder().setEmail(email).build());
//        login(Credentials.newBuilder().setPhone(phone).build());
//
//        // last one will be chosen
//        login(Credentials.newBuilder().setEmail(email).setPhone(phone).build());
//
//    }
//
//    private static void login(Credentials credentials){
//        switch (credentials.getLoginTypeCase()){
//            case EMAIL -> log.info("email -> {}", credentials.getEmail());
//            case PHONE -> log.info("phone -> {}", credentials.getPhone());
//        }
//    }

//    public static void main(String[] args) {
//
//        var car1 = Car.newBuilder()
//                .setMake("honda")
//                .setModel("civic")
//                .setYear(2000)
//                .setBodyStyle(BodyStyle.COUPE)
//                .build();
//        var car2 = Car.newBuilder()
//                .setMake("honda")
//                .setModel("accord")
//                .setYear(2002)
//                .setBodyStyle(BodyStyle.SEDAN)
//                .build();
//
//        var dealer = Dealer.newBuilder()
//                .putInventory(car1.getYear(), car1)
//                .putInventory(car2.getYear(), car2)
//                .build();
//
//        log.info("{}", dealer);
//
//        log.info("2002 ? : {}", dealer.containsInventory(2002));
//        log.info("2003 ? : {}", dealer.containsInventory(2003));
//
//        log.info("2002 model: {}", dealer.getInventoryOrThrow(2002).getBodyStyle());
//
//    }

//    public static void main(String[] args) {
//
//        // create books
//        var book1 = Book.newBuilder()
//                .setTitle("harry potter - part 1")
//                .setAuthor("j k rowling")
//                .setPublicationYear(1997)
//                .build();
//        var book2 = book1.toBuilder().setTitle("harry potter - part 2").setPublicationYear(1998).build();
//        var book3 = book1.toBuilder().setTitle("harry potter - part 3").setPublicationYear(1999).build();
//
//        var library = Library.newBuilder()
//                .setName("fantasy library")
////                .addBooks(book1)
////                .addBooks(book2)
////                .addBooks(book3)
//                .addAllBooks(List.of(book1, book2, book3))
//                .build();
//
//        log.info("{}", library);
//
//
//    }

//    public static void main(String[] args) {
//        // create student
//        var address = Address.newBuilder()
//                .setStreet("123 main st")
//                .setCity("atlanta")
//                .setState("GA")
//                .build();
//        var student = Student.newBuilder()
//                .setName("sam")
//                .setAddress(address)
//                .build();
//        // create school
//        var school = School.newBuilder()
//                .setId(1)
//                .setName("high school")
//                .setAddress(address.toBuilder().setStreet("234 main st").build())
//                .build();
//
//        log.info("school: {}", school);
//        log.info("student: {}", student);
//    }

//    public static void main(String[] args) throws IOException {
//        Person person = Person.newBuilder()
//                .setLastName("thunn")
//                .setAge(34)
//                .setEmail("test@test.com")
//                .setEmployed(true)
//                .setSalary(100000)
//                .setBankAccountNumber(9384936565L)
//                .setBalance(-1000)
//                .build();
//
//        log.info("{}", person);
//
//        serialize(person);
//        log.info("{}", deserialize());
//    }

//    public static void serialize(Person person) throws IOException {
//        try(var stream = Files.newOutputStream(PATH)) {
//            person.writeTo(stream);
//        }
//    }
//
//    public static Person deserialize() throws IOException {
//        try(var stream = Files.newInputStream(PATH)){
//            return Person.parseFrom(stream);
//        }
//    }


//    private static Person createPerson() {
//        return Person.newBuilder()
//                .setName("dylan")
//                .setAge(34)
//                .build();
//    }

    //    public static void main(String[] args) {
//        PersonOuterClass.Person person = PersonOuterClass.Person.newBuilder()
//                .setName("dylan")
//                .setAge(34)
//                .build();
//
//        log.info("{}", person);
//    }
}
