package com.edti.exceltoxml.Services;

import com.edti.exceltoxml.Services.Interfaces.IStateService;
import org.springframework.stereotype.Service;

@Service
public class StateService implements IStateService {

    private int state;

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public int getState() {
        return this.state;
    }
}
