package com.goqual.goquallunch.DTO;

/**
 * Created by ladmusician on 15. 7. 13..
 */
public class MenuDTO {
    private String menu;

    public MenuDTO(String menu) {
        this.menu = menu;
    }

    public String getMenu () { return this.menu; }
    public void setMenu (String menu) { this.menu = menu; }
}
