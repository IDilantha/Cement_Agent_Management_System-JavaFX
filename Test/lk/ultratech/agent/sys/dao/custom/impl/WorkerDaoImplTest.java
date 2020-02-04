package lk.ultratech.agent.sys.dao.custom.impl;


import lk.ultratech.agent.sys.entity.Worker;

import java.sql.SQLException;

class WorkerDaoImplTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new WorkerDaoImplTest().save();
    }
    void save() throws SQLException, ClassNotFoundException {
        boolean save = new WorkerDAOImpl().save(new Worker("W002","Amal",07111111,"Driver"));
        System.out.println(save);
    }
}