package transformer.demo;


import transformer.constants.ConstantsConversion;
import transformer.date.DateConversion;
import transformer.date.DateFormat;

import java.util.Date;

/**
 * @Author: youpengda@qq.com
 * @Description:
 */
public class ValueObj {
    @DateConversion
    private Date dateA;
    private String dateADesc;
    @DateConversion(dateFormat = DateFormat.DATE_FORMAT, fieldName = "KKK")
    private Date dateB;
    private String dateBKKK;
    @DateConversion(dateFormat = DateFormat.DATETIME_FORMAT)
    private Date dateC;
    private String dateCDesc;
    @ConstantsConversion(enumClass = BaseConstants.State.class)
    private String state;
    private String stateDesc;
    @ConstantsConversion(enumClass = BaseConstants.State.class, fieldName = "KKK")
    private String stateB;
    private String stateBKKK;

    public String getStateBKKK() {
        return stateBKKK;
    }

    public void setStateBKKK(String stateBKKK) {
        this.stateBKKK = stateBKKK;
    }

    public String getStateB() {
        return stateB;
    }

    public void setStateB(String stateB) {
        this.stateB = stateB;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    public Date getDateA() {
        return dateA;
    }

    public void setDateA(Date dateA) {
        this.dateA = dateA;
    }

    public String getDateADesc() {
        return dateADesc;
    }

    public void setDateADesc(String dateADesc) {
        this.dateADesc = dateADesc;
    }

    public Date getDateB() {
        return dateB;
    }

    public void setDateB(Date dateB) {
        this.dateB = dateB;
    }

    public String getDateBKKK() {
        return dateBKKK;
    }

    public void setDateBKKK(String dateBKKK) {
        this.dateBKKK = dateBKKK;
    }

    public Date getDateC() {
        return dateC;
    }

    public void setDateC(Date dateC) {
        this.dateC = dateC;
    }

    public String getDateCDesc() {
        return dateCDesc;
    }

    public void setDateCDesc(String dateCDesc) {
        this.dateCDesc = dateCDesc;
    }


}
