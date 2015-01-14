package com.medassets.browser;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class TimerDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Timer> data;

	public Timer getNewTransientTimer(int index) {
        Timer obj = new Timer();
        setMessage(obj, index);
        return obj;
    }

	public void setMessage(Timer obj, int index) {
        String message = "message_" + index;
        obj.setMessage(message);
    }

	public Timer getSpecificTimer(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Timer obj = data.get(index);
        Long id = obj.getId();
        return Timer.findTimer(id);
    }

	public Timer getRandomTimer() {
        init();
        Timer obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Timer.findTimer(id);
    }

	public boolean modifyTimer(Timer obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = Timer.findTimerEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Timer' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Timer>();
        for (int i = 0; i < 10; i++) {
            Timer obj = getNewTransientTimer(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
}
