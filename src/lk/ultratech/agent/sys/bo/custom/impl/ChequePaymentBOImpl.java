package lk.ultratech.agent.sys.bo.custom.impl;

import lk.ultratech.agent.sys.bo.custom.ChequePaymentBO;
import lk.ultratech.agent.sys.dao.DAOFactory;
import lk.ultratech.agent.sys.dao.DAOType;
import lk.ultratech.agent.sys.dao.custom.ChequePaymentDAO;
import lk.ultratech.agent.sys.dto.ChequePaymentDTO;
import lk.ultratech.agent.sys.entity.ChequePayment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChequePaymentBOImpl implements ChequePaymentBO {

    private ChequePaymentDAO chequePaymentDAO;

    public ChequePaymentBOImpl(){
        this.chequePaymentDAO = DAOFactory.getInstance().getDAO(DAOType.CHEQUE_PAYMENT);
    }


    @Override
    public String getLastChequeId() throws SQLException, ClassNotFoundException {
        return chequePaymentDAO.getLastChequePaymentId();
    }

    @Override
    public boolean saveChequePayment(ChequePaymentDTO chequePaymentDTO) throws SQLException, ClassNotFoundException {
        return chequePaymentDAO.save(new ChequePayment(chequePaymentDTO.getChequePaymentId(),chequePaymentDTO.getShopPaymentId(),chequePaymentDTO.getCheuqeNo(),chequePaymentDTO.getdDate(),chequePaymentDTO.getAmount(),chequePaymentDTO.getReturned()));
    }

    @Override
    public List<ChequePaymentDTO> getAllCheques() throws SQLException, ClassNotFoundException {
        List<ChequePayment> all = chequePaymentDAO.getAll();
        List<ChequePaymentDTO> dtos = new ArrayList<>();

        for (ChequePayment pay : all){
            dtos.add(new ChequePaymentDTO(pay.getChequePaymentId(),pay.getShopPaymentId(),pay.getCheuqeNo(),pay.getdDate(),pay.getAmount(),pay.getReturned()));
        }
        return dtos;
    }

    @Override
    public boolean updateChequeStatus(String s,String cheque) throws SQLException, ClassNotFoundException {
        return chequePaymentDAO.updateStatus(s,cheque);
    }


}
