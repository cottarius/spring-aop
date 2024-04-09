package ru.cotarius.springcourse.springaop;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ru.cotarius.springcourse.springaop.annotations.ExecuteTime;
import ru.cotarius.springcourse.springaop.annotations.InjectRandomInt;
import ru.cotarius.springcourse.springaop.interfaces.IntegerToListInterface;

import java.util.ArrayList;
import java.util.List;

@Component
public class InjectIntegersToList implements IntegerToListInterface {

    @InjectRandomInt(min = 1)
    private Integer repeat;

    List<Integer> integers = new ArrayList<>();

    @Override
    @ExecuteTime(level = Level.WARN)
    public void addIntegers() {
        for(int i = 0; i < repeat; i++) {
            integers.add(i);
        }
    }

    @Override
    public Integer getSize(){
        return integers.size();
    }
}
