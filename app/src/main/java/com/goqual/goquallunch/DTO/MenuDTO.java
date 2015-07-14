package com.goqual.goquallunch.DTO;

/**
 * Created by ladmusician on 15. 7. 13..
 */
public class MenuDTO {
    public int Id;
    public String Label;
  //  public Date created;
  //  public Date updated;
  //  public Date eat;
  //  public int hit;
  //  public boolean isdeprecated;

    public MenuDTO(int id, String label) {
        this.Id = id;
        this.Label = label;
    }
    public MenuDTO(String label) {
        this.Label = label;
    }

    public int getId() {
        return this.Id;
    }
    public String getLabel() {
        return this.Label;
    }

    public void setId(int id) {
        this.Id = id;
    }
    public void setLabel(String label) {
        this.Label = label;
    }
}
