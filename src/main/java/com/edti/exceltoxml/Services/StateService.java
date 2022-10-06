package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Services.Interfaces.IStateService;
import org.springframework.stereotype.Service;

@Service
public class StateService implements IStateService {

    private int state;

    @Override
    public void setState(String isPicture) {
        if (isPicture == null || isPicture.equals("")) {
            System.out.println("off");
            this.state = 0;
        } else if (isPicture.equals("on")){
            System.out.println(isPicture);
            this.state = 1;
        } else {
            System.out.println("Ne huncutkodj!");
        }

    }

    @Override
    public int getState() {
        return this.state;
    }
}
