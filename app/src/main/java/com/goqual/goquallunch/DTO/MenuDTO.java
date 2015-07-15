package com.goqual.goquallunch.DTO;

import java.sql.Date;

/**
 * Created by ladmusician on 15. 7. 13..
 */
public class MenuDTO {
    public int _id;
    public String label;
    public String number;
    public Date created;
    public Date updated;
    public Date eat;
    public int hit;
    public boolean isdeprecated;
   // public Date created;
  //  public Date updated;
  //  public Date eat;
  //  public int hit;
  //  public boolean isdeprecated;

    public MenuDTO(int id, String label) {
        this._id = id;
        this.label = label;

    }
    public MenuDTO(String label) {
        this.label = label;
    }

    public int getId() {
        return this._id;
    }
    public String getLabel() {
        return this.label;
    }

    public void setId(int id) {
        this._id = id;
    }
    public void setLabel(String label) {
        this.label = label;
    }
}
