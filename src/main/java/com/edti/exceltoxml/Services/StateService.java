package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Services.Interfaces.IStateService;
import org.springframework.stereotype.Service;

@Service
public class StateService implements IStateService {

    private String state;


    @Override
    public void setState(String isPicture) {
        if (isPicture == null || isPicture.equals("")) {
            System.out.println("off");
            this.state = "off";

        } else if (isPicture.equals("on")) {
            System.out.println(isPicture);
            this.state = "on";
        }
    }

    public String getState() {
        return state;
    }
}
