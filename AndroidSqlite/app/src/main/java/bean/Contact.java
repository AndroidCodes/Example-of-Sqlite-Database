package bean;

import java.io.Serializable;

/**
 * Created by Peacock on 15/03/17.
 */

public class Contact implements Serializable {

    //private variables
    int _id;
    String _Date;
    String _ChallanNo;
    String _Mapping;
    String _PartyName, _Rate, _Total, _TruckNo;


    // Empty constructor
    public Contact() {

    }

    // constructor
    public Contact(int id, String Date, String ChallanNo, String Mapping, String PartyName, String Rate, String Total, String TruckNo) {
        this._id = id;
        this._Date = Date;
        this._ChallanNo = ChallanNo;
        this._Mapping = Mapping;
        this._PartyName = PartyName;
        this._Rate = Rate;
        this._Total = Total;
        this._TruckNo = TruckNo;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_Date() {
        return _Date;
    }

    public void set_Date(String _Date) {
        this._Date = _Date;
    }

    public String get_ChallanNo() {
        return _ChallanNo;
    }

    public void set_ChallanNo(String _ChallanNo) {
        this._ChallanNo = _ChallanNo;
    }

    public String get_Mapping() {
        return _Mapping;
    }

    public void set_Mapping(String _Mapping) {
        this._Mapping = _Mapping;
    }

    public String get_PartyName() {
        return _PartyName;
    }

    public void set_PartyName(String _PartyName) {
        this._PartyName = _PartyName;
    }

    public String get_Rate() {
        return _Rate;
    }

    public void set_Rate(String _Rate) {
        this._Rate = _Rate;
    }

    public String get_Total() {
        return _Total;
    }

    public void set_Total(String _Total) {
        this._Total = _Total;
    }

    public String get_TruckNo() {
        return _TruckNo;
    }

    public void set_TruckNo(String _TruckNo) {
        this._TruckNo = _TruckNo;
    }
}
