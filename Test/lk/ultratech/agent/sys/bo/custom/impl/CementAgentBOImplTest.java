package lk.ultratech.agent.sys.bo.custom.impl;


import lk.ultratech.agent.sys.dto.CementAgentDTO2;

import java.sql.SQLException;

class CementAgentBOImplTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new CementAgentBOImplTest().getCementPrice();

    }

    void getCementPrice() throws SQLException, ClassNotFoundException {
        new CementAgentBOImpl().getCementPrice(new CementAgentDTO2("C001","A001"));
    }
}