package protobasics;

import com.playground.models.PersonOuterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Playground {
    private static final Logger log = LoggerFactory.getLogger(Playground.class);

    public static void main(String[] args) {
        PersonOuterClass.Person person = PersonOuterClass.Person.newBuilder()
                .setName("dylan")
                .setAge(34)
                .build();

        log.info("{}", person);
    }
}
