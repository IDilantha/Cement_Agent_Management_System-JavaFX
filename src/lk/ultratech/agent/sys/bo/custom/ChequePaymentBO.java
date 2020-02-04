package lk.ultratech.agent.sys.bo.custom;

import lk.ultratech.agent.sys.bo.SuperBO;
import lk.ultratech.agent.sys.dto.ChequePaymentDTO;

import java.sql.SQLException;
import java.util.List;

public interface ChequePaymentBO extends SuperBO {
    String getLastChequeId() throws SQLException, ClassNotFoundException;
    boolean saveChequePayment(ChequePaymentDTO chequePaymentDTO) throws SQLException, ClassNotFoundException;
    List<ChequePaymentDTO> getAllCheques() throws SQLException, ClassNotFoundException;
    boolean updateChequeStatus(String s,String cheque) throws SQLException, ClassNotFoundException;

}
